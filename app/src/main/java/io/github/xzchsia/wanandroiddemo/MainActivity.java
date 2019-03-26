package io.github.xzchsia.wanandroiddemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_nav) BottomNavigationViewEx bottomNavigationView;

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        // 创建 fragment
        createFragments();
        // 默认选中第一个 fragment，即 BottomNavigationView 的第一项
        selectFragment(0);
        getSupportActionBar().setTitle(R.string.home);

        setupBottomNavigationView();

    }

    /**
     * 创建 fragment
     */
    public void createFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // todo 目前测试使用Fragment，后续修改
        BlankFragment homeFragment = new BlankFragment();
        fragmentList.add(homeFragment);
        fragmentTransaction.add(R.id.fragment_container, homeFragment);

        BlankFragment projectFragment = new BlankFragment();
        fragmentList.add(projectFragment);
        fragmentTransaction.add(R.id.fragment_container, projectFragment);

        BlankFragment hierarchyFragment = new BlankFragment();
        fragmentList.add(hierarchyFragment);
        fragmentTransaction.add(R.id.fragment_container, hierarchyFragment);

        BlankFragment navigationFragment = new BlankFragment();
        fragmentList.add(navigationFragment);
        fragmentTransaction.add(R.id.fragment_container, navigationFragment);

        BlankFragment mineFragment = new BlankFragment();
        fragmentList.add(mineFragment);
        fragmentTransaction.add(R.id.fragment_container, mineFragment);

        fragmentTransaction.commit();
    }

    /**
     * 根据给定下标选中对应的 fragment
     * @param index fragment 在列表中的下标
     */
    public void selectFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i == index) {
                fragmentTransaction.show(fragmentList.get(i));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * 设置 BottomNavigationView
     */
    public void setupBottomNavigationView() {
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean setElevation = true;
                        int titleResId = R.string.home;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectFragment(0);
                                titleResId = R.string.home;
                                break;
                            case R.id.project:
                                selectFragment(1);
                                setElevation = false;
                                titleResId = R.string.project;
                                break;
                            case R.id.hierarchy:
                                selectFragment(2);
                                titleResId = R.string.hierarchy;
                                break;
                            case R.id.navigation:
                                selectFragment(3);
                                titleResId = R.string.navigation;
                                break;
                            case R.id.mine:
                                selectFragment(4);
                                titleResId = R.string.mine;
                                break;
                        }
//                        enableAppBarElevation(setElevation);
                        getSupportActionBar().setTitle(titleResId);
                        return true;
                    }
                });
    }
}
