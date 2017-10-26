package com.ujjwalkumargupta.intellij.Activities;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ujjwalkumargupta.intellij.Fragments.HomeFragment;
import com.ujjwalkumargupta.intellij.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    public Toolbar toolbar;
    private ActionBar mActionbar;
    private boolean doubleBackToExitPressedOnce = false;
    public static ImageView ivDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initialize();
        action();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        ivDone = (ImageView) findViewById(R.id.iv_done);
    }

    private void action() {
        setSupportActionBar(toolbar);
        mActionbar = getSupportActionBar();
        replaceFragmentsOverMainActivity(new HomeFragment());
        backStackTask();
    }

    private void backStackTask() {
        hideKeyboard();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    mActionbar.setDisplayHomeAsUpEnabled(true);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = getSupportFragmentManager().getBackStackEntryCount();
                            if (count > 0) {
                                getSupportFragmentManager().popBackStack();
                                if (ivDone.getVisibility() == View.VISIBLE) ivDone.setVisibility(View.GONE);
                            } else {
                                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            }
                        }
                    });
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        hideKeyboard();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getSupportFragmentManager().popBackStack();
            if (ivDone.getVisibility() == View.VISIBLE) ivDone.setVisibility(View.GONE);
        } else {
            if (doubleBackToExitPressedOnce) {
                System.exit(0);
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void replaceFragmentsOverMainActivity(final Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.commit();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}