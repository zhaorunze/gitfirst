package zhaorunze.gittest.base;

        import android.os.Bundle;
        import android.support.annotation.Nullable;

/**
 * Created by zhaorunze on
 * 2018/3/5 13:10
 * E-Mail Address：1159963642@qq.com
 */

public abstract class MVPFragment<T extends CommonPresenter> extends BaseFragment {

    protected T presenter;
    public String TAG = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        presenter = createPresenter();
        if(presenter != null){
            presenter.attachView();
        }
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        if(presenter != null){
            presenter.detachView();
        }
        super.onDestroy();
    }
}
