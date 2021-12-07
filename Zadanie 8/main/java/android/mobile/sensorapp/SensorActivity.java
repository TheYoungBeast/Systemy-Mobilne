package android.mobile.sensorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_SENSOR_TYPE = "KEY_SENSOR_TYPE";
    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private SensorAdapter adapter = null;
    private RecyclerView recyclerView = null;
    private boolean subtitleVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);

        recyclerView = findViewById(R.id.sensor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if(adapter == null)
        {
            adapter = new SensorAdapter(sensorList);
            recyclerView.setAdapter(adapter);
        }
        else adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateSubtitle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("subtitleVisible", subtitleVisible);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
            subtitleVisible = savedInstanceState.getBoolean("subtitleVisible");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sensor_menu, menu);
        MenuItem item = menu.findItem(R.id.show_sensor_count);

        if(subtitleVisible)
            item.setTitle(R.string.hide_count);
        else
            item.setTitle(R.string.show_count);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.show_sensor_count:
                subtitleVisible = !subtitleVisible;
                updateSubtitle();
                this.invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        String subtitle = subtitleVisible ? getString(R.string.sensors_count, sensorList.size()) : null;
        this.getSupportActionBar().setSubtitle(subtitle);
    }

    private class SensorHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Sensor sensor;
        private final TextView sensorName;
        private final ImageView sensorIcon;

        public SensorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_list_item, parent, false));
            itemView.setOnClickListener(this);

            sensorName = itemView.findViewById(R.id.sensor_name);
            sensorIcon = itemView.findViewById(R.id.sensor_icon);
        }

        public void bind(Sensor sensor) {
            this.sensor = sensor;

            if(sensor.getType() == Sensor.TYPE_LIGHT || sensor.getType() == Sensor.TYPE_PROXIMITY)
                sensorName.setTextAppearance(R.style.TextItemSensor);

            sensorName.setText(sensor.getName());
            sensorIcon.setImageResource(R.drawable.ic_sensor);
            Log.i("SENSOR INFO", String.format("Name:%s Vendor:%s MaxRange:%f", sensor.getName(), sensor.getVendor(), sensor.getMaximumRange()));
        }

        @Override
        public void onClick(View v) {
            int type = sensor.getType();
            if(type == Sensor.TYPE_LIGHT || type == Sensor.TYPE_PROXIMITY) {
                Intent intent = new Intent(SensorActivity.this, SensorDetailsActivity.class);
                intent.putExtra(KEY_EXTRA_SENSOR_TYPE, type);
                startActivity(intent);
            }
        }
    }

    private class SensorAdapter extends RecyclerView.Adapter<SensorHolder>
    {
        private final List<Sensor> sensors;

        public SensorAdapter(List<Sensor> list) {
            this.sensors = list;
        }

        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new SensorHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
            Sensor s = sensors.get(position);
            holder.bind(s);
        }

        @Override
        public int getItemCount() {
            return sensors.size();
        }
    }
}