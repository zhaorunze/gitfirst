package zhaorunze.gittest.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:18
 * E-Mail Address：1159963642@qq.com
 */

public class CommonPresenter implements BasePresenter {
    public Gson gson = new GsonBuilder().create();
    public RequestBody createBody(Object bean){
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(bean));
    }
    protected CompositeSubscription compositeSubscription;

    public void attachView(){
        compositeSubscription = new CompositeSubscription();
    }

    public void detachView(){
        compositeSubscription.clear();
        if(compositeSubscription.hasSubscriptions()){}{
            compositeSubscription.unsubscribe();
        }
    }

    protected void addIOSubscription(Observable observable, Subscriber subscriber){
        compositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    @Override
    public void start() {

    }
}
