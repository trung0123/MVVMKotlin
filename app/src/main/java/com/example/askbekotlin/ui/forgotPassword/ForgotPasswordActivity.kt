package com.example.askbekotlin.ui.forgotPassword

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.askbekotlin.R
import com.example.askbekotlin.databinding.ActivityForgotPasswordBinding
import com.example.askbekotlin.ui.base.BaseVMActivity
import com.example.askbekotlin.utils.DialogUtil
import com.example.askbekotlin.utils.SimpleTextWatcher

class ForgotPasswordActivity : BaseVMActivity<ForgotPasswordViewModel>(), View.OnClickListener {

    private lateinit var binding: ActivityForgotPasswordBinding
    override fun providerVMClass(): Class<ForgotPasswordViewModel>? =
        ForgotPasswordViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        setupToolbar()
        setEvents()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_chevron_left)

    }

    private fun setEvents() {
        binding.btnForgotPassword.setOnClickListener(this)
        binding.edtForgotEmail.addTextChangedListener(forgotPassTextWatcher())
    }

    private fun forgotPassTextWatcher() = object : SimpleTextWatcher() {
        override fun afterTextChanged(editable: Editable) {
            val email = binding.edtForgotEmail.text.toString().trim()
            if (email.isNotEmpty()) {
                binding.btnForgotPassword.isEnabled = true
                binding.btnForgotPassword.setBackgroundColor(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.blue_deep_sky_2
                    )
                )
            } else {
                binding.btnForgotPassword.isEnabled = false
                binding.btnForgotPassword.setBackgroundColor(
                    ContextCompat.getColor(
                        baseContext,
                        R.color.gray
                    )
                )
            }
        }
    }

    override fun startObserve() {
        super.startObserve()

        mViewModel.apply {
            mData.observe(this@ForgotPasswordActivity, Observer {
                if (it) {
                    resetPasswordSuccess()
                }
            })

            mError.observe(this@ForgotPasswordActivity, Observer {
                when (it.code) {
                    "USER_NOT_FOUND" -> showErrorMessage("ご入力されたメールアドレスは登録されていません")
                    else -> handleApiError(it)
                }
            })
        }
    }

    private fun resetPasswordSuccess() {
        DialogUtil.showSimpleDialog(
            this,
            "",
            "パスワードがリセットされました。", "ログインページへ戻る",
            object : DialogUtil.SimpleDialogInterface {
                override fun positiveButtonPressed() {
                    NavUtils.navigateUpFromSameTask(this@ForgotPasswordActivity)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
            }
        }
        return true
    }

    override fun onBackPressed() {
        NavUtils.navigateUpFromSameTask(this)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_forgot_password -> resetPassword()
        }
    }

    private fun resetPassword() {
        val email = binding.edtForgotEmail.text.toString()
        if (isValidEmailForgot(email)) {
            mViewModel.resetPassword(email)
        }
    }

    private fun isValidEmailForgot(email: String): Boolean {
        val errorMessage = StringBuilder()
        var isValid = true
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage.append(getString(R.string.email_not_valid))
            isValid = false
        }
        if (!isValid) {
            DialogUtil.showSimpleDialog(this, "", errorMessage.toString(), "確認")
        }
        return isValid
    }
}
