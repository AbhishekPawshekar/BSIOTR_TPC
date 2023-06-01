package com.example.bsiotr_tpc.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.adapter_and_retriveclass.all_in_one_adapter;
import com.example.bsiotr_tpc.adapter_and_retriveclass.people_data_retriveclass;
import com.example.bsiotr_tpc.main_page;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class people extends AppCompatActivity {
ImageView backbutton;
SearchView sv;
RecyclerView recyclerView;
all_in_one_adapter myadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        backbutton=findViewById(R.id.back_button);
        recyclerView=findViewById(R.id.recyclerview_people);
        sv=findViewById(R.id.searchview_people);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), main_page.class));
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startsearching(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                startsearching(newText);
                return false;
            }
        });

        FirestoreRecyclerOptions<people_data_retriveclass> options = new FirestoreRecyclerOptions.Builder<people_data_retriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("student"), people_data_retriveclass.class)
                .build();
        myadpter=new all_in_one_adapter(options,R.layout.single_entities_of_short_desc_people_display);
        recyclerView.setAdapter(myadpter);

    }
    public void startsearching(String text){
        FirestoreRecyclerOptions<people_data_retriveclass> options = new FirestoreRecyclerOptions.Builder<people_data_retriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("student").orderBy("search").startAt(text).endAt(text+"\uf8ff"), people_data_retriveclass.class)
                .build();
        myadpter=new all_in_one_adapter(options,R.layout.single_entities_of_short_desc_people_display);
        myadpter.startListening();
        recyclerView.setAdapter(myadpter);
    }
    @Override
    public void onStart() {
        super.onStart();
        myadpter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadpter.stopListening();
    }
}