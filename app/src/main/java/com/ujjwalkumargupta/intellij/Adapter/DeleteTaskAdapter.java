package com.ujjwalkumargupta.intellij.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ujjwalkumargupta.intellij.Activities.MainActivity;
import com.ujjwalkumargupta.intellij.Fragments.EditTaskFragment;
import com.ujjwalkumargupta.intellij.ModelClasses.Task;
import com.ujjwalkumargupta.intellij.R;
import com.ujjwalkumargupta.intellij.SQLiteDatabase.DBOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ujjwal Kumar Gupta on 23-Jul-17.
 */
public class DeleteTaskAdapter extends RecyclerView.Adapter<DeleteTaskAdapter.MyViewHolder> {
    private String TAG = EditTaskFragment.class.getName();
    private List<Task> taskList, deleteList;
    private Context mContext;

    public DeleteTaskAdapter(Context ctx, List<Task> list) {
        this.mContext = ctx;
        this.taskList = list;
    }

    @Override
    public DeleteTaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_items, parent, false);
        return new DeleteTaskAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DeleteTaskAdapter.MyViewHolder holder, final int position) {
        holder.tvName.setText(taskList.get(position).getTaskName());
        holder.tvdesc.setText(taskList.get(position).getTaskDescription());
        holder.tvdateCreated.setText("Created: " + taskList.get(position).getTaskCreated());
        if (taskList.get(position).getTaskUpdated() != "")
            holder.tvDateUpdated.setText("Updated: " + taskList.get(position).getTaskUpdated());
        else holder.tvDateUpdated.setText("Updated: Not Yet");

        holder.checkboxDelete.setChecked(taskList.get(position).isChecked());

        holder.checkboxDelete.setTag(taskList.get(position));

        deleteList = new ArrayList<>();

        holder.checkboxDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Task contact = (Task) cb.getTag();
                contact.setChecked(cb.isChecked());
                taskList.get(position).setChecked(cb.isChecked());
                if (cb.isChecked()) {
                    deleteList.add(taskList.get(position));
                    holder.checkboxDelete.setSelected(false);
                } else {
                    if (deleteList.size() > 0) {
                        deleteList.remove(taskList.get(position));
                    }
                    holder.checkboxDelete.setSelected(true);
                }
                MainActivity.ivDone.setVisibility(View.VISIBLE);
                MainActivity.ivDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBOperation dbOperation = new DBOperation(mContext);
                        dbOperation.deleteContact(deleteList);
                        taskList.clear();
                        taskList.addAll(dbOperation.getTasks());
                        notifyDataSetChanged();
                        MainActivity.ivDone.setVisibility(View.GONE);
                        Toast.makeText(mContext, "Tasks Deleted Successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
            checkboxDelete.setVisibility(View.VISIBLE);
        }
    }
}