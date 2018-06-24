package com.github.OctupusTea.Accounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
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
                intent.setClass(MainActivity.this, accountView.class);
                startActivity(intent);
            }
        });

        Button cal_0 = findViewById(R.id.cal_btn_0);
        Button cal_1 = findViewById(R.id.cal_btn_1);
        Button cal_2 = findViewById(R.id.cal_btn_2);
        Button cal_3 = findViewById(R.id.cal_btn_3);
        Button cal_4 = findViewById(R.id.cal_btn_4);
        Button cal_5 = findViewById(R.id.cal_btn_5);
        Button cal_6 = findViewById(R.id.cal_btn_6);
        Button cal_7 = findViewById(R.id.cal_btn_7);
        Button cal_8 = findViewById(R.id.cal_btn_8);
        Button cal_9 = findViewById(R.id.cal_btn_9);
        Button cal_plus = findViewById(R.id.cal_btn_plus);
        Button cal_minus = findViewById(R.id.cal_btn_minus);
        Button cal_mult = findViewById(R.id.cal_btn_times);
        Button cal_div = findViewById(R.id.cal_btn_divid);
        Button cal_equal = findViewById(R.id.cal_btn_equal);
        Button cal_dot = findViewById(R.id.cal_btn_dot);
        Button cal_clr = findViewById(R.id.cal_btn_clear);

        final Calc calc = new Calc( );

        cal_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("0");
            }
        });

        cal_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("1");
            }
        });

        cal_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("2");
            }
        });

        cal_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("3");
            }
        });

        cal_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("4");
            }
        });

        cal_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("5");
            }
        });

        cal_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("6");
            }
        });

        cal_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("7");
            }
        });

        cal_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("8");
            }
        });

        cal_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress("9");
            }
        });

        cal_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.NumericPress(".");
            }
        });

        cal_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Calculate( calc.PLUS );
            }
        });

        cal_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Calculate(calc.MINUS);
            }
        });

        cal_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Calculate(calc.MULT);
            }
        });

        cal_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Calculate(calc.DIV);
            }
        });

        cal_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Calculate(calc.EQ);
            }
        });

        cal_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.Clear();
            }
        });
    }

    class Calc
    {
        private double valueA = Double.NaN;
        private double valueB;

        public static final char PLUS = '+';
        public static final char MINUS = '-';
        public static final char MULT = '*';
        public static final char DIV = '/';
        public static final char EQ = '=';

        private char current_op;

        private EditText shown = findViewById( R.id.cal_input );
        private EditText info = findViewById( R.id.cal_info );

        private DecimalFormat outputFormat = new DecimalFormat( "#.###" );

        public void NumericPress( String buttonPress )
        {
			shown.setText( shown.getText() + buttonPress );
        }

		public void Calculate( char op )
		{
			if( op == '=' )
			{
				Calculate();
				info.setText( info.getText().toString() + outputFormat.format(valueB)+" = ");
				shown.setText(outputFormat.format(valueA));
				valueA = Double.NaN;
				current_op = EQ;
			}
			else
			{
				Calculate();
				current_op = op;
				info.setText(outputFormat.format(valueA) + "" + op);
				shown.setText(null);
			}
		}

        public void Calculate( ) {
			if (!Double.isNaN(valueA)) {
				try
				{
					valueB = Double.parseDouble(shown.getText().toString());
				}
				catch( Exception e )
				{
					valueB = 0;
					return;
				}

				shown.setText(null);

				switch (current_op) {
					case PLUS:
						valueA = this.valueA + valueB;
						break;
					case MINUS:
						valueA = this.valueA - valueB;
						break;
					case MULT:
						valueA = this.valueA * valueB;
						break;
					case DIV:
						valueA = this.valueA / valueB;
						break;
				}
			}
			else
			{
				try {
					valueA = Double.parseDouble(shown.getText().toString());
				} catch (Exception e) {
				}
				;
			}
		}

		public void Clear( )
		{
			valueA = Double.NaN;
			valueB = 0.0;

			info.setText("");
			shown.setText("");
		}
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
        switch ( id )
		{
			case R.id.action_settings0:
				RunActivity( null ); // TODO: add account adding activity here
				return true;
			case R.id.action_settings1:
				RunActivity( null ); // TODO: add statistics activity here
				return true;
			case R.id.action_settings2:
				RunActivity( alarmSetting.class );
				return true;
			case R.id.action_settings3:
				RunActivity( BackupActivity.class );
				return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void RunActivity( Class<?> cls ) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, cls);
		startActivity(intent);
		MainActivity.this.finish();
	}
}