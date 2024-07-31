package com.example.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button OnFlash, OffFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        OnFlash = findViewById(R.id.flashOn);
        OffFlash = findViewById(R.id.flashOff);

        OnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    flashOnMethod();
                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        OffFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    flashOffMethod();
                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void flashOnMethod() throws CameraAccessException {
        CameraManager cameraManager = null;
        cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        String cameraId = null;
        cameraId = cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraId, true);
    }
    public void flashOffMethod() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        String cameraId = null;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        cameraManager.setTorchMode(cameraId, false);

    }
}