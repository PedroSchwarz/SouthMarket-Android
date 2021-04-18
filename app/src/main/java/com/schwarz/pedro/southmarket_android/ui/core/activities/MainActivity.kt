package com.schwarz.pedro.southmarket_android.ui.core.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.schwarz.pedro.southmarket_android.R
import com.schwarz.pedro.southmarket_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.root_host) }

    private val appBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            R.id.authFragment,
            R.id.productsListFragment
        )
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(controller, appBarConfiguration)

        val valid = intent.extras?.getBoolean(VALID_TOKEN) ?: false
        setupStartDestination(valid)
    }

    private fun setupStartDestination(valid: Boolean) {
        controller.popBackStack()

        if (!valid)
            controller.navigate(R.id.productsListFragment)
        else
            controller.navigate(R.id.authFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

    private companion object {
        private const val VALID_TOKEN = "validToken"
    }
}
