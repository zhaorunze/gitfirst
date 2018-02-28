package zhaorunze.gittest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.utils.ToastUtils;
import zhaorunze.gittest.widgets.LoadingDialog;

/**
 * Created by zhaorunze on
 * 2018/2/23 15:21
 * E-Mail Address：1159963642@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContent());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
        initAction();
    }

    public abstract int getContent();

    public void initData() {
    }

    public void initView() {
    }

    public void initAction() {
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private LoadingDialog mLoadingDialog;

    @Override
    public void showLoadingDialog() {
        if(mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        if(!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if(mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    protected void showToast(String msg){
        ToastUtils.toastShort(this, msg);
    }
}
