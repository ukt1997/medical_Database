package com.example.ujjwalkumar.medical_record;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText name,phone;
    Button fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mydb = new DatabaseHelper(this);

        init();

        LoadData();
    }

    public void init()
    {
        name=(EditText)findViewById(R.id.output_name);
        phone=(EditText)findViewById(R.id.output_phone);
        fetch=(Button)findViewById(R.id.output_btn);
    }

    public void LoadData()
    {
        fetch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res=mydb.fetchData(name.getText().toString(),phone.getText().toString());

                        if(res.getCount()==0)
                        {
                            Toast.makeText(LoadActivity.this,"No Such Data Found  ",Toast.LENGTH_LONG).show();
                            name.setText("");
                            phone.setText("");
                        }
                        else
                        {
                            Intent intent=new Intent(LoadActivity.this,DataDisplayActivity.class);
                            intent.putExtra("name",name.getText().toString());
                            intent.putExtra("phone",phone.getText().toString());
                            LoadActivity.this.startActivity(intent);
                        }

                    }
                }
        );
    }
}
