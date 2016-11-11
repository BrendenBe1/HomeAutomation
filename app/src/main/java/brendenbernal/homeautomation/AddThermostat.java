package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddThermostat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thermostat);

        //Make an instance of DB helper class
        final DatabaseHelper db = new DatabaseHelper(this);

        onClick(db);
    }

    public void onClick(final DatabaseHelper db)
    {

        // set intent so can pass in name to thermostat activity
        final Intent intent = new Intent(getApplicationContext(), ChooseThermostat.class);

        Button addButton = (Button) findViewById(R.id.buttonAddButton);
        Button Back = (Button) findViewById(R.id.buttonBack);
        final EditText nameInput = (EditText) findViewById(R.id.roomName);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseThermostat.class));
            }
        });

        // back button
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //Add new thermostat to database
                db.insertThermostat(new Thermostat(0, nameInput.getText().toString(), 65, "0", "0", 65));

                // putExtra makes it so you can send variables or values to next activity
                //intent.putExtra("name", nameInput.getText().toString());
                //startActivity(intent);
                db.close();
                startActivity(new Intent(getApplicationContext(), ChooseThermostat.class));
            }
        });
    }
}
