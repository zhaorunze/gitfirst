package zhaorunze.gittest.contracts;

import java.util.List;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/3/6 11:33
 * E-Mail Address：1159963642@qq.com
 */

public interface ListAreaActivityContract {
    interface View extends BaseView{
        void loadAreaListSuccess(List<AreaBean> areaBeans);
        void loadAreaListFailure();
        void deleteAreaSuccess(int position, AreaBean bean);
        void deleteAreaFailure();
        void updateAreaSuccess();
        void updateAreaFailure();
    }
    interface Presenter extends BasePresenter{
        void loadAreaList();
        void deleteArea(int position, AreaBean bean);
        void updateArea(AreaBean bean);
    }
}
