package com.lidong.android_ibrary.switchlayout;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * ����Ϊʹ�����˵���
 * 
 * @author 85204_000
 * 
 */
public class FlipAnimation extends Animation {
	/** ֵΪtrueʱ����ȷ�鿴��������ת���� */
	public static final boolean DEBUG = false;
	/** ��Y�������򿴣���ֵ��1ʱ������ʱ����ת�� */
	public static final boolean ROTATE_DECREASE = true;
	/** ��Y�������򿴣���ֵ��1ʱ����˳ʱ����ת�� */
	public static final boolean ROTATE_INCREASE = false;
	/** Z���������ȡ� */
	public static final float DEPTH_Z = 310.0f;
	/** ������ʾʱ���� */
	public static final long DURATION = 800l;
	/** ͼƬ��ת���͡� */
	private final boolean type;
	private final float centerX;
	private final float centerY;
	private Camera camera;
	/** ���ڼ����������ȡ���ֵ����ʱ�����txtNumber�����ݡ� */
	private InterpolatedTimeListener listener;

	public FlipAnimation(float cX, float cY, boolean type) {
		centerX = cX;
		centerY = cY;
		this.type = type;
		setDuration(DURATION);
	}

	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		// �ڹ��캯��֮��getTransformation()֮ǰ���ñ�������
		super.initialize(width, height, parentWidth, parentHeight);
		camera = new Camera();
	}

	public void setInterpolatedTimeListener(InterpolatedTimeListener listener) {
		this.listener = listener;
	}

	protected void applyTransformation(float interpolatedTime,
			Transformation transformation) {
		// interpolatedTime:��������ֵ����ΧΪ[0.0f,10.f]
		if (listener != null) {
			listener.interpolatedTime(interpolatedTime);
		}
		float from = 0.0f, to = 0.0f;
		if (type == ROTATE_DECREASE) {
			from = 0.0f;
			to = 180.0f;
		} else if (type == ROTATE_INCREASE) {
			from = 360.0f;
			to = 180.0f;
		}
		float degree = from + (to - from) * interpolatedTime;
		boolean overHalf = (interpolatedTime > 0.5f);
		if (overHalf) {
			// ��ת���������£�Ϊ��֤������Ϊ�ɶ������ֶ��Ǿ���Ч�������֣��跭ת180�ȡ�
			degree = degree - 180;
		}
		// float depth = 0.0f;
		float depth = (0.5f - Math.abs(interpolatedTime - 0.5f)) * DEPTH_Z;
		final Matrix matrix = transformation.getMatrix();
		camera.save();
		camera.translate(0.0f, 0.0f, depth);
		camera.rotateY(degree);
		camera.getMatrix(matrix);
		camera.restore();
		if (DEBUG) {
			if (overHalf) {
				matrix.preTranslate(-centerX * 2, -centerY);
				matrix.postTranslate(centerX * 2, centerY);
			}
		} else {
			// ȷ��ͼƬ�ķ�ת����һֱ������������ĵ�λ��
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
		}
	}

	/** �������ȼ������� */
	public static interface InterpolatedTimeListener {
		public void interpolatedTime(float interpolatedTime);
	}
}
