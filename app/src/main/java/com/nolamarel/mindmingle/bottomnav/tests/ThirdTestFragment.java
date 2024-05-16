package com.nolamarel.mindmingle.bottomnav.tests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nolamarel.mindmingle.R;

public class ThirdTestFragment extends Fragment{
    int score = 0;
    private RadioGroup gr1, gr2, gr3;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private Button btnDisplay, bn1, bn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_third_test, container, false);

        gr1 = view.findViewById(R.id.gr1);
        gr2 = view.findViewById(R.id.gr2);
        gr3 = view.findViewById(R.id.gr3);

        btnDisplay = view.findViewById(R.id.btnDisplay);
        bn1 = view.findViewById(R.id.bn1);
        bn2 = view.findViewById(R.id.bn2);

        TextView textView = view.findViewById(R.id.textView);

        gr1.setVisibility(View.VISIBLE);
        gr2.setVisibility(View.GONE);
        gr3.setVisibility(View.GONE);
        btnDisplay.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr1.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr1.getCheckedRadioButtonId();
                    radioButton1 = view.findViewById(selectedId1);
                    if (radioButton1.getText().toString().equals("Большинству людей можно доверять.")) {
                        score += 2;
                    }
                    if (radioButton1.getText().toString().equals("Во взаимодействии с другими нужно соблюдать осторожность.")) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.VISIBLE);
                    gr3.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr2.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr2.getCheckedRadioButtonId();
                    radioButton2 = view.findViewById(selectedId1);
                    if (radioButton2.getText().toString().equals("Стремятся быть полезными другим.")) {
                        score += 2;
                    }
                    if (radioButton2.getText().toString().equals("Думают только о себе.")) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.VISIBLE);
                    btnDisplay.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        if (gr3.getCheckedRadioButtonId() != -1) {
            int selectedId5 = gr3.getCheckedRadioButtonId();
            radioButton3 = view.findViewById(selectedId5);
            if (radioButton3.getText().toString().equals("Вели бы себя честно.")) {
                score += 2;
            }
            if (radioButton3.getText().toString().equals("Попытались бы обмануть, если бы им представилась такая возможность.")) {
                score += 0;
            }
        }
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gr1.setVisibility(View.GONE);
                gr2.setVisibility(View.GONE);
                gr3.setVisibility(View.GONE);
                btnDisplay.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);

                if (score == 6) {
                    textView.setText("У вас высокий показатель доверия");
                } else if (score == 4) {
                    textView.setText("У вас средний показатель доверия");
                } else if (score == 2) {
                    textView.setText("У вас низкий показатель доверия");
                } else if (score == 0) {
                    textView.setText("У вас низкий показатель доверия");
                }
            }
        });
        return view;
    }
}
