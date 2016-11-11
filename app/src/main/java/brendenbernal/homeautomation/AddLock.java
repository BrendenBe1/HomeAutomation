package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddLock extends AppCompatActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lock);
        myDB = new DatabaseHelper(this);

        onClick();
    }

    public void onClick()
    {
        // set intent so can pass in name to thermostat activity
        final Intent intent = new Intent(getApplicationContext(), LocksMain.class);

        Button addButton = (Button) findViewById(R.id.buttonAddButton);
        Button Back = (Button) findViewById(R.id.buttonBack);
        final EditText nameInput = (EditText) findViewById(R.id.roomName);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //startActivity(new Intent(getApplicationContext(), ChooseLight.class));
                intent.putExtra("name", nameInput.getText().toString());
                startActivity(intent);
            }
        });

        // back button
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // putExtra makes it so you can send variables or values to next activity
                //myDB.insertThermo(nameInput.getText().toString(), 67, "0", "0");
                intent.putExtra("name", nameInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}