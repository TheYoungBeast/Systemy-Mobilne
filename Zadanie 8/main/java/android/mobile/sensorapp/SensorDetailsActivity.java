package android.mobile.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SensorDetailsActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor sensorLight;
    private TextView sensorName;
    private TextView sensorValue;
    private TextView sensorMissing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        sensorName = findViewById(R.id.text_sensor_name);
        sensorValue = findViewById(R.id.text_sensor_value);
        sensorMissing = findViewById(R.id.text_missin_sensor);

        int sensorType = getIntent().getIntExtra(SensorActivity.KEY_EXTRA_SENSOR_TYPE, Sensor.TYPE_LIGHT);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(sensorType);

        if(sensorLight == null) {
            sensorMissing.setVisibility(View.VISIBLE);
            sensorName.setVisibility(View.GONE);
            sensorValue.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currenValue = event.values[0];

        switch (sensorType)
        {
            case Sensor.TYPE_LIGHT:
            {
                if(currenValue < 13000.0f)
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                else if(currenValue < 26000.0f)
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                else
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                sensorName.setText(event.sensor.getName());
                sensorValue.setText(getString(R.string.light_sensor_label, currenValue));
                break;
            }
            case Sensor.TYPE_PROXIMITY:
            {
                if(currenValue < 2.0f)
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                else if(currenValue < 8.0f)
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                else
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                sensorName.setText(event.sensor.getName());
                sensorValue.setText(getString(R.string.light_sensor_label, currenValue));
                break;
            }
            default:
                sensorName.setText(event.sensor.getName());
                sensorValue.setText(getString(R.string.light_sensor_label, currenValue));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i("ACCURACY", new Integer(accuracy).toString());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sensorLight != null)
            sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}