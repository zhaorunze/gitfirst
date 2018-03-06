package zhaorunze.gittest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhaorunze.gittest.R;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/3/6 13:25
 * E-Mail Address：1159963642@qq.com
 */

public class ListAreaAdapter extends BaseQuickAdapter<AreaBean,BaseViewHolder> {

    public ListAreaAdapter(@Nullable List<AreaBean> data) {
        super(R.layout.item_list_area, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
        helper.setText(R.id.areaId, String.valueOf(item.getAreaId()));
        helper.setText(R.id.areaName, item.getAreaName());
        helper.setText(R.id.priority, String.valueOf(item.getPriority()));
        helper.addOnClickListener(R.id.tvDeleteArea);
        helper.addOnClickListener(R.id.tvUpdateArea);
    }
}
