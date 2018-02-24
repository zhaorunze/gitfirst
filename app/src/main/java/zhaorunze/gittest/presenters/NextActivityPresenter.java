package zhaorunze.gittest.presenters;

import rx.Observable;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.NextActivityContract;
import zhaorunze.gittest.entity.GuideBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/2/24 17:03
 * E-Mail Address：1159963642@qq.com
 */

public class NextActivityPresenter extends CommonPresenter implements NextActivityContract.Presenter{

    private NextActivityContract.View mView;

    public NextActivityPresenter(NextActivityContract.View view){
        mView = view;
    }

    @Override
    public void loadGuide() {
        Observable<ResponseBody<GuideBean>> observable = RetrofitClient.builderRetrofit().create(ApiService.class)
                .loadGuide();
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<GuideBean>() {

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
            public void onSuccess(GuideBean mode) {
                mView.loadGuideSuccess(mode);
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.showMsg(msg);
            }
        }));
    }
}
