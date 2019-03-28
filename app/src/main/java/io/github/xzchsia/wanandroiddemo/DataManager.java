package io.github.xzchsia.wanandroiddemo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.xzchsia.wanandroiddemo.model.Banner;
import io.github.xzchsia.wanandroiddemo.model.BaseResponse;
import io.reactivex.Observable;

@Singleton
public class DataManager {
    private WanAndroidService wanAndroidService;

    public DataManager(WanAndroidService wanAndroidService) {
        this.wanAndroidService = wanAndroidService;
    }

    public Observable<BaseResponse<List<Banner>>> getBannerData() {
        return wanAndroidService.getBannerData();
    }

}
