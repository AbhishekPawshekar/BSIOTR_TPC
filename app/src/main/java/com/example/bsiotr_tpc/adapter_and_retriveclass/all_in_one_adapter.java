package com.example.bsiotr_tpc.adapter_and_retriveclass;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.messages.chatting_layout;
import com.example.bsiotr_tpc.navigationbar_option_layout.profile;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class all_in_one_adapter  extends FirestoreRecyclerAdapter<people_data_retriveclass,all_in_one_adapter.myholder> {
    int layouttoinflater;

    public all_in_one_adapter(@NonNull FirestoreRecyclerOptions<people_data_retriveclass> options,int layouttoinflater) {
        super(options);
        this.layouttoinflater=layouttoinflater;
    }
    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull people_data_retriveclass model) {
        if (layouttoinflater==R.layout.single_entities_of_short_desc_people_display){
            holder.dpname.setText(model.getFirst_name().toUpperCase().substring(0,1)+model.getLast_name().toUpperCase().substring(0,1));
            holder.nm.setText(model.getFirst_name()+" "+model.getLast_name());
            holder.dis.setText(model.getDescription());
            holder.cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), profile.class);
                    i.putExtra("callfrom","adapter");
                    i.putExtra("firstname",model.getFirst_name());
                    i.putExtra("lastname",model.getLast_name());
                    i.putExtra("description",model.getDescription());
                    i.putExtra("education",model.getEducation());
                    i.putExtra("technical",model.getTechnical());
                    i.putExtra("hobbies",model.getHobbies());
                    i.putExtra("email",model.getEmail());
                    i.putExtra("mobile",model.getMobile());
                    i.putExtra("gender",model.getGender());
                    i.putExtra("dob",model.getDob());
                    if (!model.getProfile_pic().equals(""))
                        i.putExtra("profile_pic",model.getProfile_pic());
                    else i.putExtra("profile_pic","empty");
                    v.getContext().startActivity(i);
                }
            });
        }
        else if (layouttoinflater==R.layout.single_entities_of_messages){
            if(model.getProfile_pic().equals("")){
                holder.dpname.setText(model.getFirst_name().toUpperCase().substring(0,1)+model.getLast_name().toUpperCase().substring(0,1));
            }else {
                Picasso.get().load(model.getProfile_pic()).into(holder.circleImageView);
            }
            holder.nm.setText(model.getFirst_name()+" "+model.getLast_name());
            holder.cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), chatting_layout.class);
                    i.putExtra("callfrom","chats_chatting");
                    i.putExtra("firstname",model.getFirst_name());
                    i.putExtra("lastname",model.getLast_name());
                    i.putExtra("uid",model.getUid());
                    i.putExtra("description",model.getDescription());
                    i.putExtra("email",model.getEmail());
                    if (!model.getProfile_pic().equals(""))
                        i.putExtra("profile_pic",model.getProfile_pic());
                    else i.putExtra("profile_pic","empty");
                    v.getContext().startActivity(i);
                }
            });
        }else if (layouttoinflater==R.layout.single_entities_new_messages){
            if(model.getProfile_pic().equals("")){
                holder.dpname.setText(model.getFirst_name().toUpperCase().substring(0,1)+model.getLast_name().toUpperCase().substring(0,1));
            }else {
                Picasso.get().load(model.getProfile_pic()).into(holder.circleImageView);
            }
            holder.nm.setText(model.getFirst_name()+" "+model.getLast_name());
            holder.cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), chatting_layout.class);
                    i.putExtra("callfrom","new_chatting");
                    i.putExtra("firstname",model.getFirst_name());
                    i.putExtra("lastname",model.getLast_name());
                    i.putExtra("uid",model.getUid());
                    i.putExtra("description",model.getDescription());
                    i.putExtra("email",model.getEmail());
                    if (!model.getProfile_pic().equals(""))
                        i.putExtra("profile_pic",model.getProfile_pic());
                    else i.putExtra("profile_pic","empty");
                    v.getContext().startActivity(i);
                }
            });
        }

    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layouttoinflater,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {


        TextView nm,dis,dpname;
        CardView cd;
        CircleImageView circleImageView;
        public myholder(@NonNull View itemView) {
            super(itemView);
            if (layouttoinflater==R.layout.single_entities_of_short_desc_people_display){
                cd=itemView.findViewById(R.id.short_desc_ui);
                nm=itemView.findViewById(R.id.student_name_short_desc_people);
                dis=itemView.findViewById(R.id.student_description_short_desc_people);
                dpname=itemView.findViewById(R.id.dp_name);
                circleImageView=itemView.findViewById(R.id.profile_pic_short_desc_people);
            }
            else if (layouttoinflater==R.layout.single_entities_of_messages){
                cd=itemView.findViewById(R.id.single_messages_ui);
                nm=itemView.findViewById(R.id.student_name_messages);
                dpname=itemView.findViewById(R.id.dp_name_messages);
                circleImageView=itemView.findViewById(R.id.profile_pic_messages);
            }
            else if (layouttoinflater==R.layout.single_entities_new_messages){
                cd=itemView.findViewById(R.id.single_new_messages_ui);
                nm=itemView.findViewById(R.id.student_name_new_messages);
                dpname=itemView.findViewById(R.id.dp_name_new_messages);
                circleImageView=itemView.findViewById(R.id.profile_pic_new_messages);
            }


        }
    }
}

