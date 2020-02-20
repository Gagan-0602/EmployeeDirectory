package com.example.employeedirectory.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.employeedirectory.Models.EmployeeMC;
import com.example.employeedirectory.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Employee_ListAdapter extends ArrayAdapter<EmployeeMC> implements View.OnClickListener {
   Context context;
   ArrayList<EmployeeMC> mcArrayList;

   private static class ViewHolder
   {
    TextView emp_name;
   }

    public Employee_ListAdapter( Context context, ArrayList<EmployeeMC> mcArrayList) {
        super(context, R.layout.employe_name_row, mcArrayList);
        this.context=context;
        this.mcArrayList=mcArrayList;
    }

    @Override
    public void onClick(View v) {

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EmployeeMC modelClass = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        // final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.employe_name_row, parent, false);
            viewHolder.emp_name = (TextView) convertView.findViewById(R.id.emp_name_row);

            //    result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.e(TAG, "Response getemployee: " + modelClass.getFull_name());
       viewHolder.emp_name.setText(modelClass.getFull_name());
        return convertView;    }

}
