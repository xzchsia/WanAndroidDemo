package io.github.xzchsia.wanandroiddemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.xzchsia.wanandroiddemo.model.Banner;
import io.github.xzchsia.wanandroiddemo.model.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.btn_test)
    Button button;
    private View mView = null;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);

            //在这里做一些初始化处理
            ButterKnife.bind(this, mView);
        } else {
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup != null)
                viewGroup.removeView(mView);
        }

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBanner();
            }
        });
    }

    void initBanner() {
        MyAppInit myAppInit = new MyAppInit();
        DataManager dataManager = new DataManager(myAppInit.provideWanAndroidService(myAppInit.getRetrofit()));
        Observable<BaseResponse<List<Banner>>> observable = dataManager.getBannerData();
//        System.out.println("111111111111111111111");
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<List<Banner>>, List<Banner>>() {
                    @Override
                    public List<Banner> apply(@io.reactivex.annotations.NonNull BaseResponse<List<Banner>> response)
                            throws Exception {
                        return response.getData();
                    }
                })
                .subscribeWith(new Observer<List<Banner>>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
//                disposable.add(d);
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<Banner> banners) {
                //getView().showBannerData(banners);

                System.out.println("222222222222");
                for (Banner banner : banners) {
                    System.out.println("ImagePath : " + banner.getImagePath());
                    System.out.println("Title : " + banner.getTitle());
                    System.out.println("Url : " + banner.getUrl());
                    System.out.println("3333333333333333333333333");
                }
//                System.out.println("222222222222");
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                System.out.println("555555555555555555");
            }

            @Override
            public void onComplete() {
                System.out.println("ffffffffffffffffffffff");
            }
        });
    }
}
