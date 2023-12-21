package com.my.campingcuy

import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import android.util.Log
import android.text.method.PasswordTransformationMethod
import android.text.Editable
import android.widget.CheckBox
import com.my.campingcuy.ui.custom.PasswordEditText
import com.my.campingcuy.ui.custom.EmailEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EmailEditText
    private lateinit var passwordEditText: PasswordEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var showPasswordCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.edt_email)
        passwordEditText = findViewById(R.id.edt_password)
        loginButton = findViewById(R.id.btn_login)
        showPasswordCheckBox = findViewById(R.id.checkboxPassword)

        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Toggle the password visibility based on the checked state
            togglePasswordVisibility(isChecked)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Create login request body
            val loginRequest = LoginRequest(email, password)

            // Make the login API call
            val call = RetrofitInstance.apiService.loginUser(loginRequest)
            call.enqueue(object : Callback<LoginResponseModel> {
                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {
                    if (response.isSuccessful) {
                        // Handle successful response
                        val responseData = response.body()
                        // Process responseData as needed

                        // Redirect to MainActivity on successful login
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish() // Finish LoginActivity
                    } else {
                        // Handle error response
                        Log.e("LoginActivity", "Login failed with status code: ${response.code()}")
                        showToast("Login failed. Please check your credentials.")
                    }
                }

                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    // Handle failure
                    Log.e("LoginActivity", "Login failed", t)
                    showToast("Login failed. Please try again.")
                }
            })
        }
        // Set up text change listeners
        emailEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)
    }

    private fun togglePasswordVisibility(isChecked: Boolean) {
        // If checked, set inputType to text to make the password visible
        // Otherwise, set it to password to hide the password
        passwordEditText.transformationMethod =
            if (isChecked) null else PasswordTransformationMethod.getInstance()
    }

    data class LoginRequest(
        val email: String,
        val password: String
    )


    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Enable the button only if both username and password are not empty
            loginButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    fun goToMain(view: android.view.View) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    fun goRegister(view: android.view.View) {
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, RegisterActivity::class.java)
        startActivity(mainIntent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}