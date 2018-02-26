package zhaorunze.gittest.presenters;

import java.util.List;

import zhaorunze.gittest.AppContext;
import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.MainActivityContract;
import zhaorunze.gittest.entity.User;

/**
 * Created by zhaorunze on
 * 2018/2/26 09:59
 * E-Mail Address：1159963642@qq.com
 */

public class MainActivityPresenter extends CommonPresenter implements MainActivityContract.Presenter {
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
}
