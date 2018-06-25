package com.github.OctupusTea.Accounting;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.github.OctupusTea.Accounting.DBAdapter.StatisticsAdapter;
import com.github.OctupusTea.Accounting.Data.DatePart;
import com.github.OctupusTea.Accounting.Data.Statistics;
import com.github.OctupusTea.Accounting.Statistics.StatisticsFunction;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.*;

public class StatisticsActivity extends AppCompatActivity {


    private static int[] COLORS = new int[] { Color.RED, Color.GREEN,
            Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.DKGRAY };

    double data[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    String category[] = new String []{"食","衣","住","行","育","樂","收入"};

    private CategorySeries mSeries = new CategorySeries("");// PieChart的DataSet

    private DefaultRenderer mRenderer = new DefaultRenderer();// PieChart的主要描繪器

    private GraphicalView mChartView;// 用來顯示PieChart 需要在配置文件Manifest中添加

    private LinearLayout mLinear;
    private StatisticsAdapter sAdapter = new StatisticsAdapter( this );

    int onetime=1;    // 第一次點箭頭
    int slt=3;    // 選擇年or月or日button
    int slt_tmp=0;    // 切換年月日button時，日期重新修改成系統日期
    Calendar calendar = Calendar.getInstance();

    final SimpleDateFormat formatter_y = new SimpleDateFormat("yyyy");
    final SimpleDateFormat formatter_m = new SimpleDateFormat("MM");
    final SimpleDateFormat formatter_d = new SimpleDateFormat("dd");

    public void token ( int choice ){    // 選擇年or月or日button
        SimpleDateFormat formatter;

        DatePart datePart = new DatePart();
        datePart.setYear( formatter_y.format( calendar.getTime() ) );
        datePart.setMonth( formatter_m.format( calendar.getTime() ) );
        datePart.setDay( formatter_d.format( calendar.getTime() ) );

        double sum, income, balance;
        DecimalFormat numberFormat = new DecimalFormat( "#.##" );

        TextView textView_sum = (TextView) findViewById(R.id.textView7);
        TextView textView_income = (TextView) findViewById(R.id.textView5);
        TextView textView_balance = (TextView) findViewById(R.id.textView9);
        TextView textView_date = (TextView) findViewById(R.id.textView3);

        switch(choice){
            case 1:
                formatter = new SimpleDateFormat ("yyyy");

                sum = sAdapter.getSumOfAllCategory( "year", datePart ); //支出
                income = sAdapter.getEachCategory("year", datePart, category[6]); //收入

                for(int i = 0; i < 7; i++ )
                {
                    data[ i ] = sAdapter.getEachCategory( "year", datePart, category[ i ] );
                }

                break;
            case 2:
                formatter = new SimpleDateFormat ("yyyy/MM");

                sum = sAdapter.getSumOfAllCategory( "month", datePart ); //支出
                income = sAdapter.getEachCategory( "month", datePart, category[6] ); //收入

                for(int i=0;i<7;i++){
                    data[i]=sAdapter.getEachCategory( "month", datePart,category[i] );
                }

                break;
            default:
                formatter = new SimpleDateFormat ("yyyy/MM/dd");

                sum = sAdapter.getSumOfAllCategory( "day", datePart );
                income = sAdapter.getEachCategory( "day", datePart,category[6] ); //收入

                for(int i=0;i<7;i++){
                    data[i]=sAdapter.getEachCategory( "day", datePart,category[i] );
                }
        }

        balance = income - sum; //結餘
        textView_sum.setText(numberFormat.format(sum));
        textView_income.setText(numberFormat.format(income));
        textView_balance.setText(numberFormat.format(balance));

        String dateString = formatter.format( calendar.getTime() );
        textView_date.setText( dateString );
    }

    public void arrow (int direction,int slt_now){    //點擊箭頭，調整日期顯示

        Date currentDate = new Date( System.currentTimeMillis() );

        if( onetime==1 ){
            calendar.setTime( currentDate );
            onetime=0;
            slt_tmp=slt_now;
        }
        if( onetime==0 && slt_tmp != slt_now ){
            calendar.setTime( currentDate );
            slt_tmp=slt_now;
        }

        token( slt_now );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        Button btn01 = (Button) findViewById(R.id.button8);
        Button btn02 = (Button) findViewById(R.id.button7);
        Button btn03 = (Button) findViewById(R.id.button6);
        ImageButton iBtn01 = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton iBtn02 = (ImageButton) findViewById(R.id.imageButton2);

        token(slt);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slt=1;
                token(slt);
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slt=2;
                token(slt);
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slt=3;
                token(slt);
            }
        });
        iBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow(-1,slt);
            }
        });
        iBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow(1,slt);
            }
        });


        mLinear = (LinearLayout) findViewById(R.id.chart);
        mLinear.setBackgroundColor(WHITE);

        //mRenderer.setZoomButtonsVisible(true);// 顯示放大縮小功能按鈕
        mRenderer.setShowLegend(false);//不顯示底部說明
        mRenderer.setStartAngle(180);// 設置為水平開始
        mRenderer.setDisplayValues(true);//
        mRenderer.setLabelsTextSize(40);//顯示數據顯示標籤字體大小
        mRenderer.setLabelsColor(BLACK); //設置標籤字體顏色

        mSeries.add("食", data[0]);
        mSeries.add("衣", data[1]);
        mSeries.add("住", data[2]);
        mSeries.add("行", data[3]);
        mSeries.add("育", data[4]);
        mSeries.add("樂", data[5]);
        mSeries.add("收入", data[6]);

        for (int i = 0; i < data.length; i++) {
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setChartValuesFormat(NumberFormat.getCurrencyInstance());// 設置百分比
            //mSeries.add("示例 " + (i + 1), data[i]);//mSeries.add("示例 " + (i + 1), data[i]);
            // 設置種類名稱和對應的數值，前面是（key,value）鍵值對
            if (i < COLORS.length) {
                renderer.setColor(COLORS[i]);// 設置描繪器的顏色
            } else {
                renderer.setColor(getRandomColor());// 設置描繪器的顏色
            }
            mRenderer.setChartTitleTextSize(14);// 設置圓餅圖標題大小
            mRenderer.addSeriesRenderer(renderer);// 將最新的描繪器添加到DefaultRenderer中
        }
        mChartView = ChartFactory.getPieChartView(getApplicationContext(), mSeries, mRenderer);// 建構mChartView
        mLinear.addView((View)mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        mRenderer.setClickEnabled(true);// 允許點擊事件
        mChartView.setOnClickListener(new View.OnClickListener() {// 具体内容
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = mChartView
                        .getCurrentSeriesAndPoint();// 獲取當前的類別與指標
                if (seriesSelection == null) {
                    Toast.makeText(getApplicationContext(),
                            "您未選擇數據", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mSeries.getItemCount(); i++) {
                        mRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());
                    }
                    mChartView.repaint();
                    Toast.makeText(getApplicationContext(), "金額：" + NumberFormat.getCurrencyInstance().format(seriesSelection.getValue()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getRandomColor() {// 分別產生RGB數值
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        return Color.rgb(R, G, B);
    }
}
