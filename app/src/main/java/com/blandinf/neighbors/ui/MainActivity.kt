package com.blandinf.neighbors.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blandinf.neighbors.R
import com.blandinf.neighbors.databinding.ActivityMainBinding
import com.blandinf.neighbors.di.DI
import com.blandinf.neighbors.listeners.NavigationListener
import com.blandinf.neighbors.ui.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var binding: ActivityMainBinding
    private var isInMemory: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.in_memory_button -> {
                isInMemory = true
            }
            R.id.in_db_button -> {
                isInMemory = false
            }
            else -> super.onOptionsItemSelected(item)
        }

        val bundle = Bundle()
        bundle.putBoolean("isInMemory", isInMemory)
        val fragment = ListNeighborsFragment()
        fragment.arguments = bundle

        showFragment(fragment)

        return true
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        binding.toolbar.setTitle(title)
    }
}
