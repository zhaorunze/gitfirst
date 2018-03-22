package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListUserAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.contracts.MainActivityContract;
import zhaorunze.gittest.entity.User;
import zhaorunze.gittest.presenters.MainActivityPresenter;
import zhaorunze.gittest.utils.ToastUtils;
import zhaorunze.gittest.widgets.CircleImageView;

public class MainActivity extends MVPActivity<MainActivityPresenter> implements MainActivityContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etSex)
    EditText etSex;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.imgCircle)
    CircleImageView imgCircle;
    @BindView(R.id.ivwebp)
    ImageView ivwebp;
    ListUserAdapter mUserAdapter;

    @Override
    public int getContent() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    public void initView() {
        mUserAdapter = new ListUserAdapter(null);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AppContext.getInstance().getDaoSession().delete(adapter.getData().get(position));
                adapter.getData().remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        Glide.with(this).load(R.drawable.testwebp).into(ivwebp);
        Log.d(TAG, "initView: ================");
        Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "initView: ================" + Thread.currentThread().getName());
                        Glide.with(MainActivity.this).load("https://pic.zhaoxi.net/images/mycache/2018030610380245511.webp?506172018-03-06%2010:38:03").into(imgCircle);
                    }
                });
    }

    @Override
    public void insertUserFailure(String msg) {
        ToastUtils.toastShort(this, msg);
    }

    @Override
    public void insertUserSuccess(User user) {

    }

    int index = 0;
    @OnClick(R.id.ivwebp)
    void testivwebp(View view){
        List<User> userData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            index ++;
            User userItem = new User();
            userItem.setName("userData:" + index);
            userData.add(userItem);
        }

        List<User> homeData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            index ++;
            User userItem = new User();
            userItem.setName("homeData:" + index);
            homeData.add(userItem);
        }
        mPresenter.insertUseres(userData, homeData);
    }

    @Override
    public void insertUseresFailure(User user) {
        Log.d(TAG, "insertUseresFailure: ===" + user);
    }

    @Override
    public void insertUseresSuccess(User user) {
        Log.d(TAG, "insertUseresSuccess: ===" + user);
    }

    @OnClick(R.id.btAdd)
    void addUserClick(View view) {
        mPresenter.insertUser(etName.getText().toString().trim(), etSex.getText().toString().trim(), etAge.getText().toString().trim());
    }

    @OnClick(R.id.btQuery)
    void queryUserClick(View view) {
        mUserAdapter.setNewData(mPresenter.loadAllUser());
    }

    @OnClick(R.id.btNext)
    void nextClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);
        startActivity(intent);
    }
}
