package com.josh.ParallaxSampleGitHub.parallax;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;


/**
 * Handle sensor registration life cycle for parallax linear layout
 */
public class ParallaxFragment extends Fragment {

    /**
     * sensor manager from system service
     */
    private SensorManager mSensorManager;

    /**
     * rotation to get rotation event
     */
    private Sensor mRotationSensor;

    /**
     * parallax layout whose children are going to be animated
     */
    private ParallaxRelativeLayout mParallaxRelativeLayout;

    /**
     * set parallax linear layout which will be register to rotation event
     *
     * @param parallaxRelativeLayout
     */
    protected void setParallaxRelativeLayout(ParallaxRelativeLayout parallaxRelativeLayout) {
        mParallaxRelativeLayout = parallaxRelativeLayout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //get sensor manager
        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);

        //get rotation sensor
        mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onResume() {
        super.onResume();
        //register listener for rotation event
        if (mParallaxRelativeLayout != null) {
            mSensorManager.registerListener(
                    mParallaxRelativeLayout,
                    mRotationSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister listener
        if (mParallaxRelativeLayout != null) {
            mSensorManager.unregisterListener(mParallaxRelativeLayout, mRotationSensor);
        }
    }

}
