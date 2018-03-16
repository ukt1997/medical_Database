package com.example.ujjwalkumar.medical_record;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataDisplayActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    GridView gridView;
    ArrayList<Data> list;
    DataListAdapter adapter=null;
    TextView na,ad,ag,ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        na = (TextView) findViewById(R.id.grid_name);
        ad = (TextView) findViewById(R.id.grid_add);
        ag = (TextView)findViewById(R.id.grid_age);
        ph = (TextView)findViewById(R.id.grid_phone);

        String name= getIntent().getStringExtra("name");

        String phone= getIntent().getStringExtra("phone");

        gridView = (GridView) findViewById(R.id.data_display_gridview);
        list = new ArrayList<>();
        adapter = new DataListAdapter(this, R.layout.activity_data_display, list);
        mydb=new DatabaseHelper(this);
        Cursor res=mydb.fetchData(name,phone);
        //gridView.setAdapter(adapter);
        int id;
        String age=null,address=null;
        list.clear();
        while (res.moveToNext()) {
            id = res.getInt(0);
            name = res.getString(1);
           address = res.getString(2);
           phone =res.getString(3);
           age=res.getString(4);
            byte[] image = res.getBlob(5);

            list.add(new Data(id,name, address,phone,age, image));
        }
        na.setText("Name : "+name);
        ph.setText("Phone No. : "+phone);
        ag.setText("Age : "+age);
        ad.setText("Address : "+address);
        //holder.phoneview.setText(d.getMobile());
        //System.err.println(" reached here ----------------------------1");
        //System.err.println(list.size());
        //System.err.println(" reached here ----------------------------2");
        adapter = new DataListAdapter(this, R.layout.custom_grid_view, list);
        ///System.err.println(" reached here ----------------------------3");
        gridView.setAdapter(adapter);
       // System.err.println(" reached here ----------------------------4");
        adapter.notifyDataSetChanged();
        //System.err.println(" reached here ----------------------------5");
    }
}
