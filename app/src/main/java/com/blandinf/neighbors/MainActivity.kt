package com.blandinf.neighbors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.blandinf.neighbors.databinding.ActivityMainBinding
import com.blandinf.neighbors.fragments.ListNeighborsFragment
import com.blandinf.neighbors.listeners.NavigationListener

class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showFragment(ListNeighborsFragment())
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