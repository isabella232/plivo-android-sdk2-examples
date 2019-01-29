package com.plivo.plivooutgoingcall.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AlertUtils {
    private Context context;

    public AlertUtils(Context viewContext) {
        context = viewContext;
    }

    public AlertDialog showAlertTwoButton(String title,
                                          String message,
                                          String positiveButtontext,
                                          DialogInterface.OnClickListener positiveButtonClickListener,
                                          String negativeButtonText,
                                          DialogInterface.OnClickListener negativeButtonClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtontext, positiveButtonClickListener)
                .setNegativeButton(negativeButtonText, negativeButtonClickListener)
                .show();
    }

    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void requestDialPermissionsRequired(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((Activity) context).requestPermissions(new String[]{
//                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS,
                            Manifest.permission.PROCESS_OUTGOING_CALLS,Manifest.permission.WRITE_SETTINGS,
                            Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_WIFI_STATE,
//                            Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.WAKE_LOCK,
//                            Manifest.permission.VIBRATE,Manifest.permission.READ_LOGS,Manifest.permission.USE_SIP,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    requestCode);
        }
    }
}
