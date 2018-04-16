package com.example.tranvanmanh.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tranvanmanh on 4/14/2018.
 */

public class TaskAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Task> taskList;

    public TaskAdapter(MainActivity context, int layout, List<Task> taskList) {
        this.context = context;
        this.layout = layout;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        private ImageView imvDelete, imvEdit;
        private TextView tvNameTask;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
           holder.imvDelete = (ImageView) view.findViewById(R.id.imvdelete);
           holder.imvEdit = (ImageView) view.findViewById(R.id.imvedit);
            holder.tvNameTask = (TextView) view.findViewById(R.id.nametask);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        final Task task = taskList.get(i);
        holder.tvNameTask.setText(task.getNameTask());

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogEdit(task.getNameTask(), task.getId());
            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(task.getNameTask(), task.getId());
            }
        });
        return view;

    }
}
