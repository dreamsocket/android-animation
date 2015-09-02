package com.dreamsocket.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by keithpeters on 9/1/15.
 */
public class AnimationSequencer {
    protected int m_defaultDuration = 1000;
    protected int m_defaultStartDelay = 0;

    protected AnimatorSet m_masterAnimatorSet;
    protected ArrayList<Animator> m_masterAnimationList;
    protected Boolean m_isPlaying;
    protected Object m_target;
    protected String m_masterPlayMode;
    protected Interpolator m_interpolator;

    protected AnimationSequencer(String p_playMode) {
        this.m_masterPlayMode = p_playMode;
        this.m_masterAnimatorSet = new AnimatorSet();
        this.m_masterAnimationList = new ArrayList<>();
        this.m_isPlaying = false;
        this.m_interpolator = new LinearInterpolator();
    }

    public static AnimationSequencer newSequence() {
        return new AnimationSequencer("sequence");
    }

    public static AnimationSequencer newParallel() {
        return new AnimationSequencer("parallel");
    }

    public AnimationSequencer target(Object p_target) {
        this.m_target = p_target;
        return this;
    }

    public AnimationSequencer setDefaultDuration(int p_value) {
        this.m_defaultDuration = p_value;
        return this;
    }

    public int getDefaultDuration() {
        return this.m_defaultDuration;
    }

    public AnimationSequencer setDefaultStartDelay(int p_value) {
        this.m_defaultStartDelay = p_value;
        return this;
    }

    public int getDefaultStartDelay() {
        return this.m_defaultStartDelay;
    }

    public AnimationSequencer fromTo(String p_property, float p_start, float p_end) {
        return this.fromTo(p_property, p_start, p_end, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer fromTo(String p_property, float p_start, float p_end, int p_duration) {
        return this.fromTo(p_property, p_start, p_end, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer fromTo(String p_property, float p_start, float p_end, int p_duration, int p_startDelay) {
        if(this.m_target != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(this.m_target, p_property, p_start, p_end);
            animator.setDuration(p_duration);
            animator.setStartDelay(p_startDelay);
            animator.setInterpolator(this.m_interpolator);
            this.m_masterAnimationList.add(animator);
        }
        return this;
    }

    public AnimationSequencer to(String p_property, float p_end) {
        return this.to(p_property, p_end, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer to(String p_property, float p_end, int p_duration) {
        return this.to(p_property, p_end, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer to(String p_property, float p_end, int p_duration, int p_startDelay) {
        if(this.m_target != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(this.m_target, p_property, p_end);
            animator.setDuration(p_duration);
            animator.setStartDelay(p_startDelay);
            animator.setInterpolator(this.m_interpolator);
            this.m_masterAnimationList.add(animator);
        }
        return this;
    }

    public AnimationSequencer setInterpolator(Interpolator p_interpolator) {
        this.m_interpolator = p_interpolator;
        return this;
    }

    public AnimationSequencer insert(AnimationSequencer p_sequencer) {
        this.m_masterAnimationList.add(p_sequencer.getAnimator());
        return this;
    }

    public AnimationSequencer insert(Animator p_animator) {
        this.m_masterAnimationList.add(p_animator);
        return this;
    }

    public Animator getAnimator() {
        this.addAnimations();
        return this.m_masterAnimatorSet;
    }

    public AnimationSequencer start() {
        if(!this.m_isPlaying) {
            this.m_isPlaying = true;
            this.addAnimations();
            this.m_masterAnimatorSet.start();
        }
        return this;
    }

    protected void addAnimations() {
        if(this.m_masterPlayMode == "sequence") {
            this.m_masterAnimatorSet.playSequentially(this.m_masterAnimationList);
        }
        else {
            this.m_masterAnimatorSet.playTogether(this.m_masterAnimationList);
        }
    }


    public AnimationSequencer cancel() {
        this.m_masterAnimatorSet.cancel();
        return this;
    }

    public AnimationSequencer setStartDelay(int p_startDelay) {
        this.m_masterAnimatorSet.setStartDelay(p_startDelay);
        return this;
    }

    public AnimationSequencer addListener(Animator.AnimatorListener p_listener) {
        this.m_masterAnimatorSet.addListener(p_listener);
        return this;
    }

    public AnimationSequencer alpha(float p_value) {
        return this.to("alpha", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer alpha(float p_value, int p_duration) {
        return this.to("alpha", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer alpha(float p_value, int p_duration, int p_startDelay) {
        return this.to("alpha", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer x(float p_value) {
        return this.to("x", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer x(float p_value, int p_duration) {
        return this.to("x", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer x(float p_value, int p_duration, int p_startDelay) {
        return this.to("x", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer y(float p_value) {
        return this.to("y", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer y(float p_value, int p_duration) {
        return this.to("y", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer y(float p_value, int p_duration, int p_startDelay) {
        return this.to("y", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer moveTo(float p_x, float p_y) {
        return this.moveTo(p_x, p_y, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer moveTo(float p_x, float p_y, int p_duration) {
        return this.moveTo(p_x, p_y, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer moveTo(float p_x, float p_y, int p_duration, int p_startDelay) {
        return this.insert(AnimationSequencer.newParallel()
                .target(this.m_target)
                .to("x", p_x, p_duration, p_startDelay)
                .to("y", p_y, p_duration, p_startDelay));
    }

    public AnimationSequencer rotation(float p_value) {
        return this.to("rotation", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer rotation(float p_value, int p_duration) {
        return this.to("rotation", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer rotation(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotation", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer rotationX(float p_value) {
        return this.to("rotationX", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer rotationX(float p_value, int p_duration) {
        return this.to("rotationX", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer rotationX(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotationX", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer rotationY(float p_value) {
        return this.to("rotationY", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer rotationY(float p_value, int p_duration) {
        return this.to("rotationY", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer rotationY(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotationY", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scaleX(float p_value) {
        return this.to("scaleX", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer scaleX(float p_value, int p_duration) {
        return this.to("scaleX", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer scaleX(float p_value, int p_duration, int p_startDelay) {
        return this.to("scaleX", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scaleY(float p_value) {
        return this.to("scaleY", p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer scaleY(float p_value, int p_duration) {
        return this.to("scaleY", p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer scaleY(float p_value, int p_duration, int p_startDelay) {
        return this.to("scaleY", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scale(float p_value) {
        return this.scale(p_value, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer scale(float p_value, int p_duration) {
        return this.scale(p_value, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer scale(float p_value, int p_duration, int p_startDelay) {
        return this.insert(AnimationSequencer.newParallel()
                .target(this.m_target)
                .to("scaleX", p_value, p_duration, p_startDelay)
                .to("scaleY", p_value, p_duration, p_startDelay));
    }

    public AnimationSequencer width(int p_start, int p_end) {
        return this.width(p_start, p_end, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer width(int p_start, int p_end, int p_duration) {
        return this.width(p_start, p_end, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer width(int p_start, int p_end, int p_duration, int p_startDelay) {
        if(this.m_target != null && this.m_target instanceof View) {
            final View v = (View)this.m_target;
            final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            ValueAnimator animator = ValueAnimator.ofInt(p_start, p_end);
            animator.setDuration(p_duration);
            animator.setStartDelay(p_startDelay);
            animator.setInterpolator(this.m_interpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    layoutParams.width = (Integer)animation.getAnimatedValue();
                    v.requestLayout();
                }
            });
            this.m_masterAnimationList.add(animator);
        }
        return this;
    }

    public AnimationSequencer height(int p_start, int p_end) {
        return this.height(p_start, p_end, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer height(int p_start, int p_end, int p_duration) {
        return this.height(p_start, p_end, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer height(int p_start, int p_end, int p_duration, int p_startDelay) {
        if(this.m_target != null && this.m_target instanceof View) {
            final View v = (View)this.m_target;
            final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            ValueAnimator animator = ValueAnimator.ofInt(p_start, p_end);
            animator.setDuration(p_duration);
            animator.setStartDelay(p_startDelay);
            animator.setInterpolator(this.m_interpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    layoutParams.height = (Integer)animation.getAnimatedValue();
                    v.requestLayout();
                }
            });
            this.m_masterAnimationList.add(animator);
        }
        return this;
    }

    public AnimationSequencer size(int p_startWidth, int p_startHeight, int p_endWidth, int p_endHeight) {
        return this.size(p_startWidth, p_startHeight, p_endWidth, p_endHeight, m_defaultDuration, m_defaultStartDelay);
    }

    public AnimationSequencer size(int p_startWidth, int p_startHeight, int p_endWidth, int p_endHeight, int p_duration) {
        return this.size(p_startWidth, p_startHeight, p_endWidth, p_endHeight, p_duration, m_defaultStartDelay);
    }

    public AnimationSequencer size(int p_startWidth, int p_startHeight, int p_endWidth, int p_endHeight, int p_duration, int p_startDelay) {
        return this.insert(
                AnimationSequencer.newParallel()
                        .target(this.m_target)
                        .width(p_startWidth, p_endWidth, p_duration, p_startDelay)
                        .height(p_startHeight, p_endHeight, p_duration, p_startDelay)
        );
    }



}
