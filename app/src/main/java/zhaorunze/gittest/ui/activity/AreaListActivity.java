package zhaorunze.gittest.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.AreaListAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.AreaListActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.presenters.AreaListActivityPresenter;
import zhaorunze.gittest.widgets.VerticalSwipeRefreshLayout;

/**
 * Created by zhaorunze on
 * 2018/2/28 10:10
 * E-Mail Address：1159963642@qq.com
 */

public class AreaListActivity extends MVPActivity<AreaListActivityPresenter> implements AreaListActivityContract.View{

    @BindView(R.id.imgBg)
    ImageView imgBg;
    @BindView(R.id.swipeRefresh)
    VerticalSwipeRefreshLayout swipeRefresh;
    @BindView(R.id.areaList)
    RecyclerView areaList;

    @Override
    public AreaListActivityPresenter createPresenter() {
        return new AreaListActivityPresenter(this);
    }

    @Override
    public int getContent() {
        return R.layout.activity_arealist;
    }
    AreaListAdapter areaListAdapter;
    List<AreaBean> areaBeanList = new ArrayList<>();
    private int lastAreaId = 0;
    @Override
    public void initView() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lastAreaId = 0;
                mPresenter.loadListAreas(lastAreaId, Constant.PAGE_SIZE);
            }
        });
        areaList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        areaListAdapter = new AreaListAdapter(areaBeanList);
        areaListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadListAreas(lastAreaId, Constant.PAGE_SIZE);
            }
        }, areaList);
        areaList.setAdapter(areaListAdapter);
        areaListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.tvShow){
                    showToast(((TextView)view).getText().toString());
                }
            }
        });
        areaListAdapter.setEnableLoadMore(true);
    }

    @Override
    public void initData() {
        mPresenter.loadListAreas(lastAreaId, Constant.PAGE_SIZE);
        Glide.with(this).load(Constant.PICES.get(1)).into(imgBg);
    }

    @Override
    public void loadListAreasSuccess(List<AreaBean> bean) {
        swipeRefresh.setRefreshing(false);
        if(bean.size() > 0){
            areaBeanList.addAll(bean);
            lastAreaId = bean.get(bean.size() - 1).getAreaId();
            areaListAdapter.setNewData(areaBeanList);
            if(bean.size() < Constant.PAGE_SIZE){
                areaListAdapter.loadMoreEnd();
            }else{
                areaListAdapter.loadMoreComplete();
            }
        }else{
            areaListAdapter.loadMoreEnd();
        }

    }

    @Override
    public void loadListAreasFailure() {
        areaListAdapter.loadMoreFail();
        swipeRefresh.setRefreshing(false);
    }
}
