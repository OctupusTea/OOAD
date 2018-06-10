package com.accounting1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class categoryView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button categoryViewBtn12 = (Button) findViewById(R.id.button12);
        Button categoryViewBtn13 = (Button) findViewById(R.id.button13);
        Button categoryViewBtn14 = (Button) findViewById(R.id.button14);
        Button categoryViewBtn15 = (Button) findViewById(R.id.button15);
        Button categoryViewBtn16 = (Button) findViewById(R.id.button16);
        Button categoryViewBtn17 = (Button) findViewById(R.id.button17);
        Button categoryViewBtn18 = (Button) findViewById(R.id.button18);
        categoryViewBtn12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn16.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn17.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn18.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(categoryView.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
