package zhaorunze.gittest.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:41
 * E-Mail Address：1159963642@qq.com
 */

public abstract class BaseDialog {
    protected Dialog mDialog;
    protected Context mContext;

    public abstract int getLayoutId();
    public abstract void initLayoutParams(Context context, View view);

    public BaseDialog(Context context){
        mContext = context;
        View root = LayoutInflater.from(context).inflate(getLayoutId(), null);
        initLayoutParams(context, root);
    }

    public void show(){
        mDialog.show();
    }

    public void dismiss(){
        if(mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public boolean isShowing(){
        return mDialog.isShowing();
    }
}
