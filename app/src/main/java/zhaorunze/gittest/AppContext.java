package zhaorunze.gittest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import zhaorunze.gittest.entity.DaoMaster;
import zhaorunze.gittest.entity.DaoSession;

/**
 * Created by zhaorunze on
 * 2018/2/23 14:37
 * E-Mail Address：1159963642@qq.com
 */

public class AppContext extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppContext getInstance(){
        return instance;
    }

    private void setDataBase(){
        mHelper = new DaoMaster.DevOpenHelper(this, "app.db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
