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

public class SecondTestFragment extends Fragment{
    int score = 0;
    private RadioGroup gr1, gr2, gr3, gr4, gr5, gr6, gr7, gr8;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8;
    private Button btnDisplay, bn1, bn2, bn3, bn4, bn5, bn6, bn7 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_second_test, container, false);

        gr1 = view.findViewById(R.id.gr1);
        gr2 = view.findViewById(R.id.gr2);
        gr3 = view.findViewById(R.id.gr3);
        gr4 = view.findViewById(R.id.gr4);
        gr5 = view.findViewById(R.id.gr5);
        gr6 = view.findViewById(R.id.gr6);
        gr7 = view.findViewById(R.id.gr7);
        gr8 = view.findViewById(R.id.gr8);

        btnDisplay = view.findViewById(R.id.btnDisplay);
        bn1 = view.findViewById(R.id.bn1);
        bn2 = view.findViewById(R.id.bn2);
        bn3 = view.findViewById(R.id.bn3);
        bn4 = view.findViewById(R.id.bn4);
        bn5 = view.findViewById(R.id.bn5);
        bn6 = view.findViewById(R.id.bn6);
        bn7 = view.findViewById(R.id.bn7);

        TextView textView = view.findViewById(R.id.textView);

        gr1.setVisibility(View.VISIBLE);
        gr2.setVisibility(View.GONE);
        gr3.setVisibility(View.GONE);
        gr4.setVisibility(View.GONE);
        gr5.setVisibility(View.GONE);
        gr6.setVisibility(View.GONE);
        gr7.setVisibility(View.GONE);
        gr8.setVisibility(View.GONE);
        btnDisplay.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr1.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr1.getCheckedRadioButtonId();
                    radioButton1 = view.findViewById(selectedId1);
                    if (radioButton1.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton1.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.VISIBLE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.GONE);
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
                    if (radioButton2.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton2.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.VISIBLE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr3.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr3.getCheckedRadioButtonId();
                    radioButton3 = view.findViewById(selectedId1);
                    if (radioButton3.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton3.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.VISIBLE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr4.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr4.getCheckedRadioButtonId();
                    radioButton4 = view.findViewById(selectedId1);
                    if (radioButton4.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton4.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.VISIBLE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr5.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr5.getCheckedRadioButtonId();
                    radioButton5 = view.findViewById(selectedId1);
                    if (radioButton5.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton5.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.VISIBLE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr6.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr6.getCheckedRadioButtonId();
                    radioButton6 = view.findViewById(selectedId1);
                    if (radioButton6.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton6.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.VISIBLE);
                    gr8.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        bn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gr7.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = gr7.getCheckedRadioButtonId();
                    radioButton7 = view.findViewById(selectedId1);
                    if (radioButton7.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton7.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    gr1.setVisibility(View.GONE);
                    gr2.setVisibility(View.GONE);
                    gr3.setVisibility(View.GONE);
                    gr4.setVisibility(View.GONE);
                    gr5.setVisibility(View.GONE);
                    gr6.setVisibility(View.GONE);
                    gr7.setVisibility(View.GONE);
                    gr8.setVisibility(View.VISIBLE);
                    btnDisplay.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                }
            }
        });

        if (gr8.getCheckedRadioButtonId() != -1) {
            int selectedId5 = gr8.getCheckedRadioButtonId();
            radioButton8 = view.findViewById(selectedId5);
            if (radioButton8.getText().toString().equals(R.string.ay)) {
                score += 1;
            }
            if (radioButton8.getText().toString().equals(R.string.an)) {
                score += 0;
            }
        }
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gr1.setVisibility(View.GONE);
                gr2.setVisibility(View.GONE);
                gr3.setVisibility(View.GONE);
                gr4.setVisibility(View.GONE);
                gr5.setVisibility(View.GONE);
                gr6.setVisibility(View.GONE);
                gr7.setVisibility(View.GONE);
                gr8.setVisibility(View.GONE);
                btnDisplay.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);

                if (score >= 7) {
                    textView.setText(R.string.r1t2);
                } else if (score < 7 && score >= 5) {
                    textView.setText(R.string.r2t2);
                } else if (score < 5 && score >= 3) {
                    textView.setText(R.string.r3t2);
                } else if (score < 3) {
                    textView.setText(R.string.r4t2);
                }
            }
        });
        return view;
    }
}