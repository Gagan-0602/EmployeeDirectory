package com.example.employeedirectory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeedirectory.Models.EmployeeMC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Employee_info extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView ph_no;
    TextView email;
    TextView biography;
    TextView team;
    TextView emp_type;
    String full_name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_info);
        imageView = (ImageView) findViewById(R.id.emp_image);
        name = (TextView) findViewById(R.id.emp_name);
        ph_no = (TextView) findViewById(R.id.emp_ph);
        email = (TextView) findViewById(R.id.emp_email);
        biography = (TextView) findViewById(R.id.biography);
        team = (TextView) findViewById(R.id.emp_team);
        emp_type = (TextView) findViewById(R.id.emp_type);

        EmployeeMC employeeMC = (EmployeeMC) getIntent().getSerializableExtra("data");
        new AsyncTaskLoadImage(imageView).execute(employeeMC.getPhoto_url_small());

        full_name = employeeMC.getFull_name();
        name.setText(full_name);
        Log.e("name:", employeeMC.getFull_name());
        ph_no.setText(employeeMC.getPhone_number());
        Log.e("ph", employeeMC.getPhone_number());
        email.setText(employeeMC.getEmail_address());
        biography.setText(employeeMC.getBiography());
        team.setText(employeeMC.getTeam());
        emp_type.setText(employeeMC.getEmployee_type());


    }

    public class AsyncTaskLoadImage extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;

        public AsyncTaskLoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                java.net.URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}

