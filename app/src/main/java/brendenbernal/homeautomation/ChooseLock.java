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

public class ChooseLock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locks_main);

        final DatabaseHelper db = new DatabaseHelper(this);

        List<Lock> locks = db.getAllLocks();

        String[] locksNames = new String[locks.size()];

        int i = 0;
        for(Lock lock : locks){
            locksNames[i] = lock.getName();
            i++;
        }
        db.close();
        populateListView(locksNames);
        registerClick();


        onClick();
    }

    private void populateListView(String[] locksNames) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, locksNames);
        ListView listView = (ListView) findViewById(R.id.lockList);
        listView.setAdapter(adapter);
    }

    public void registerClick(){
        ListView listView = (ListView) findViewById(R.id.lockList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = (TextView) view;
                Intent intent = new Intent(getApplicationContext(), LockAction.class);
                intent.putExtra("name", textview.getText().toString());
                startActivity(intent);
            }
        });
    }


    public void onClick() {

        Button back = (Button) findViewById(R.id.buttonBack);
        Button editLocks = (Button) findViewById(R.id.buttonEditLockList);

        editLocks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), AddLock.class));
            }
        });

        // back button
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}