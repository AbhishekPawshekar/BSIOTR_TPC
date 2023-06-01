package com.example.bsiotr_tpc.navigationbar_option_layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bsiotr_tpc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class edu_tech_hobbies_edit extends AppCompatActivity {
String edit_which_data;
Button submit;
TextInputLayout edit_data;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
FirebaseAuth fa=FirebaseAuth.getInstance();
FirebaseUser fbu=fa.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_tech_hobbies_edit);
        edit_data=findViewById(R.id.edit_details);
        submit=findViewById(R.id.submit_edu_tech_hobbies);
        Intent i=getIntent();
        edit_which_data=i.getStringExtra("edit_which_data");


        if(edit_which_data.equals("education")){
            edit_data.setHint("Enter Education Details");
        }else if (edit_which_data.equals("technical")){ edit_data.setHint("Enter Technical Details");}
        else {
            edit_data.setHint("Enter Hobbies");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(edit_which_data.equals("education")){

            fbfs.collection("student").document(fbu.getEmail()).update("education",edit_data.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Education Details Successfully Updated !!..",Toast.LENGTH_LONG).show();
                        Intent j=new Intent(getApplicationContext(),profile.class);
                        j.putExtra("callfrom","edu_tech_hobbies");
                        startActivity(j);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(edu_tech_hobbies_edit.this);
                    builder.setMessage("Error:"+e)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });
        }else if (edit_which_data.equals("technical")){
            fbfs.collection("student").document(fbu.getEmail()).update("technical",edit_data.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Technical Details Successfully Updated !!..",Toast.LENGTH_LONG).show();
                        Intent j=new Intent(getApplicationContext(),profile.class);
                        j.putExtra("callfrom","edu_tech_hobbies");
                        startActivity(j);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(edu_tech_hobbies_edit.this);
                    builder.setMessage("Error:"+e)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });

        }else if(edit_which_data.equals("hobbies")){
            fbfs.collection("student").document(fbu.getEmail()).update("hobbies",edit_data.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Hobbies Details Successfully Updated !!..",Toast.LENGTH_LONG).show();
                        Intent j=new Intent(getApplicationContext(),profile.class);
                        j.putExtra("callfrom","edu_tech_hobbies");
                        startActivity(j);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(edu_tech_hobbies_edit.this);
                    builder.setMessage("Error:"+e)
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });
        }
            }
        });
    }
}