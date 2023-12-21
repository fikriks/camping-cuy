package com.my.campingcuy

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.text.TextWatcher
import android.widget.Toast
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.my.campingcuy.ui.custom.EmailEditText
import com.my.campingcuy.ui.custom.PasswordEditText
import com.my.campingcuy.ui.custom.ConfirmPasswordEditText
import com.my.campingcuy.ui.custom.NameEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var emailEditText: EmailEditText
    private lateinit var fullNameEditText: NameEditText
    private lateinit var passwordEditText: PasswordEditText
    private lateinit var confirmPasswordEditText: ConfirmPasswordEditText
    private lateinit var registerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        emailEditText = findViewById(R.id.edt_email)
        fullNameEditText = findViewById(R.id.edt_name)
        passwordEditText = findViewById(R.id.edt_password)
        registerButton = findViewById(R.id.btn_register)
        confirmPasswordEditText = findViewById(R.id.edt_confirm_password)

        emailEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)
        fullNameEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)
        confirmPasswordEditText.addTextChangedListener(textWatcher)

        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Create register request body
            val registerRequest = RegisterRequest(fullName, email, password)

            // Make the registration API call
            val call = RetrofitInstance.apiService.registerUser(registerRequest)
            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        // Handle successful registration response
                        val registerResponse = response.body()
                        // Process registerResponse as needed
                        showToast(registerResponse?.message ?: "User registered successfully!")
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        // Handle error response
                        Log.e("RegisterActivity", "Registration failed with status code: ${response.code()}")
                        showToast("Registration failed. Please check your input.")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    // Handle failure
                    Log.e("RegisterActivity", "Registration failed", t)
                    showToast("Registration failed. Please try again.")
                }
            })
        }
    }

    fun goToMain(view: android.view.View) {
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            val name = fullNameEditText.text.toString().trim()

            // Enable the button only if both username and password are not empty
            registerButton.isEnabled = email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && confirmPassword.isNotEmpty()
        }
    }

    data class RegisterRequest(
        val full_name: String,
        val email: String,
        val password: String
    )
}