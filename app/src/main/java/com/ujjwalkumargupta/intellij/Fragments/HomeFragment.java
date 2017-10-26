package com.ujjwalkumargupta.intellij.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ujjwalkumargupta.intellij.Common.CommonFragment;
import com.ujjwalkumargupta.intellij.R;

public class HomeFragment extends CommonFragment {
    private String TAG = HomeFragment.class.getName();
    private View rootView;
    private Button btnCreateTask, btnEditTask, btnDeleteTask, btnListAllTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();
        action();
        return rootView;
    }

    private void initialize() {
        btnCreateTask = (Button) rootView.findViewById(R.id.btn_create_task);
        btnEditTask = (Button) rootView.findViewById(R.id.btn_edit_task);
        btnDeleteTask = (Button) rootView.findViewById(R.id.btn_delete_task);
        btnListAllTask = (Button) rootView.findViewById(R.id.btn_list_all_task);
    }

    private void action() {
        mFragmentContext.getSupportActionBar().setTitle("Home");
        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFramgentWithBackStack(new CreateTaskFragment());
            }
        });

        btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFramgentWithBackStack(new EditTaskFragment(true));
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFramgentWithBackStack(new DeleteTaskFragment());
            }
        });

        btnListAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFramgentWithBackStack(new EditTaskFragment(false));
            }
        });
    }
}
