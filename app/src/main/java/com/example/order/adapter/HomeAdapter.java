package com.example.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.order.R;
import com.example.order.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public HomeAdapter(List<Snack> snacks) {
        super(R.layout.item_home_snack, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        baseViewHolder.setImageResource(R.id.homeSnackImage, snack.getImage());
        baseViewHolder.setText(R.id.homeSnackName, snack.getName());
        baseViewHolder.setText(R.id.homeSnackPrice, String.format(java.util.Locale.ENGLISH, "¥%.2f", snack.getPrice()));
    }
}
