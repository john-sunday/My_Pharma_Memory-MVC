package com.juandomingo.mypharmamemory.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.juandomingo.mypharmamemory.R
import com.juandomingo.mypharmamemory.databinding.ActivityMainBinding
import com.juandomingo.mypharmamemory.ui.view.about.AboutFragment
import com.juandomingo.mypharmamemory.ui.view.cimavsearcher.CimavSearcherFragment
import com.juandomingo.mypharmamemory.ui.view.codelector.CodeLectorFragment
import com.juandomingo.mypharmamemory.ui.view.contact.ContactFragment
import com.juandomingo.mypharmamemory.ui.view.login.LoginFragment
import com.juandomingo.mypharmamemory.ui.view.mypharmahome.MyPharmaHomeFragment
import com.juandomingo.mypharmamemory.ui.viewmodel.PharmaViewModel
import dagger.hilt.android.AndroidEntryPoint
// 2º paso Dagger Hilt:
@AndroidEntryPoint
/*  Requisitos:
*       -que la clase que va a ser inyectada esté preparada
*       -que la clase que va a recibir la inyección(this.class -> MainActivity)
*       esté preparada
* */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private  lateinit var binding: ActivityMainBinding
    /*  Con el '...by viewModels()' nos va a hacer toda la lógica,
    *   toda la conexión entre el viewmodel(PharmaViewModel)
    *   y la view(MainActivity), el ciclo de vida y lo demás, todo
    *   estará automatizado.    */
    private val pharmaViewModel: PharmaViewModel by viewModels()

    // Navigation Drawer
    private lateinit var drawer: DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        /*  El establecimiento del tema tiene que ir en primer lugar, para
        *   que no se solapen la 'splashscreen.xml' y el 'activity_main.xml  */
        setTheme(R.style.Theme_MyPharmaMemory2)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*  TODO Cuando tenga varias 'activity_.....xml' probar con 'binding.root',
            a ver si toma por defecto la main.xml   */
        val view = binding.root
        setContentView(view)
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        setDefaultFragment()
        pharmaViewModel.onCreate()
        /*pharmaViewModel.pharmaModel.observe(this, Observer {
            /*  Todo lo que esté dentro del Observer, estará enganchado al
            *   LiveData, y cuando el LiveData tenga un cambio, se ejecutará
            *   todo lo que esté aquí dentro.   */
            binding.tvQuote.text = it[0].quote
            binding.tvAuthor.text = it[0].author
            for (pharma in it) {
                Log.d("Pharmaceuticals", pharma.quote)
            }
            for ((index, value ) in it.withIndex()) {
                Log.d("Pharmaceuticals $index", value.author)
            }
        })
        pharmaViewModel.isLoading.observe(this, Observer {
            binding.progressBarCircle.isVisible = it
        })
        pharmaViewModel.isLoading.observe(this, Observer {
            binding.progressBarHor.isVisible = it
        })
        binding.getListPharmaBtn.setOnClickListener {
            //pharmaViewModel.getPharmaList()
            pharmaViewModel.randomPharma()
        }*/

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
                    replace(R.id.navHostFragment, CimavSearcherFragment())
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
    fun setDefaultFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, LoginFragment())
            commit()
        }
    }
}

