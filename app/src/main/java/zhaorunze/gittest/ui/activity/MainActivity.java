package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListUserAdapter;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.entity.User;
import zhaorunze.gittest.utils.ToastUtils;
import zhaorunze.gittest.widgets.CircleImageView;

public class MainActivity extends BaseActivity{
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

    @OnClick(R.id.btAdd)
    void addUserClick(View view){
        String name = etName.getText().toString().trim();
        String sex = etSex.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        if("".equals(name)){
            ToastUtils.toastShort(this, "请输入用户名");
            return;
        }
        if("".equals(sex)){
            ToastUtils.toastShort(this, "请输入性别");
            return;
        }
        if("".equals(age)){
            ToastUtils.toastShort(this, "请输入年龄");
            return;
        }
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        AppContext.getInstance().getDaoSession().getUserDao().insert(user);
    }

    @OnClick(R.id.btQuery)
    void queryUserClick(View view){
        List<User> users = AppContext.getInstance().getDaoSession().loadAll(User.class);
        mUserAdapter.setNewData(users);
    }

    @OnClick(R.id.btNext)
    void nextClick(View view){
        Intent intent = new Intent(this, NextActivity.class);
        startActivity(intent);
    }
}
