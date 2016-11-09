package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Thermostat1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat1);

        Intent thermostatActivity = getIntent();
        String thermostatRoom = thermostatActivity.getStringExtra("name");

        onClick(thermostatRoom);
    }

    public void onClick(final String roomChoice)
    {

        final Intent intent = new Intent(getApplicationContext(), ThermoSettings.class);

        // set value of textView to be proper room name
        final TextView roomName = (TextView) findViewById(R.id.textViewChoosethermostat);
        roomName.setText(roomChoice);

        // instantiate number picker
        NumberPicker pickNumber = (NumberPicker) findViewById(R.id.numberPickerThermo);


        // set min and max values on the number picker
        pickNumber.setMinValue(50);
        pickNumber.setMaxValue(100);
        pickNumber.setValue(70);

        // instatiate buttons
        Button Back = (Button) findViewById(R.id.buttonBack);
        Button Settings = (Button) findViewById(R.id.buttonSettings);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseThermostat.class));
            }
        });

        // settings button
        Settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), ThermoSettings.class));
                intent.putExtra("name", roomChoice);
                startActivity(intent);
            }
        });





    }
}
