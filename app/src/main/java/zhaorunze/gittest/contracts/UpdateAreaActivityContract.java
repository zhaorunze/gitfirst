package zhaorunze.gittest.contracts;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/3/6 15:20
 * E-Mail Address：1159963642@qq.com
 */

public interface UpdateAreaActivityContract {
    interface View extends BaseView{
        void addAreaSuccess(AreaBean areaBean);
        void addAreaFailure(AreaBean areaBean);
        void updateAreaSuccess(AreaBean areaBean);
        void updateAreaFailure(AreaBean areaBean);
    }
    interface Presenter extends BasePresenter{
        void addArea(AreaBean areaBean);
        void updateArea(AreaBean areaBean);
    }
}
