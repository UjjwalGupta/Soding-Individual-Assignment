package com.ujjwalkumargupta.intellij.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ujjwalkumargupta.intellij.Common.CommonFragment;
import com.ujjwalkumargupta.intellij.Fragments.EditTaskFragment;
import com.ujjwalkumargupta.intellij.Fragments.EditingTaskFragment;
import com.ujjwalkumargupta.intellij.ModelClasses.Task;
import com.ujjwalkumargupta.intellij.R;

import java.util.List;

public class EditTaskAdapter extends RecyclerView.Adapter<EditTaskAdapter.MyViewHolder> {
    private String TAG = EditTaskFragment.class.getName();
    private List<Task> taskList;
    private Context mContext;
    private boolean isEditable;

    public EditTaskAdapter(Context ctx, List<Task> list, boolean editable) {
        this.mContext = ctx;
        this.taskList = list;
        this.isEditable = editable;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(taskList.get(position).getTaskName());
        holder.tvdesc.setText(taskList.get(position).getTaskDescription());
        holder.tvdateCreated.setText("Created: " + taskList.get(position).getTaskCreated());
        if (taskList.get(position).getTaskUpdated() != null)
            holder.tvDateUpdated.setText("Updated: " + taskList.get(position).getTaskUpdated());
        else holder.tvDateUpdated.setText("Updated: Not Yet");

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName, tvdesc, tvdateCreated, tvDateUpdated;
        public CardView cardView;
        public CheckBox checkboxDelete;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardview_edit_task);
            tvName = (TextView) view.findViewById(R.id.tv_edit_task_rv_name);
            tvdesc = (TextView) view.findViewById(R.id.tv_edit_task_rv_desc);
            tvdateCreated = (TextView) view.findViewById(R.id.tv_edit_task_rv_date_created);
            tvDateUpdated = (TextView) view.findViewById(R.id.tv_edit_task_rv_date_updated);
            checkboxDelete = (CheckBox) view.findViewById(R.id.checkbox_delete);
            checkboxDelete.setVisibility(View.GONE);
            if (isEditable) cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("editing_id", taskList.get(getAdapterPosition()).getTaskId());
            bundle.putString("editing_name", taskList.get(getAdapterPosition()).getTaskName());
            bundle.putString("editing_desc", taskList.get(getAdapterPosition()).getTaskDescription());
            CommonFragment.replaceFramgentWithBackStack(bundle, new EditingTaskFragment());
        }
    }
}