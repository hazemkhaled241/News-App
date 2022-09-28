package com.hazem.newsapp.presentation.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hazem.newsapp.R
import com.hazem.newsapp.presentation.ui.Fragments.TechnologyFragment
import com.hazem.newsapp.presentation.ui.Fragments.ScienceFragment
import com.hazem.newsapp.presentation.ui.Fragments.SportsFragment
import com.hazem.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var status=savedInstanceState?.getBoolean("first")
        if(status==null)
            showFragment(SportsFragment())
       bottomNavigationView=binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { item ->
        bottomNavigationView.menu.findItem(item.itemId).isChecked = true


            var fragment: Fragment? = null
            if (item.itemId == R.id.fragment_sports)
                fragment = SportsFragment()
            else if (item.itemId == R.id.fragment_science)
                fragment = ScienceFragment()
            else if (item.itemId == R.id.fragment_technology)
                fragment = TechnologyFragment()

            /*supportFragmentManager
                .beginTransaction()
                .replace(binding.frameLayout.id, fragment!!)
                .commit()*/
            if (fragment != null) {
                showFragment(fragment)
            }
            false
        }


    }

    override fun onStop() {
        super.onStop()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("first",false)
    }
    private fun showFragment(fragment: Fragment){
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.frameLayout,fragment)
          .commit()
    }
}