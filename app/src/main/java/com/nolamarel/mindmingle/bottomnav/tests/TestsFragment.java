package com.nolamarel.mindmingle.bottomnav.tests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nolamarel.mindmingle.R;
import com.nolamarel.mindmingle.databinding.FragmentTestsBinding;
import com.nolamarel.mindmingle.tests.Test;
import com.nolamarel.mindmingle.tests.TestsAdapter;

import java.util.ArrayList;

public class TestsFragment extends Fragment {
    private FragmentTestsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        binding = FragmentTestsBinding.inflate(inflater, container, false);

        ArrayList<Test> tests = new ArrayList<>();
        tests.add(new Test(R.drawable.peace1, getString(R.string.t11tf), "", ""));
        tests.add(new Test(R.drawable.peace1, getString(R.string.t22tf), "", ""));
        tests.add(new Test(R.drawable.peace1, getString(R.string.t33tf), "", ""));
        tests.add(new Test(R.drawable.peace1, getString(R.string.t44tf), "", ""));


        binding.testsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.testsRv.setAdapter(new TestsAdapter(tests));

        TestsAdapter adapter = new TestsAdapter(tests);
        adapter.setOnItemClickListener(new TestsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch (position) {
                    case 0:
                        FirstTestFragment firstTestFragment = new FirstTestFragment();
                        ft.replace(R.id.fragment_contsiner, firstTestFragment);
                        break;
                    case 1:
                        SecondTestFragment secondTestFragment = new SecondTestFragment();
                        ft.replace(R.id.fragment_contsiner, secondTestFragment);
                        break;
                    case 2:
                        ThirdTestFragment thirdTestFragment = new ThirdTestFragment();
                        ft.replace(R.id.fragment_contsiner, thirdTestFragment);
                        break;
                    case 3:
                        FourthTestFragment fourthTestFragment = new FourthTestFragment();
                        ft.replace(R.id.fragment_contsiner, fourthTestFragment);
                        break;
                    // Добавьте дополнительные случаи для обработки кликов на других элементах списка, если необходимо
                }
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        binding.testsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.testsRv.setAdapter(adapter);


        return binding.getRoot();
    }

    private void loadTests(){
        ArrayList<Test> tests = new ArrayList<>();
    }
}