package brendenbernal.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChooseThermostat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_thermostat);

        final DatabaseHelper db = new DatabaseHelper(this);

        /*Log.d("Reading: ", "Reading all thermostats");
        Cursor data = db.getAllThermostatData();

        if(data.getCount() == 0){
            Log.d("Empty", "Thermostats are empty.");
        }else {
            Log.d("Not empty", "Thermostats are not empty.");
        }*/


        List<Thermostat> thermostats = db.getAllThermostats();

        String[] thermostatNames = new String[thermostats.size()];

        int i = 0;
        for(Thermostat thermostat : thermostats){
            thermostatNames[i] = thermostat.getName();
            Log.d("ThermName", thermostat.getName());
            i++;
        }
        db.close();
        populateListView(thermostatNames);
        registerClick();

        onClick();

    }

    private void populateListView(String[] thermostatNames) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, thermostatNames);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    public void registerClick(){
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = (TextView) view;
                Intent intent = new Intent(getApplicationContext(), Thermostat1.class);
                intent.putExtra("name", textview.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void onClick() {

        Button back = (Button) findViewById(R.id.buttonBack);
        Button editButtons = (Button) findViewById(R.id.buttonEditThermoList);

        editButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(getApplicationContext(), AddThermostat.class));
            }
        });

        // back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
