package com.nolamarel.mindmingle.tests;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nolamarel.mindmingle.R;

public class TestsViewHolder extends RecyclerView.ViewHolder {
    ImageView test_iv;
    TextView test_title, test_subhead, test_paragraph;
    public TestsViewHolder(@NonNull View itemView) {
        super(itemView);

        test_iv = itemView.findViewById(R.id.test_iv);
        test_title = itemView.findViewById(R.id.test_title);
        test_subhead = itemView.findViewById(R.id.test_subhead);
        test_paragraph = itemView.findViewById(R.id.test_paragraph);
    }
}
