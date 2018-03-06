package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListAreaAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.ListAreaActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.presenters.ListAreaActivityPresenter;

public class ListAreaActivity extends MVPActivity<ListAreaActivityPresenter> implements ListAreaActivityContract.View, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.listRecyclerView)
    RecyclerView listRecyclerView;
    @BindView(R.id.ivImageBg)
    ImageView ivImageBg;

    @Override
    public int getContent() {
        return R.layout.activity_list_area;
    }

    @Override
    public ListAreaActivityPresenter createPresenter() {
        return new ListAreaActivityPresenter(this);
    }

    private ListAreaAdapter mListAreaAdapter;

    @Override
    public void initView() {
        super.initView();
        Glide.with(this).load(Constant.LIST_AREA_BG).into(ivImageBg);
        mListAreaAdapter = new ListAreaAdapter(null);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listRecyclerView.setAdapter(mListAreaAdapter);
        mListAreaAdapter.setOnItemChildClickListener(this);
        swipeRefresh.setOnRefreshListener(this);
        mPresenter.loadAreaList();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        AreaBean areaBean = (AreaBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.tvDeleteArea:
                mPresenter.deleteArea(position, areaBean);
                break;
            case R.id.tvUpdateArea:
                addOrUpdateAreaBean(areaBean);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btAdd)
    public void btAdd(View view) {
        addOrUpdateAreaBean(null);
    }

    private void addOrUpdateAreaBean(AreaBean bean) {
        Intent intent = new Intent(ListAreaActivity.this, UpdateAreaActivity.class);
        if (bean != null) {
            intent.putExtra(UpdateAreaActivity.BEAN, bean);
        }
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            mPresenter.loadAreaList();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.loadAreaList();
    }

    @Override
    public void loadAreaListSuccess(List<AreaBean> areaBeans) {
        swipeRefresh.setRefreshing(false);
        mListAreaAdapter.setNewData(areaBeans);
    }

    @Override
    public void loadAreaListFailure() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void deleteAreaSuccess(int position, AreaBean bean) {
        mListAreaAdapter.getData().remove(bean);
        mListAreaAdapter.notifyItemRemoved(position);
        showToast("删除" + bean.getAreaName() + "成功");
    }

    @Override
    public void deleteAreaFailure() {

    }

    @Override
    public void updateAreaSuccess() {

    }

    @Override
    public void updateAreaFailure() {

    }
}
