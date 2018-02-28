package zhaorunze.gittest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.List;

import zhaorunze.gittest.R;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/2/28 10:46
 * E-Mail Address：1159963642@qq.com
 */

public class AreaListAdapter extends BaseQuickAdapter<AreaBean, BaseViewHolder> {
    Gson gson;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    public AreaListAdapter(@Nullable List<AreaBean> data) {
        super(R.layout.item_area_list, data);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
        int position = helper.getLayoutPosition();
        if(position % 2 == 0){
            helper.setBackgroundColor(R.id.llAreaListContainer, mContext.getResources().getColor(R.color.color_area_list_first));
        }else{
            helper.setBackgroundColor(R.id.llAreaListContainer, mContext.getResources().getColor(R.color.color_area_list_second));
        }
        helper.setText(R.id.tvAreaId, String.valueOf(item.getAreaId()));
        helper.setText(R.id.tvAreaName, item.getAreaName());
        helper.setText(R.id.tvAreaPriority, String.valueOf(item.getPriority()));
        helper.setText(R.id.tvCreateTime, simpleDateFormat.format(item.getCreateTime()));
        helper.setText(R.id.tvLastEditTime, simpleDateFormat.format(item.getLastEditTime()));
    }
}
