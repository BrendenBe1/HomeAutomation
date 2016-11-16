package brendenbernal.homeautomation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

public class LightAction extends AppCompatActivity {

    boolean isClicked = true;
    PopupWindow popUpWindow;
    ViewGroup.LayoutParams layoutParams;
    LinearLayout mainLayout;
    Button confirmRemoval;
    Button cancelRemoval;
    LinearLayout containerLayout;
    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_action);

        final DatabaseHelper db = new DatabaseHelper(this);


        Intent lightActivity = getIntent();
        String lightRoom = lightActivity.getStringExtra("name");

        Light light = db.getLight(lightRoom);

        onClick(light, db);
    }

    public void onClick(final Light light, final DatabaseHelper db) {

        final Intent intent = new Intent(getApplicationContext(), LightSettings.class);

        // set value of textView to be proper room name
        final TextView lightName = (TextView) findViewById(R.id.textViewLightName);
        lightName.setText(light.getName());

        // instatiate buttons
        Button back = (Button) findViewById(R.id.buttonBack);
        Button Settings = (Button) findViewById(R.id.buttonSettings);
        Button removeLight = (Button) findViewById(R.id.buttonRemoveLight);

        final Switch lightSwitch = (Switch) findViewById(R.id.lightSwitch);

        if(light.getStatus() == 1){
            lightSwitch.setChecked(true);
        }

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                int status = 0;
                if(lightSwitch.isChecked()){
                    status = 1;
                }
                Light updatedLight = new Light(light.getId(), light.getName(), status, light.getOnTime(), light.getOffTime(), light.getSetStatus());
                db.updateLight(updatedLight);
                db.close();

                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
            }
        });

        // settings button
        Settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                int status = 0;
                if(lightSwitch.isChecked()){
                    status = 1;
                }
                Light updatedLight = new Light(light.getId(), light.getName(), status, light.getOnTime(), light.getOffTime(), light.getSetStatus());
                db.updateLight(updatedLight);
                db.close();
                intent.putExtra("name", light.getName());
                startActivity(intent);
            }
        });

        containerLayout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);

        removeLight.setOnClickListener(new View.OnClickListener()
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
                startActivity(new Intent(getApplicationContext(), ChooseLight.class));
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


        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        //containerLayout.addView(tvMsg, layoutParams);
        containerLayout.addView(confirmRemoval, layoutParams);
        containerLayout.addView(cancelRemoval, layoutParams);
        popUpWindow.setContentView(containerLayout);
        //mainLayout.addView(removeLock, layoutParams);
        //setContentView(mainLayout);

    }
}