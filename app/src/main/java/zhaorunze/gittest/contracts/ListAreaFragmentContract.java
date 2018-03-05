package zhaorunze.gittest.contracts;

import java.util.List;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/3/5 13:15
 * E-Mail Address：1159963642@qq.com
 */

public interface ListAreaFragmentContract {
    interface Presenter extends BasePresenter{
        void loadAreaList();
    }
    interface View extends BaseView{
        void loadAreaListSuccess(List<AreaBean> bean);
        void loadAreaListFailure();
    }
}
