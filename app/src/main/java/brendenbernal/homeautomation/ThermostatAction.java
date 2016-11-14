package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ThermostatAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat1);

        final DatabaseHelper db = new DatabaseHelper(this);

        Intent thermostatActivity = getIntent();
        String thermostatRoom = thermostatActivity.getStringExtra("name");

        Thermostat thermostat = db.getThermostat(thermostatRoom);

        onClick(thermostat, db);
    }

    public void onClick(final Thermostat thermostat, final DatabaseHelper db)
    {

        final Intent intent = new Intent(getApplicationContext(), ThermoSettings.class);

        // set value of textView to be proper room name
        final TextView roomName = (TextView) findViewById(R.id.textViewChoosethermostat);
        roomName.setText(thermostat.getName());

        // instantiate number picker
        final NumberPicker pickNumber = (NumberPicker) findViewById(R.id.numberPickerThermo);


        // set min and max values on the number picker
        pickNumber.setMinValue(50);
        pickNumber.setMaxValue(100);
        pickNumber.setValue(thermostat.getStatus());

        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);
        Button Settings = (Button) findViewById(R.id.buttonSettings);

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Log.d("change", Integer.toString(pickNumber.getValue()));
                Thermostat updatedThermostat = new Thermostat(thermostat.getId(), thermostat.getName(), pickNumber.getValue(), thermostat.getOnTime(), thermostat.getOffTime(), thermostat.getSetTemp());
                db.updateThermostat(updatedThermostat);
                db.close();

                startActivity(new Intent(getApplicationContext(), ChooseThermostat.class));
            }
        });

        // settings button
        Settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //Log.d("change", Integer.toString(pickNumber.getValue()));
                Thermostat updatedThermostat = new Thermostat(thermostat.getId(), thermostat.getName(), pickNumber.getValue(), thermostat.getOnTime(), thermostat.getOffTime(), thermostat.getSetTemp());
                db.updateThermostat(updatedThermostat);
                db.close();

                intent.putExtra("name", thermostat.getName());
                startActivity(intent);
            }
        });





    }
}
