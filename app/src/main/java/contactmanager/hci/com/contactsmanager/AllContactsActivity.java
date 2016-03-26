package contactmanager.hci.com.contactsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import contactmanager.hci.com.contactsmanager.adapters.ContactsAdapter;
import contactmanager.hci.com.contactsmanager.datahandler.DataHandler;
import contactmanager.hci.com.contactsmanager.model.ContactModel;
/**
 * Created by darshan bidkar and darshan reddy on 3/20/16.
 */
public class AllContactsActivity extends AppCompatActivity {

    private ListView mContactsView;
    private ContactsAdapter mContactsAdapter;
    private ArrayList<ContactModel> mContacts;

    /*
    *
    * @author: darshan bidkar
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        mContactsView = (ListView) findViewById(R.id.contactsList);
        mContacts = new ArrayList<>();
        mContactsAdapter = new ContactsAdapter(getApplicationContext(), R.layout.item_contact, mContacts);

        mContactsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                ContactModel selectedContact = mContacts.get((int) id);
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                intent.putParcelableArrayListExtra("contacts", mContacts);
                Bundle bundle = new Bundle();
                bundle.putParcelable("contact", selectedContact);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mContactsView.setAdapter(mContactsAdapter);
    }
    /*
    *
    * @author: darshan bidkar
    * */
    @Override
    protected void onResume() {
        super.onResume();
        mContacts = DataHandler.getInstance().getEntries(getApplicationContext());
        mContactsAdapter.clear();
        mContactsAdapter.addAll(mContacts);
        mContactsAdapter.notifyDataSetChanged();
    }
    /*
    *
    * @author: darshan bidkar
    * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return true;
    }

    /*
    *
    * @author: darshan reddy
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
            intent.putParcelableArrayListExtra("contacts", mContacts);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
