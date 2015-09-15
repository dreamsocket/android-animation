package com.dreamsocket.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dreamsocket.animationtest.R;

import java.util.ArrayList;

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
    protected ArrayList<View> m_sides;
    protected int m_index;

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
        this.m_sides = new ArrayList<>();

        this.m_view1 = new FrameLayout(this.getContext());
        this.m_view1.setBackgroundColor(0xffeeeeee);
        this.addView(this.m_view1);

        this.m_view2 = new FrameLayout(this.getContext());
        this.m_view2.setBackgroundColor(0xffeeeeee);
        this.addView(this.m_view2);

        this.m_rotation = 0;
        this.m_index = -1;
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
        this.setSides();
        float rotation = this.m_rotation % 90;
        float percent = rotation / 90;
        this.m_view1.setPivotX(0);
        this.m_view2.setPivotX(this.m_width);
        this.m_view2.setTranslationX(percent * this.m_width - this.m_width);
        this.m_view2.setRotationY(rotation - 90);
        this.m_view1.setRotationY(rotation);
        this.m_view1.setTranslationX(percent * this.m_width);
    }

    protected void rotateVertical() {
        this.setSides();
        float rotation = this.m_rotation % 90;
        float percent = rotation / 90;
        this.m_view1.setPivotY(0);
        this.m_view2.setPivotY(this.m_height);
        this.m_view2.setTranslationY(percent * this.m_height - this.m_height);
        this.m_view2.setRotationX(90 - rotation);
        this.m_view1.setRotationX(-rotation);
        this.m_view1.setTranslationY(percent * this.m_height);
    }


    protected void setSides() {
        if(this.m_sides.size() > 0) {
            int index = (int) (this.m_rotation / 90) % this.m_sides.size();
            Log.d("stuff", "rotation: " + this.m_rotation);
            Log.d("stuff", "index: " + index);
            if(this.m_index != index) {
                this.m_index = index;
                this.m_view1.removeAllViews();
                this.m_view2.removeAllViews();
                this.m_view1.addView(this.m_sides.get(this.m_index));
                this.m_view2.addView(this.m_sides.get((this.m_index + 1) % this.m_sides.size()));
            }
        }
    }

    public void setSize(int p_width, int p_height) {
        this.m_width = p_width;
        this.m_height = p_height;
        this.doLayout();
    }

    public void setDrawables(int p_resId1, int p_resId2) {
        View side1 = new View(this.getContext());
        side1.setBackground(this.getContext().getDrawable(p_resId1));
        View side2 = new View(this.getContext());
        side2.setBackground(this.getContext().getDrawable(p_resId2));
        this.setViews(side1, side2);
    }

    public void setDrawables(int p_resId1, int p_resId2, int p_resId3, int p_resId4) {
        View side1 = new View(this.getContext());
        side1.setBackground(this.getContext().getDrawable(p_resId1));
        View side2 = new View(this.getContext());
        side2.setBackground(this.getContext().getDrawable(p_resId2));
        View side3 = new View(this.getContext());
        side3.setBackground(this.getContext().getDrawable(p_resId3));
        View side4 = new View(this.getContext());
        side4.setBackground(this.getContext().getDrawable(p_resId4));
        this.setViews(side1, side2, side3, side4);
    }

    public void setViews(View p_view1, View p_view2) {
        this.m_sides.add(p_view1);
        this.m_sides.add(p_view2);
        this.setSides();
    }

    public void setViews(View p_view1, View p_view2, View p_view3, View p_view4) {
        this.m_sides.add(p_view1);
        this.m_sides.add(p_view2);
        this.m_sides.add(p_view3);
        this.m_sides.add(p_view4);
        this.setSides();
    }


    public void rotate(float p_angle) {
        p_angle %= 360;
        if(p_angle < -0) {
            p_angle += 360;
        }
        this.m_rotation = p_angle;
        this.doRotation();
    }

    public void setOrientation(int p_orientation) {
        this.m_orientation = p_orientation;
        this.doLayout();
    }



}
