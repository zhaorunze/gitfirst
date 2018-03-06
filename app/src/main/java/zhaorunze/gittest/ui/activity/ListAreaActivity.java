package zhaorunze.gittest.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import zhaorunze.gittest.R;
import zhaorunze.gittest.adapter.ListAreaAdapter;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.contracts.ListAreaActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.presenters.ListAreaActivityPresenter;

public class ListAreaActivity extends MVPActivity<ListAreaActivityPresenter> implements ListAreaActivityContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.listRecyclerView)
    RecyclerView listRecyclerView;

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
        mListAreaAdapter = new ListAreaAdapter(null);
        listRecyclerView.setAdapter(mListAreaAdapter);
        mListAreaAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        AreaBean areaBean = (AreaBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.tvDeleteArea:
                break;
            case R.id.tvUpdateArea:
                break;
            default:
                break;
        }
    }
}
