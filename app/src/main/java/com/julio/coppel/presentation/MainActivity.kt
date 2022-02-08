package com.julio.coppel.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.julio.coppel.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity principal que contiene la lógica de navegación.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * Controlador de la navegación.
     */
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}