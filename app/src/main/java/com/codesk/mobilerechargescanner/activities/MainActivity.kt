package com.codesk.mobilerechargescanner.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codesk.mobilerechargescanner.R
import com.codesk.mobilerechargescanner.constants.MyConstants
import com.codesk.mobilerechargescanner.dialogues.MyDialogues
import com.codesk.mobilerechargescanner.permissions.MyPermissions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            checkPermissions()
        }
    }

    private fun checkPermissions() {
        if (MyPermissions.hasCameraPermission(this@MainActivity)) {
            //proceed to next activity
            openChooseNetworkActivity()
        } else {
         //permissions not given
            MyPermissions.requestCameraPermission(this@MainActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MyConstants.CAMERA_PERMISSION_SETTINGS) {
            if (MyPermissions.hasCameraPermission(this@MainActivity)) {
                //proceed next
                openChooseNetworkActivity()
            } else {

                //permissions were not given do nothing
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MyConstants.CAMERA_PERMISIION_REQUEST_CODE && grantResults.size > 0 && permissions.size > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //proceed next
                openChooseNetworkActivity()
            } else {
               //permissions denied do nothing
            }
        }
    }

    fun openChooseNetworkActivity() {
        val intent = Intent(this@MainActivity, ChooseNetwork::class.java)
        startActivity(intent)
    }

}