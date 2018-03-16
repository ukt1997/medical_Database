package com.example.ujjwalkumar.medical_record;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ip,fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip=(Button)findViewById(R.id.home_insertbtn);
        fetch=(Button)findViewById(R.id.home_fetchbtn);

        ip.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
        );

        fetch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, LoadActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
        );
    }

}
