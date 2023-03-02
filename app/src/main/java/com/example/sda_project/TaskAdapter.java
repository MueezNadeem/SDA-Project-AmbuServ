package com.example.sda_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {


    private int resourceLayout;
    private Context mContext;

    public TaskAdapter(Context context, int resource, List<Task> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Task p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
            TextView tt3 = (TextView) v.findViewById(R.id.description);

            //if (tt1 != null) {
                tt1.setText(p.getTaskID());
            //}

            //if (tt2 != null) {
                tt2.setText(p.getLocation());
            //}

            //if (tt3 != null) {
                tt3.setText(p.getCurrentStatus().toString());
            //}
        }

        return v;
    }
}
