package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

public class LightAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_action);

        final DatabaseHelper db = new DatabaseHelper(this);


        Intent lightActivity = getIntent();
        String lightRoom = lightActivity.getStringExtra("name");

        Light light = db.getLight(lightRoom);

        onClick(light, db);
    }

    public void onClick(final Light light, final DatabaseHelper db) {

        final Intent intent = new Intent(getApplicationContext(), LightSettings.class);

        // set value of textView to be proper room name
        final TextView lightName = (TextView) findViewById(R.id.textViewLightName);
        lightName.setText(light.getName());

        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);
        Button Settings = (Button) findViewById(R.id.buttonSettings);

        final Switch lightSwitch = (Switch) findViewById(R.id.lightSwitch);

        if(light.getStatus() == 1){
            lightSwitch.setChecked(true);
        }

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                int status = 0;
                if(lightSwitch.isChecked()){
                    status = 1;
                }
                Light updatedLight = new Light(light.getId(), light.getName(), status, light.getOnTime(), light.getOffTime(), light.getSetStatus());
                db.updateLight(updatedLight);
                db.close();

                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });

        // settings button
        Settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                int status = 0;
                if(lightSwitch.isChecked()){
                    status = 1;
                }
                Light updatedLight = new Light(light.getId(), light.getName(), status, light.getOnTime(), light.getOffTime(), light.getSetStatus());
                db.updateLight(updatedLight);
                db.close();
                intent.putExtra("name", light.getName());
                startActivity(intent);
            }
        });

    }
}