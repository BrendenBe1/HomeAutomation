package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);
        onClick();
    }

    public void onClick()
    {
        Button Thermostat = (Button) findViewById(R.id.buttonThermostats);
        Button Lights = (Button) findViewById(R.id.buttonLights);
        Button Locks = (Button) findViewById(R.id.buttonLocks);

        Thermostat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseThermostat.class));
            }
        });

        Locks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseLock.class));
            }
        });

        Lights.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });
    }
}
