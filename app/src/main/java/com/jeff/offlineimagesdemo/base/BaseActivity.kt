package com.jeff.offlineimagesdemo.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.test.espresso.idling.CountingIdlingResource
import com.jeff.offlineimagesdemo.R
import dmax.dialog.SpotsDialog

abstract class BaseActivity : AppCompatActivity(){

    private lateinit var spotDialog: SpotsDialog
    public var countingIdlingResource= CountingIdlingResource("Main")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        spotDialog= SpotsDialog(this,resources.getString(R.string.Loading_data))
    }

    protected fun showError(str:String) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE),
            1
        )
    }

    protected fun showDialog() {
        if(!spotDialog.isShowing) {
            spotDialog.show()
            countingIdlingResource.increment()

        }
    }

    protected fun hideDialog() {
        if(spotDialog.isShowing){
            spotDialog.dismiss()
            countingIdlingResource.decrement()


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
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                } else {
                    showError(resources.getString(R.string.Permission_denied))
                    finish()
                }
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideDialog()
    }
}
