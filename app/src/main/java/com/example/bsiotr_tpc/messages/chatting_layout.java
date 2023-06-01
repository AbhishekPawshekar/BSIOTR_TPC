package com.example.bsiotr_tpc.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.adapter_and_retriveclass.chat_adapter;
import com.example.bsiotr_tpc.adapter_and_retriveclass.people_data_retriveclass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatting_layout extends AppCompatActivity {
FirebaseAuth fa= FirebaseAuth.getInstance();
FirebaseDatabase fbd=FirebaseDatabase.getInstance();
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
CircleImageView profile_pic;
TextInputLayout chat_message;
ArrayList<people_data_retriveclass> arrayList;

String fn,ln,des,email,Profile_pic,callform,senderid,reciverid,uid,profile_pic_data;
TextView student_name,description,dp_name;
Button submit;
RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_layout);
        profile_pic=findViewById(R.id.profile_pic_of_chatting_layout);
        student_name=findViewById(R.id.student_name_chatting_layout);
        description=findViewById(R.id.student_description_chatting_layout);
        chat_message=findViewById(R.id.chat_messages_input);
        rv=findViewById(R.id.recyclerview_chatting_layout);
        dp_name=findViewById(R.id.dp_name_chatting_layout);
        submit=findViewById(R.id.submit_chatting_layout);
rv.setLayoutManager(new LinearLayoutManager(chatting_layout.this));
        Intent i=getIntent();
        callform=i.getStringExtra("callfrom");
        fn=i.getStringExtra("firstname");
       uid= i.getStringExtra("uid");
        ln=i.getStringExtra("lastname");
        des=i.getStringExtra("description");
        email=i.getStringExtra("email");
        Profile_pic=i.getStringExtra("profile_pic");

        if (Profile_pic.equals("empty")){
            profile_pic_data="";
            dp_name.setText(fn.toUpperCase().substring(0,1)+ln.toUpperCase().substring(0,1));
        }else{
            profile_pic_data=Profile_pic;
            Picasso.get().load(Profile_pic).into(profile_pic);
        }
        student_name.setText(fn+" "+ln);
        description.setText(des);

senderid=fa.getUid()+uid;
reciverid=uid+fa.getUid();
//displaying data to recyclerview
        arrayList=new ArrayList<>();
        chat_adapter ca=new chat_adapter(arrayList,chatting_layout.this);
rv.setAdapter(ca);



fbd.getReference().child("chats").child(senderid).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        arrayList.clear();
        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
            people_data_retriveclass model=dataSnapshot.getValue(people_data_retriveclass.class);
            arrayList.add(model);
        }
        ca.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=chat_message.getEditText().getText().toString();
                final people_data_retriveclass model=new people_data_retriveclass(message,fa.getUid());
                model.setTimestamp(new Date().getTime());
                chat_message.getEditText().setText("");
                fbd.getReference().child("chats").child(senderid).push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   fbd.getReference().child("chats").child(reciverid).push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               if(callform.equals("new_chatting")){
                               Map<String,String> data=new HashMap<>();
                               data.put("first_name",fn);
                               data.put("last_name",ln);
                               data.put("description",des);
                               data.put("profile_pic",profile_pic_data);
                               data.put("search",fn+" "+ln);
                               data.put("sender",fa.getUid());
                               data.put("reciver",uid);
                               fbfs.collection("messages").document(senderid).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {

                                   }
                               });
                           }
                           }
                       }
                   });
                    }
                });
            }
        });

    }
}