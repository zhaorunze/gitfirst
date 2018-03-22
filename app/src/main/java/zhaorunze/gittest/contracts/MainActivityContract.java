package zhaorunze.gittest.contracts;

import java.util.List;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.AreaBean;
import zhaorunze.gittest.entity.User;

/**
 * Created by zhaorunze on
 * 2018/2/26 09:58
 * E-Mail Address：1159963642@qq.com
 */

public interface MainActivityContract {

    interface View extends BaseView{
        void insertUserFailure(String msg);
        void insertUserSuccess(User user);
        void insertUseresFailure(User user);
        void insertUseresSuccess(User user);
    }
    interface Presenter extends BasePresenter{
        void insertUser(String name, String sex, String age);
        List<User> loadAllUser();
        void insertUseres(List<User> userData,List<User> homeData);
    }
}
