package com.example.open_camera_kotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.open_camera_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val CAMERA_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (i.resolveActivity(packageManager) != null)
            {
                startActivityForResult(i,CAMERA_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null)
                {
                    binding.camera.setImageBitmap(data.extras?.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(this, "Unrecognized", Toast.LENGTH_SHORT).show()
            }
        }
    }


}