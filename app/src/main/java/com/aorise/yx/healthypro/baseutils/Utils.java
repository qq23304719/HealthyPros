package com.aorise.yx.healthypro.baseutils;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Yx on 9:27 2017/3/28 03.
 */

public class Utils {
    public static String getJson(String fileName, Activity activity) {
        String info = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = activity.getResources().getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((info = bufferedReader.readLine()) != null) {
//                Log.d("TAG", "getSpcialfoucs: " + info);
                sb.append(info);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
