package com.example.sarah.paramedicsguide;



import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class take_photo extends AppCompatActivity {
    private  static  final int CAMERA_REQUST=1;
    private StorageReference mStorageReference;
    Uri imgUri;

   ImageView button , imageView;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);


       mStorageReference=FirebaseStorage.getInstance().getReference();
        button=(ImageView)findViewById(R.id.button);
       imageView=(ImageView)findViewById(R.id.imageView);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ContentValues contentValues = new ContentValues();
               contentValues.put(MediaStore.Images.Media.TITLE,"صورة جديدة");
               contentValues.put(MediaStore.Images.Media.DESCRIPTION,"بواسطة الكاميرا");
               imgUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
               Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               i.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
               startActivityForResult(i,CAMERA_REQUST);

           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri=data.getData();
            imageView.setImageURI(imgUri);
            StorageReference filepath=mStorageReference.child("Photos").child(imgUri.getLastPathSegment());
            filepath.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(take_photo.this,"تم ارسال الصورة ", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
               @Override
                public void onFailure(@NonNull Exception e) {

                    e.fillInStackTrace();

                }
           });
        }
    }
}
