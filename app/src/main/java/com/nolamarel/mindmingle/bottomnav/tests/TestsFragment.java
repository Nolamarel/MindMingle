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
        tests.add(new Test(R.drawable.peace1, "Тест1", "Диагностика манипулятивного отношения (по шкале Банта)", ""));
        tests.add(new Test(R.drawable.peace1, "Тест2", "Немного описания теста", ""));
        tests.add(new Test(R.drawable.peace1, "Тест3", "Немного описания теста", ""));
        tests.add(new Test(R.drawable.peace1, "Тест4", "Немного описания теста", ""));
        tests.add(new Test(R.drawable.peace1, "Тест5", "Немного описания теста", ""));
        tests.add(new Test(R.drawable.peace1, "Тест5", "Немного описания теста", ""));

        binding.testsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.testsRv.setAdapter(new TestsAdapter(tests));

        TestsAdapter adapter = new TestsAdapter(tests);
        adapter.setOnItemClickListener(new TestsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0){
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    FirstTestFragment firstTestFragment = new FirstTestFragment();
                    ft.replace(R.id.fragment_contsiner, firstTestFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
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