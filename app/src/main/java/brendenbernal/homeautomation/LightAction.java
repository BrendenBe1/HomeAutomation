package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class LightAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_action);

        Intent lightActivity = getIntent();
        String lightRoom = lightActivity.getStringExtra("name");

        onClick(lightRoom);
    }

    public void onClick(String roomChoice)
    {
        // set value of textView to be proper room name
        final TextView ligthName = (TextView) findViewById(R.id.textViewLightName);
        ligthName.setText(roomChoice);


        // instatiate buttons
        Button Back = (Button) findViewById(R.id.buttonBack);
        Button Settings = (Button) findViewById(R.id.buttonSettings);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });

        // settings button
        Settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), LightSettings.class));
            }
        });

    }
}