package zhaorunze.gittest.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListUserAdapter;
import zhaorunze.gittest.entity.User;
import zhaorunze.gittest.utils.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etSex)
    EditText etSex;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.btAdd)
    Button btAdd;
    @BindView(R.id.btQuery)
    Button btQuery;
    @BindView(R.id.list)
    RecyclerView list;

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
    }

    @Override
    public void initAction() {
        btAdd.setOnClickListener(this);
        btQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
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
                break;
            case R.id.btQuery:
                List<User> users = AppContext.getInstance().getDaoSession().loadAll(User.class);
                mUserAdapter.setNewData(users);
                break;
            default:
                break;
        }
    }
}
