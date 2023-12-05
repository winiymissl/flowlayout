package com.example.beautifulspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.beautifulspinner.databinding.ActivitySpinnerBinding;

/**
 * @author winiymissl
 */
public class SpinnerActivity extends AppCompatActivity {
    ActivitySpinnerBinding binding;
    private SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
//        spinnerAdapter = new SpinnerAdapter(Character.getInfo());
//        binding.spinner.setAdapter(spinnerAdapter);

    }
}