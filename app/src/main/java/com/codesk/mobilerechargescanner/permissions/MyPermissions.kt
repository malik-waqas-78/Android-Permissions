package com.codesk.mobilerechargescanner.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.provider.SyncStateContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.codesk.mobilerechargescanner.R
import com.codesk.mobilerechargescanner.constants.MyConstants
import com.codesk.mobilerechargescanner.dialogues.MyDialogues

class MyPermissions() {


   companion object{

       var permissions = arrayOf(
               Manifest.permission.CAMERA
       )

       fun hasCameraPermission(context: Context):Boolean{
           return ContextCompat.checkSelfPermission(context,permissions[0])==PackageManager.PERMISSION_GRANTED
       }
      /* title="""Allow "Phone Clone " to Read or Write Contacts.""";
       msg="This will allow us to read contacts from your old device and write them to your new device.";*/
       fun requestCameraPermission(context:Context){
          if (Build.VERSION.SDK_INT >= 23) {
              if((context as Activity).shouldShowRequestPermissionRationale(permissions[0])){
                  MyDialogues.showRational(
                      context,
                      context.resources.getString(R.string.camera_permission_title),
                      context.resources.getString(R.string.camera_permission_msg),
                      object : OpenSettingsForPermissions {
                          override fun allowCameraPermissions(context: Context) {
                              MyPermissions.openAppPermissionsSettings(context)
                          }
                      })
              }else{
                  ActivityCompat.requestPermissions(
                      context as Activity, permissions,
                      MyConstants.CAMERA_PERMISIION_REQUEST_CODE
                  )
              }

          }else {
              ActivityCompat.requestPermissions(
                  context as Activity, permissions,
                  MyConstants.CAMERA_PERMISIION_REQUEST_CODE
              )
          }
       }

       interface OpenSettingsForPermissions{
           fun allowCameraPermissions(context: Context);
       }

       fun openAppPermissionsSettings(context: Context){
               val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
               val uri: Uri = Uri.fromParts("package", context.packageName, null)
               intent.data = uri

              /* if(permission==myPermissions?.accessFineLocation){
                   requestCode=Constants.LOCATION_PERMISIION_REQUEST_CODE
               }else if(permission==myPermissions?.storageReadPermission||permission==myPermissions?.storageWritePermission){
                   requestCode=Constants.STORAGE_PERMISSION
               }else{
                   requestCode=2344
               }*/
           ( context as Activity).startActivityForResult(intent,MyConstants.CAMERA_PERMISSION_SETTINGS)
       }
   }

}