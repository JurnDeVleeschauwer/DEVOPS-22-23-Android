package com.hogent.devOps_Android.app

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.customview.widget.ViewDragHelper
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.auth0.android.Auth0
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.databinding.ActivityMainBinding
import com.hogent.devOps_Android.ui.login.CredentialsManager
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menu: Menu
    private lateinit var account: Auth0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        menu = binding.navView.menu

        val navController = this.findNavController(R.id.nav_host_fragment)

        binding.navView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        setDrawerOptions()

        CredentialsManager.LoggedIn.observe(
            this,
            Observer {
                onCreateOptionsMenu(menu)
            }
        )
        account = Auth0(
            "{MhyH2kcfW9oZB5ybh7F5yAAq4EmRtF3u}",
            "{dev-22b6kt7pbvj6yahl.us.auth0.com}"
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        if (CredentialsManager.LoggedIn.value!!) {
            menuInflater.inflate(R.menu.navdrawer_menu_logged_in, menu)
        } else {
            menuInflater.inflate(R.menu.navdrawer_menu_logged_out, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setDrawerOptions() {
        val field: Field = drawerLayout.javaClass.getDeclaredField("mLeftDragger")
        field.isAccessible = true
        val dragger: ViewDragHelper = field.get(drawerLayout) as ViewDragHelper
        val drag_size: Field = dragger.javaClass.getDeclaredField("mEdgeSize")
        drag_size.isAccessible = true
        drag_size.setInt(dragger, 100)
    }
}
