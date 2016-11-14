package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class LockAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_action);

        final DatabaseHelper db = new DatabaseHelper(this);

        // get room name from last activity
        Intent lockActivity = getIntent();
        String lockRoom = lockActivity.getStringExtra("name");


        Lock lock = db.getLock(lockRoom);

        onClick(lock, db);
    }

    public void onClick(final Lock lock, final DatabaseHelper db) {
        // set value of textView to be proper room name
        final TextView lockName = (TextView) findViewById(R.id.textViewDoorName);
        lockName.setText(lock.getName());
        final Switch lockSwitch = (Switch) findViewById(R.id.lockSwitch);

        if(lock.getStatus() == 1){
            lockSwitch.setChecked(true);
        }


        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                int status = 0;
                if(lockSwitch.isChecked()){
                    status = 1;
                }
                Lock updatedLock = new Lock(lock.getId(), lock.getName(), status);
                db.updateLock(updatedLock);
                db.close();

                startActivity(new Intent(getApplicationContext(), ChooseLock.class));
            }
        });

    }
}