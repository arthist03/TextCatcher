package com.example.textcatcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Extract Text From Images")
                .setContent("Easily recognize and extract text from images using ML Kit on Android. This feature allows you to digitize text from printed documents, signs, and more.")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("Capture and digitize text seamlessly.")
                .build());

        addFragment(new Step.Builder().setTitle("Copy and Paste Anywhere")
                .setContent("With the text recognition feature, you can copy extracted text and paste it into any application or document. Enhance productivity by saving time on manual typing.")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("Boost productivity with easy text extraction.")
                .build());

        addFragment(new Step.Builder().setTitle("Smart with Machine Learning")
                .setContent("Leverage the power of machine learning to enhance your text recognition capabilities. Our app uses advanced ML algorithms to ensure high accuracy and reliability.")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard3) // int top drawable
                .setSummary("Experience high accuracy with ML-powered text recognition.")
                .build());

    }


    @Override
    public void finishTutorial() {
        // Navigate to MainActivity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}