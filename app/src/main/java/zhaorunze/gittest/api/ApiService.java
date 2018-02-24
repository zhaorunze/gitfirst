package zhaorunze.gittest.api;

import retrofit2.http.GET;
import rx.Observable;
import zhaorunze.gittest.entity.GuideBean;
import zhaorunze.gittest.entity.ResponseBody;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:09
 * E-Mail Address：1159963642@qq.com
 */

public interface ApiService {

    @GET("home/start")
    Observable<ResponseBody<GuideBean>> loadGuide();
}
