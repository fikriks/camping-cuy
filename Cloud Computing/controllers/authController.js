const User = require("../models").User;
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const config = require('../config/config')

module.exports = {
  login: async (req, res) => {
    try {
      const { email, password } = req.body;

      const user = await User.findOne({
        where: {
          email: email,
        },
      });

      if (!user) {
        return res.status(404).send({ message: "User Not found." });
      }

      const passwordIsValid = bcrypt.compareSync(password, user.password);
      if (!passwordIsValid) {
        return res.status(401).send({
          message: "Invalid Password!",
        });
      }

      const token = jwt.sign({ id: user.id }, config.secret, {
        expiresIn: 86400, // 24 hours
      });

      req.session.token = token;
      return res.status(200).send({
        id: user.id,
        email: user.email,
      });
    } catch (error) {
      return res.status(500).send({ message: error.message });
    }
  },

  register: async (req, res) => {
    try {
      const { email, password } = req.body;
      
      await User.create({
       email: email,
       password: bcrypt.hashSync(password),
     });

      return res.send({ message: "User registered successfully!"});
    } catch (error) {
      return res.status(500).send({ message: error.message });
    }
  },

  logout: (req, res) => {
    try {
      req.session = null;
      return res.status(200).send({
        message: "You've been signed out!",
      });
    } catch (err) {
      this.next(err);
    }
  },
};
