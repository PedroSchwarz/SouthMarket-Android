package com.schwarz.pedro.southmarket_android.ui.core.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.schwarz.pedro.southmarket_android.R
import com.schwarz.pedro.southmarket_android.ui.core.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.validToken.observe(this, this::goToMainWithStatus)
        viewModel.validateTokenExpireDate()
    }

    private fun goToMainWithStatus(valid: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(VALID_TOKEN, valid)
        startActivity(intent)
        finish()
    }

    private companion object {
        private const val VALID_TOKEN = "validToken"
    }
}