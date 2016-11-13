package brendenbernal.homeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ChooseLight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_light);

        final DatabaseHelper db = new DatabaseHelper(this);

        List<Light> lights = db.getAllLights();

        String[] lightsNames = new String[lights.size()];

        int i = 0;
        for(Light light : lights){
            lightsNames[i] = light.getName();
            Log.d("Light Name", light.getName());
            i++;
        }
        db.close();
        populateListView(lightsNames);
        registerClick();

        onClick(db);

    }

    private void populateListView(String[] lightsNames) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lightsNames);
        ListView listView = (ListView) findViewById(R.id.lightList);
        listView.setAdapter(adapter);
    }

    public void registerClick(){
        ListView listView = (ListView) findViewById(R.id.lightList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = (TextView) view;
                Intent intent = new Intent(getApplicationContext(), LightAction.class);
                intent.putExtra("name", textview.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void onClick(final DatabaseHelper db)
    {

        Button back = (Button) findViewById(R.id.buttonBack);
        Button editLights = (Button) findViewById(R.id.buttonEditLightList);


        editLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                db.close();
                startActivity(new Intent(getApplicationContext(), AddLight.class));
            }
        });

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                db.close();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
