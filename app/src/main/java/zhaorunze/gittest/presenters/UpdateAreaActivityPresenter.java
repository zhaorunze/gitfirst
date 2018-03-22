package zhaorunze.gittest.presenters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.UpdateAreaActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/3/6 15:22
 * E-Mail Address：1159963642@qq.com
 */

public class UpdateAreaActivityPresenter extends CommonPresenter implements UpdateAreaActivityContract.Presenter {

    private UpdateAreaActivityContract.View mView;
    public UpdateAreaActivityPresenter(UpdateAreaActivityContract.View mView){
        this.mView = mView;
    }
    @Override
    public void addArea(final AreaBean areaBean) {
        Observable<ResponseBody> observable = RetrofitClient.builderRetrofit().create(ApiService.class)
                .addArea(createBody(areaBean));
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
                mView.addAreaSuccess(areaBean);
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.addAreaFailure(areaBean);
            }
        }));
    }

    @Override
    public void updateArea(final AreaBean areaBean) {
        Observable<ResponseBody> observable = RetrofitClient.builderRetrofit().create(ApiService.class).updateArea(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(areaBean)));
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
                mView.updateAreaSuccess(areaBean);
            }

            @Override
            public void onFailure(int code, String msg) {
                mView.updateAreaFailure(areaBean);
            }
        }));
    }
}
