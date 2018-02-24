package zhaorunze.gittest.api;

/**
 * Created by zhaorunze on
 * 2018/2/24 15:58
 * E-Mail Address：1159963642@qq.com
 */

public abstract class ApiCallBack<T> {

    public void onStart(){}
    public void onCompleted(){}
    public abstract void onSuccess(T mode);
    public abstract void onFailure(int code, String msg);
}
