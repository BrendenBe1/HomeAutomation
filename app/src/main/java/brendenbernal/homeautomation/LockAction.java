package brendenbernal.homeautomation;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Space;
import android.widget.Switch;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;


public class LockAction extends AppCompatActivity {

    boolean isClicked = true;
    PopupWindow popUpWindow;
    LayoutParams layoutParams;
    LinearLayout mainLayout;
    Button confirmRemoval;
    Button cancelRemoval;
    LinearLayout containerLayout;
    TextView tvMsg;

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
        Button removeLock = (Button) findViewById(R.id.buttonRemoveLock);



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



        containerLayout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);

        removeLock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (isClicked) {
                    isClicked = false;
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                    popUpWindow.update(0, 0, 1500, 5000);
                } else {
                    isClicked = true;
                    popUpWindow.dismiss();
                }

            }
        });

        tvMsg = new TextView(this);
        tvMsg.setText("Hi this is pop up window...");

        // set button in popup
        confirmRemoval = new Button(this);
        confirmRemoval.setText("Confirm Removal");
        confirmRemoval.setBackgroundColor(Color.BLACK);
        confirmRemoval.setTextColor(Color.WHITE);
        confirmRemoval.setGravity(Gravity.CENTER);
        confirmRemoval.setAlpha((float) .75);
        confirmRemoval.setPaddingRelative(0,150,0,0);
        confirmRemoval.setHeight(500);
        confirmRemoval.setWidth(3000);

        cancelRemoval = new Button(this);
        cancelRemoval.setText("      Cancel      ");
        cancelRemoval.setBackgroundColor(Color.GRAY);
        cancelRemoval.setGravity(Gravity.CENTER);
        cancelRemoval.setAlpha((float) .75);
        cancelRemoval.setHeight(500);
        cancelRemoval.setWidth(3000);

        confirmRemoval.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                db.deleteLock(lock);
                db.close();
                startActivity(new Intent(getApplicationContext(), ChooseLock.class));
            }
        });

        cancelRemoval.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                isClicked = true;
                popUpWindow.dismiss();
            }
        });


        layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        //containerLayout.addView(tvMsg, layoutParams);
        containerLayout.addView(confirmRemoval, layoutParams);
        containerLayout.addView(cancelRemoval, layoutParams);
        popUpWindow.setContentView(containerLayout);
        //mainLayout.addView(removeLock, layoutParams);
        //setContentView(mainLayout);


    }
}