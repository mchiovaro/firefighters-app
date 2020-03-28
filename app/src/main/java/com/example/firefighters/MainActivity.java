// main point of entry for app
package com.example.firefighters;

// imports for buttons and views
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        /* launching movement data capture */

        // create the intent to launch the class
        Intent intent = new Intent(this, LaunchRecordingActivity.class);

        // pass the filename to the next activity
        intent.putExtra("FILENAME", filename);

        // launch the other activity using the intent
        startActivity(intent);
    }

}
