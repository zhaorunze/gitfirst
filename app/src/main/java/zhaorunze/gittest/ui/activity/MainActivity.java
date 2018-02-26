package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListUserAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.MainActivityContract;
import zhaorunze.gittest.entity.User;
import zhaorunze.gittest.presenters.MainActivityPresenter;
import zhaorunze.gittest.utils.ToastUtils;
import zhaorunze.gittest.widgets.CircleImageView;

public class MainActivity extends MVPActivity<MainActivityPresenter> implements MainActivityContract.View{
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
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        list.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AppContext.getInstance().getDaoSession().delete(adapter.getData().get(position));
                adapter.getData().remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        Glide.with(this).load(Constant.PIC_URL).into(imgCircle);
    }

    @Override
    public void insertUserFailure(String msg) {
        ToastUtils.toastShort(this, msg);
    }

    @Override
    public void insertUserSuccess(User user) {

    }

    @OnClick(R.id.btAdd)
    void addUserClick(View view){
        mPresenter.insertUser(etName.getText().toString().trim(), etSex.getText().toString().trim(),etAge.getText().toString().trim());
    }

    @OnClick(R.id.btQuery)
    void queryUserClick(View view){
        mUserAdapter.setNewData(mPresenter.loadAllUser());
    }

    @OnClick(R.id.btNext)
    void nextClick(View view){
        Intent intent = new Intent(this, NextActivity.class);
        startActivity(intent);
    }
}
