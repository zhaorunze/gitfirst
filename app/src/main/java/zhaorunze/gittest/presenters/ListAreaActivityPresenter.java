package zhaorunze.gittest.presenters;

import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.ListAreaActivityContract;

/**
 * Created by zhaorunze on
 * 2018/3/6 11:34
 * E-Mail Address：1159963642@qq.com
 */

public class ListAreaActivityPresenter extends CommonPresenter implements ListAreaActivityContract.Presenter {
    private ListAreaActivityContract.View mView;
    public ListAreaActivityPresenter(ListAreaActivityContract.View mView){
        this.mView = mView;
    }
}
