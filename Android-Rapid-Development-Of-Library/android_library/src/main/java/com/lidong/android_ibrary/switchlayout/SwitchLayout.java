package com.lidong.android_ibrary.switchlayout;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;


/**
 * SwitchLayout
 */
public abstract class SwitchLayout {

	private static Activity activity;
	private static View v;
	private static Animation anim;
	private static ObjectAnimator objAnim;
	public static long animDuration = 600;
	public static long longAnimDuration = 1000;

	public static void getSlideFromBottom(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideFromBottom(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideFromBottom(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideFromBottom(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideToBottom(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideToBottom(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideToBottom(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideToBottom(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideFromTop(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideFromTop(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideFromTop(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideFromTop(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideToTop(Activity context, boolean isCloseActivity,
			Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideToTop(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideToTop(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideToTop(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideFromLeft(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideFromLeft(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideFromLeft(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideFromLeft(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideToLeft(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideToLeft(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideToLeft(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideToLeft(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideFromRight(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideFromRight(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideFromRight(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideFromRight(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getSlideToRight(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.SlideToRight(interpolator);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getSlideToRight(View view, Interpolator interpolator) {
		v = view;
		anim = BaseAnimViewS.SlideToRight(interpolator);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getFadingIn(Activity context) {
		anim = BaseAnimViewS.FadingIn();
		activity = context;
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getFadingIn(View view) {
		anim = BaseAnimViewS.FadingIn();
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getFadingOut(Activity context, boolean isCloseActivity) {
		anim = BaseAnimViewS.FadingOut();
		activity = context;
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void getFadingOut(View view, boolean isCloseActivity) {
		anim = BaseAnimViewS.FadingOut();
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void get3DRotateFromLeft(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		WindowManager wm = activity.getWindowManager();

		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		FlipAnimation rotate3dAnim = new FlipAnimation(width / 2, height / 2,
				false);
		if (interpolator != null) {
			rotate3dAnim.setInterpolator(interpolator);
		}
		rotate3dAnim.setDuration(animDuration);
		anim = rotate3dAnim;
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void get3DRotateFromLeft(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		FlipAnimation rotate3dAnim = new FlipAnimation(
				(view.getLeft() + (view.getWidth() / 2)) / 2,
				(view.getTop() + (view.getHeight() / 2)), false);
		if (interpolator != null) {
			rotate3dAnim.setInterpolator(interpolator);
		}
		rotate3dAnim.setDuration(animDuration);
		anim = rotate3dAnim;
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void get3DRotateFromRight(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		WindowManager wm = activity.getWindowManager();

		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		FlipAnimation rotate3dAnim = new FlipAnimation(width / 2, height / 2,
				true);
		if (interpolator != null) {
			rotate3dAnim.setInterpolator(interpolator);
		}
		rotate3dAnim.setDuration(animDuration);
		anim = rotate3dAnim;
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void get3DRotateFromRight(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		FlipAnimation rotate3dAnim = new FlipAnimation(
				(view.getLeft() + (view.getWidth() / 2)) / 2,
				(view.getTop() + (view.getHeight() / 2)), true);
		if (interpolator != null) {
			rotate3dAnim.setInterpolator(interpolator);
		}
		rotate3dAnim.setDuration(animDuration);
		anim = rotate3dAnim;
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void FlipUpDown(Activity context, boolean isCloseActivity,
			Interpolator interpolator) {
		activity = context;
		objAnim = ObjectAnimator.ofFloat(getRootView(context), "rotationX",
				-180, 0);
		if (interpolator != null) {
			objAnim.setInterpolator(interpolator);
		}
		objAnim.setDuration(animDuration);
		if (isCloseActivity) {
			objAnim.addListener(animatorListener);
		}
		AnimatorSet as = new AnimatorSet();
		as.play(objAnim);
		as.start();
	}

	public static void FlipUpDown(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		objAnim = ObjectAnimator.ofFloat(view, "rotationX", 0, 360);
		if (interpolator != null) {
			objAnim.setInterpolator(interpolator);
		}
		objAnim.setDuration(animDuration);
		if (isCloseActivity) {
			objAnim.addListener(animatorListener);
		}
		AnimatorSet as = new AnimatorSet();
		as.play(objAnim);
		as.start();
	}

	public static void RotateLeftCenterIn(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaLeftCenterIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateLeftCenterIn(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaLeftCenterIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void RotateLeftCenterOut(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaLeftCenterOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateLeftCenterOut(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaLeftCenterOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void RotateCenterIn(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaCenterIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateCenterIn(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaCenterIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void RotateCenterOut(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaCenterOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void RotateCenterOut(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaCenterOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateLeftTopIn(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaLeftTopIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateLeftTopIn(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaLeftTopIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void RotateLeftTopOut(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.RotaLeftTopOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void RotateLeftTopOut(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.RotaLeftTopOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleBig(Activity context, boolean isCloseActivity,
			Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleBig(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleBig(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleBig(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleSmall(Activity context, boolean isCloseActivity,
			Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleSmall(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleSmall(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleSmall(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleBigLeftTop(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleBigLeftTop(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleBigLeftTop(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleBigLeftTop(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleSmallLeftTop(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleSmallLeftTop(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleSmallLeftTop(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleSmallLeftTop(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getShakeMode(Activity context, boolean isCloseActivity,
			Interpolator interpolator, Integer shakeCount) {
		activity = context;
		anim = BaseAnimViewS.ShakeMode(interpolator, shakeCount);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
			anim.setFillAfter(true);
		}

		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleToBigHorizontalIn(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleToBigHorizontalIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleToBigHorizontalIn(View view,
			boolean isCloseActivity, Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleToBigHorizontalIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleToBigHorizontalOut(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleToBigHorizontalOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleToBigHorizontalOut(View view,
			boolean isCloseActivity, Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleToBigHorizontalOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleToBigVerticalIn(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleToBigVerticalIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleToBigVerticalIn(View view, boolean isCloseActivity,
			Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleToBigVerticalIn(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void ScaleToBigVerticalOut(Activity context,
			boolean isCloseActivity, Interpolator interpolator) {
		activity = context;
		anim = BaseAnimViewS.ScaleToBigVerticalOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		getRootView(activity).setAnimation(anim);
		getRootView(activity).startAnimation(anim);
	}

	public static void ScaleToBigVerticalOut(View view,
			boolean isCloseActivity, Interpolator interpolator) {
		anim = BaseAnimViewS.ScaleToBigVerticalOut(interpolator);
		anim.setDuration(animDuration);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
		}
		anim.setFillAfter(true);
		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	public static void getShakeMode(View view, boolean isCloseActivity,
			Interpolator interpolator, Integer shakeCount) {
		anim = BaseAnimViewS.ShakeMode(interpolator, shakeCount);
		if (isCloseActivity) {
			anim.setAnimationListener(animListener);
			anim.setFillAfter(true);
		}

		view.setAnimation(anim);
		view.startAnimation(anim);
	}

	/***************************************************************************/

	public static View getRootView(Activity context) {

		return ((ViewGroup) context.findViewById(android.R.id.content))
				.getChildAt(0);
	}

	private static AnimationListener animListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation arg0) {

		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation arg0) {
			activity.finish();
		}
	};
	private static AnimatorListener animatorListener = new AnimatorListener() {

		@Override
		public void onAnimationStart(Animator arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animator arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animator arg0) {
			activity.finish();

		}

		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub

		}
	};
}
