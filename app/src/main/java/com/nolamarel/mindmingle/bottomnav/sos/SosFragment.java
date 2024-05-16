package com.nolamarel.mindmingle.bottomnav.sos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.nolamarel.mindmingle.R;
import com.nolamarel.mindmingle.databinding.FragmentSosBinding;

public class SosFragment extends Fragment {
    private FragmentSosBinding binding;
    private Animation breathingAnimation;
    private boolean isButtonClicked = false;
    private CountDownTimer timer;
    private int state = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSosBinding.inflate(inflater, container, false);


        ObjectAnimator scaleX = ObjectAnimator.ofFloat(binding.breathingButton, "scaleX", 1f, 1.7f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(binding.breathingButton, "scaleY", 1f, 1.7f);
        scaleX.setDuration(4000);
        scaleY.setDuration(4000);

        AnimatorSet set1 = new AnimatorSet();
        set1.play(scaleX).with(scaleY);


        ObjectAnimator backX = ObjectAnimator.ofFloat(binding.breathingButton, "scaleX", 1.7f, 1f);
        ObjectAnimator backY = ObjectAnimator.ofFloat(binding.breathingButton, "scaleY", 1.7f, 1f);
        backX.setDuration(4000);
        backY.setDuration(4000);

        AnimatorSet set2 = new AnimatorSet();
        set2.play(backX).with(backY);


        timer = new CountDownTimer(4000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch (state) {
                    case 0:
                        binding.breathingButton.setText(R.string.inhale);
                        break;
                    case 1:
                        binding.breathingButton.setText(R.string.pause);
                        break;
                    case 2:
                        binding.breathingButton.setText(R.string.exhale);
                        break;
                    case 3:
                        binding.breathingButton.setText(R.string.pause);
                        break;
                }
            }

            @Override
            public void onFinish() {
                state = (state + 1) % 4;
                timer.start();
            }
        };

        binding.breathingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        binding.breathingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set1.cancel();
                timer.cancel();
                timer.start();
                state = 0;
                binding.breathingButton.setText("Вдох");
                set1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                set2.start();
                            }
                        }, 4000);
                    }
                });

                set2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                set1.start();
                            }
                        }, 4000);
                    }
                });

                set1.start();
            }
        });

        return binding.getRoot();
    }

}