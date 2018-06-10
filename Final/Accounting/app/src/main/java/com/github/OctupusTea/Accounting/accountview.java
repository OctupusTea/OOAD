package com.github.OctupusTea.Accounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.accounting.R;

public class accountview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button categoryViewBtn10 = (Button) findViewById(R.id.button10);
        Button categoryViewBtn11 = (Button) findViewById(R.id.button11);
        Button categoryViewBtn19 = (Button) findViewById(R.id.button19);
        Button categoryViewBtn20 = (Button) findViewById(R.id.button20);
        categoryViewBtn10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(accountview.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(accountview.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn19.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(accountview.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryViewBtn20.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(accountview.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
