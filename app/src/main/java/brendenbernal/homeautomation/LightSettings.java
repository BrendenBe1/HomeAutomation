package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class LightSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);

        // get room name from last activity
        Intent lightActivity = getIntent();
        String lightRoom = lightActivity.getStringExtra("name");

        onClick(lightRoom);
    }

    public void onClick(final String roomName)
    {

        final Intent intent = new Intent(getApplicationContext(), LightAction.class);

        TimePicker pickTime = (TimePicker) findViewById(R.id.timePickerLight);


        // instatiate buttons
        Button Back = (Button) findViewById(R.id.buttonBack);
        Button setTime = (Button) findViewById(R.id.buttonSetTime);

        // set time
        setTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), LightAction.class));
                intent.putExtra("name", roomName);
                startActivity(intent);
            }
        });

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), LightAction.class));
                intent.putExtra("name", roomName);
                startActivity(intent);
            }
        });
    }
}
