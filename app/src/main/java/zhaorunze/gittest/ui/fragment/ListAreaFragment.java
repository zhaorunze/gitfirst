package zhaorunze.gittest.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.AreaListAdapter;
import zhaorunze.gittest.base.MVPFragment;
import zhaorunze.gittest.contracts.ListAreaFragmentContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.presenters.ListAreaFragmentPresenter;

/**
 * Created by zhaorunze on
 * 2018/3/5 13:07
 * E-Mail Address：1159963642@qq.com
 */

public class ListAreaFragment extends MVPFragment<ListAreaFragmentPresenter> implements ListAreaFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.areaListView)
    RecyclerView areaListView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    AreaListAdapter areaListAdapter;
    @Override
    protected int getContentId() {
        return R.layout.fragment_listarea;
    }

    @Override
    protected ListAreaFragmentPresenter createPresenter() {
        return new ListAreaFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        swipeRefresh.setOnRefreshListener(this);
        areaListAdapter = new AreaListAdapter(null);
        areaListView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        areaListView.setAdapter(areaListAdapter);
        areaListAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void loadAreaListSuccess(List<AreaBean> bean) {
        swipeRefresh.setRefreshing(false);
        areaListAdapter.setNewData(bean);
    }

    @Override
    public void loadAreaListFailure() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.loadAreaList();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
