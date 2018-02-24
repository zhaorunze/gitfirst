package zhaorunze.gittest.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import zhaorunze.gittest.R;
import zhaorunze.gittest.base.BaseDialog;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:45
 * E-Mail Address：1159963642@qq.com
 */

public class LoadingDialog extends BaseDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_dialog_loading;
    }

    @Override
    public void initLayoutParams(Context context, View view) {
        mDialog = new Dialog(context, R.style.BaseDialog);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mDialog.setCanceledOnTouchOutside(false);
    }
}
