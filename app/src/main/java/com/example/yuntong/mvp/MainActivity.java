package com.example.yuntong.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.mvp.fragment.contract.ContractFragment;
import com.example.yuntong.mvp.fragment.homefragment.HomeFragment;
import com.example.yuntong.mvp.fragment.instrument.InstrumentFragment;
import com.example.yuntong.mvp.fragment.mefragment.MeFragment;
import com.example.yuntong.mvp.fragment.materials.MaterialsFragment;
import com.example.yuntong.utils.UiUtils;

import butterknife.ButterKnife;

/**
 * @author 杨晓峰
 * @create 2019/3/25
 * @Describe
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment homeFragment, contractFragment, materialsFragment, instrumentFragment, meFragment;

    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initRaView();
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.layFrame, homeFragment);
        transaction.commitAllowingStateLoss();
    }

    private void initRaView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        RadioButton tabHome = (RadioButton) radioGroup.getChildAt(0);
        tabHome.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction ft = manager.beginTransaction();
        switch (checkedId) {
            case R.id.home_rbtn:
                hideAll(ft);
                if (homeFragment != null) {
                    ft.show(homeFragment);
                } else {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.layFrame, homeFragment);
                }
                ft.commit();
                break;
            case R.id.find_rbtn:
                hideAll(ft);
                if (contractFragment != null) {
                    ft.show(contractFragment);
                } else {
                    contractFragment = new ContractFragment();
                    ft.add(R.id.layFrame, contractFragment);
                }
                ft.commit();
                break;
            case R.id.money_rbtn:
                hideAll(ft);
                if (materialsFragment != null) {
                    ft.show(materialsFragment);
                } else {
                    materialsFragment = new MaterialsFragment();
                    ft.add(R.id.layFrame, materialsFragment);
                }
                ft.commit();
                break;

            case R.id.instrument_rbtn:
                hideAll(ft);
                if (instrumentFragment != null) {
                    ft.show(instrumentFragment);
                } else {
                    instrumentFragment = new InstrumentFragment();
                    ft.add(R.id.layFrame, instrumentFragment);
                }
                ft.commit();
                break;
            case R.id.me_rbtn:
                hideAll(ft);
                if (meFragment != null) {
                    ft.show(meFragment);
                } else {
                    meFragment = new MeFragment();
                    ft.add(R.id.layFrame, meFragment);
                }
                ft.commit();
                break;

            default:
                break;
        }
    }

    private void hideAll(FragmentTransaction ft) {
        if (ft == null) {
            return;
        }
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (contractFragment != null) {
            ft.hide(contractFragment);
        }
        if (materialsFragment != null) {
            ft.hide(materialsFragment);
        }
        if (instrumentFragment != null) {
            ft.hide(instrumentFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
    }

    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            UiUtils.showToast(MainActivity.this, "再点一次退出运通");
            firstTime = secondTime;
        } else {
            this.finish();
            System.exit(0);
        }
    }
}
