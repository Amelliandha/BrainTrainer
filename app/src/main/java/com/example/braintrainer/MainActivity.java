package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button Btn_Mulai;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button mulailagi;
    TextView soal;
    TextView TV_Hasil;
    TextView TV_Point;
    TextView Timer;
    ConstraintLayout layer2;

    ArrayList<Integer>jawaban = new ArrayList<Integer>();
    int JawabanBenar;
    int Point = 0;
    int jumlahSoal = 0;

    public void mulaiLagi(View view){
        Point =0;
        jumlahSoal =0;

        Timer.setText("10s");
        TV_Point.setText("0/0");
        TV_Hasil.setText("");
        mulailagi.setVisibility(View.INVISIBLE);
        generatorPertanyaan();

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                Timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                mulailagi.setVisibility(View.VISIBLE);
                Timer.setText("0s");
                TV_Hasil.setText("Point Kamu: "+Integer.toString(Point)+"/"+ Integer.toString(jumlahSoal));
            }
        }.start();
    }

    public void mulai(View view){
        Btn_Mulai.setVisibility(View.INVISIBLE);
        layer2.setVisibility(ConstraintLayout.VISIBLE);

        mulaiLagi(findViewById(R.id.mulaiLagi));
    }

    public void generatorPertanyaan(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        soal.setText(Integer.toString(a)+" + "+Integer.toString(b));
        JawabanBenar = rand.nextInt(4);

        jawaban.clear();

        int JawabanSalah;

        for(int  i=0; i<4; i++){
            if (i==JawabanBenar){
                jawaban.add(a + b);
            }else {
                JawabanSalah = rand.nextInt(41);
                while (JawabanSalah == a + b){
                    JawabanSalah = rand.nextInt(41);
                }
                jawaban.add(JawabanSalah);
            }
        }
        button1.setText(Integer.toString(jawaban.get(0)));
        button2.setText(Integer.toString(jawaban.get(1)));
        button3.setText(Integer.toString(jawaban.get(2)));
        button4.setText(Integer.toString(jawaban.get(3)));
    }

    public void Jawab(View view){
        if (view.getTag().toString().equals(Integer.toString(JawabanBenar))){
            Point++;
            TV_Hasil.setText("Benar!!");
        }else {
            TV_Hasil.setText("Salah!!");
        }
        jumlahSoal++;
        TV_Point.setText(Integer.toString(Point)+"/"+ Integer.toString(jumlahSoal));
        generatorPertanyaan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_Mulai = (Button) findViewById(R.id.Btn_Mulai);
        soal = (TextView)findViewById(R.id.Soal);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        TV_Hasil = (TextView)findViewById(R.id.TV_Hasil);
        TV_Point = (TextView)findViewById(R.id.TV_Point);
        Timer = (TextView)findViewById(R.id.Timer);
        mulailagi = (Button)findViewById(R.id.mulaiLagi);
        layer2 = (ConstraintLayout)findViewById(R.id.Layer2);

    }
}