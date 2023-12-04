package com.example.beautifulspinner;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * @Author winiymissl
 * @Date 2023-12-04 22:56
 * @Version 1.0
 */
public class BeautifulSpinner extends androidx.appcompat.widget.AppCompatSpinner {
    public BeautifulSpinner(Context context) {
        super(context);
        init();
    }

    public BeautifulSpinner(Context context, int mode) {
        super(context, mode);
        init();
    }

    public BeautifulSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BeautifulSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BeautifulSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }

    private void init() {

    }
}
