package zhaorunze.gittest.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhaorunze on
 * 2018/2/23 15:40
 * E-Mail Address：1159963642@qq.com
 */

public class ToastUtils {

    public static void toastShort(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
