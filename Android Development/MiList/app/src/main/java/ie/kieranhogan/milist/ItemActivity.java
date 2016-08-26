package ie.kieranhogan.milist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.UUID;

import io.realm.Realm;

public class ItemActivity extends AppCompatActivity {

    private EditText inputItemName;
    private EditText inputItemQuantity;
    private Realm realm;
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        if(getIntent().hasExtra("TITLE")){
            setTitle(getIntent().getStringExtra("TITLE"));
        }

        inputItemName = (EditText)findViewById(R.id.input_item_name);
        inputItemQuantity = (EditText)findViewById(R.id.input_item_quantity);

        realm = Realm.getDefaultInstance();

        if(getIntent().hasExtra("ITEM_NAME")){
            inputItemName.setText(getIntent().getStringExtra("ITEM_NAME"));
            inputItemQuantity.setText(getIntent().getStringExtra("ITEM_QUANTITY"));
            editMode = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.save_action && !editMode){
            realm.beginTransaction();
            ListItem listItem = realm.createObject(ListItem.class);
            listItem.setName(inputItemName.getText().toString());
            listItem.setQuantity(inputItemQuantity.getText().toString());
            listItem.setCompleted(false);
            listItem.setId(UUID.randomUUID().toString());
            listItem.setTimestamp(System.currentTimeMillis());
            realm.commitTransaction();

            setResult(RESULT_OK);

            finish();
        }

        if(item.getItemId()==R.id.save_action && editMode){
            realm.beginTransaction();
            ListItem listItem = realm.where(ListItem.class).equalTo("id", getIntent().getStringExtra("ITEM_ID")).findFirst();
            listItem.setName(inputItemName.getText().toString());
            listItem.setQuantity(inputItemQuantity.getText().toString());
            listItem.setTimestamp(System.currentTimeMillis());
            realm.commitTransaction();

            setResult(RESULT_OK);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
