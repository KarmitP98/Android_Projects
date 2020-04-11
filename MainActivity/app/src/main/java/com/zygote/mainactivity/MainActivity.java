package com.zygote.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.zygote.mainactivity.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView title_txt;
    private TabItem tabItem1, tabItem2, tabItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();

    }

    private void setComponents() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        title_txt = findViewById(R.id.title);
//        tabItem1 = findViewById(R.id.tabItem);
//        tabItem2 = findViewById(R.id.tabItem2);
//        tabItem3 = findViewById(R.id.tabItem3);

//        Objects.requireNonNull(tabs.getTabAt(0)).setText("Tab 1");
//        Objects.requireNonNull(tabs.getTabAt(1)).setText("Tab 2");
//        Objects.requireNonNull(tabs.getTabAt(2)).setText("Tab 3");
    }
}