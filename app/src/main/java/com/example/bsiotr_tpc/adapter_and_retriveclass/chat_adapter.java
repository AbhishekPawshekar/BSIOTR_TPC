package com.example.bsiotr_tpc.adapter_and_retriveclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bsiotr_tpc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class chat_adapter extends RecyclerView.Adapter  {
    ArrayList<people_data_retriveclass> model;
    Context context;
    FirebaseAuth fa=FirebaseAuth.getInstance();

    public chat_adapter(ArrayList<people_data_retriveclass> model, Context context) {
        this.model = model;
        this.context = context;
    }
int SENDER_TYPE=1;
    int RECIVER_TYPE=2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==SENDER_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.single_entities_sender_messages,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.single_entities_reciver_message,parent,false);
            return new ReciverViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(model.get(position).getUid().equals(fa.getUid())){
            return SENDER_TYPE;
        }
        else{
            return RECIVER_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        people_data_retriveclass people_data_retriveclass=model.get(position);
        if (holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).textmsg.setText(people_data_retriveclass.getMessage());
        }
        else {
            ((ReciverViewHolder)holder).textmsg.setText(people_data_retriveclass.getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView textmsg,time;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textmsg=itemView.findViewById(R.id.textmeassage);
            time=itemView.findViewById(R.id.time_of_sender);
        }
    }
    public class ReciverViewHolder extends RecyclerView.ViewHolder{
        TextView textmsg,time;
        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            textmsg=itemView.findViewById(R.id.textmeassage_reciver);
            time= itemView.findViewById(R.id.time_of_reciver);
        }
    }
}
