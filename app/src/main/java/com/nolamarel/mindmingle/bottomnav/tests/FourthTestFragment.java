package com.nolamarel.mindmingle.bottomnav.tests;

import static com.nolamarel.mindmingle.R.string.*;

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

public class FourthTestFragment extends Fragment {
    int score = 0;
    private RadioGroup rg1, rg2, rg3, rg4, rg5;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private Button btnDisplay, btnNext1, btnNext2, btnNext3, btnNext4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_fourth_test, container, false);

        rg1 = view.findViewById(R.id.rg1);
        rg2 = view.findViewById(R.id.rg2);
        rg3 = view.findViewById(R.id.rg3);
        rg4 = view.findViewById(R.id.rg4);
        rg5 = view.findViewById(R.id.rg5);

        btnDisplay = view.findViewById(R.id.btnDisplay);
        btnNext1 = view.findViewById(R.id.btnNext1);
        btnNext2 = view.findViewById(R.id.btnNext2);
        btnNext3 = view.findViewById(R.id.btnNext3);
        btnNext4 = view.findViewById(R.id.btnNext4);

        TextView textView = view.findViewById(R.id.textView);

        rg1.setVisibility(View.VISIBLE);
        rg2.setVisibility(View.GONE);
        rg3.setVisibility(View.GONE);
        rg4.setVisibility(View.GONE);
        rg5.setVisibility(View.GONE);
        btnDisplay.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg1.getCheckedRadioButtonId() != -1) {
                    int selectedId1 = rg1.getCheckedRadioButtonId();
                    radioButton1 = view.findViewById(selectedId1);
                    if (radioButton1.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton1.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    rg1.setVisibility(View.GONE);
                    rg2.setVisibility(View.VISIBLE);
                    rg3.setVisibility(View.GONE);
                    rg4.setVisibility(View.GONE);
                    rg5.setVisibility(View.GONE);
                    btnDisplay.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }
        });
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg2.getCheckedRadioButtonId() != -1) {
                    int selectedId2 = rg2.getCheckedRadioButtonId();
                    radioButton2 = view.findViewById(selectedId2);
                    if (radioButton2.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton2.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    btnNext2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rg1.setVisibility(View.GONE);
                            rg2.setVisibility(View.GONE);
                            rg3.setVisibility(View.VISIBLE);
                            rg4.setVisibility(View.GONE);
                            rg5.setVisibility(View.GONE);
                            btnDisplay.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg3.getCheckedRadioButtonId() != -1) {
                    int selectedId3 = rg3.getCheckedRadioButtonId();
                    radioButton3 = view.findViewById(selectedId3);
                    if (radioButton3.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton3.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    btnNext3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rg1.setVisibility(View.GONE);
                            rg2.setVisibility(View.GONE);
                            rg3.setVisibility(View.GONE);
                            rg4.setVisibility(View.VISIBLE);
                            rg5.setVisibility(View.GONE);
                            btnDisplay.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg4.getCheckedRadioButtonId() != -1) {
                    int selectedId4 = rg4.getCheckedRadioButtonId();
                    radioButton4 = view.findViewById(selectedId4);
                    if (radioButton4.getText().toString().equals(R.string.ay)) {
                        score += 1;
                    }
                    if (radioButton4.getText().toString().equals(R.string.an)) {
                        score += 0;
                    }
                    btnNext4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rg1.setVisibility(View.GONE);
                            rg2.setVisibility(View.GONE);
                            rg3.setVisibility(View.GONE);
                            rg4.setVisibility(View.GONE);
                            rg5.setVisibility(View.VISIBLE);
                            btnDisplay.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        if (rg5.getCheckedRadioButtonId() != -1) {
            int selectedId5 = rg5.getCheckedRadioButtonId();
            radioButton5 = view.findViewById(selectedId5);
            if (radioButton5.getText().toString().equals(R.string.ay)) {
                score += 1;
            }
            if (radioButton5.getText().toString().equals(R.string.an)) {
                score += 0;
            }
        }
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg1.setVisibility(View.GONE);
                rg2.setVisibility(View.GONE);
                rg3.setVisibility(View.GONE);
                rg4.setVisibility(View.GONE);
                rg5.setVisibility(View.GONE);
                btnDisplay.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);

                if (score >= 4) {
                    textView.setText(R.string.r1t4);
                } else if (score < 4 && score >= 2) {
                    textView.setText(R.string.r2t4);
                } else if (score < 2 && score >= 0) {
                    textView.setText(R.string.r3t4);
                }
            }
        });
        return view;
    }
}