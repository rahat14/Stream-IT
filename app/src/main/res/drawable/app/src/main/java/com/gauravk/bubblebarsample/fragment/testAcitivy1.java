package com.gauravk.bubblebarsample.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gauravk.bubblebarsample.R;

public class testAcitivy1 extends Fragment {
    View view;

    public testAcitivy1() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        LinearLayout car ;


        view = inflater.inflate(R.layout.activity_test2, container, false);



        return view ;
    }

}
