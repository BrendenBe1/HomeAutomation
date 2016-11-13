package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddLight extends AppCompatActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_light);
        final DatabaseHelper db = new DatabaseHelper(this);

        onClick(db);
    }

    public void onClick(final DatabaseHelper db)
    {

        // set intent so can pass in name to thermostat activity
        final Intent intent = new Intent(getApplicationContext(), ChooseLight.class);

        Button addButton = (Button) findViewById(R.id.buttonAddButton);
        Button Back = (Button) findViewById(R.id.buttonBack);
        final EditText nameInput = (EditText) findViewById(R.id.roomName);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });

        // back button
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity

                db.insertLight(new Light(0, nameInput.getText().toString(), 0, "0", "0", 0));
                db.close();

                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });
    }
}

