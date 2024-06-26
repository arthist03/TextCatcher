package com.example.textcatcher

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {

    lateinit var result:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val camera = findViewById<ImageView>(R.id.btnCamera)
        val erase = findViewById<ImageView>(R.id.btnErase)
        val copy = findViewById<ImageView>(R.id.btnCopy)

        result= findViewById(R.id.resultTV)
        camera.setOnClickListener {
            //open camera and store image
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, 123)
            } else{
            Toast.makeText(this, "Oops, Something went super super wrong!", Toast.LENGTH_SHORT).show()
        }
        }

        erase.setOnClickListener {
            result.setText("")
        }

        copy.setOnClickListener {
            val clipBoard= getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip= ClipData.newPlainText("label", result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==123 && resultCode == RESULT_OK){
            val extras= data?.extras
            val bitmap= extras?.get("data") as Bitmap
            detectTextUsingML(bitmap)
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(bitmap, 0)
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(this, "Oops, Something went super super wrong!", Toast.LENGTH_SHORT).show()
            }
    }
}