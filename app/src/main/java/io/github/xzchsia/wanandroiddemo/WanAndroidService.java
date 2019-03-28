package io.github.xzchsia.wanandroiddemo;

import java.util.List;


import io.github.xzchsia.wanandroiddemo.model.Banner;
import io.github.xzchsia.wanandroiddemo.model.BaseResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface WanAndroidService {
	// 获取首页 banner 数据
	@GET("banner/json")
	Observable<BaseResponse<List<Banner>>> getBannerData();
}