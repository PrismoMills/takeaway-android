package com.example.order.ui.place;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.order.MyApplication;
import com.example.order.R;
import com.example.order.activity.DetailActivity;
import com.example.order.adapter.PlaceOrderAdapter;
import com.example.order.dao.OrderDao;
import com.example.order.model.Order;
import com.example.order.model.Snack;
import com.example.order.utils.Tips;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceFragment extends Fragment {

    private PlaceViewModel placeViewModel;

    @BindView(R.id.placeRecyclerView)
    RecyclerView orderRecyclerView;

    @BindView(R.id.placeBuyBtn)
    Button buyButton;

    @BindView(R.id.placeMoney)
    TextView placeMoney;

    PlaceOrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        placeViewModel =
                ViewModelProviders.of(this).get(PlaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_place, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initOrderAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (orderAdapter != null) orderAdapter.notifyDataSetChanged();
        calcTotalMoney();
    }

    /**
     * 初始化购物车列表适配器
     */
    private void initOrderAdapter() {
        // 实例化购物车列表适配器对象
        orderAdapter = new PlaceOrderAdapter(MyApplication.getCartSnacks());

        // 设置空布局
        orderAdapter.setEmptyView(getEmptyView());

        // 设置动画效果
        orderAdapter.setAnimationEnable(true);
//        orderAdapter.setAnimationFirstOnly(false);
        orderAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);

        // 点击item事件触发
        orderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Snack snack = (Snack) adapter.getItem(position);
                DetailActivity.actionStart(getContext(), snack);
            }
        });

        // 注册item内子控件id
        orderAdapter.addChildClickViewIds(R.id.orderLessLabel, R.id.orderAddLabel);
        // 子控件点击监听
        orderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Snack snack = (Snack) adapter.getItem(position);
            MyApplication.changeCartQuantity(snack, view.getId() == R.id.orderAddLabel ? 1 : -1);
            adapter.notifyDataSetChanged();
            calcTotalMoney();
        });

        // 设置适配器
        orderRecyclerView.setAdapter(orderAdapter);
    }

    /**
     * 点击下单按钮事件触发器
     */
    @OnClick(R.id.placeBuyBtn)
    void initClick() {
        if (MyApplication.getCartSnacks().isEmpty()) {
            Tips.show("Your cart is empty");
        } else {
            if (MyApplication.isLogin()) {
                // 显示Dialog
                showDialog();
            } else {
                Tips.show("Please sign in first");
            }
        }
    }

    /**
     * 显示下单备注提示框
     */
    @SuppressLint("InflateParams")
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View notesView = inflater.inflate(R.layout.dialog_view, null);
        android.widget.EditText notes = notesView.findViewById(R.id.editTextTextPersonName);
        builder.setView(notesView)
                .setNegativeButton("Cancel", null)
                .setTitle("Order notes")
                .setPositiveButton("Place order", (dialog, which) -> {
                    // 持久化订单数据
                    try {
                        saveOrder(notes.getText().toString().trim());
                    } catch (android.database.sqlite.SQLiteException exception) {
                        Tips.show("Could not save the order. Your cart was kept.");
                        return;
                    }

                    // 清空购物车数据
                    MyApplication.getCartSnacks().clear();
                    // 通知适配器数据变化
                    orderAdapter.notifyDataSetChanged();
                    // 刷新总金额
                    calcTotalMoney();

                    Tips.show("Order placed");
                })
                .create()
                .show();
    }

    /**
     * 持久化订单数据
     */
    private void saveOrder(String notes) {
        String batch = java.util.UUID.randomUUID().toString();
        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH)
                .format(new java.util.Date());
        List<Order> orders = new ArrayList<>();
        // 购物车数据产生订单
        for (Snack snack : MyApplication.getCartSnacks()) {
            Order order = new Order(snack);
            order.setBatchId(batch);
            order.setTime(time);
            order.setNotes(notes);
            order.setUsername(MyApplication.getUser().getUsername());
            orders.add(order);
        }

        OrderDao.saveOrder(orders);
    }

    /**
     * 点击垃圾桶事件触发器
     */
    @OnClick(R.id.deleteOrder)
    void deleteOrder() {
        if (MyApplication.getCartSnacks().isEmpty()) {
            Tips.show("Your cart is empty");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirm")
                    .setMessage("Clear your cart?")
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        // 清空购物车数据
                        MyApplication.getCartSnacks().clear();
                        // 通知适配器数据变化
                        orderAdapter.notifyDataSetChanged();
                        // 刷新总金额
                        calcTotalMoney();

                        Tips.show("Cart cleared");
                    })
                    .create()
                    .show();
        }
    }

    // 计算合计金额
    @SuppressLint("SetTextI18n")
    private void calcTotalMoney() {
        BigDecimal totalMoney = BigDecimal.valueOf(0);

        // 遍历计算总金额（解决舍入误差）
        if (!MyApplication.getCartSnacks().isEmpty()) {
            for (Snack snack : MyApplication.getCartSnacks()) {
                // 小吃单价 × 小吃数量
                BigDecimal tmp = BigDecimal.valueOf(snack.getPrice()).multiply(BigDecimal.valueOf(snack.getCount()));
                totalMoney = totalMoney.add(tmp);
            }
        }

        placeMoney.setText("￥" + totalMoney.doubleValue());
    }

    /**
     * 下单页面购物车空布局
     */
    private View getEmptyView() {
        return getLayoutInflater().inflate(R.layout.empty_cart_view, orderRecyclerView, false);
    }
}
