package com.mtlteam.openhelperlib;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mtlteam.openhelperlibmain.UpdateManagerAsLifecycleObserver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attach update observer
        getLifecycle().addObserver(new UpdateManagerAsLifecycleObserver(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UpdateManagerAsLifecycleObserver.UPDATE_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Log.d("TAG", "Update flow failed! Result code: " + requestCode);
                // If the update is cancelled or fails,
                // we can request to start the update again.
            } else {
                Log.d("TAG", "Update flow success! Result code: " + requestCode);
                // do stuff
            }
        }
    }
}