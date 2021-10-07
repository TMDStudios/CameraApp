package com.example.cameraapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

private const val REQUEST_CODE = 7 // this can be any number

/**
This application will allow users to take a picture and import it into our application
Make sure to add the following permission to your Android Manifest:
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

Since we are passing the image from our camera to the application, the image can only be
up to 1MB. This means that our image quality will be very low.
**/

class MainActivity : AppCompatActivity() {

    private lateinit var btTakePhoto: Button
    private lateinit var ivMain: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivMain = findViewById(R.id.ivMain)
        btTakePhoto = findViewById(R.id.btTakePhoto)
        btTakePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // make sure that our device has a camera
            if(intent.resolveActivity(this.packageManager) != null){
                startActivityForResult(intent, REQUEST_CODE)
            }else{
                Toast.makeText(this, "No camera found", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // check if user has successfully taken a picture
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val image = data?.extras?.get("data") as Bitmap
            ivMain.setImageBitmap(image)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}