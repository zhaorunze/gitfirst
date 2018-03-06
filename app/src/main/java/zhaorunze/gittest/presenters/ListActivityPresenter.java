package zhaorunze.gittest.presenters;

import zhaorunze.gittest.base.CommonPresenter;
import zhaorunze.gittest.contracts.ListActivityContract;

/**
 * Created by zhaorunze on
 * 2018/3/5 17:09
 * E-Mail Address：1159963642@qq.com
 */

public class ListActivityPresenter extends CommonPresenter implements ListActivityContract.Presenter {

    private ListActivityContract.View mView;
    public ListActivityPresenter(ListActivityContract.View mView){
        this.mView = mView;
    }


}
