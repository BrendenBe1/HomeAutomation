package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class ThermoSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermo_settings);

        final DatabaseHelper db = new DatabaseHelper(this);

        // get room name from last activity
        Intent thermoActivity = getIntent();
        String thermostatRoom = thermoActivity.getStringExtra("name");


        Thermostat thermostat = db.getThermostat(thermostatRoom);

        onClick(thermostat, db);

    }

    public void onClick(final Thermostat thermostat, final DatabaseHelper db)
    {


        final TimePicker pickTime = (TimePicker) findViewById(R.id.timePickerThermoSettings);
        if(!thermostat.getOnTime().equals("0")){
            Log.d("change", "here");
            String setTime = thermostat.getOnTime();
            String[] timeParts = setTime.split(":");
            pickTime.setHour(Integer.parseInt(timeParts[0]));
            pickTime.setMinute(Integer.parseInt(timeParts[1]));
        }

        // instantiate number picker
        final NumberPicker pickNumber = (NumberPicker) findViewById(R.id.numberPickerThermoSettings);

        // set min and max values on the number picker
        pickNumber.setMinValue(50);
        pickNumber.setMaxValue(100);
        pickNumber.setValue(thermostat.getSetTemp());

        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);
        Button setTime = (Button) findViewById(R.id.buttonSetTime);


        final Intent intent = new Intent(getApplicationContext(), Thermostat1.class);
        // set time
        setTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), Thermostat1.class));
                String time = (Integer.toString(pickTime.getHour())+":"+Integer.toString(pickTime.getMinute()));

                Thermostat updatedThermostat = new Thermostat(thermostat.getId(), thermostat.getName(), thermostat.getStatus(), time, thermostat.getOffTime(), pickNumber.getValue());

                db.updateThermostat(updatedThermostat);

                intent.putExtra("name", thermostat.getName());
                db.close();
                startActivity(intent);
            }
        });

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), Thermostat1.class));
                intent.putExtra("name", thermostat.getName());
                db.close();
                startActivity(intent);
            }
        });


    }
}
