package com.example.ujjwalkumar.medical_record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateActivity extends AppCompatActivity {

    EditText name,age,phone,address;
    ImageView image;
    Button capture,uploadimage,submit;

    DatabaseHelper mydb;

    final int Request_code_gallery=999;
    static final int Cam_request =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mydb = new DatabaseHelper(this);

        init();

        uploadimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpdateActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        Request_code_gallery
                );
            }
        });
        /*capture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent camera_intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file=getfile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                startActivityForResult(camera_intent,Cam_request);

            }
        });*/

        capture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent camera_intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //File file=getfile();
                //camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                startActivityForResult(camera_intent,Cam_request);

            }
        });

        AddData();

    }

    public void AddData()
    {
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isinserted = mydb.insertData(
                                name.getText().toString(),
                                address.getText().toString(),
                                phone.getText().toString(),
                                age.getText().toString(),
                                imageViewToByte(image)
                        );
                        if(isinserted = true)
                            Toast.makeText(UpdateActivity.this,"Data Inserted ",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UpdateActivity.this,"Data Insertion Failed  ",Toast.LENGTH_LONG).show();

                        name.setText("");
                        age.setText("");
                        address.setText("");
                        phone.setText("");
                        image.setImageResource(R.mipmap.ic_launcher);

                    }
                }
        );

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Request_code_gallery)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_code_gallery);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"ypu dont have access to file",Toast.LENGTH_SHORT).show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Request_code_gallery && resultCode==RESULT_OK && data != null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==Cam_request && resultCode== RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);


        }

        super.onActivityResult(requestCode, resultCode, data);
    }




    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void init()
    {
        name=(EditText)findViewById(R.id.input_name);
        age=(EditText)findViewById(R.id.input_age);
        address=(EditText)findViewById(R.id.input_add);
        phone=(EditText)findViewById(R.id.input_phone);
        image=(ImageView)findViewById(R.id.input_image);
        uploadimage=(Button)findViewById(R.id.input_btn_upload);
        capture=(Button)findViewById(R.id.input_btn_capture);
        submit=(Button)findViewById(R.id.submit);
    }

    private File getfile()
    {
        File folder= new File("sdcard/medical_report");
        if(!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"last_medical_record.jpg");
        return image_file;
    }

}
