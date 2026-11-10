package com.example.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.order.R;
import com.example.order.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class  PlaceOrderAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public PlaceOrderAdapter(List<Snack> snacks) {
        super(R.layout.item_place_order, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        baseViewHolder.setImageResource(R.id.placeOrderImage, snack.getImage())
                .setText(R.id.placeOrderName, snack.getName())
                .setText(R.id.placeOrderPrice, "￥" + snack.getPrice())
                .setText(R.id.orderCountBtn, String.valueOf(snack.getCount()));
    }
}
