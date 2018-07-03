package com.example.lsh.webviewtbs;

import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Object object) {
        Toast toast = Toast.makeText(APPAplication.getInstance(), object + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
