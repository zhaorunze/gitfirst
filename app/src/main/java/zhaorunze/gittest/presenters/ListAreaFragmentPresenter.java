package zhaorunze.gittest.presenters;

import rx.Observable;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.ListAreaFragmentContract;
import zhaorunze.gittest.entity.AreaListBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/3/5 13:19
 * E-Mail Address：1159963642@qq.com
 */

public class ListAreaFragmentPresenter extends CommonPresenter implements ListAreaFragmentContract.Presenter {

    private ListAreaFragmentContract.View mView;
    public ListAreaFragmentPresenter(ListAreaFragmentContract.View mView){
        this.mView = mView;
    }
    @Override
    public void loadAreaList() {
        Observable<ResponseBody<AreaListBean>> observable = RetrofitClient.builderRetrofit().create(ApiService.class).loadAreaList();
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
}
