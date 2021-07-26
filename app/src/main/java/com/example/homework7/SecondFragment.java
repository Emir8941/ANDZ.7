package com.example.homework7;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.io.Serializable;


public class SecondFragment extends Fragment {
    EditText etTitle, etDescription;
    Button btnSave;
    TaskModel taskModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }


    public static SecondFragment newInstance(String param1, String param2) {

        SecondFragment fragment = new SecondFragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                taskModel = new TaskModel(title, description);
                Bundle args = new Bundle();

                args.putSerializable("model", taskModel);

                getActivity().getSupportFragmentManager().setFragmentResult("title", args);
                getActivity().getSupportFragmentManager().popBackStack();


            }
        });
        return view;
    }
}