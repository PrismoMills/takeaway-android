package com.example.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.MyApplication;
import com.example.order.R;
import com.example.order.adapter.OrderAdapter;
import com.example.order.dao.OrderDao;
import com.example.order.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.orderRecyclerView)
    RecyclerView orderRecyclerView;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Orders");

        ButterKnife.bind(this);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        initAdapter();
    }


    private void initAdapter() {

        List<Order> orders = OrderDao.findAllByUsername(MyApplication.getUser().getUsername());

        OrderAdapter adapter = new OrderAdapter(orders);


        adapter.setEmptyView(getEmptyView());

        orderRecyclerView.setAdapter(adapter);
    }

    public View getEmptyView() {
        return getLayoutInflater().inflate(R.layout.empty_order_view, orderRecyclerView, false);
    }
}