package com.wh.bdgw.net;

import com.wh.bdgw.net.model.bean.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    //获取banner
    @FormUrlEncoded
    @POST("System/GetBanner")
    Observable<BaseResponse<List<BannerBean>>> getBanner(@Field("key") String key, @Field("type") int type);
}
