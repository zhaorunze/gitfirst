package zhaorunze.gittest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhaorunze.gittest.R;
import zhaorunze.gittest.entity.User;

/**
 * Created by zhaorunze on
 * 2018/2/23 16:18
 * E-Mail Address：1159963642@qq.com
 */

public class ListUserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {


    public ListUserAdapter(@Nullable List<User> data) {
        super(R.layout.item_user_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tvId, item.getId() + "");
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvSex, item.getSex());
        helper.setText(R.id.tvAge, item.getAge());
        helper.addOnClickListener(R.id.ivDelete);
    }
}
