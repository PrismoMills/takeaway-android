package com.example.order.ui.my;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.order.MyApplication;
import com.example.order.R;
import com.example.order.activity.LoginActivity;
import com.example.order.activity.OrderActivity;
import com.example.order.dao.UserDao;
import com.example.order.model.User;
import com.example.order.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {

    private MyViewModel myViewModel;
    private static final int[] PREFERENCE_FIELDS = {
            R.id.checkBox, R.id.checkBox2, R.id.checkBox4, R.id.checkBox5, R.id.checkBox6,
            R.id.radioButton, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4, R.id.radioButton5
    };

    @BindView(R.id.logoutBtn) TextView sessionButton;
    @BindView(R.id.editTextTextPersonName2) android.widget.EditText phone;
    @BindView(R.id.editTextTextPersonName3) android.widget.EditText address;

    private String profileKey(String field) {
        return MyApplication.getUser().getUsername().length() + ":" +
                MyApplication.getUser().getUsername() + ":" + field;
    }

    private android.widget.CompoundButton preference(int id) {
        return generalView.findViewById(id);
    }

    private String preferenceKey(int id) {
        return profileKey(getResources().getResourceEntryName(id));
    }

    @BindView(R.id.myUserHead)
    CircleImageView image;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.myUserNickName)
    TextView nickname;

    @BindView(R.id.myUserName)
    TextView username;

    @BindView(R.id.myModifyView)
    LinearLayout modifyView;

    @BindView(R.id.myGeneralView)
    LinearLayout generalView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myViewModel =
                ViewModelProviders.of(this).get(MyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my, container, false);

        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        boolean signedIn = MyApplication.isLogin();
        sessionButton.setText(signedIn ? "Sign out" : "Sign in");
        if (signedIn) {
            User user = MyApplication.getUser();
            nickname.setText("Your account");
            username.setText("Username: " + user.getUsername());
            android.content.SharedPreferences profile = UserDao.profile();
            phone.setText(profile.getString(profileKey("phone"), ""));
            address.setText(profile.getString(profileKey("address"), ""));
            for (int id : PREFERENCE_FIELDS) preference(id).setChecked(false);
            for (int id : PREFERENCE_FIELDS) {
                if (profile.getBoolean(preferenceKey(id), false)) preference(id).setChecked(true);
            }
        } else {
            nickname.setText("Sign in to your account");
            username.setText("");
            phone.setText("");
            address.setText("");
            for (int id : PREFERENCE_FIELDS) preference(id).setChecked(false);
            modifyView.setVisibility(View.GONE);
            generalView.setVisibility(View.GONE);
        }
    }

    //点击头像
    @OnClick(R.id.myUserHead)
    void clickImage() {
        if (MyApplication.isLogin()) {
            Tips.show("Already signed in");
        } else {
            LoginActivity.actionStart(getActivity());
        }
    }

    @OnClick(R.id.constraintLayout)
    void clickcslayout() {
        if (MyApplication.isLogin()) {
            Tips.show("Already signed in");
        } else {
            LoginActivity.actionStart(getActivity());
        }
    }

    //我的订单
    @OnClick(R.id.myOrderView)
    void clickOrder() {
        if (MyApplication.isLogin()) {
            OrderActivity.actionStart(getContext());
        } else {
            Tips.show("Please sign in first");
        }
    }

    @OnClick(R.id.myModifyText)
    void clickShowModify() {
        if (!MyApplication.isLogin()) {
            LoginActivity.actionStart(requireActivity());
            return;
        }
        if (modifyView.getVisibility() == View.GONE) {
            modifyView.setVisibility(View.VISIBLE);
        } else {
            modifyView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.myGeneralText)
    void clickShowGeneral() {
        if (!MyApplication.isLogin()) {
            LoginActivity.actionStart(requireActivity());
            return;
        }
        if (generalView.getVisibility() == View.GONE) {
            generalView.setVisibility(View.VISIBLE);
        } else {
            generalView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.myModifyBtn)
    void clickModifySubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm")
                .setMessage("Save this address?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDao.profile().edit()
                                .putString(profileKey("phone"), phone.getText().toString().trim())
                                .putString(profileKey("address"), address.getText().toString().trim())
                                .apply();
                        modifyView.setVisibility(View.GONE);
                        Tips.show("Address saved");
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.myGeneralBtn)
    void clickGeneralSubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm")
                .setMessage("Save your preferences?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.content.SharedPreferences.Editor editor = UserDao.profile().edit();
                        for (int id : PREFERENCE_FIELDS) {
                            editor.putBoolean(preferenceKey(id), preference(id).isChecked());
                        }
                        editor.apply();
                        generalView.setVisibility(View.GONE);
                        Tips.show("Preferences saved");
                    }
                })
                .create()
                .show();
    }

    /**
     * 点击退出登录
     */
    @OnClick({R.id.pendingPaymentView, R.id.pendingReviewView, R.id.refundsView,
            R.id.paymentSettingsView})
    void showUnavailableFeature() {
        Tips.show("This feature is not available in this version");
    }

    @OnClick(R.id.logoutBtn)
    void clickLogout() {
        if (MyApplication.isLogin()) {
            // 清除持久化数据
            UserDao.removeUserAndLoginStatus();
            // 清除全局数据
            MyApplication.isLogin(false);
            MyApplication.setUser(null);
            MyApplication.getCartSnacks().clear();
            initView();
        } else {
            LoginActivity.actionStart(requireActivity());
        }
    }
}