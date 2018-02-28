package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.BannerAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.NextActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.entity.GuideBean;
import zhaorunze.gittest.presenters.NextActivityPresenter;

/**
 * Created by zhaorunze on
 * 2018/2/24 13:49
 * E-Mail Address：1159963642@qq.com
 */

public class NextActivity extends MVPActivity<NextActivityPresenter> implements NextActivityContract.View{

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
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

    Gson gson;
    @Override
    public void initData() {
        super.initData();
        gson = new GsonBuilder().setPrettyPrinting().create();
        bannerComponent = new BannerComponent(fixedIndicatorView, viewPager, false);
        BannerAdapter bannerAdapter = new BannerAdapter(this, Constant.PICES);
        bannerComponent.setAdapter(bannerAdapter);
    }

    @Override
    public void initView() {
        super.initView();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mPresenter.loadGuide();
                mPresenter.loadAreaList();
            }
        });
    }

    @OnClick(R.id.btLoadAreaList)
    void loadAreaList(View view){
        mPresenter.loadAreaList();
    }

    @Override
    public void loadAreaListSuccess(List<AreaBean> bean) {
        refreshLayout.setRefreshing(false);
        tvShow.setText(gson.toJson(bean));
    }

    @Override
    public void loadAreaListFailure() {
        refreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.btLoad)
    void loadGuide(View view){
//        mPresenter.loadGuide();
        Intent intent = new Intent(this, AreaListActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadGuideSuccess(GuideBean bean) {
        refreshLayout.setRefreshing(false);
        tvShow.setText(gson.toJson(bean));
    }

    @Override
    public void loadGuideFailure() {
        refreshLayout.setRefreshing(false);
    }
}
