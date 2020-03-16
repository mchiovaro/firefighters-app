// main point of entry for app
// build and run will launch an instance and load layout
package com.example.firefighters;

// imports for buttons and views
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


// start main activity
public class MainActivity extends AppCompatActivity {

    // create button object
    private Button launch_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // presets on new project
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the launch button
        launch_button = (Button) findViewById(R.id.launch_button);

        // set button listener
        launch_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openLaunchRecordingActivity();
            }
        });
    }

    // create the function to launch the other activity
    public void openLaunchRecordingActivity(){
        // create the intent to launch the class
        Intent intent = new Intent(this, LaunchRecordingActivity.class);
        // launch the other activity using the intent
        startActivity(intent);
    }


}
