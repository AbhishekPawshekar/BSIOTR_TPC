package com.example.bsiotr_tpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class registration_page extends AppCompatActivity {
TextInputLayout firstname,lastname,username,email,description;
Button next;
ImageView back;
    private boolean validatefirstname()
    {
        String first = firstname.getEditText().getText().toString().trim();
        if (first.isEmpty()) {
            firstname.setError("Field Can't Be Empty");
            return false;
        } else if (first.length() > 25) {
            firstname.setError("First Name Can't Be Greater Then 10");
            return false;
        } else {
            firstname.setError(null);
            firstname.setErrorEnabled(false);
            return true; }
    }
    private boolean validateLastName()
    {
        String last = lastname.getEditText().getText().toString().trim();
        if (last.isEmpty()) {
            lastname.setError("Field Can't Be Empty");
            return false;
        } else if (last.length() >25) {
            lastname.setError("Last Name Can't Be Greater Then 10");
            return false;
        }
        else {
            lastname.setError(null);
            lastname.setErrorEnabled(false);
            return true; }
    }
    private boolean validatedescription()
    {
        String desc= description.getEditText().getText().toString().trim();


        if (desc.isEmpty()) {
            description.setError("Field Can't Be Empty");
            return false;
        }
        else {
            description.setError(null);
            description.setErrorEnabled(false);
            return true; }
    }
    private boolean validateemail()
    {
        String emailff = email.getEditText().getText().toString().trim();
        if (emailff.isEmpty()) {
            email.setError("Field Can't Be Empty");
            return false;
        } else if (!emailff.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            email.setError("Wrong Email Format");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true; }
    }
    private boolean validateusername()
    {
        String user = username.getEditText().getText().toString().trim();
        if (user.isEmpty()) {
            username.setError("Field Can't Be Empty");
            return false;
        } else if (user.length() > 15) {
            username.setError("User Name Can't Be Greater Then 15");
            return false;
        } else if(user.matches("\\A\\w\\ {4,10}\\z")){
            username.setError("No WhiteSpace Are Allowed");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true; }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        firstname=findViewById(R.id.first_name_registration);
        lastname=findViewById(R.id.lastname_registration);
        username=findViewById(R.id.username_registration);
        email=findViewById(R.id.email_registration);
        next=findViewById(R.id.next_registration);
        back=findViewById(R.id.back_button);
        description=findViewById(R.id.description_registration);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login__page.class));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatefirstname() | !validateLastName() | !validateemail() | !validateusername() | !validatedescription()) {
                    return;
                }
                Intent i=new Intent(getApplicationContext(),registration_page2.class);
                i.putExtra("firstname",firstname.getEditText().getText().toString().trim());
                i.putExtra("lastname",lastname.getEditText().getText().toString().trim());
                i.putExtra("username",username.getEditText().getText().toString().trim());
                i.putExtra("email",email.getEditText().getText().toString().trim());
                i.putExtra("description",description.getEditText().getText().toString());
                startActivity(i);
            }
        });
    }
}