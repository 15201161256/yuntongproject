package com.example.yuntong.base;

import android.util.SparseArray;

import com.example.yuntong.mvp.fragment.contract.ContractFragment;
import com.example.yuntong.mvp.fragment.homefragment.HomeFragment;
import com.example.yuntong.mvp.fragment.instrument.InstrumentFragment;
import com.example.yuntong.mvp.fragment.mefragment.MeFragment;
import com.example.yuntong.mvp.fragment.materials.MaterialsFragment;

/**
 * Fragments工厂   该工厂的fragment只创建一次
 * Created by zh
 */
public class FragmentsFactory {

    public static final int MENU_HOME = 0;
    public static final int MENU_CONSTRACT = 1;
    public static final int MENU_MATERIALS = 2;
    public static final int MENU_INSTRUMENT = 3;
    public static final int MENU_ME = 4;
    public static final int LOCAL_VIDEO = 5;
    public static final int MENU_TOU_TIAO = 6;
    // private static Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
    // android  APi SparseArray代替HashMap 更为高效
    private static SparseArray<BaseFragment> mFragments = new SparseArray<BaseFragment>();
    
    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mFragments.get(position);
        if(fragment == null){
            switch (position) {
                case MENU_HOME:
                    fragment = new HomeFragment();
                    break;
                case MENU_CONSTRACT:
                    fragment = new ContractFragment();
                    break;
                case MENU_MATERIALS:
                    fragment = new MaterialsFragment();
                    break;
                case MENU_INSTRUMENT:
                    fragment = new InstrumentFragment();
                    break;
                case MENU_ME:
                    fragment = new MeFragment();
                    break;
                case LOCAL_VIDEO:
                    break;
                case MENU_TOU_TIAO:
                    break;
                default:
                    break;
            }
            mFragments.put(position, fragment);
        }
        return fragment;
    }

    public static BaseFragment getFragment(int position) {
        return mFragments.get(position,null);
    }
}
