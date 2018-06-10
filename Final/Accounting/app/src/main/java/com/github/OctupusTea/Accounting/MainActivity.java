package com.github.OctupusTea.Accounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();

    public void arrow (int direction){    //點擊箭頭，調整日期顯示
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
        calendar.add(Calendar.DATE, direction);
        String Ago = formatter2.format(calendar.getTime());
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Ago);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton iBtn01 = (ImageButton) findViewById(R.id.imageButton);  //主頁面左按鍵
        ImageButton iBtn02 = (ImageButton) findViewById(R.id.imageButton1); //主頁面右按鍵

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis()); //取得系統時間
        String str = formatter.format(curDate);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(str);
        iBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow(-1);
            }
        });
        iBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow(1);
            }
        });

        Button categoryViewBtn = (Button) findViewById(R.id.button);
        categoryViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, categoryView.class);
                startActivity(intent);
            }
        });

        Button accountViewBtn = (Button) findViewById(R.id.button1);
        accountViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, accountview.class);
                startActivity(intent);
            }
        });
    }



    @Override  // menu UI
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override   // 觸發 menu
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
