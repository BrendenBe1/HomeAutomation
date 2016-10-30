package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class ThermoSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermo_settings);

        onClick();
    }

    public void onClick()
    {
        TimePicker pickTime = (TimePicker) findViewById(R.id.timePicker);

        // instantiate number picker
        NumberPicker pickNumber = (NumberPicker) findViewById(R.id.numberPickerSettings);


        // set min and max values on the number picker
        pickNumber.setMinValue(50);
        pickNumber.setMaxValue(100);
        pickNumber.setValue(70);

        // instatiate buttons
        Button Back = (Button) findViewById(R.id.buttonBack);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), Thermostat1.class));
            }
        });


    }
}
