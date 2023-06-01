package com.example.bsiotr_tpc.messages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.adapter_and_retriveclass.all_in_one_adapter;
import com.example.bsiotr_tpc.adapter_and_retriveclass.people_data_retriveclass;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link new_messages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class new_messages extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public new_messages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment new_messages.
     */
    // TODO: Rename and change types and number of parameters
    public static new_messages newInstance(String param1, String param2) {
        new_messages fragment = new new_messages();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    SearchView sv;
    RecyclerView recyclerView;
    all_in_one_adapter myadpter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_messages, container, false);
        recyclerView=view.findViewById(R.id.new_messages_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        sv=view.findViewById(R.id.new_messages_searchview);
        FirestoreRecyclerOptions<people_data_retriveclass> options = new FirestoreRecyclerOptions.Builder<people_data_retriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("student"), people_data_retriveclass.class)
                .build();
        myadpter=new all_in_one_adapter(options,R.layout.single_entities_new_messages);
        recyclerView.setAdapter(myadpter);
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
        return  view;
    }
    public void startsearching(String text){
        FirestoreRecyclerOptions<people_data_retriveclass> options = new FirestoreRecyclerOptions.Builder<people_data_retriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("student").orderBy("search").startAt(text).endAt(text+"\uf8ff"), people_data_retriveclass.class)
                .build();
        myadpter=new all_in_one_adapter(options,R.layout.single_entities_new_messages);
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