package zhaorunze.gittest.ui.activity;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import zhaorunze.gittest.R;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.contracts.ListActivityContract;
import zhaorunze.gittest.presenters.ListActivityPresenter;

/**
 * Created by zhaorunze on
 * 2018/3/5 17:07
 * E-Mail Address：1159963642@qq.com
 */

public class ListActivity extends MVPActivity<ListActivityPresenter> implements ListActivityContract.View{

    @BindView(R.id.viewpager)
    SViewPager viewpager;
    @BindView(R.id.fixedIndicatorView)
    FixedIndicatorView fixedIndicatorView;


    @Override
    public ListActivityPresenter createPresenter() {
        return new ListActivityPresenter(this);
    }

    @Override
    public int getContent() {
        return R.layout.activity_list;
    }

    @Override
    public void initView() {
        super.initView();

    }
}
