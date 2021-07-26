package com.example.homework7;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FirstFragment extends Fragment {
    RecyclerView rvTask;
    FloatingActionButton btnOpenAddTask;
    RecyclerAdapter adapter;
    private TaskModel taskModel;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public FirstFragment() {


    }


    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

        adapter = new RecyclerAdapter(requireContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        rvTask = view.findViewById(R.id.rv_task);
        btnOpenAddTask = view.findViewById(R.id.btn_open_add_task);
        rvTask.setAdapter(adapter);
        rvTask.setLayoutManager(new LinearLayoutManager(requireContext()));
        btnOpenAddTask.setOnClickListener(v -> {

            SecondFragment nextFrag = new SecondFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, nextFrag, "secondFragment")
                    .addToBackStack(null)
                    .commit();
        });
        getData();
        return view;
    }

    private void getData() {
        getActivity().getSupportFragmentManager().setFragmentResultListener("title", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                taskModel = (TaskModel) result.getSerializable("model");
                adapter.addTask(taskModel);
                Log.e("tag", "onFragmentResult: ");
            }
        });

    }
}