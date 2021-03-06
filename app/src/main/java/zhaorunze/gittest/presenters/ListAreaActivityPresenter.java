package zhaorunze.gittest.presenters;

import rx.Observable;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.ListAreaActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.entity.AreaListBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/3/6 11:34
 * E-Mail Address：1159963642@qq.com
 */

public class ListAreaActivityPresenter extends CommonPresenter implements ListAreaActivityContract.Presenter {
    private ListAreaActivityContract.View mView;
    public ListAreaActivityPresenter(ListAreaActivityContract.View mView){
        this.mView = mView;
    }

    @Override
    public void loadAreaList() {
        Observable<ResponseBody<AreaListBean>> observable = RetrofitClient.builderRetrofit().create(ApiService.class)
                .loadAreaList();
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<AreaListBean>() {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoadingDialog();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.dismissLoadingDialog();
            }

            @Override
            public void onSuccess(AreaListBean mode) {
                mView.loadAreaListSuccess(mode.getAreaList());
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.showMsg(msg);
            }
        }));
    }

    @Override
    public void deleteArea(final int position, final AreaBean bean) {
        Observable<ResponseBody> observable = RetrofitClient.builderRetrofit().create(ApiService.class).deleteArea(bean.getAreaId());
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack() {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoadingDialog();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.dismissLoadingDialog();
            }

            @Override
            public void onSuccess(Object mode) {
                mView.deleteAreaSuccess(position, bean);
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.deleteAreaFailure();
            }
        }));
    }

    @Override
    public void updateArea(AreaBean bean) {

    }
}
