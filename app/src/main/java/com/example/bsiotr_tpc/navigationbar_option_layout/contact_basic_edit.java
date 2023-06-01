package com.example.bsiotr_tpc.navigationbar_option_layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.registration_page2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class contact_basic_edit extends AppCompatActivity {
    String edit_which_data,date;
    Button submit;
    TextInputLayout edit_data1;
    TextView edit_data2;
    FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    FirebaseAuth fa=FirebaseAuth.getInstance();
    FirebaseUser fbu=fa.getCurrentUser();
    private int mYear, mMonth, mDay;
    private boolean validateedit1()
    {
        String edit1= edit_data1.getEditText().getText().toString().trim();


        if (edit1.isEmpty()) {
            edit_data1.setError("Field Can't Be Empty");
            return false;
        }
        else {
            edit_data1.setError(null);
            edit_data1.setErrorEnabled(false);
            return true; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_basic_edit);
        edit_data1=findViewById(R.id.edit_details1);
        edit_data2=findViewById(R.id.edit_details2);
        submit=findViewById(R.id.submit_contact_basic);
        Intent i=getIntent();
        edit_which_data=i.getStringExtra("edit_which_data");

        if(edit_which_data.equals("basic")){
            edit_data1.setHint("Enter Gender");
            edit_data1.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
            edit_data2.setText("Enter Date Of Birth");
        }else if (edit_which_data.equals("contact")){ edit_data1.setHint("Enter Mobile No:");
        edit_data2.setEnabled(false);
        edit_data1.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        edit_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(contact_basic_edit.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edit_data2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_which_data.equals("basic")){
                    if ( !validateedit1()) {
                        return;
                    }


                    fbfs.collection("student").document(fbu.getEmail()).update("gender",edit_data1.getEditText().getText().toString(),"dob",date).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Basic Details Successfully Updated !!..",Toast.LENGTH_LONG).show();
                                Intent j=new Intent(getApplicationContext(),profile.class);
                                j.putExtra("callfrom","contact_basic_edit");
                                startActivity(j);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(contact_basic_edit.this);
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
                }else if (edit_which_data.equals("contact")){
                    if ( !validateedit1()) {
                        return;
                    }
                    fbfs.collection("student").document(fbu.getEmail()).update("mobile",edit_data1.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"mobile_no Successfully Updated !!..",Toast.LENGTH_LONG).show();
                                Intent j=new Intent(getApplicationContext(),profile.class);
                                j.putExtra("callfrom","contact_basic_edit");
                                startActivity(j);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(contact_basic_edit.this);
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