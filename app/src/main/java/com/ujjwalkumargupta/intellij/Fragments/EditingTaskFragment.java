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

public class EditingTaskFragment extends CommonFragment {
    private String TAG = EditingTaskFragment.class.getName();
    private View rootView;
    private EditText etEditTaskName, etEditTaskDescription;
    private Button btnUpdateTask;
    private int editingId;
    private String editingName, editingDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_editing_task, container, false);
        initialize();
        action();
        return rootView;
    }

    private void initialize() {
        etEditTaskName = (EditText) rootView.findViewById(R.id.et_edit_task_name);
        etEditTaskDescription = (EditText) rootView.findViewById(R.id.et_edit_task_description);
        btnUpdateTask = (Button) rootView.findViewById(R.id.btn_update_task);
    }

    private void action() {
        mFragmentContext.getSupportActionBar().setTitle("Edit Task");
        final String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        editingId = getArguments().getInt("editing_id");
        editingName = getArguments().getString("editing_name");
        editingDesc = getArguments().getString("editing_desc");
        etEditTaskName.setText(editingName);
        etEditTaskName.requestFocus();
        etEditTaskName.setFocusableInTouchMode(true);
        etEditTaskDescription.setText(editingDesc);
        btnUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, date);
                Log.d(TAG, "ID: " + editingId);
                if (etEditTaskName.getText().toString().equals("")){
                    etEditTaskName.setError("Task name");
                } else if (etEditTaskDescription.getText().toString().equals("")){
                    etEditTaskDescription.setError("Description");
                } else {
                    new DBOperation(mFragmentContext).updateTask(editingId, etEditTaskName.getText().toString(),
                            etEditTaskDescription.getText().toString(), date);

                    for(int i = 0; i < mFragmentContext.getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                        mFragmentContext.getSupportFragmentManager().popBackStack();
                    }

                    Toast.makeText(mFragmentContext, "Task Updated Successfully", Toast.LENGTH_LONG).show();
                    etEditTaskName.setText("");
                    etEditTaskDescription.setText("");
                }
            }
        });
    }
}
