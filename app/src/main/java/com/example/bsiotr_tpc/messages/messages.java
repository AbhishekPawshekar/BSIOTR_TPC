package com.example.bsiotr_tpc.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.app.ActivityManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.home_page.homeclick;
import com.google.android.material.tabs.TabLayout;

public class messages extends AppCompatActivity {
TextView chats,newmessages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        chats=findViewById(R.id.chat_messages);
        newmessages=findViewById(R.id.new_messages);
        chats.setBackgroundColor(Color.argb(49,248,70,70));

        getSupportFragmentManager().beginTransaction().replace(R.id.messages_framelayout,new chats_messages()).commit();

        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newmessages.setBackgroundColor(Color.argb(255,255,255,255));
                chats.setBackgroundColor(Color.argb(49,248,70,70));
                getSupportFragmentManager().beginTransaction().replace(R.id.messages_framelayout,new chats_messages()).commit();

            }
        });
        newmessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chats.setBackgroundColor(Color.argb(255,255,255,255));
                newmessages.setBackgroundColor(Color.argb(49,248,70,70));
                getSupportFragmentManager().beginTransaction().replace(R.id.messages_framelayout,new new_messages()).commit();

            }
        });

    }
}