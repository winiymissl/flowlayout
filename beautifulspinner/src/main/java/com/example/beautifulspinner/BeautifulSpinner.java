package com.example.beautifulspinner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

/**
 * @Author winiymissl
 * @Date 2023-12-05 13:58
 * @Version 1.0
 */
public class BeautifulSpinner extends FrameLayout {
    private Context mContext;

    private boolean isClosed = true;
    private ImageView iv_indicator;
    private TextView tv_title;
    private ListView lv_content;
    private LinearLayout ll;
    private ObjectAnimator objectAnimator = isClosed ? ObjectAnimator.ofFloat(iv_indicator, "rotation", 180f, 0f) : ObjectAnimator.ofFloat(iv_indicator, "rotation", 0f, 180f);

    public BeautifulSpinner(@NonNull Context context) {
        super(context);
        this.mContext = context;
        init();
    }


    public BeautifulSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BeautifulSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public BeautifulSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        init();
    }

    private void reverseAnim() {
        float temp = 0f;
        if (isClosed) {
            temp = 180f;
        }
        ObjectAnimator o = ObjectAnimator.ofFloat(iv_indicator, "rotation", (Float) objectAnimator.getAnimatedValue(), temp);
        o.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                //修改旋转方向
                isClosed = !isClosed;
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.beautiful_spinner, this, true);
        ll = view.findViewById(R.id.ll_Title);

        ll.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()) {
                    anim();
                } else {
//                    objectAnimator.cancel();
//                    reverseAnim();
//                    objectAnimator.reverse();
                }
            }
        });
        iv_indicator = view.findViewById(R.id.iv_indicator);
        tv_title = view.findViewById(R.id.tv_title);
        lv_content = view.findViewById(R.id.lv_content);
    }

    private void anim() {
        //让指示器转
        objectAnimator = isClosed ? ObjectAnimator.ofFloat(iv_indicator, "rotation", 0f, 180f) : ObjectAnimator.ofFloat(iv_indicator, "rotation", 180f, 0f);
        objectAnimator.setDuration(600);
        objectAnimator.start();
        isClosed = !isClosed;

        //出现ListView
        final int answerHeight = tv_title.getMeasuredHeight();
        final int listview = lv_content.getMeasuredHeight();
    }
}
