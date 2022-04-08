package com.pjm0922;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt_c,txt_f,result_txt1,result_txt2;
    SeekBar skb_c,skb_f;
    RadioGroup rg;
    RadioButton rbt_cf,rbt_fc;
    Button ch_bt,rs_bt;

    float result_C;
    float result_F;
    double CC;
    double FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_c = findViewById(R.id.TXT_C);
        txt_f = findViewById(R.id.TXT_F);
        result_txt1 = findViewById(R.id.result_TXT1);
        result_txt2 = findViewById(R.id.result_TXT2);
        skb_c = findViewById(R.id.SKB_C);
        skb_f = findViewById(R.id.SKB_F);
        rg = findViewById(R.id.RG);
        rbt_cf = findViewById(R.id.RBT_CF);
        rbt_fc = findViewById(R.id.RBT_FC);
        ch_bt = findViewById(R.id.change_BT);
        rs_bt = findViewById(R.id.reset_BT);

        setTitle("Report 02");

        skb_c.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                result_C = (i - 100);
                txt_c.setText(String.format("섭씨 (%.1f ℃)",result_C));
                calculator(result_C, result_F);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        skb_f.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                result_F = (i - 100);
                txt_f.setText(String.format("화씨 (%.1f ℉)",result_F));
                calculator(result_C, result_F);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ch_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"선택해주세요",Toast.LENGTH_SHORT).show();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.RBT_CF:
                        ch_bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                result_txt1.setText(String.format("섭씨온도 : %.2f ℃",result_C));
                                result_txt2.setText(String.format("화씨온도 : %.2f ℉",FF));
                                skb_f.setVisibility(skb_f.INVISIBLE);
                            }
                        });
                        break;

                    case R.id.RBT_FC:
                        ch_bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                result_txt1.setText(String.format("화씨온도 : %.2f ℃",result_F));
                                result_txt2.setText(String.format("섭씨온도 : %.2f ℉",CC));
                                skb_c.setVisibility(skb_c.INVISIBLE);
                            }
                        });
                        break;
                }
            }
        });

        rs_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_c.setText("섭씨온도 (0.0 ℃)");
                txt_f.setText("화씨온도 (0.0 ℉)");
                rbt_cf.setChecked(false);
                rbt_fc.setChecked(false);
                result_txt1.setText("");
                result_txt2.setText("");
                skb_c.setProgress(100);
                skb_f.setProgress(100);
                skb_c.setVisibility(skb_c.VISIBLE);
                skb_f.setVisibility(skb_f.VISIBLE);
            }
        });
    }

    private void calculator(float C, float F) {
        CC = 0.5556 * (result_F - 32);
        FF = 32 + 1.8 * result_C;
    }
}