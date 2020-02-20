package com.example.employeedirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.employeedirectory.Adapter.Employee_ListAdapter;
import com.example.employeedirectory.Models.EmployeeMC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView emp_list;
    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<EmployeeMC> array_emplist;
    public static final String URL_DATA = "https://s3.amazonaws.com/sq-mobile-interview/employees.json";
    Context context;
    EmployeeMC employeeMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        array_emplist = new ArrayList<>();
        emp_list = (ListView) findViewById(R.id.employee_list);


        emp_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(MainActivity.this, Employee_info.class);
                EmployeeMC employeeMC = array_emplist.get(position);
                intent.putExtra("data", employeeMC);
                startActivity(intent);

            }
        });
        new getEmployee().execute();
    }


    public class getEmployee extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("please wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HttpHandler handler = new HttpHandler();
            String jsonstr = handler.makeServiceCall(URL_DATA);
            if(jsonstr != null) {
                System.out.println("success");
            } else {
                System.out.println("failed");
            }


            return jsonstr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                if (s != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray employee = jsonObject.getJSONArray("employees");
                        final int numberOfItemsInResp = employee.length();
                        for (int i = 0; i < numberOfItemsInResp; i++) {
                            JSONObject object = employee.getJSONObject(i);
                            String uuid = object.getString("uuid");
                            String full_name = object.getString("full_name");
                            String phone_number = object.getString("phone_number");
                            String email_address = object.getString("email_address");
                            String biography = object.getString("biography");
                            String photo_url_small = object.getString("photo_url_small");
                            String photo_url_large = object.getString("photo_url_large");
                            String team = object.getString("team");
                            String employee_type = object.getString("employee_type");
                            employeeMC = new EmployeeMC(uuid, full_name, phone_number, email_address, biography, photo_url_small, photo_url_large, team, employee_type);

                            array_emplist.add(employeeMC);


                        }
                        emp_list.setAdapter(new Employee_ListAdapter(MainActivity.this, array_emplist));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "JOSN PARSING ERROR Exist", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
