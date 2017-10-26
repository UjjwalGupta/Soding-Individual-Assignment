package com.ujjwalkumargupta.intellij.Common;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ujjwalkumargupta.intellij.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by TCL-MOBILE on 10-Aug-16.
 */
public abstract class CommonFragment extends Fragment {

    public static AppCompatActivity mFragmentContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentContext = (AppCompatActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentContext.getSupportActionBar().show();
    }

    public static void replaceFramgentWithBackStack(Bundle bundle, final Fragment fragment) {
        FragmentManager fragmentManager = mFragmentContext.getSupportFragmentManager();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.commit();
    }

    public static void replaceFramgentWithBackStack(final Fragment fragment) {
        FragmentManager fragmentManager = mFragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.commit();
    }

    public static void replaceFramgentWithoutBackStack(Bundle bundle, final Fragment fragment) {
        FragmentManager fragmentManager = mFragmentContext.getSupportFragmentManager();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.commit();
    }

  public static void replaceFramgentWithoutBackStack(final Fragment fragment) {
        FragmentManager fragmentManager = mFragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.commit();
    }

    public static void hideSoftKeyBoard(){
        InputMethodManager inputManager = (InputMethodManager) mFragmentContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(mFragmentContext.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
