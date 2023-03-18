package com.test.myframework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.myframework.forty.four.LifecycleActivity;
import com.test.myframework.utils.CommonAdapter;
import com.test.myframework.utils.ViewHolder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String[] TITLES = {"移动架构43_ Lifecycle与生命周期"};

    private static final Class[] target = {LifecycleActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(new CommonAdapter<String>(MainActivity.this, Arrays.asList(TITLES), android.R.layout.simple_list_item_1) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, target[position]));
            }
        });
    }
}