package com.example.bsiotr_tpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class registration_page2 extends AppCompatActivity {
TextInputLayout mobile,password,repassword;
RadioButton male,female,other;
Spinner department,classes;
Button submit;
TextView dob;
ProgressDialog pd;
FirebaseFirestore fbfs= FirebaseFirestore.getInstance();
FirebaseAuth fa=FirebaseAuth.getInstance();
String firstname,lastname,email,username,description,selecteddepartment,selectedclass;
String date="0";
ImageView back,calendar_icon;
    private int mYear, mMonth, mDay;

    private boolean validatephone()
    {
        String phone= mobile.getEditText().getText().toString().trim();


        if (phone.isEmpty()) {
            mobile.setError("Field Can't Be Empty");
            return false;
        } else if (phone.length() !=10) {
            mobile.setError("Phone No Should Be Exactly 10Digits ");
            return false;
        }
        else {
            mobile.setError(null);
            mobile.setErrorEnabled(false);
            return true; }
    }


    private boolean validatepassword() {
        String pass = password.getEditText().getText().toString().trim();
        String re_pass = repassword.getEditText().getText().toString().trim();

        if (pass.isEmpty()|re_pass.isEmpty()) {
            password.setError("Field Can't Be Empty");
            repassword.setError("Field Can't Be Empty");
            return false;
        } else if (!pass.equals(re_pass)) {
            repassword.setError("Password Not Match");
            return false;
        } else if (pass.length() < 8|re_pass.length()<8) {
            repassword.setError("Must Greater Then 7 Character");
            password.setError("Must Greater Then 7 Character");
            return false;
        }  else {
            password.setError(null);
            password.setErrorEnabled(false);
            repassword.setError(null);
            repassword.setErrorEnabled(false);

            return true;
        }
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);
        mobile=findViewById(R.id.mobile_registration);
        password=findViewById(R.id.password_registration);
        repassword=findViewById(R.id.re_passwword_registration);
        male=findViewById(R.id.male_radio_button);
        female=findViewById(R.id.female_radio_button);
        other=findViewById(R.id.other_radio_button);
        department=findViewById(R.id.select_department);
        classes=findViewById(R.id.select_class);
        submit=findViewById(R.id.submit_registration);
        dob=findViewById(R.id.dob_registration);
        calendar_icon=findViewById(R.id.calendar_icon);
       pd =new ProgressDialog(registration_page2.this);
        Intent i=getIntent();
        firstname=i.getStringExtra("firstname");
        lastname=i.getStringExtra("lastname");
        email=i.getStringExtra("email");
        username=i.getStringExtra("username");
        description=i.getStringExtra("description");
        back=findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),registration_page.class));
            }
        });
        List<String> departmentlist=new ArrayList<>();
        departmentlist.add("CO Dept");
        departmentlist.add("IT Dept");
        departmentlist.add("EE Dept");
        departmentlist.add("ME Dept");
        departmentlist.add("ENTC Dept");
        List<String> classlist=new ArrayList<>();
        classlist.add("FY");
        classlist.add("SY");
        classlist.add("TY");
        classlist.add("BE");

        ArrayAdapter<String> departmentaa=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,departmentlist);
        departmentaa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        department.setAdapter(departmentaa);
        ArrayAdapter<String> classaa=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, classlist);
        classaa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        classes.setAdapter(classaa);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(registration_page2.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        calendar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(registration_page2.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecteddepartment=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selecteddepartment="CO Dept";
            }
        });
      classes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              selectedclass=(String) parent.getItemAtPosition(position);
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              selectedclass="FY";
          }
      });
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if ( !validatepassword() | !validatephone()) {
            return;
        }//setting progress bar
        pd.show();
        pd.setContentView(R.layout.progress_bar_register);
        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//sending data to database
        fa.createUserWithEmailAndPassword(email,password.getEditText().getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String,String> data=new HashMap<>();
                    data.put("first_name",firstname);
                    data.put("last_name",lastname);
                    data.put("description",description);
                    data.put("email",email);
                    data.put("username",username);
                    data.put("mobile",mobile.getEditText().getText().toString().trim());
                    data.put("password",password.getEditText().getText().toString().trim());
                    if(male.isChecked()){
                        data.put("gender","male");
                    }else if(female.isChecked()){
                        data.put("gender","female");
                    }else if(other.isChecked()){
                        data.put("gender","other");
                    }
                    data.put("profile_pic","");
                    data.put("department",selecteddepartment);
                    data.put("classes",selectedclass);
                    data.put("dob",date);
                    data.put("education","");
                    data.put("technical","");
                    data.put("hobbies","");
                    data.put("uid",fa.getUid());
                    data.put("search",firstname.toLowerCase()+" "+lastname.toLowerCase());




                    fbfs.collection("student").document(email).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),login__page.class));
                            }else {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Fail To SignUp", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            AlertDialog.Builder builder=new AlertDialog.Builder(registration_page2.this);
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
                }else{
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(),"Already Register Email-Id !!.",Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                AlertDialog.Builder builder=new AlertDialog.Builder(registration_page2.this);
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
});


    }
}