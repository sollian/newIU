package com.sollian.iu.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.sollian.base.Utils.IUUtil
import com.sollian.base.kotlinext.validString
import com.sollian.base.view.BaseFragmentActivity
import com.sollian.iu.R
import com.sollian.iu.presenter.SignPresenter
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : BaseFragmentActivity<SignPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val textWatcher = MyTextWatcher()
        userName.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        submit.setOnClickListener {
            submit.isEnabled = false
            userName.isEnabled = false
            password.isEnabled = false

            submit.progress = 50
            IUUtil.hideSoftKeyBoard(password)
            presenter!!.signIn(userName.validString(),
                    password.validString())
        }
    }

    override fun initPresenter() = SignPresenter(this)

    fun onPostLogin(result: Boolean) {
        submit.progress = 0
        submit.isEnabled = !result
        userName.isEnabled = !result
        password.isEnabled = !result
    }

    inner class MyTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = userName.validString()
            val password = password.validString()

            submit.isEnabled = name.isNotEmpty() && password.isNotEmpty()
        }
    }
}
