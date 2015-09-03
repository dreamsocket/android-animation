package com.dreamsocket.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by keithpeters on 9/3/15.
 */
public class Cube3D extends FrameLayout {
    public final static int HORIZONTAL = 0;
    public final static int VERTICAL = 1;

    protected FrameLayout m_view1;
    protected FrameLayout m_view2;
    protected int m_width;
    protected int m_height;
    protected float m_rotation;
    protected int m_orientation;

    public Cube3D(Context p_context) {
        this(p_context, null, 0);    }

    public Cube3D(Context p_context, AttributeSet p_attrs) {
        this(p_context, p_attrs, 0);
    }

    public Cube3D(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    protected void init() {
        this.m_view1 = new FrameLayout(this.getContext());
        this.m_view1.setBackgroundColor(0xffeeeeee);
        this.addView(this.m_view1);

        this.m_view2 = new FrameLayout(this.getContext());
        this.m_view2.setBackgroundColor(0xffeeeeee);
        this.addView(this.m_view2);

        this.m_rotation = 0;
        this.m_orientation = HORIZONTAL;
        this.setSize(100, 100);
    }

    protected void doLayout() {
        this.m_view1.setRotationX(0);
        this.m_view1.setRotationY(0);
        this.m_view1.setTranslationX(0);
        this.m_view1.setTranslationY(0);
        this.m_view2.setRotationX(0);
        this.m_view2.setRotationY(0);
        this.m_view2.setTranslationX(0);
        this.m_view2.setTranslationY(0);

        ViewGroup.LayoutParams params = this.m_view1.getLayoutParams();
        params.width = this.m_width;
        params.height = this.m_height;
        this.m_view1.setLayoutParams(params);

        params = this.m_view2.getLayoutParams();
        params.width = this.m_width;
        params.height = this.m_height;
        this.m_view2.setLayoutParams(params);


        if(this.m_orientation == VERTICAL) {
            this.m_view1.setPivotX(this.m_width / 2);
            this.m_view2.setPivotX(this.m_width / 2);
        }
        else {
            this.m_view1.setPivotY(this.m_height / 2);
            this.m_view2.setPivotY(this.m_height / 2);
        }

        this.doRotation();
    }

    protected void doRotation() {
        if (this.m_orientation == VERTICAL) {
            this.rotateVertical();
        } else {
            this.rotateHorizontal();
        }
    }

    protected void rotateHorizontal() {
        float percent = this.m_rotation / 90;
        if(this.m_rotation >= 0) {
            this.m_view1.setPivotX(0);
            this.m_view2.setPivotX(this.m_width);
            this.m_view2.setTranslationX(percent * this.m_width - this.m_width);
            this.m_view2.setRotationY(this.m_rotation - 90);
        }
        else {
            this.m_view1.setPivotX(this.m_width);
            this.m_view2.setPivotX(0);
            this.m_view2.setTranslationX(this.m_width + percent * this.m_width);
            this.m_view2.setRotationY(90 + this.m_rotation);
        }
        this.m_view1.setRotationY(this.m_rotation);
        this.m_view1.setTranslationX(percent * this.m_width);
    }

    protected void rotateVertical() {
        float percent = this.m_rotation / 90;
        if(this.m_rotation >= 0) {
            this.m_view1.setPivotY(0);
            this.m_view2.setPivotY(this.m_height);
            this.m_view2.setTranslationY(percent * this.m_height - this.m_height);
            this.m_view2.setRotationX(90 - this.m_rotation);
        } else {
            this.m_view1.setPivotY(this.m_height);
            this.m_view2.setPivotY(0);
            this.m_view2.setTranslationY(this.m_height + percent * this.m_height);
            this.m_view2.setRotationX(-this.m_rotation - 90);
        }
        this.m_view1.setRotationX(-this.m_rotation);
        this.m_view1.setTranslationY(percent * this.m_height);
    }

    public void setSize(int p_width, int p_height) {
        this.m_width = p_width;
        this.m_height = p_height;
        this.doLayout();
    }

    public void setViews(View p_view1, View p_view2) {
        this.m_view1.addView(p_view1);
        this.m_view2.addView(p_view2);
    }

    public void rotate(float p_angle) {
        while(p_angle > 90) {
            p_angle -= 180;
        }
        while(p_angle < -90) {
            p_angle += 180;
        }
        this.m_rotation = p_angle;
        this.doRotation();
    }

    public void setOrientation(int p_orientation) {
        this.m_orientation = p_orientation;
        this.doLayout();
    }



}
