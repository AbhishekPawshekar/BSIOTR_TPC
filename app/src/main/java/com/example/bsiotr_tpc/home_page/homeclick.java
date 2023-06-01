package com.example.bsiotr_tpc.home_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bsiotr_tpc.R;
import com.example.bsiotr_tpc.people.people;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeclick.
     */
    // TODO: Rename and change types and number of parameters
    public static homeclick newInstance(String param1, String param2) {
        homeclick fragment = new homeclick();
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
    CardView people,message,placement,test;
    ImageView peoplei,messagei,placementi,testi,pr,mr,plr,tr;
    TextView peopletext,messagetext,placementtext,testtext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_homeclick, container, false);
        people=view.findViewById(R.id.people_cv);
        message=view.findViewById(R.id.meassegs_cv);
        placement=view.findViewById(R.id.placement_cv);
        test=view.findViewById(R.id.test_cv);

        peoplei=view.findViewById(R.id.people_icon);
        messagei=view.findViewById(R.id.msg_icon);
        placementi=view.findViewById(R.id.training_icon);
        testi=view.findViewById(R.id.practice_test_icon);

        peopletext=view.findViewById(R.id.text_people);
        messagetext=view.findViewById(R.id.text_chat);
        placementtext=view.findViewById(R.id.text_training);
        testtext=view.findViewById(R.id.text_practice);

        pr=view.findViewById(R.id.people_right_icon);
        mr=view.findViewById(R.id.chat_right_icon);
        plr=view.findViewById(R.id.training_right_icon);
        tr=view.findViewById(R.id.practice_right_icon);

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.people.people.class));
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.messages.messages.class));
            }
        });
        placement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.placement.placement.class));
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.practice_test.practice_test.class));
            }
        });

        peoplei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.people.people.class));
            }
        });
        messagei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.messages.messages.class));
            }
        });
        placementi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.placement.placement.class));
            }
        });
        testi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.practice_test.practice_test.class));
            }
        });

        peopletext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.people.people.class));
            }
        });
        messagetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.messages.messages.class));
            }
        });
        placementtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.placement.placement.class));
            }
        });
        testtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.practice_test.practice_test.class));
            }
        });

        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.people.people.class));
            }
        });
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.messages.messages.class));
            }
        });
        plr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.placement.placement.class));
            }
        });
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), com.example.bsiotr_tpc.practice_test.practice_test.class));
            }
        });
        return view;
    }
}