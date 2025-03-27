package com.androidadgeistsdk

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.androidadgeistsdk.databinding.ActivityMainBinding
import com.adgeistcreatives.AdGeistSDK
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize AdGeistSDK
        val adGeist = AdGeistSDK.initialize(applicationContext)
    
        // Create LoadAd instance
        val getAd = adGeist.getCreative()
    
        getAd.fetchCreative("67c99c7a34929568f405e7ff", "67a056c63205fce2290d1cda") { adData ->
          if (adData != null) {
            Log.d("MyActivity of app module", "${adData}")
          } else {
          }
        }

        val postAnalytics = adGeist.postCreativeAnalytics()

        postAnalytics.sendTrackingData("67dd0dca83189049e16b02f6", "67c99c7a34929568f405e7ff", "67a056c63205fce2290d1cda", "click") { adData ->
            if (adData != null) {
              Log.d("MyActivity of app module", "${adData}")
            } else {
            }
          }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}