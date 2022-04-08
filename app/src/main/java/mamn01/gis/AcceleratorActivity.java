package mamn01.gis;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AcceleratorActivity extends AppCompatActivity implements SensorEventListener {

    float alpha = 0.8f;
    float [] gravity = new float[3];
    float [] linearAcceleration = new float[3];

    private  SensorManager mSensorManager;
    private  Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_accelerator_values);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Intent intent = getIntent();

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String [] values = new String[3];

        for (int i = 0; i <values.length; i++) {
            values[i] = Float.toString(sensorEvent.values[i]).substring(0, 3);
        }

        TextView xAcceleration = findViewById(R.id.x_acceleration);
        TextView yAcceleration = findViewById(R.id.y_acceleration);
        TextView zAcceleration = findViewById(R.id.z_acceleration);

        xAcceleration.setText(values[0]);
        yAcceleration.setText(values[1]);
        zAcceleration.setText(values[2]);

        if (sensorEvent.values[0] >= 0 ) {
            xAcceleration.setTextColor(Color.RED);
        } else if (sensorEvent.values[0] < 0 ) {
            xAcceleration.setTextColor(Color.YELLOW);
        }

        if (sensorEvent.values[1] >= 0 ) {
            yAcceleration.setTextColor(Color.DKGRAY);
        } else if (sensorEvent.values[1] < 0 ) {
            yAcceleration.setTextColor(Color.GRAY);
        }
        if (sensorEvent.values[2] >= 0 ) {
            zAcceleration.setTextColor(Color.BLUE);
        } else if (sensorEvent.values[2] < 0 ) {
            zAcceleration.setTextColor(Color.CYAN);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}