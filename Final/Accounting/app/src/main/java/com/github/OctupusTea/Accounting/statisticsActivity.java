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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.*;

public class statisticsActivity extends AppCompatActivity {


    private static int[] COLORS = new int[] { Color.RED, Color.GREEN,
            Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.DKGRAY };
    double data[] = new double[] { 2000, 1000, 6000, 1280, 6000,2000,22000 };

    private CategorySeries mSeries = new CategorySeries("");// PieChart的DataSet

    private DefaultRenderer mRenderer = new DefaultRenderer();// PieChart的主要描繪器

    private GraphicalView mChartView;// 用來顯示PieChart 需要在配置文件Manifest中添加

    private LinearLayout mLinear;
    private StatisticsAdapter sAdapter;

    int onetime=1;    // 第一次點箭頭
    int slt=3;    // 選擇年or月or日button
    int slt_tmp=0;    // 切換年月日button時，日期重新修改成系統日期
    Calendar calendar = Calendar.getInstance();

    public void token (int choice){    // 選擇年or月or日button
        SimpleDateFormat formatter;
        SimpleDateFormat formatter_y;
        SimpleDateFormat formatter_m;
        SimpleDateFormat formatter_d;
        String str_y;
        String str_m;
        String str_d;
        DatePart datePart = new DatePart();
        double sum;
        double income;
        double balance;
        TextView textView_sum = (TextView) findViewById(R.id.textView7);
        TextView textView_income = (TextView) findViewById(R.id.textView5);
        TextView textView_balance = (TextView) findViewById(R.id.textView9);
        switch(choice){
            case 1:
                formatter = new SimpleDateFormat ("yyyy");
                Date curDate_1 =  new Date(System.currentTimeMillis());
                str_y = formatter.format(curDate_1); //年
                datePart.setYear(str_y);
                sum = sAdapter.getSumOfAllCategory( "year", datePart ); //支出
                textView_sum.setText(Double.valueOf(sum).toString());
                // List<Statistics> sumOfCategoryList_1 = sAdapter.getSumOfEachCategory("year", datePart);
                // textView_sum.setText(sumOfCategoryList_1);
                income = sAdapter.getIncomeCategory( "year", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
                break;
            case 2:
                formatter = new SimpleDateFormat ("yyyy/MM");
                formatter_y = new SimpleDateFormat ("yyyy");
                formatter_m = new SimpleDateFormat ("MM");
                Date curDate_2 =  new Date(System.currentTimeMillis());
                str_y = formatter_y.format(curDate_2); //年
                str_m= formatter_m.format(curDate_2); //月
                datePart.setYear(str_y);
                datePart.setMonth(str_m);
                sum = sAdapter.getSumOfAllCategory( "month", datePart ); //支出
                textView_sum.setText(Double.valueOf(sum).toString());
                // List<Statistics> sumOfCategoryList_2 = sAdapter.getSumOfEachCategory("month", datePart);
                // textView_sum.setText(sumOfCategoryList_2);
                income = sAdapter.getIncomeCategory( "month", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
                break;
            default:
                formatter = new SimpleDateFormat ("yyyy/MM/dd");
                formatter_y = new SimpleDateFormat ("yyyy");
                formatter_m = new SimpleDateFormat ("MM");
                formatter_d = new SimpleDateFormat ("dd");
                Date curDate_3 =  new Date(System.currentTimeMillis());
                str_y = formatter_y.format(curDate_3);
                str_m= formatter_m.format(curDate_3);
                str_d= formatter_d.format(curDate_3);
                datePart.setYear(str_y);
                datePart.setMonth(str_m);
                datePart.setMonth(str_d);
                sum = sAdapter.getSumOfAllCategory( "day", datePart );
                textView_sum.setText(Double.valueOf(sum).toString());
                //List<Statistics> sumOfCategoryList_3 = sAdapter.getSumOfEachCategory("day", datePart);
                //textView_sum.setText(sumOfCategoryList_3);
                income = sAdapter.getIncomeCategory( "day", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
        }
        Date curDate =  new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(str);
    }

    public void arrow (int direction,int slt_now){    //點擊箭頭，調整日期顯示
        if(onetime==1){
            Date curDate =  new Date(System.currentTimeMillis());
            calendar.setTime(curDate);
            onetime=0;
            slt_tmp=slt_now;
        }
        if(onetime==0 && slt_tmp!=slt_now){
            Date curDate =  new Date(System.currentTimeMillis());
            calendar.setTime(curDate);
            slt_tmp=slt_now;
        }
        SimpleDateFormat formatter2;
        SimpleDateFormat formatter_y;
        SimpleDateFormat formatter_m;
        SimpleDateFormat formatter_d;
        String str_y;
        String str_m;
        String str_d;
        DatePart datePart = new DatePart();
        double sum;
        double income;
        double balance;
        TextView textView_sum = (TextView) findViewById(R.id.textView7);
        TextView textView_income = (TextView) findViewById(R.id.textView5);
        TextView textView_balance = (TextView) findViewById(R.id.textView9);
        switch(slt_now){
            case 1:
                formatter2 = new SimpleDateFormat("yyyy");
                calendar.add(Calendar.YEAR, direction);
                Date curDate_1 =  new Date(System.currentTimeMillis());
                str_y = formatter2.format(curDate_1); //年
                datePart.setYear(str_y);
                sum = sAdapter.getSumOfAllCategory( "year", datePart );
                textView_sum.setText(Double.valueOf(sum).toString());
                income = sAdapter.getIncomeCategory( "year", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
                break;
            case 2:
                formatter2 = new SimpleDateFormat("yyyy/MM");
                calendar.add(Calendar.MONTH, direction);
                formatter_y = new SimpleDateFormat ("yyyy");
                formatter_m = new SimpleDateFormat ("MM");
                Date curDate_2 =  new Date(System.currentTimeMillis());
                str_y = formatter_y.format(curDate_2); //年
                str_m= formatter_m.format(curDate_2); //月
                datePart.setYear(str_y);
                datePart.setMonth(str_m);
                sum = sAdapter.getSumOfAllCategory( "month", datePart );
                textView_sum.setText(Double.valueOf(sum).toString());
                income = sAdapter.getIncomeCategory( "month", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
                break;
            default:
                formatter2 = new SimpleDateFormat("yyyy/MM/dd");
                calendar.add(Calendar.DATE, direction);
                formatter_y = new SimpleDateFormat ("yyyy");
                formatter_m = new SimpleDateFormat ("MM");
                formatter_d = new SimpleDateFormat ("dd");
                Date curDate_3 =  new Date(System.currentTimeMillis());
                str_y = formatter_y.format(curDate_3);
                str_m= formatter_m.format(curDate_3);
                str_d= formatter_d.format(curDate_3);
                datePart.setYear(str_y);
                datePart.setMonth(str_m);
                datePart.setMonth(str_d);
                sum = sAdapter.getSumOfAllCategory( "day", datePart );
                textView_sum.setText(Double.valueOf(sum).toString());
                income = sAdapter.getIncomeCategory( "day", datePart ); //收入
                textView_income.setText(Double.valueOf(income).toString());
                balance=income-sum; //結餘
                textView_balance.setText(Double.valueOf(balance).toString());
        }
        String Ago = formatter2.format(calendar.getTime());
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(Ago);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
