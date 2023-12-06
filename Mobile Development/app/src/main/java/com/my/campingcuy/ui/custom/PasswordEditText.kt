package com.my.campingcuy.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import com.my.campingcuy.R

class PasswordEditText : AppCompatEditText {

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context) : super(context) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init() {
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        compoundDrawablePadding = 24

        setHint(R.string.password)
        setAutofillHints(AUTOFILL_HINT_PASSWORD)
        transformationMethod = PasswordTransformationMethod()

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty() && s.length < 8)
                    error = context.getString(R.string.error_password_min_8_char)
            }
        })
    }
}