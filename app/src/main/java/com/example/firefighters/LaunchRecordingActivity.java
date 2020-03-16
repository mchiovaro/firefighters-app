// main point of entry for app
// build and run will launch an instance and load layout
package com.example.firefighters;

// presets
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

// imports for sensor data
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.util.Log; // for logcat

// start launch activity                              // addition from tutorial - see overrides for
// onAccuracyChanged and onSensorChanged
public class LaunchRecordingActivity extends AppCompatActivity implements SensorEventListener {

    // create Tag for Logcat
    public static final String TAG = "LaunchRecordingActivity";

    // make variables for accel. sensor
    private TextView xTextAccel, yTextAccel, zTextAccel;
    private Sensor accelSensor;
    private SensorManager SMAccel;

    // make variables for gyro. sensor
    private TextView xTextGyro, yTextGyro, zTextGyro;
    private Sensor gyroSensor;
    private SensorManager SMGyro;

    // make variables for magnet. sensor
    private TextView xTextMag, yTextMag, zTextMag;
    private Sensor magSensor;
    private SensorManager SMMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // presets on new project
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        /* *
        Accelerometer set up
        * */

        // create sensor manager
        SMAccel = (SensorManager)getSystemService(SENSOR_SERVICE);

        // create accel. sensor
        accelSensor = SMAccel.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMAccel.registerListener((SensorEventListener) this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered accelerometer");

        // Assign TextViews for accelerometer
        xTextAccel = (TextView)findViewById(R.id.xTextAccel);
        yTextAccel = (TextView)findViewById(R.id.yTextAccel);
        zTextAccel = (TextView)findViewById(R.id.zTextAccel);

        /* *
        Gyroscope set up
        * */

        // create sensor manager
        SMGyro = (SensorManager) getSystemService(SENSOR_SERVICE);

        // create gyro sensor
        gyroSensor = SMGyro.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMGyro.registerListener((SensorEventListener) this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered gyroscope");

        // Assign TextViews for accelerometer
        xTextGyro = (TextView)findViewById(R.id.xTextGyro);
        yTextGyro = (TextView)findViewById(R.id.yTextGyro);
        zTextGyro = (TextView)findViewById(R.id.zTextGyro);

        /* *
        Magnetometer set up
        * */

        // create sensor manager
        SMMag = (SensorManager)getSystemService(SENSOR_SERVICE);

        // create magnet. sensor
        magSensor = SMMag.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMMag.registerListener((SensorEventListener) this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered magnetometer");

        // Assign TextViews for magnetometer
        xTextMag = (TextView)findViewById(R.id.xTextMag);
        yTextMag = (TextView)findViewById(R.id.yTextMag);
        zTextMag = (TextView)findViewById(R.id.zTextMag);

    }

    // onAccuracyChanged and on SensorChanged are from the implements in the start of LaunchRecordingActivity

    //not in use
    @Override
    public void onAccuracyChanged(Sensor sensor, int Accuracy){
    }

    // update the accelerometer values when sensor changes
    @Override
    public void onSensorChanged(SensorEvent event){

        // accelerometer values display
        xTextAccel.setText("X: " + event.values[0]);
        yTextAccel.setText("Y: " + event.values[1]);
        zTextAccel.setText("Z: " + event.values[2]);

        // gyroscope values display
        xTextGyro.setText("X: " + event.values[0]);
        yTextGyro.setText("Y: " + event.values[1]);
        zTextGyro.setText("Z: " + event.values[2]);

        // magnetometer values display
        xTextMag.setText("X: " + event.values[0]);
        yTextMag.setText("Y: " + event.values[1]);
        zTextMag.setText("Z: " + event.values[2]);

        // save values for each time a sensor changes
        Log.d(TAG, "onSensorChanged: X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);

    }

}
