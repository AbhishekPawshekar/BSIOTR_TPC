package com.example.bsiotr_tpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsiotr_tpc.home_page.homeclick;
import com.example.bsiotr_tpc.messages.messages;
import com.example.bsiotr_tpc.navigationbar_option_layout.calendar;
import com.example.bsiotr_tpc.navigationbar_option_layout.profile;
import com.example.bsiotr_tpc.people.people;
import com.example.bsiotr_tpc.placement.placement;
import com.example.bsiotr_tpc.practice_test.practice_test;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class main_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , BottomNavigationView.OnNavigationItemSelectedListener{
    TextView name,username;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth fa=FirebaseAuth.getInstance();
    FirebaseUser fbu=fa.getCurrentUser();
    FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        drawerLayout=findViewById(R.id.drawerlayout);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new homeclick()).commit();
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        updatenavheader();
    }

        @Override
        public void onBackPressed() {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are You Sure You Want To Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            onnavigationitemselect(item);
            return true;
        }
        public void onnavigationitemselect(MenuItem item){
            int id=item.getItemId();
            switch (id)
            {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new homeclick()).commit();
                    break;
                case R.id.profile:
                    Intent j=new Intent(this,profile.class);
                    j.putExtra("callfrom","main_page");
                    startActivity(j);
                    break;
                case R.id.people:
                    startActivity(new Intent(this,people.class));
                    break;
                case R.id.placement:
                    startActivity(new Intent(this, placement.class));
                    break;
                case R.id.text_practice:
                    startActivity(new Intent(this, practice_test.class));
                    break;
                case R.id.messages:
                    startActivity(new Intent(this, messages.class));
                    break;
                case R.id.delete_account:
                    if(fbu!=null) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(main_page.this);
                        builder.setMessage("Sure To Delete Account")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        fbu.delete();
                                        Intent i=new Intent(getApplicationContext(),main_page.class);
                                        fa.signOut();
                                        startActivity(i);
                                        fbfs.collection("student").document(fbu.getEmail()).delete();
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }  else{
                        Toast.makeText(getApplicationContext(),"Not Able To Delete Account",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.logout:
                    if(fbu!=null)
                    {
                        Intent i=new Intent(getApplicationContext(),login__page.class);
                        fa.signOut();
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"already Logout",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.calendar:
                    startActivity(new Intent(this, calendar.class));
                    break;
                case R.id.audit:
                    break;
                case R.id.resume:
                    break;
                case R.id.info:
                    break;

            }

            drawerLayout.closeDrawer(GravityCompat.START);

        }
        public void updatenavheader(){
            navigationView=findViewById(R.id.nav_view);
            View headerview=navigationView.getHeaderView(0);
            name=headerview.findViewById(R.id.student_name);
            username=headerview.findViewById(R.id.username);
            if(fbu!=null) {
                fbfs.collection("student").document(fbu.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            name.setText( (String) task.getResult().get("first_name")+" "+task.getResult().get("last_name"));
                            username.setText((String) task.getResult().get("username"));
                        }
                    }
                });
            }
        }
}
