package zhaorunze.gittest.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import zhaorunze.gittest.R;
import zhaorunze.gittest.base.MVPActivity;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.contracts.UpdateAreaActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.presenters.UpdateAreaActivityPresenter;

public class UpdateAreaActivity extends MVPActivity<UpdateAreaActivityPresenter> implements UpdateAreaActivityContract.View {

    public static final String BEAN = "bean";
    private AreaBean bean;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.etAreaName)
    EditText etAreaName;
    @BindView(R.id.etPriority)
    EditText etPriority;

    @Override
    public int getContent() {
        return R.layout.activity_update_area;
    }

    @Override
    public void initView() {
        super.initView();
        bean = (AreaBean) getIntent().getSerializableExtra(BEAN);
        if (bean != null) {
            etAreaName.setText(bean.getAreaName());
            etPriority.setText(String.valueOf(bean.getPriority()));
        }
        Glide.with(this).load(Constant.UPDATE_AREA_BG).into(ivImage);
    }

    @OnClick(R.id.ivImage)
    public void ivImageClick(View view) {
        Intent intent = new Intent();
        intent.setClassName("cn.sharefun.android", "cn.sharefun.android.ui.activities.MainActivity");
        startActivity(intent);
    }

    @Override
    public UpdateAreaActivityPresenter createPresenter() {
        return new UpdateAreaActivityPresenter(this);
    }

    @OnClick(R.id.btCommit)
    public void btCommit(View view) {
        String areaName = etAreaName.getText().toString();
        if (TextUtils.isEmpty(areaName)) {
            showMsg("请输入区域名");
            return;
        }

        String priority = etPriority.getText().toString();
        if (TextUtils.isEmpty(priority)) {
            showMsg("请输入权重值");
            return;
        }
        int priorityInt;
        try {
            priorityInt = Integer.valueOf(priority);
        } catch (Exception e) {
            showMsg("请输入正确的权重值");
            return;
        }

        if (bean != null) {
            bean.setAreaName(areaName);
            bean.setPriority(priorityInt);
            mPresenter.updateArea(bean);
        } else {
            AreaBean bean = new AreaBean();
            bean.setAreaName(areaName);
            bean.setPriority(priorityInt);
            mPresenter.addArea(bean);
        }
    }

    @OnClick(R.id.btClear)
    public void btClear(View view) {
        etAreaName.getText().clear();
        etPriority.getText().clear();
        bean = null;
    }

    @Override
    public void addAreaSuccess(AreaBean areaBean) {
        showMsg("添加[" + areaBean.getAreaName() + "]成功");
        finish();
    }

    @Override
    public void addAreaFailure(AreaBean areaBean) {

    }

    @Override
    public void updateAreaSuccess(AreaBean areaBean) {
        showMsg("更新[" + areaBean.getAreaName() + "]成功");
        finish();
    }

    @Override
    public void updateAreaFailure(AreaBean areaBean) {

    }
}
