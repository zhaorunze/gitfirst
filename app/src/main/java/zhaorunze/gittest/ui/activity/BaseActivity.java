package zhaorunze.gittest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by zhaorunze on
 * 2018/2/23 15:21
 * E-Mail Address：1159963642@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContent());
        ButterKnife.bind(this);
        initData();
        initView();
        initAction();
    }

    public abstract int getContent();

    public void initData() {
    }

    public void initView() {
    }

    public void initAction() {
    }

}
