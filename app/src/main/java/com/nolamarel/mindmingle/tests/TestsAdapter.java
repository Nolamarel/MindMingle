package com.nolamarel.mindmingle.tests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nolamarel.mindmingle.R;

import java.util.ArrayList;

public class TestsAdapter extends RecyclerView.Adapter<TestsViewHolder> {
    private ArrayList<Test> tests = new ArrayList<>();
    private OnItemClickListener listener;

    public TestsAdapter(ArrayList<Test> tests) {
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tests_item, parent, false);
        return new TestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestsViewHolder holder, int position) {
        Test test = tests.get(position);

        holder.test_title.setText(test.testTitle);
        holder.test_subhead.setText(test.testSubhead);
        holder.test_paragraph.setText(test.testParagraph);
        holder.test_iv.setImageResource(test.testIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
