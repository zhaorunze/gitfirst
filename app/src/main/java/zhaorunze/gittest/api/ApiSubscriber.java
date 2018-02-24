package zhaorunze.gittest.api;

import rx.Subscriber;
import zhaorunze.gittest.config.Constant;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/2/24 15:57
 * E-Mail Address：1159963642@qq.com
 */

public class ApiSubscriber<T> extends Subscriber<ResponseBody<T>> {

    public static final int UNKNOW_CODE = -1;
    private final ApiCallBack apiCallBack;

    public ApiSubscriber(ApiCallBack apiCallBack) {
        this.apiCallBack = apiCallBack;
    }

    @Override
    public void onStart() {
        super.onStart();
        apiCallBack.onStart();
    }

    @Override
    public void onCompleted() {
        apiCallBack.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        apiCallBack.onCompleted();
        apiCallBack.onFailure(UNKNOW_CODE, Constant.NET_ERROR);
    }

    @Override
    public void onNext(ResponseBody<T> tResponseBody) {
        if(tResponseBody.getCode() == Constant.NET_CODE_SUCCESS){
            apiCallBack.onSuccess(tResponseBody.getData());
        }else{
            apiCallBack.onFailure(tResponseBody.getCode(), tResponseBody.getMsg());
        }
    }
}
