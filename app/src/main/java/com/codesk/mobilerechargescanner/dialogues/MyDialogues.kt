package com.codesk.mobilerechargescanner.dialogues

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import com.codesk.mobilerechargescanner.R
import com.codesk.mobilerechargescanner.permissions.MyPermissions

class MyDialogues {

    companion object{
        fun showRational(context: Context, title:String, msg:String,clickListener:MyPermissions.Companion.OpenSettingsForPermissions) {
            val dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.db_permissions_rational)
            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val btn_allow: Button = dialog.findViewById(R.id.btn_allow)
            val btn_dontAllow:Button = dialog.findViewById(R.id.btn_dont_allow)
            val tv_title: TextView =dialog.findViewById(R.id.tv_title)
            val tv_msg:TextView=dialog.findViewById(R.id.tv_msg)

            tv_msg.setText(msg)
            tv_title.setText(title)

            btn_allow.setOnClickListener {
                dialog.dismiss()
                clickListener.allowCameraPermissions(context)
            }
            btn_dontAllow.setOnClickListener {
                dialog.cancel()
            }
            btn_dontAllow.setOnClickListener {
                dialog.cancel()
            }

            dialog.setOnCancelListener {
                //dialogueClickListner.negativeHotSpotTurnOFF()
                dialog.dismiss()
            }
            dialog.show()
        }

    }
}