package com.example.yuntong.mvp.activity.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dk.view.patheffect.PathTextView;
import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.mvp.MainActivity;
import com.example.yuntong.mvp.activity.login.LoginActivity;
import com.jaeger.library.StatusBarUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.path)
    PathTextView mPathTextView;

    private static final int ANIM_TIME = 3000;

    private static final float SCALE_END = 1.15F;

    private static final int[] Imgs = {R.drawable.a
//            R.drawable.timg,
//            R.drawable.welcomimg5, R.drawable.welcomimg7, R.drawable.welcomimg8,
//            R.drawable.welcomimg10
    };
    @BindView(R.id.iv_entry)
    ImageView mIVEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.BLACK);
        startMainActivity();
    }

    private void startMainActivity() {
        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(Imgs[random.nextInt(Imgs.length)]);
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        startAnim();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void startAnim() {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mPathTextView.init("YUN TONG");
        mPathTextView.setPaintType(PathTextView.Type.SINGLE);
        mPathTextView.setTextColor(Color.WHITE);
        mPathTextView.setTextWeight(5);
        mPathTextView.setDuration(2);
    }

    @Override
    protected void initData() {

    }
}
