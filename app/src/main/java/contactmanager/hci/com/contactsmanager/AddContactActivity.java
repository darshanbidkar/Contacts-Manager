package contactmanager.hci.com.contactsmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import contactmanager.hci.com.contactsmanager.datahandler.DataHandler;
import contactmanager.hci.com.contactsmanager.model.ContactModel;
import contactmanager.hci.com.contactsmanager.widgets.CustomDatePicker;

/**
 * Created by darshanbidkar on 3/21/16.
 */
public class AddContactActivity extends AppCompatActivity {

    private EditText mFirstName, mLastName, mPhone, mEmailId;
    private Button mSaveButton, mEditButton;
    private CustomDatePicker mDatePicker;
    private TextView mDateAdded;

    private ArrayList<ContactModel> contacts;
    private ContactModel model;
    private Button mDeleteButton;
    private ScrollView mainScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mFirstName = (EditText) findViewById(R.id.new_firstname);
        mLastName = (EditText) findViewById(R.id.new_lastname);
        mPhone = (EditText) findViewById(R.id.new_phone);
        mEmailId = (EditText) findViewById(R.id.new_emailid);
        mDateAdded = (TextView) findViewById(R.id.new_dateAdded);
        mainScrollView = (ScrollView)findViewById(R.id.scrollView);
        mainScrollView.smoothScrollTo(0,0);
        contacts = getIntent().getParcelableArrayListExtra("contacts");
        model = getIntent().getExtras().getParcelable("contact");

        mDatePicker = (CustomDatePicker) findViewById(R.id.new_datePicker);
        mDateAdded.setText("Date added: " + mDatePicker.getMonth() + "/" + mDatePicker.getDayOfMonth() + "/" + mDatePicker.getYear());
        mDatePicker.init(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), mOnDateChangedListener);
        mEditButton = (Button) findViewById(R.id.new_edit);
        mSaveButton = (Button) findViewById(R.id.new_save);
        mDeleteButton = (Button) findViewById(R.id.new_delete);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model == null) {
                    model = new ContactModel();
                } else {
                    contacts.remove(model);
                }
                model.setmFirstName(mFirstName.getText().toString());
                model.setmLastName(mLastName.getText().toString());
                model.setmPhone(mPhone.getText().toString());
                model.setmEmailId(mEmailId.getText().toString());
                model.setmDateAdded(mDateAdded.getText().toString());
                contacts.add(model);
                DataHandler.getInstance().writeEntries(getApplicationContext(), contacts);
                finish();
            }
        });

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditable(true);
                mainScrollView.pageScroll(View.FOCUS_UP);
                Toast.makeText(AddContactActivity.this, "You can now edit!", Toast.LENGTH_SHORT).show();
                mEditButton.setVisibility(View.GONE);
                mSaveButton.setVisibility(View.VISIBLE);
            }
        });
        populate(model);

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddContactActivity.this).setMessage("Are you sure you want to delete?").
                        setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    private void populate(ContactModel contactModel) {
        if (contactModel == null) {
            mSaveButton.setVisibility(View.VISIBLE);
            mEditButton.setVisibility(View.GONE);
            return;
        }

        mSaveButton.setVisibility(View.GONE);
        mEditButton.setVisibility(View.VISIBLE);
        /*mSaveButton.setText("EDIT");
        */
        mFirstName.setText(contactModel.getmFirstName());
        mLastName.setText(contactModel.getmLastName());
        mPhone.setText(contactModel.getmPhone());
        mEmailId.setText(contactModel.getmEmailId());
        mDateAdded.setText(contactModel.getmDateAdded());
        String date = contactModel.getmDateAdded().substring(contactModel.getmDateAdded().lastIndexOf(" ") + 1);
        String dateSplit[] = date.split("/");
        mDatePicker.init(Integer.valueOf(dateSplit[2]),
                Integer.valueOf(dateSplit[0]) - 1,
                Integer.valueOf(dateSplit[1]), mOnDateChangedListener);
        setEditable(false);
    }

    public void setEditable(boolean what){
        mFirstName.setFocusable(what);
        mLastName.setFocusable(what);
        mPhone.setFocusable(what);
        mEmailId.setFocusable(what);
        mDateAdded.setFocusable(what);
        mDatePicker.setFocusable(what);
        mFirstName.setFocusable(what);
        mFirstName.setFocusableInTouchMode(what);
        mFirstName.setClickable(what);
        mLastName.setFocusableInTouchMode(what);
        mLastName.setClickable(what);
        mPhone.setFocusableInTouchMode(what);
        mPhone.setClickable(what);
        mEmailId.setFocusableInTouchMode(what);
        mEmailId.setClickable(what);
        /*mPhone.setFocusableInTouchMode(what);
        mPhone.setClickable(what);
*/


    }
    private final DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mDateAdded.setText("Date added: " + monthOfYear + "/" + dayOfMonth + "/" + year);
        }
    };
}
