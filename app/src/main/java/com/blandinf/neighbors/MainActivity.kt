package com.blandinf.neighbors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.blandinf.neighbors.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity() {

    Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = MainActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        changeFragment(ListNeighborsFragment())
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }
}