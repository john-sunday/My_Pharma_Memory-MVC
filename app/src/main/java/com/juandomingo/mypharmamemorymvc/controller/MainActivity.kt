package com.juandomingo.mypharmamemorymvc.controller

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.ActivityMainBinding
import com.juandomingo.mypharmamemorymvc.view.AboutFragment
import com.juandomingo.mypharmamemorymvc.view.ContactFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    // Navigation Drawer
    private lateinit var drawer: DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle
    // Firebase auth.
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    // Navigation Fragments.
    private lateinit var navController: NavController
    private val codeA: CodeLectorFragmentB = CodeLectorFragmentB()

    override fun onCreate(savedInstanceState: Bundle?) {
        /*  El establecimiento del tema tiene que ir en primer lugar, para
        *   que no se solapen la 'splashscreen.xml' y el 'activity_main.xml  */
        setTheme(R.style.Theme_MyPharmaMemory2)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        setDefaultFragment()

        // Nav Host Fragment.
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        toogle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        drawer.addDrawerListener(toogle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        // Nagitation Fragments using Navigation Graph.
        try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHostFragment.navController
        }
        catch (e: Exception){
            println("Error $e")
        }
        //setupActionBarWithNavController(navController)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_item_lector -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, CodeLectorFragment())
                    commit()
                }
            }
            R.id.nav_item_mypharma -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, MyPharmaHomeFragment())
                    commit()
                }
            }
            R.id.nav_item_cima -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, CimaSearcherFragment())
                    commit()
                }
            }
            R.id.nav_item_logout -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, LoginFragment())
                    commit()
                }
            }
            R.id.nav_item_about -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, AboutFragment())
                    commit()
                }
            }
            R.id.nav_item_contact -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.navHostFragment, ContactFragment())
                    commit()
                }
            }
        }
        // Escondemos panel de navegación después de pulsar opción
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setDefaultFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, LoginFragment())
            commit()
        }
    }
}

