package com.ujjwalkumargupta.intellij.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ujjwalkumargupta.intellij.Adapter.DeleteTaskAdapter;
import com.ujjwalkumargupta.intellij.Common.CommonFragment;
import com.ujjwalkumargupta.intellij.ModelClasses.Task;
import com.ujjwalkumargupta.intellij.R;
import com.ujjwalkumargupta.intellij.SQLiteDatabase.DBOperation;

import java.util.ArrayList;
import java.util.List;

public class DeleteTaskFragment extends CommonFragment {
    private String TAG = DeleteTaskFragment.class.getName();
    private View rootView;
    private RecyclerView rvDelete;
    private List<Task> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_delete_task, container, false);
        initialize();
        action();
        return rootView;
    }

    private void initialize() {
        taskList = new ArrayList<>();
        rvDelete = (RecyclerView) rootView.findViewById(R.id.rv_delete_task);
    }

    private void action() {
        mFragmentContext.getSupportActionBar().setTitle("Select Tasks To Delete");
        DBOperation dbOperation = new DBOperation(mFragmentContext);
        taskList.clear();
        taskList.addAll(dbOperation.getTasks());
        rvDelete.setHasFixedSize(true);
        rvDelete.setLayoutManager(new LinearLayoutManager(mFragmentContext));
        DeleteTaskAdapter deleteTaskAdapter = new DeleteTaskAdapter(mFragmentContext, taskList);
        rvDelete.setAdapter(deleteTaskAdapter);
    }

}
