package zhaorunze.gittest.presenters;

import rx.Observable;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.AreaListActivityContract;
import zhaorunze.gittest.entity.AreaListBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/2/28 10:15
 * E-Mail Address：1159963642@qq.com
 */

public class AreaListActivityPresenter extends CommonPresenter implements AreaListActivityContract.Presenter {

    private AreaListActivityContract.View mView;
    public AreaListActivityPresenter(AreaListActivityContract.View mView){
        this.mView = mView;
    }

    @Override
    public void loadListAreas(int areaid, int size) {
        Observable<ResponseBody<AreaListBean>> observable = RetrofitClient.builderRetrofit().create(ApiService.class).loadAreaPage(areaid, size);
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
                mView.loadListAreasSuccess(mode.getAreaList());
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.showMsg(msg);
                mView.loadListAreasFailure();
            }
        }));
    }
}
