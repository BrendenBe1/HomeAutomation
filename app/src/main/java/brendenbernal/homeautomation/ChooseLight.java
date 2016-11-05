package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseLight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_light);

        String buttonName = "";

        try
        {
            Intent newButton = getIntent();
            buttonName = newButton.getStringExtra("name");
            Log.d("name of new button", buttonName);
            onClick(buttonName);
        }
        catch(Exception e)
        {
            Log.d("nothing", "nothing");
            onClick("null");
        }
    }

    public void onClick(final String buttonName)
    {

        Button livingRoomLight = (Button) findViewById(R.id.buttonLivingRoomLight);
        Button kitchenLight = (Button) findViewById(R.id.buttonKitchenLight);
        Button porchLight = (Button) findViewById(R.id.buttonPorchLight);
        Button backPatio = (Button) findViewById(R.id.buttonBackPatio);
        final Button newButton = (Button) findViewById(R.id.button3);
        Button Back = (Button) findViewById(R.id.buttonBack);
        Button editLights = (Button) findViewById(R.id.buttonEditLightList);

        if(buttonName.equals("null"))
        {
            newButton.setVisibility(View.GONE);
            Log.d("blank name", buttonName);
        }
        else
        {
            newButton.setVisibility(View.VISIBLE);
            newButton.setText(buttonName);
        }


        // set intent so can pass in name to thermostat activity
        final Intent intent = new Intent(getApplicationContext(), LightAction.class);

        livingRoomLight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                intent.putExtra("name", "Living Room");
                startActivity(intent);
            }
        });

        kitchenLight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                intent.putExtra("name", "Kitchen");
                startActivity(intent);
            }
        });

        porchLight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                intent.putExtra("name", "porch");
                startActivity(intent);
            }
        });

        backPatio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                intent.putExtra("name", "Back Patio");
                startActivity(intent);
            }
        });

        newButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                intent.putExtra("name", buttonName);
                startActivity(intent);
            }
        });

        editLights.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), AddThermostat.class));
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
