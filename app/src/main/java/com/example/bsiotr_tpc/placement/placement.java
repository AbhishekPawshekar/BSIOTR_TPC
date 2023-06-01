package com.example.bsiotr_tpc.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.main_page;

public class placement extends AppCompatActivity {
ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);
        backbutton=findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), main_page.class));
            }
        });
    }
}