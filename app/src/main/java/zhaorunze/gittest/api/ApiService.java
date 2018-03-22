package zhaorunze.gittest.api;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import zhaorunze.gittest.entity.AreaListBean;
import zhaorunze.gittest.entity.GuideBean;
import zhaorunze.gittest.entity.ResponseBody;
import zhaorunze.gittest.entity.User;

/**
 * Created by zhaorunze on
 * 2018/2/24 16:09
 * E-Mail Address：1159963642@qq.com
 */

public interface ApiService {

    @GET("home/start")
    Observable<ResponseBody<GuideBean>> loadGuide();
    @GET("superadmin/listarea")
    Observable<ResponseBody<AreaListBean>> loadAreaList();
    @GET("superadmin/listareapage")
    Observable<ResponseBody<AreaListBean>> loadAreaPage(@Query("areaid") int areaid, @Query("limit") int limit);
    @GET("superadmin/removearea")
    Observable<ResponseBody> deleteArea(@Query("areaid") int areaid);

    @POST("superadmin/addarea")
    Observable<ResponseBody> addArea(@Body RequestBody body);

    @POST("superadmin/modifyarea")
    Observable<ResponseBody> updateArea(@Body RequestBody body);
    @POST("hello")
    Observable<ResponseBody<User>> addUser(@Body RequestBody body);
}
