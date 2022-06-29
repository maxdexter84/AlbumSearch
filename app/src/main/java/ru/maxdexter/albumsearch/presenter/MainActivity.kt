package ru.maxdexter.albumsearch.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.maxdexter.albumsearch.R
import ru.maxdexter.albumsearch.databinding.ActivityMainBinding
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.albumsSearchFragment -> exitProcess(EXIT_CODE)
            R.id.detailFragment -> navController.navigateUp()
            R.id.loginFragment -> exitProcess(EXIT_CODE)
            R.id.registrationFragment -> exitProcess(EXIT_CODE)
        }

    }

    companion object {
        const val EXIT_CODE = 0
    }
}