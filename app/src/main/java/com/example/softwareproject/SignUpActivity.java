package com.example.softwareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;



import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    String server_url="http://192.168.43.182/soft/Dbconfig.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView id=(TextView) findViewById(R.id.signName);
        TextView pass=(TextView) findViewById(R.id.signPass);
        builder = new AlertDialog.Builder(SignUpActivity.this);
        Button loginBtn= (Button) findViewById(R.id.signBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if(name.getText().toString().equals("name")&&pass.getText().toString().equals("pass"))
                {
                    Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                } */

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage(response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                id.setText("");
                                pass.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,"some error found .....",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String> Params = new HashMap<String, String>();
                        Params.put("id",id.getText().toString());
                        Params.put("pass",pass.getText().toString());
                        return Params;

                    }
                };
                Mysingleton.getInstance(SignUpActivity.this).addTorequestque(stringRequest);

            }
        });

    }
}