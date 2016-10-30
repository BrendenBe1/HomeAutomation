package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseThermostat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_thermostat);

        onClick();
    }

    public void onClick()
    {
        Button LivingRoom = (Button) findViewById(R.id.buttonLivingRoom);
        Button MasterBedroom = (Button) findViewById(R.id.buttonMasterBed);
        Button Back = (Button) findViewById(R.id.buttonBack);

        // set intent so can pass in name to thermostat activity
        final Intent intent = new Intent(getApplicationContext(), Thermostat1.class);

        LivingRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                intent.putExtra("name", "Living Room");
                startActivity(intent);
            }
        });

        MasterBedroom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                intent.putExtra("name", "Master Bedroom");
                startActivity(intent);
            }
        });

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
