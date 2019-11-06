package com.example.askbekotlin.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.askbekotlin.R
import com.example.askbekotlin.databinding.ActivityRegisterBinding
import com.example.askbekotlin.ui.base.BaseVMActivity
import com.example.askbekotlin.ui.WebViewActivity
import com.example.askbekotlin.utils.DialogUtil
import com.example.askbekotlin.utils.SimpleTextWatcher

class RegisterActivity : BaseVMActivity<RegisterViewModel>(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    override fun providerVMClass(): Class<RegisterViewModel>? = RegisterViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        setupToolbar()
        setEvents()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_chevron_left)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setEvents() {
        binding.tvTermOfUse.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        binding.edtRegisterFirstName.addTextChangedListener(registerTextWatcher())
        binding.edtRegisterLastName.addTextChangedListener(registerTextWatcher())
        binding.edtRegisterNickname.addTextChangedListener(registerTextWatcher())
        binding.edtRegisterEmail.addTextChangedListener(registerTextWatcher())
        binding.edtRegisterPassword.addTextChangedListener(registerTextWatcher())
        binding.edtRegisterRePassword.addTextChangedListener(registerTextWatcher())
    }

    private fun registerTextWatcher() = object : SimpleTextWatcher() {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val firstName = binding.edtRegisterFirstName.text.toString().trim()
            val lastName = binding.edtRegisterLastName.text.toString().trim()
            val nickName = binding.edtRegisterNickname.text.toString().trim()
            val email = binding.edtRegisterEmail.text.toString().trim()
            val password = binding.edtRegisterPassword.text.toString().trim()
            val rePassword = binding.edtRegisterRePassword.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()
                && nickName.isNotEmpty() && email.isNotEmpty()
                && password.isNotEmpty() && rePassword.isNotEmpty()
            ) {
                binding.btnRegister.isEnabled = true
                binding.btnRegister.setBackgroundColor(
                    ContextCompat.getColor(
                        this@RegisterActivity, R.color.orange_logo
                    )
                )
            } else {
                binding.btnRegister.isEnabled = false
                binding.btnRegister.setBackgroundColor(
                    ContextCompat.getColor(
                        this@RegisterActivity, R.color.gray
                    )
                )
            }
        }
    }

    override fun startObserve() {
        super.startObserve()

        mViewModel.apply {
            mData.observe(this@RegisterActivity, Observer {
                if (it) {
                    toLoginPage()
                }
            })

            mError.observe(this@RegisterActivity, Observer {
                when (it.code) {
                    "DUPLICATE_EMAIL" -> showErrorMessage(getString(R.string.error_register_duplicate_email))
                    "DUPLICATE_NICKNAME" -> showErrorMessage(getString(R.string.error_register_duplicate_nickname))
                    else -> handleApiError(it)
                }
            })
        }
    }

    private fun toLoginPage() {
        DialogUtil.showSimpleDialog(
            this,
            "",
            "ご登録いただいたメールアドレスに認証メールをお送りいたしました。メール内のリンクをクリックしてご登録を完了してください。",
            "確認",
            object : DialogUtil.SimpleDialogInterface {
                override fun positiveButtonPressed() {
                    NavUtils.navigateUpFromSameTask(this@RegisterActivity)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
                }
            })
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_term_of_use -> toWebView()
            R.id.btn_register -> registerAccount()
        }
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

    private fun toWebView() {
        val intent = Intent(baseContext, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.URL, "https://test.askbe.net/term-of-service")
        startActivity(intent)
    }

    private fun registerAccount() {
        if (validateUserRegister()) {
            mViewModel.register(
                binding.edtRegisterFirstName.text.toString(),
                binding.edtRegisterLastName.text.toString(),
                binding.edtRegisterNickname.text.toString(),
                binding.edtRegisterEmail.text.toString(),
                binding.edtRegisterPassword.text.toString()
            )
        }
    }

    private fun validateUserRegister(): Boolean {
        val errorMessage = StringBuilder()
        var isValid = true
        if (TextUtils.isEmpty(binding.edtRegisterFirstName.text.toString())) {
            errorMessage.append("- FirstName not null \n")
            isValid = false
        }
        if (TextUtils.isEmpty(binding.edtRegisterLastName.text.toString())) {
            errorMessage.append("- LastName not null \n")
            isValid = false
        }
        if (TextUtils.isEmpty(binding.edtRegisterNickname.text.toString())) {
            errorMessage.append("- Nickname not null \n")
            isValid = false
        }
        if (TextUtils.isEmpty(binding.edtRegisterEmail.text.toString()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(
                binding.edtRegisterEmail.text.toString()
            ).matches()
        ) {
            errorMessage.append(getString(R.string.error_register_email))
            errorMessage.append("\n")
            isValid = false
        }
        if (TextUtils.isEmpty(binding.edtRegisterPassword.text.toString())) {
            errorMessage.append(getString(R.string.error_register_password_empty))
            errorMessage.append("\n")
            isValid = false
        } else if (binding.edtRegisterPassword.text.toString().length < 8) {
            errorMessage.append(getString(R.string.error_register_password_length))
            errorMessage.append("\n")
            isValid = false
        }
        if (binding.edtRegisterRePassword.text.toString() != binding.edtRegisterRePassword.text.toString()) {
            errorMessage.append(getString(R.string.error_register_repassword))
            isValid = false
        }
        if (!isValid) {
            showErrorMessage(errorMessage.toString())
        }
        return isValid
    }

}
