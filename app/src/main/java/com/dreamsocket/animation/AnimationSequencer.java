package com.dreamsocket.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by keithpeters on 9/1/15.
 */
public class AnimationSequencer {
    protected final int kDEFAULT_DURATION = 1000;
    protected final int kDEFAULT_DELAY = 0;

    protected AnimatorSet m_masterAnimatorSet;
    protected ArrayList<Animator> m_masterAnimationList;
    protected AnimatorSet m_currentAnimatorSet;
    protected ArrayList<Animator> m_currentAnimationList;
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

    public AnimationSequencer fromTo(String p_property, float p_start, float p_end) {
        return this.fromTo(p_property, p_start, p_end, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer fromTo(String p_property, float p_start, float p_end, int p_duration) {
        return this.fromTo(p_property, p_start, p_end, p_duration, kDEFAULT_DELAY);
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
        return this.to(p_property, p_end, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer to(String p_property, float p_end, int p_duration) {
        return this.to(p_property, p_end, p_duration, kDEFAULT_DELAY);
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
        return this.to("alpha", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer alpha(float p_value, int p_duration) {
        return this.to("alpha", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer alpha(float p_value, int p_duration, int p_startDelay) {
        return this.to("alpha", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer x(float p_value) {
        return this.to("x", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer x(float p_value, int p_duration) {
        return this.to("x", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer x(float p_value, int p_duration, int p_startDelay) {
        return this.to("x", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer y(float p_value) {
        return this.to("y", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer y(float p_value, int p_duration) {
        return this.to("y", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer y(float p_value, int p_duration, int p_startDelay) {
        return this.to("y", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer moveTo(float p_x, float p_y) {
        return this.moveTo(p_x, p_y, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer moveTo(float p_x, float p_y, int p_duration) {
        return this.moveTo(p_x, p_y, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer moveTo(float p_x, float p_y, int p_duration, int p_startDelay) {
        return this.insert(AnimationSequencer.newParallel()
                .target(this.m_target)
                .to("x", p_x, p_duration, p_startDelay)
                .to("y", p_y, p_duration, p_startDelay));
    }

    public AnimationSequencer rotation(float p_value) {
        return this.to("rotation", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotation(float p_value, int p_duration) {
        return this.to("rotation", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotation(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotation", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer rotationX(float p_value) {
        return this.to("rotationX", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotationX(float p_value, int p_duration) {
        return this.to("rotationX", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotationX(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotationX", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer rotationY(float p_value) {
        return this.to("rotationY", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotationY(float p_value, int p_duration) {
        return this.to("rotationY", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer rotationY(float p_value, int p_duration, int p_startDelay) {
        return this.to("rotationY", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scaleX(float p_value) {
        return this.to("scaleX", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer scaleX(float p_value, int p_duration) {
        return this.to("scaleX", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer scaleX(float p_value, int p_duration, int p_startDelay) {
        return this.to("scaleX", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scaleY(float p_value) {
        return this.to("scaleY", p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer scaleY(float p_value, int p_duration) {
        return this.to("scaleY", p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer scaleY(float p_value, int p_duration, int p_startDelay) {
        return this.to("scaleY", p_value, p_duration, p_startDelay);
    }

    public AnimationSequencer scale(float p_value) {
        return this.scale(p_value, kDEFAULT_DURATION, kDEFAULT_DELAY);
    }

    public AnimationSequencer scale(float p_value, int p_duration) {
        return this.scale(p_value, p_duration, kDEFAULT_DELAY);
    }

    public AnimationSequencer scale(float p_value, int p_duration, int p_startDelay) {
        return this.insert(AnimationSequencer.newParallel()
                .target(this.m_target)
                .to("scaleX", p_value, p_duration, p_startDelay)
                .to("scaleY", p_value, p_duration, p_startDelay));
    }



}
