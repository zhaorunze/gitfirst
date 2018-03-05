package zhaorunze.gittest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import rx.subscriptions.CompositeSubscription;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.ui.activity.BaseActivity;

/**
 * Created by zhaorunze on
 * 2018/3/5 11:34
 * E-Mail Address：1159963642@qq.com
 */

public abstract class BaseFragment extends LazyFragment implements BaseView{

    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        if(mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
        super.onDestroy();
    }

    @Override
    protected void initUI() {
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
    }

    private void initData() {
    }

    protected void initView(){};

    @Override
    public void showLoadingDialog() {
        ((BaseActivity)getActivity()).showLoadingDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        ((BaseActivity)getActivity()).dismissLoadingDialog();
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    protected void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
