package com.ujjwalkumargupta.intellij.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujjwalkumargupta.intellij.Adapter.EditTaskAdapter;
import com.ujjwalkumargupta.intellij.Common.CommonFragment;
import com.ujjwalkumargupta.intellij.ModelClasses.Task;
import com.ujjwalkumargupta.intellij.R;
import com.ujjwalkumargupta.intellij.SQLiteDatabase.DBOperation;

import java.util.ArrayList;
import java.util.List;

public class EditTaskFragment extends CommonFragment {
    private View rootView;
    private String TAG = EditTaskFragment.class.getName();
    private RecyclerView rvEditList;
    private List<Task> taskList;
    private boolean isEditable;

    public EditTaskFragment(boolean editable){
        this.isEditable = editable;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        initialize();
        action();
        return rootView;
    }

    private void initialize() {
        taskList = new ArrayList<>();
        rvEditList = (RecyclerView) rootView.findViewById(R.id.rv_edit_task);
    }

    private void action() {
        if (isEditable)
        mFragmentContext.getSupportActionBar().setTitle("Select One To Edit");
        else mFragmentContext.getSupportActionBar().setTitle("Final List Of Task");
        DBOperation dbOperation = new DBOperation(mFragmentContext);
        taskList.clear();
        taskList.addAll(dbOperation.getTasks());
        rvEditList.setHasFixedSize(true);
        rvEditList.setLayoutManager(new LinearLayoutManager(mFragmentContext));
        rvEditList.setAdapter(new EditTaskAdapter(mFragmentContext, taskList, isEditable));
    }
}
