package zhaorunze.gittest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import zhaorunze.gittest.ui.activity.BaseActivity;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:57
 * E-Mail Address：1159963642@qq.com
 */

public abstract class MVPActivity<T extends CommonPresenter> extends BaseActivity{

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.attachView();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    public abstract T createPresenter();
}
