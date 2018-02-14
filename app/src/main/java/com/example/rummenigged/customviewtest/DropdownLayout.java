package com.example.rummenigged.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by rummenigged on 14/02/18.
 */

public class DropdownLayout extends LinearLayout implements View.OnClickListener{

    private RelativeLayout mContainerTitle;
    private TextView mTitle;
    private TextView mSubtitle;
    private ImageView mIcon;

    private String styleableTitle;
    private String styleableSubtitle;
    int styleableIconDown;
    int styleableIconUp;
    private boolean contentVisible = true;

    public DropdownLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.dropdown_layout, this, true);
        setOrientation(VERTICAL);
        TypedArray tArray = context.obtainStyledAttributes(attrs,R.styleable.DropdownLayout, 0, 0);

        try{
            styleableTitle = tArray.getString(R.styleable.DropdownLayout_dropdownTitle);
            styleableSubtitle = tArray.getString(R.styleable.DropdownLayout_dropdownSubtitle);
            styleableIconDown = tArray.getInt(R.styleable.DropdownLayout_dropdownIconDown, R.drawable.ic_action_dropdown_down);
            styleableIconUp = tArray.getInt(R.styleable.DropdownLayout_dropdownIconUp, R.drawable.ic_action_dropdown_up);

        }finally {
            tArray.recycle();
        }

        if (styleableTitle == null){
            throw new RuntimeException("No title Provided");
        }

        init(styleableTitle, styleableSubtitle);
    }

    private void init(String title, String subtitle){
        mContainerTitle = findViewById(R.id.rl_container);
        mTitle = findViewById(R.id.dropdown_title);
        mSubtitle = findViewById(R.id.dropdown_subtitle);
        mIcon = findViewById(R.id.dropdown_icon);

        mTitle.setText(title);
        mSubtitle.setText(subtitle);
        mIcon.setImageResource(styleableIconUp);
        mContainerTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_container:
//                setVisibility(getVisibility() == GONE ? VISIBLE : GONE);
                Log.d("DropdownLayout", "onClick: ");
                int cont = getChildCount();
                if (contentVisible){
                    for (int i = 1; i < cont; i++) {
                        getChildAt(i).setVisibility(View.GONE);
                    }
                    contentVisible = false;
                    mIcon.setImageResource(styleableIconDown);
                }else {
                    for (int i = 1; i < cont; i++) {
                        getChildAt(i).setVisibility(View.VISIBLE);
                    }
                    contentVisible = true;
                    mIcon.setImageResource(styleableIconUp);
                }
            break;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
