package contactmanager.hci.com.contactsmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import contactmanager.hci.com.contactsmanager.R;
import contactmanager.hci.com.contactsmanager.model.ContactModel;

/**
 * Created by darshanbidkar on 3/20/16.
 */
public class ContactsAdapter extends ArrayAdapter<ContactModel> {

    Context context;
    private static class ViewHolder {
        TextView mName;
        TextView mPhone;
    }

    public ContactsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactModel currentEntry = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, null);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) convertView.findViewById(R.id.item_contact_name);
            viewHolder.mPhone = (TextView) convertView.findViewById(R.id.item_contact_phone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mName.setText(currentEntry.getmFirstName() + " " + currentEntry.getmLastName());
        viewHolder.mPhone.setText(currentEntry.getmPhone());


        return convertView;
    }
}
