package zhaorunze.gittest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

import zhaorunze.gittest.R;

/**
 * Created by zhaorunze on
 * 2018/2/26 10:47
 * E-Mail Address：1159963642@qq.com
 */

public class BannerAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    List<String> mData = new ArrayList<String>();

    Context mContext;

    public BannerAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData.clear();
        this.mData.addAll(data);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.indicator_pot, container, false);
        }
        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_banner, container, false);
        }
        ImageView imageView = convertView.findViewById(R.id.img);
        Glide.with(mContext).load(mData.get(position)).into(imageView);
        return convertView;
    }
}
