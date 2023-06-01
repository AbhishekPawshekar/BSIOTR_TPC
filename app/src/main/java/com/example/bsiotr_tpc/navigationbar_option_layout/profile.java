package com.example.bsiotr_tpc.navigationbar_option_layout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.main_page;
import com.example.bsiotr_tpc.people.people;
import com.example.bsiotr_tpc.registration_page2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {
CircleImageView civ;
Intent i;
Uri imagefile1;
ImageView back;
DocumentReference dr;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
FirebaseAuth fba=FirebaseAuth.getInstance();
FirebaseUser fbu=fba.getCurrentUser();
    ProgressDialog pd;
    StorageReference riversRef;
    StorageReference riverRef1;
private static final int PICK_IMAGE_REQUEST1=1;
String fn,ln,des,edu,tech,ho,em,mo,ge,dob,pp,callform,downloadurl1;
TextView educationedit,hobbiesedit,technicaledit,basicedit,contactedit,educationdetails,technicaldetails,genderdetails,dobdetails,contactdetails,emaildetails,hobbiesdetails,accountname,accountdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        civ=findViewById(R.id.profile_pic);
        back=findViewById(R.id.back_button);
        educationedit=findViewById(R.id.education_edit);
        hobbiesedit=findViewById(R.id.hobbies_edit);
        technicaledit=findViewById(R.id.technical_edit);
        basicedit=findViewById(R.id.basic_edit);
        contactedit=findViewById(R.id.contact_edit);
        pd =new ProgressDialog(profile.this);
        accountdescription=findViewById(R.id.account_description);
        accountname=findViewById(R.id.accountholdername);
        educationdetails=findViewById(R.id.education_details);
        technicaldetails=findViewById(R.id.technical_details);
        genderdetails=findViewById(R.id.gender_details);
        dobdetails=findViewById(R.id.dob_details);
        contactdetails=findViewById(R.id.contact_details);
        emaildetails=findViewById(R.id.email_details);
        hobbiesdetails=findViewById(R.id.hobbies_details);

         i=getIntent();
        callform=i.getStringExtra("callfrom");
        if (callform.equals("adapter")) {
            pp = i.getStringExtra("profile_pic");
            if(!pp.equals("empty")){
                Picasso.get().load(pp).into(civ);
            }
            fn = i.getStringExtra("firstname");
            ln = i.getStringExtra("lastname");
            des = i.getStringExtra("description");
            edu = i.getStringExtra("education");
            tech = i.getStringExtra("technical");
            ho = i.getStringExtra("hobbies");
            ge = i.getStringExtra("gender");
            dob = i.getStringExtra("dob");
            em = i.getStringExtra("email");
            mo = i.getStringExtra("mobile");
            accountname.setText(fn+" "+ln);
            accountdescription.setText(des);
            educationdetails.setText(edu);
            technicaldetails.setText(tech);
            hobbiesdetails.setText(ho);
            emaildetails.setText(em);
            contactdetails.setText(mo);
            genderdetails.setText(ge);
            dobdetails.setText(dob);
            educationedit.setEnabled(false);
            educationedit.setText("");
            technicaledit.setEnabled(false);
            technicaledit.setText("");
            hobbiesedit.setEnabled(false);
            hobbiesedit.setText("");
            basicedit.setEnabled(false);
            basicedit.setText("");
            contactedit.setEnabled(false);
            contactedit.setText("");

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), people.class));
                }
            });

        }else{
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), main_page.class));
                }
            });
           riverRef1 = FirebaseStorage.getInstance().getReference().child(fbu.getEmail()+ "/" + "profile_pic");
            dr = fbfs.collection("student").document(fbu.getEmail());
            civ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(i, PICK_IMAGE_REQUEST1);
                }
            });
            fbfs.collection("student").document(fbu.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        accountname.setText((String)task.getResult().get("first_name")+" "+task.getResult().get("last_name"));
                        accountdescription.setText((String) task.getResult().get("description"));
                        educationdetails.setText((String) task.getResult().get("education"));
                        technicaldetails.setText((String) task.getResult().get("technical"));
                        hobbiesdetails.setText((String) task.getResult().get("hobbies"));
                        emaildetails.setText((String)task.getResult().get("email"));
                        contactdetails.setText((String)task.getResult().get("mobile"));
                        genderdetails.setText((String)task.getResult().get("gender"));
                        dobdetails.setText((String) task.getResult().get("dob"));
                        String pp1=(String)task.getResult().get("profile_pic");
                        if(!pp1.equals("")){
                            Picasso.get().load(pp1).into(civ);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
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



            educationedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),edu_tech_hobbies_edit.class);
                    i.putExtra("edit_which_data","education");
                    startActivity(i);
                }
            });
            technicaledit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),edu_tech_hobbies_edit.class);
                    i.putExtra("edit_which_data","technical");
                    startActivity(i);
                }
            });

            hobbiesedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),edu_tech_hobbies_edit.class);
                    i.putExtra("edit_which_data","hobbies");
                    startActivity(i);
                }
            });
            basicedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),contact_basic_edit.class);
                    i.putExtra("edit_which_data","basic");
                    startActivity(i);
                }
            });
            contactedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),contact_basic_edit.class);
                    i.putExtra("edit_which_data","contact");
                    startActivity(i);
                }
            });




        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagefile1 = data.getData();
            pd.show();
            pd.setContentView(R.layout.progress_bar_register);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Picasso.get().load(imagefile1).into(civ);
            em=fbu.getEmail();
            getreducemoreimagesize(imagefile1, "profile_pic");

        }

    }

    public void getreducemoreimagesize(Uri imageuri, String location) {
        riversRef = FirebaseStorage.getInstance().getReference().child(fbu.getEmail() + "/" + location);
        Bitmap fullsizebitmap = null;
        try {
            fullsizebitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), imageuri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        fullsizebitmap.compress(Bitmap.CompressFormat.JPEG, 25, bos);
        byte[] bitmapdata = bos.toByteArray();
        UploadTask uploadTask = riversRef.putBytes(bitmapdata);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                riverRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    public void onSuccess(Uri uri) {
                        downloadurl1 = uri.toString();
                        dr.update("profile_pic", downloadurl1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(), "Profile Image Changed Successfully!!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error In Uploading", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }});
            }
        });

    }

}