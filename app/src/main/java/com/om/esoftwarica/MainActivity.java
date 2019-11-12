package com.om.esoftwarica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.om.esoftwarica.adapter.ViewPageAdapters;
import com.om.esoftwarica.fragments.SignIn;
import com.om.esoftwarica.fragments.SignUp;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpage);
        tabLayout=findViewById(R.id.tabid);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        ViewPageAdapters viewPageAdapter=new ViewPageAdapters(getSupportFragmentManager());
        viewPageAdapter.addfragment(new SignIn(),"Login");
        viewPageAdapter.addfragment(new SignUp(),"Sign Up");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
