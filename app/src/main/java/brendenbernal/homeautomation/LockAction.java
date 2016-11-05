package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class LockAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_action);

        Intent thermostatActivity = getIntent();
        String thermostatRoom = thermostatActivity.getStringExtra("name");

        onClick(thermostatRoom);
    }

    public void onClick(String roomChoice)
    {
        // set value of textView to be proper room name
        final TextView lockName = (TextView) findViewById(R.id.textViewDoorName);
        lockName.setText(roomChoice);

        //need to instatiate switch

        // instatiate buttons
        Button Back = (Button) findViewById(R.id.buttonBack);

        // back button
        Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), LocksMain.class));
            }
        });

    }
}