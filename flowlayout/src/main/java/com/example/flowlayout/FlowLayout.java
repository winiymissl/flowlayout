package com.example.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2023-12-06 11:07
 * @Version 1.0
 */
public class FlowLayout extends ViewGroup {
    private int mHorizontalSpacing = 1;
    private int mVerticalSpacing = 1;
    private List<List<View>> allLines = new ArrayList<>();
    private List<Integer> lineHeights = new ArrayList<>();

    //记录所有行，一行一行存储，用于layout
    void init() {
        allLines.clear();
        //记录每一行的行高，用于layout
        lineHeights.clear();
    }


    public FlowLayout(Context context) {
        super(context);

    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i = 0; i < allLines.size(); i++) {
            List<View> lineViews = allLines.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                //一个结点，一个view
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;

                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = right + mHorizontalSpacing;
            }
            //加上行高和垂直间距就可以了
            //加上行高和垂直间距就可以了
            curT = curT + lineHeights.get(i) + mVerticalSpacing;
            curL = getPaddingLeft();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //父亲可能会多次调用onMeasure，需要在这里清零
        init();
//        //整体思路
//        //难点：四个参数怎么来的
//        //先测量孩子
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            //按照下标得到每一个Child
//            View child = getChildAt(i);
//            child.measure(widthMeasureSpec, heightMeasureSpec);
//        }
//        //测量自己,保存，
//        setMeasuredDimension(width,height);


        //得到padding
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        //保存一行中所有的view
        List<View> lineViews = new ArrayList<>();
        //记录这行已经使用了多宽的size
        int lineWidthUsed = 0;
        //一行的行高
        int lineHeightUsed = 0;

        //作为测量时的参考值
        //父亲给我的宽度
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        //父亲给我的高度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int parentNeededWidth = 0;
        int parentNeededHeight = 0;


        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //通过父亲的参数，padding和上一层传递下来的Measure，子view的基本布局参数，得到子view的值
            //按照下标得到每一个Child
            View child = getChildAt(i);
            //可能需要自己定义LayoutParams类，继承原来的，之后继承，这种其情况，
            LayoutParams childLayoutParams = child.getLayoutParams();
            //计算子view真正的大小。padding来自父亲,计算父view，需要减去父view的padding，
            //才能算是孩子可以规划的区域。
            int childMeasureSpecWidth = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width);
            int childMeasureSpecHeight = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.height);

            //需要解析孩子的大小
            child.measure(widthMeasureSpec, heightMeasureSpec);
            //这是一个void函数，最终测量完成之后，会保存下来，所以可以直接获取了
            //获取孩子的宽高信息：
            int childMeasuredHeight = child.getMeasuredHeight();
            int childMeasuredWidth = child.getMeasuredWidth();
            //接下来就是自定义viewGroup的算法了
            //流式布局的宽度是整个宽度最宽的一个。
            //高度是所有高度加起来
            if (lineWidthUsed + mHorizontalSpacing + childMeasuredWidth > selfWidth) {
                allLines.add(lineViews);
                lineHeights.add(lineHeightUsed);
                parentNeededHeight = parentNeededHeight + lineHeightUsed + mVerticalSpacing;
                //行与行之间 有空格
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeightUsed = 0;
            }
            lineViews.add(child);
            lineWidthUsed = lineWidthUsed + childMeasuredWidth + mHorizontalSpacing;
            lineHeightUsed = Math.max(lineHeightUsed, childMeasuredHeight);
            //处理最后一行
            //最后一行，没有被算在里面去
            if (i == childCount - 1) {
                allLines.add(lineViews);
                lineHeights.add(lineHeightUsed);
                parentNeededHeight = parentNeededHeight + lineHeightUsed + mVerticalSpacing;
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
            }
        }
        //测量自己,保存，
        //考虑自己的mode
        //自自己虽然是一个viewGroup，但同时也是一个子view
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //对比父亲的view模式之后，得到自己真正的宽高
        //如果是一个确切的值就没有必要管子view的宽高了
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededHeight;
        setMeasuredDimension(realWidth, realHeight);
    }
}
