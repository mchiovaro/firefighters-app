// main point of entry for app
// build and run will launch an instance and load layout
package com.example.firefighters;

// imports for buttons and views
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


// start main activity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // presets for onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // starting data collection (button function)
    public void start(View v){

        // initialize and assign text variables
        EditText initials = findViewById(R.id.initials);
        EditText partnum = findViewById(R.id.partnum);
        
        // turn file name into a string
        String filename = partnum.getText().toString() + "-" + initials.getText().toString() + ".txt";

        // declarative statement to tell us what this file is about
        String firstline = "Data for participant number " + partnum.getText().toString();

        // initialize output stream
        FileOutputStream fos = null;

        // try writing to file
        try {
            // create file output stream to participant file
            fos = openFileOutput(filename, MODE_PRIVATE);

            // clear the variables
            initials.getText().clear();
            partnum.getText().clear();

            // write the first line so we know what data file this is
            fos.write(firstline.getBytes());

            // tell us that it saved and to where
            Toast.makeText(this, "Created participant file: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();

        // catch if if can't find the variables or files
        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // if the fos is open, close it
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /* launching movement data capture */

        // create the intent to launch the class
        Intent intent = new Intent(this, LaunchRecordingActivity.class);

        // pass the filename to the next activity
        intent.putExtra("FILENAME", filename);

        // launch the other activity using the intent
        startActivity(intent);
    }

}
