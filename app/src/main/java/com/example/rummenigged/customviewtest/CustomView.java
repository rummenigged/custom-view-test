package com.example.rummenigged.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rummenigged on 14/02/18.
 */

public class CustomView extends LinearLayout {
    private String title;
    private String subTitle;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.custom_view, this, true);

        TypedArray tArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            title = tArray.getString(R.styleable.CustomView_customViewTitle);
            subTitle = tArray.getString(R.styleable.CustomView_customViewSubtitle);
        }finally {
            tArray.recycle();
        }

        if (title == null){
            throw new RuntimeException("No Title Provided");
        }

        if (subTitle == null){
            throw new RuntimeException("No Subtitle Provided");
        }

        init(title, subTitle);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(String title, String subtitle){
        TextView titleView = findViewById(R.id.customview_textview_title);
        TextView subtitleView = findViewById(R.id.customview_textview_subtitle);

        titleView.setText(title);
        subtitleView.setText(subtitle);
    }

    public void setTitle(String title){
        this.title = title;
    }
}
