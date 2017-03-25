package com.zzw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzw.activity.R;

public class MyFragment extends Fragment {
    private static final String LAYOUT_RES = "LAYOUT_RES";
    private int layoutRes;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param layoutRes layout resource.
     * @return A new instance of fragment MyFragment.
     */
    public static MyFragment newInstance(int layoutRes) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            layoutRes=getArguments().getInt(LAYOUT_RES, R.layout.fragment_first);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layoutRes, container, false);
    }
}
