package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class LightSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);

        final DatabaseHelper db = new DatabaseHelper(this);

        // get room name from last activity
        Intent lightActivity = getIntent();
        String lightRoom = lightActivity.getStringExtra("name");

        Light light = db.getLight(lightRoom);

        onClick(light, db);
    }

    public void onClick(final Light light, final DatabaseHelper db)
    {

        final TimePicker pickTime = (TimePicker) findViewById(R.id.timePickerLight);
        if(!light.getOnTime().equals("0")){
            String setTime = light.getOnTime();
            String[] timeParts = setTime.split(":");
            pickTime.setHour(Integer.parseInt(timeParts[0]));
            pickTime.setMinute(Integer.parseInt(timeParts[1]));
        }


        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);
        Button setTime = (Button) findViewById(R.id.buttonSetTime);


        final Intent intent = new Intent(getApplicationContext(), LightAction.class);
        // set time
        setTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), LightAction.class));

                String time = (Integer.toString(pickTime.getHour())+":"+Integer.toString(pickTime.getMinute()));
                Light updatedLight = new Light(light.getId(), light.getName(), light.getStatus(), time, light.getOffTime(), light.getSetStatus());
                db.updateLight(updatedLight);
                db.close();

                intent.putExtra("name", light.getName());

                startActivity(intent);
            }
        });

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                db.close();
                intent.putExtra("name", light.getName());
                startActivity(intent);
            }
        });
    }
}
