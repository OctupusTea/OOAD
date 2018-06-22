package com.github.OctupusTea.Accounting

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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static android.graphics.Color.*;

public class statisticsActivity extends AppCompatActivity {


    private static int[] COLORS = new int[] { Color.RED, Color.GREEN,
            Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.DKGRAY };
    double data[] = new double[] { 2000, 1000, 6000, 1280, 6000,2000,22000 };

    private CategorySeries mSeries = new CategorySeries("");// PieChart的DataSet
    // 其实就是一些键值对，跟Map使用方法差不多

    private DefaultRenderer mRenderer = new DefaultRenderer();// PieChart的主要描绘器

    private GraphicalView mChartView;// 用来显示PieChart 需要在配置文件Manifest中添加
    // <activity
    // android:name="org.achartengine.GraphicalActivity"
    // />

    private LinearLayout mLinear;

    int onetime=1;    // 第一次點箭頭
    int slt=3;    // 選擇年or月or日button
    int slt_tmp=0;    // 切換年月日button時，日期重新修改成系統日期
    Calendar calendar = Calendar.getInstance();

    public void token (int choice){    // 選擇年or月or日button
        SimpleDateFormat formatter;
        switch(choice){
            case 1:
                formatter = new SimpleDateFormat ("yyyy");
                break;
            case 2:
                formatter = new SimpleDateFormat ("yyyy/MM");
                break;
            default:
                formatter = new SimpleDateFormat ("yyyy/MM/dd");
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
        switch(slt_now){
            case 1:
                formatter2 = new SimpleDateFormat("yyyy");
                calendar.add(Calendar.YEAR, direction);
                break;
            case 2:
                formatter2 = new SimpleDateFormat("yyyy/MM");
                calendar.add(Calendar.MONTH, direction);
                break;
            default:
                formatter2 = new SimpleDateFormat("yyyy/MM/dd");
                calendar.add(Calendar.DATE, direction);
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

        //mRenderer.setZoomButtonsVisible(true);// 显示放大缩小功能按钮
        mRenderer.setShowLegend(false);//不显示底部说明
        mRenderer.setStartAngle(180);// 设置为水平开始
        mRenderer.setDisplayValues(true);// 显示数据
        mRenderer.setLabelsTextSize(40);//设置标签字体大小
        mRenderer.setLabelsColor(BLACK); //设置标签字体顏色

        mSeries.add("食", data[0]);
        mSeries.add("衣", data[1]);
        mSeries.add("住", data[2]);
        mSeries.add("行", data[3]);
        mSeries.add("育", data[4]);
        mSeries.add("樂", data[5]);
        mSeries.add("收入", data[6]);


        for (int i = 0; i < data.length; i++) {
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setChartValuesFormat(NumberFormat.getCurrencyInstance());// 设置百分比
            //mSeries.add("示例 " + (i + 1), data[i]);// 设置种类名称和对应的数值，前面是（key,value）键值对
            if (i < COLORS.length) {
                renderer.setColor(COLORS[i]);// 设置描绘器的颜色
            } else {
                renderer.setColor(getRandomColor());// 设置描绘器的颜色
            }
            mRenderer.setChartTitleTextSize(14);// 设置饼图标题大小
            mRenderer.addSeriesRenderer(renderer);// 将最新的描绘器添加到DefaultRenderer中
        }
        mChartView = ChartFactory.getPieChartView(getApplicationContext(), mSeries, mRenderer);// 构建mChartView
        mLinear.addView((View)mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        mRenderer.setClickEnabled(true);// 允许点击事件
        mChartView.setOnClickListener(new View.OnClickListener() {// 具体内容
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = mChartView
                        .getCurrentSeriesAndPoint();// 获取当前的类别和指针
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

    private int getRandomColor() {// 分别产生RBG数值
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        return Color.rgb(R, G, B);
    }


}