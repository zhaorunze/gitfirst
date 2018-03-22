package zhaorunze.gittest.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;
import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.api.ApiCallBack;
import zhaorunze.gittest.api.ApiService;
import zhaorunze.gittest.api.ApiSubscriber;
import zhaorunze.gittest.api.RetrofitClient;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.MainActivityContract;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.entity.ResponseBody;
import zhaorunze.gittest.entity.User;

/**
 * Created by zhaorunze on
 * 2018/2/26 09:59
 * E-Mail Address：1159963642@qq.com
 */

public class MainActivityPresenter extends CommonPresenter implements MainActivityContract.Presenter {
    private static final String TAG = "MainActivityPresenter";
    private MainActivityContract.View mView;
    public MainActivityPresenter(MainActivityContract.View mView){
        this.mView = mView;
    }

    @Override
    public void insertUser(String name, String sex, String age) {
        if("".equals(name)){
            mView.insertUserFailure("请输入用户名");
            return;
        }
        if("".equals(sex)){
            mView.insertUserFailure("请输入性别");
            return;
        }
        if("".equals(age)){
            mView.insertUserFailure("请输入年龄");
            return;
        }
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        AppContext.getInstance().getDaoSession().getUserDao().insert(user);
    }

    @Override
    public List<User> loadAllUser() {
        return AppContext.getInstance().getDaoSession().loadAll(User.class);
    }

    @Override
    public void insertUseres(List<User> userData,List<User> homeData) {
        final List<Observable<ResponseBody<User>>> userObservables = new ArrayList<>();
        for (int i = 0; i < userData.size(); i++) {
            userObservables.add(RetrofitClient.builderRetrofit().create(ApiService.class)
                    .addUser(createBody(userData.get(i))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()));
        }

        final List<Observable<ResponseBody<User>>> homeObservables = new ArrayList<>();
        for (int i = 0; i < homeData.size(); i++) {
            homeObservables.add(RetrofitClient.builderRetrofit().create(ApiService.class)
                    .addUser(createBody(homeData.get(i))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()));
        }

        Observable.zip(userObservables, new FuncN<List<User>>() {
            @Override
            public List<User> call(Object... args) {
                List<User> comeData = new ArrayList<>();
                for(Object obj : args){
                    comeData.add(((ResponseBody<User>)obj).getData());
                }
                return comeData;
            }
        }).subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ===>>==");
                Observable.zip(homeObservables, new FuncN<List<User>>() {
                    @Override
                    public List<User> call(Object... args) {
                        List<User> comeData = new ArrayList<>();
                        for(Object obj : args){
                            comeData.add(((ResponseBody<User>)obj).getData());
                        }
                        return comeData;
                    }
                }).subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ===>>==");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ===>>==" + e.getMessage());

                    }

                    @Override
                    public void onNext(List<User> o) {
                        Log.d(TAG, "onNext: ===>>==" + o);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ===>>==" + e.getMessage());

            }

            @Override
            public void onNext(List<User> o) {
                Log.d(TAG, "onNext: ===>>==" + o);
            }
        });
    }


    public void insertUseres1(List<User> userData,List<User> homeData) {
        final List<Observable<ResponseBody<User>>> userObservables = new ArrayList<>();
        for (int i = 0; i < userData.size(); i++) {
            userObservables.add(RetrofitClient.builderRetrofit().create(ApiService.class)
                    .addUser(createBody(userData.get(i))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()));
        }

        final List<Observable<ResponseBody<User>>> homeObservables = new ArrayList<>();
        for (int i = 0; i < homeData.size(); i++) {
            homeObservables.add(RetrofitClient.builderRetrofit().create(ApiService.class)
                    .addUser(createBody(homeData.get(i))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()));
        }
        Observable.zip(userObservables, new FuncN<List<User>>() {
            @Override
            public List<User> call(Object... args) {
                List<User> comeData = new ArrayList<>();
                for(Object obj : args){
                    comeData.add(((ResponseBody<User>)obj).getData());
                }
                return comeData;
            }
        }).subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ===>>==");
                Observable.zip(homeObservables, new FuncN<List<User>>() {
                    @Override
                    public List<User> call(Object... args) {
                        List<User> comeData = new ArrayList<>();
                        for(Object obj : args){
                            comeData.add(((ResponseBody<User>)obj).getData());
                        }
                        return comeData;
                    }
                }).subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ===>>==");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ===>>==" + e.getMessage());

                    }

                    @Override
                    public void onNext(List<User> o) {
                        Log.d(TAG, "onNext: ===>>==" + o);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ===>>==" + e.getMessage());

            }

            @Override
            public void onNext(List<User> o) {
                Log.d(TAG, "onNext: ===>>==" + o);
            }
        });
    }


    public void inserUser(final int type, final User user, final UpUser upUser){
        Observable<ResponseBody<User>> observable = RetrofitClient.builderRetrofit().create(ApiService.class).addUser(createBody(user));
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<User>() {
            @Override
            public void onSuccess(User mode) {
                user.setId(mode.getId());
                upUser.upResult(type, user, 0);
            }

            @Override
            public void onFailure(int code, String msg) {
                upUser.upResult(type, user, 1);
            }
        }));
    }

    public interface UpUser{
        void upResult(int type, User user, int resultCode);
    }
}
