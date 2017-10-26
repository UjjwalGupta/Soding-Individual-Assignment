package com.ujjwalkumargupta.intellij.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ujjwalkumargupta.intellij.Common.CommonFragment;
import com.ujjwalkumargupta.intellij.R;
import com.ujjwalkumargupta.intellij.SQLiteDatabase.DBOperation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTaskFragment extends CommonFragment {
    private String TAG = CreateTaskFragment.class.getName();
    private View rootView;
    private EditText etCreateTaskName, etCreateTaskDescription;
    private Button btnSubmitTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_task, container, false);
        initialize();
        action();
        return rootView;
    }

    private void initialize() {
        etCreateTaskName = (EditText) rootView.findViewById(R.id.et_create_task_name);
        etCreateTaskDescription = (EditText) rootView.findViewById(R.id.et_create_task_description);
        btnSubmitTask = (Button) rootView.findViewById(R.id.btn_submit_task);
    }

    private void action() {
        final String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        mFragmentContext.getSupportActionBar().setTitle("Create New Task");
        btnSubmitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCreateTaskName.getText().toString().equals("")){
                    etCreateTaskName.setError("Task name");
                } else if (etCreateTaskDescription.getText().toString().equals("")){
                    etCreateTaskDescription.setError("Description");
                } else {
                    new DBOperation(mFragmentContext).insertNewTask(etCreateTaskName.getText().toString(),
                            etCreateTaskDescription.getText().toString(), date);
                    Toast.makeText(mFragmentContext, "Task Created Successfully", Toast.LENGTH_LONG).show();
                    mFragmentContext.getSupportFragmentManager().popBackStack();
                    etCreateTaskName.setText("");
                    etCreateTaskDescription.setText("");
                }
            }
        });
    }
}
