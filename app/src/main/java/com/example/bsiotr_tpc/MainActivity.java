package com.example.bsiotr_tpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bsiotr_tpc.admin.admin_view;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static final int time=1000;
    FirebaseAuth fba;
    FirebaseUser fbu;
    FirebaseFirestore fbfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fba=FirebaseAuth.getInstance();
                fbu=fba.getCurrentUser();
                if(fbu!=null){
                    fbfs=FirebaseFirestore.getInstance();
                    fbfs.collection("admin").document(fbu.getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Intent i = new Intent(getApplicationContext(), admin_view.class);
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(), main_page.class);
                                startActivity(i);
                            }
                        }});
                }
                else{
                    Intent i = new Intent(getApplicationContext(), login__page.class);
                    startActivity(i);
                }
            }
        },time);

    }
}