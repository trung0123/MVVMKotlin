package com.example.askbekotlin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.askbekotlin.R
import com.example.askbekotlin.databinding.ActivityLoginBinding
import com.example.askbekotlin.ui.base.BaseVMActivity
import com.example.askbekotlin.ui.main.MainActivity
import com.example.askbekotlin.ui.register.RegisterActivity
import com.example.askbekotlin.utils.Preference

class LoginActivity : BaseVMActivity<LoginViewModel>(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun providerVMClass(): Class<LoginViewModel>? = LoginViewModel::class.java

    private var token by Preference(Preference.USER_TOKEN, "")
    private var code by Preference(Preference.USER_CODE, "")

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setEvents()
    }

    private fun setEvents() {
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun startObserve() {
        mViewModel.apply {
            mLoginData.observe(this@LoginActivity, Observer {
                token = it.token!!
                code = it.code!!
                toMainPage()
            })

            mError.observe(this@LoginActivity, Observer {
                when (it.code) {
                    "DUPLICATE_EMAIL" -> showErrorMessage(getString(R.string.error_login_duplicate_email))
                    "LOGIN_FAIL" -> showErrorMessage(getString(R.string.error_login_fail))
                    else -> handleApiError(it)
                }
            })
        }
    }

    private fun toMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_login -> login()
            R.id.btn_register -> toRegisterAccount()
        }
    }

    private fun login() {
        val username = binding.edtLoginEmail.text.toString().trim()
        val password = binding.edtLoginPassword.text.toString().trim()
        mViewModel.login(username, password, "")
    }

    private fun toRegisterAccount() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}
