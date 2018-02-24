package zhaorunze.gittest.contracts;

import zhaorunze.gittest.base.BasePresenter;
import zhaorunze.gittest.base.BaseView;
import zhaorunze.gittest.entity.GuideBean;

/**
 * Created by zhaorunze on
 * 2018/2/24 17:03
 * E-Mail Address：1159963642@qq.com
 */

public interface NextActivityContract {
    interface View extends BaseView{
        void loadGuideSuccess(GuideBean bean);
    }
    interface Presenter extends BasePresenter{
        void loadGuide();
    }
}
