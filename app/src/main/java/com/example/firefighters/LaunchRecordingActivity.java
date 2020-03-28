package com.example.firefighters;

// presets
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

// imports for sensor data
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.util.Log; // for logcat
import android.widget.Toast;

// misc imports for saving data
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

// start launch activity
public class LaunchRecordingActivity extends AppCompatActivity implements SensorEventListener {

    // create Tag for Logcat
    public static final String TAG = "LaunchRecordingActivity";

    // make variables for accel. sensor
    private TextView xTextAccel, yTextAccel, zTextAccel;

    // make variables for gyro. sensor
    private TextView xTextGyro, yTextGyro, zTextGyro;

    // make variables for magnet. sensor
    private TextView xTextMag, yTextMag, zTextMag;

    // make storage variables for saving values
    public float xAccel, yAccel, zAccel;
    public float xGyro, yGyro, zGyro;
    public float xMag, yMag, zMag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // presets on new project
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        // get the file name from input screen to log movement data to file
        Intent intent = getIntent();
        String filename = intent.getStringExtra("FILENAME");

        // initialize output stream
        FileOutputStream fos = null;

        // try creating and writing to a file
        try {
            // create file output stream to participant file
            fos = openFileOutput(filename, MODE_PRIVATE);

            // tell us that it saved and to where
            Toast.makeText(this, "Created participant file: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();

          // catch if if can't find the variables or files
        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save.", Toast.LENGTH_LONG).show();
        } finally {
            // if the fos is still open, close it
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // show file name on movement data screen so we know it was entered correctly
        TextView file = findViewById(R.id.file);
        file.setText(filename);

        /* *
        Accelerometer set up
        * */

        // create sensor manager
        SensorManager SMAccel = (SensorManager) getSystemService(SENSOR_SERVICE);

        // create accel. sensor
        Sensor accelSensor = SMAccel.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMAccel.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered accelerometer");

        // Assign TextViews for accelerometer
        xTextAccel = findViewById(R.id.xTextAccel);
        yTextAccel = findViewById(R.id.yTextAccel);
        zTextAccel = findViewById(R.id.zTextAccel);

        /* *
        Gyroscope set up
        * */

        // create sensor manager
        SensorManager SMGyro = (SensorManager) getSystemService(SENSOR_SERVICE);

        // create gyro sensor
        Sensor gyroSensor = SMGyro.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMGyro.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered gyroscope");

        // Assign TextViews for accelerometer
        xTextGyro = findViewById(R.id.xTextGyro);
        yTextGyro = findViewById(R.id.yTextGyro);
        zTextGyro = findViewById(R.id.zTextGyro);

        /* *
        Magnetometer set up
        * */

        // create sensor manager
        SensorManager SMMag = (SensorManager) getSystemService(SENSOR_SERVICE);

        // create magnet. sensor
        Sensor magSensor = SMMag.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // register sensor listener // using NORMAL delay here (default), there is also an option FASTEST
        SMMag.registerListener(this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // tell logcat that sensor has been registered
        Log.d(TAG, "onCreate: Registered magnetometer");

        // Assign TextViews for magnetometer
        xTextMag = findViewById(R.id.xTextMag);
        yTextMag = findViewById(R.id.yTextMag);
        zTextMag = findViewById(R.id.zTextMag);

    }

    // onAccuracyChanged and on SensorChanged are from the implements in the start of LaunchRecordingActivity

    //not in use
    @Override
    public void onAccuracyChanged(Sensor sensor, int Accuracy){
    }

    // update the accelerometer values when sensor changes
    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event){

        // accelerometer values display
        xTextAccel.setText("X: " + event.values[0]);
        yTextAccel.setText("Y: " + event.values[1]);
        zTextAccel.setText("Z: " + event.values[2]);
        // save to variables for easier writing
        xAccel = event.values[0];
        yAccel = event.values[1];
        zAccel = event.values[2];

        // gyroscope values display
        xTextGyro.setText("X: " + event.values[0]);
        yTextGyro.setText("Y: " + event.values[1]);
        zTextGyro.setText("Z: " + event.values[2]);
        // save to variables for easier writing
        xGyro = event.values[0];
        yGyro = event.values[1];
        zGyro = event.values[2];

        // magnetometer values display
        xTextMag.setText("X: " + event.values[0]);
        yTextMag.setText("Y: " + event.values[1]);
        zTextMag.setText("Z: " + event.values[2]);
        // save to variables for easier writing
        xMag = event.values[0];
        yMag = event.values[1];
        zMag = event.values[2];

        // save values for each time a sensor changes
        // Timestamp: offset from the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
        long currentTime = Calendar.getInstance().getTimeInMillis();

        // call append function for each sensor
        try {
            appendToFile(currentTime + "," + xAccel + "," + yAccel + "," + zAccel + "," + xGyro + "," + yGyro + "," + zGyro + "," + xMag + "," + yMag + "," + zMag);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // create a data output stream function here
    private void appendToFile(String str) throws IOException {

        // get the filename from the first activity
        Intent intent = getIntent();
        String filename = intent.getStringExtra("FILENAME");

        // create the writer
        FileOutputStream writer = openFileOutput(filename, MODE_APPEND);

        // write the data string
        writer.write(str.getBytes());

        // line breaks between onSensorChanged calls
        writer.write('\n');

        // flush the data out to file
        writer.flush();

        // close writer
        writer.close();
    }


}
