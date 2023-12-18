package com.my.campingcuy.ui.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.my.campingcuy.R

class ConfirmPassword : AppCompatEditText, View.OnFocusChangeListener {

    var isPasswordValid = false

    init {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        // Set background with border
        background = ContextCompat.getDrawable(context, R.drawable.border)
        transformationMethod = PasswordTransformationMethod.getInstance()

        // Set onFocusChangeListener to validate password
        onFocusChangeListener = this

        // Set text change listener
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            validatePassword()
        }
    }

    private fun validatePassword() {
        val password = text.toString().trim()
        val confirmPassword =
            (parent as ViewGroup).findViewById<PasswordEditText>(R.id.edt_password).text.toString()
                .trim()

        isPasswordValid = password.length >= 8 && password == confirmPassword
        error = if (!isPasswordValid) {
            resources.getString(R.string.passwordNotMatch)
        } else {
            null
        }
    }

}