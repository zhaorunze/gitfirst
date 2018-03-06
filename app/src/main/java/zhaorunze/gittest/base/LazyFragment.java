package zhaorunze.gittest.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaorunze on
 * 2018/3/5 11:06
 * E-Mail Address：1159963642@qq.com
 */

public abstract class LazyFragment extends Fragment {

    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad;

    public View view;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        view = inflater.inflate(getContentId(), container, false);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @LayoutRes
    protected abstract int getContentId();

    protected void lazyLoad(){
        if(!isPrepared || !isVisible || !isFirstLoad){
            return;
        }
        isFirstLoad = false;
        unbinder = ButterKnife.bind(this, view);
        initUI();
    }

    protected abstract void initUI();

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * 与ViewPager一起使用 调用的是setUserVisibleHint
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged
     * 如果初始是show的Fragment为了触发该事件需要先hide再show
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){

    }
}
