var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const session = require("express-session");

const PREFIX = `/api/v1`;
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
const authRouter = require("./routes/auth");

var app = express();

app.use(
  session({
    secret: process.env.APP_SECRET,
    resave: true,
    saveUninitialized: true,
    cookie: { maxAge: 3600000 },
  })
);

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(`${PREFIX}/`, authRouter);

module.exports = app;
