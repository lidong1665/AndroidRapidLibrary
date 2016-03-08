package com.lidong.android_ibrary.switchlayout;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 */
public class BaseEffects {
	public static Interpolator acceInter = new AccelerateInterpolator();
	public static Interpolator deceInter = new DecelerateInterpolator();
	public static Interpolator acceToDeceInter = new AccelerateDecelerateInterpolator();
	public static Interpolator anticInter = new AnticipateInterpolator();
	public static Interpolator overInter = new OvershootInterpolator();
	public static Interpolator anticOverInter = new AnticipateOvershootInterpolator();
	public static Interpolator bounInter = new BounceInterpolator();
	public static Interpolator linearInter = new LinearInterpolator();

	public static Interpolator getMoreQuickEffect() {
		return (Interpolator) acceInter;
	}

	public static Interpolator getMoreSlowEffect() {
		return deceInter;
	}

	public static Interpolator getQuickToSlowEffect() {
		return acceToDeceInter;
	}

	public static Interpolator getBackQuickToForwardEffect() {
		return anticInter;
	}

	public static Interpolator getQuickReScrollEffect() {
		return overInter;
	}

	public static Interpolator getReScrollEffect() {
		return anticOverInter;
	}

	public static Interpolator getBounceEffect() {
		return bounInter;
	}

	public static Interpolator getLinearInterEffect() {
		return linearInter;
	}
}
