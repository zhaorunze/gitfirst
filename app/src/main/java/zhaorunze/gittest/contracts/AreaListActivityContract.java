package zhaorunze.gittest.contracts;

import java.util.List;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.AreaBean;

/**
 * Created by zhaorunze on
 * 2018/2/28 10:11
 * E-Mail Address：1159963642@qq.com
 */

public interface AreaListActivityContract {
    interface Presenter extends BasePresenter{
        void loadListAreas(int areaid, int size);
    }
    interface View extends BaseView{
        void loadListAreasSuccess(List<AreaBean> bean);
        void loadListAreasFailure();
    }
}
