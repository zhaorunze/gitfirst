package zhaorunze.gittest.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.BannerAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.NextActivityContract;
import zhaorunze.gittest.entity.GuideBean;
import zhaorunze.gittest.presenters.NextActivityPresenter;

/**
 * Created by zhaorunze on
 * 2018/2/24 13:49
 * E-Mail Address：1159963642@qq.com
 */

public class NextActivity extends MVPActivity<NextActivityPresenter> implements NextActivityContract.View{

    @BindView(R.id.tvShow)
    TextView tvShow;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fixedIndicatorView)
    FixedIndicatorView fixedIndicatorView;
    BannerComponent bannerComponent;
    @Override
    public int getContent() {
        return R.layout.activity_next;
    }

    @Override
    public NextActivityPresenter createPresenter() {
        return new NextActivityPresenter(this);
    }

    @Override
    public void initData() {
        super.initData();
        bannerComponent = new BannerComponent(fixedIndicatorView, viewPager, false);
        BannerAdapter bannerAdapter = new BannerAdapter(this, Constant.PICES);
        bannerComponent.setAdapter(bannerAdapter);
    }

    @OnClick(R.id.btLoad)
    void loadGuide(View view){
        mPresenter.loadGuide();
    }

    @Override
    public void loadGuideSuccess(GuideBean bean) {
        tvShow.setText(new Gson().toJson(bean));
    }
}
