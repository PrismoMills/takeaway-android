package com.example.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.order.R;
import com.example.order.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SnackRightAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public SnackRightAdapter(List<Snack> snacks) {
        super(R.layout.item_snack_right, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        int quantity = com.example.order.MyApplication.cartQuantity(snack);
        baseViewHolder.setText(R.id.snackRightCount, String.valueOf(quantity));
        baseViewHolder.getView(R.id.snackRightLessBtn).setEnabled(quantity > 0);
        baseViewHolder.setImageResource(R.id.snackRightImage, snack.getImage())
                .setText(R.id.snackRightName, snack.getName())
                .setText(R.id.snackRightPrice, "￥" + snack.getPrice());
    }
}
