package contactmanager.hci.com.contactsmanager.datahandler;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import contactmanager.hci.com.contactsmanager.model.ContactModel;

/**
 * Created by darshanbidkar on 3/21/16.
 */
public class DataHandler {
    private static DataHandler sDataHandler;

    private DataHandler() {

    }

    public synchronized static DataHandler getInstance() {
        if (sDataHandler == null) {
            sDataHandler = new DataHandler();
        }
        return sDataHandler;
    }

    public void writeEntries(Context context, ArrayList<ContactModel> entries) {
        try {
            File dataFile = new File(context.getFilesDir(), "contacts.txt");
            if (dataFile.exists()) {
                dataFile.delete();
                dataFile.createNewFile();
            }

            FileWriter writer = new FileWriter(dataFile);
            for (ContactModel contact : entries) {
                Log.d(getClass().getSimpleName(), contact.toString());
                writer.write(contact.getmFirstName() + "|" + contact.getmLastName() + "|" + contact.getmPhone() + "|" + contact.getmEmailId() + "|" + contact.getmDateAdded() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ContactModel> getEntries(Context context) {
        ArrayList<ContactModel> contacts = new ArrayList<>();
        try {
            File dataFile = new File(context.getFilesDir(), "contacts.txt");
            if (!dataFile.exists())
                return contacts;
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String contactString;
            while ((contactString = reader.readLine()) != null && !contactString.trim().equals("")) {
                Log.d(getClass().getSimpleName(), "Entry: " + contactString);
                String[] split = contactString.split("\\|");
                ContactModel model = new ContactModel();
                model.setmFirstName(split[0]);
                model.setmLastName(split[1]);
                model.setmPhone(split[2]);
                model.setmEmailId(split[3]);
                model.setmDateAdded(split[4]);
                contacts.add(model);
                Log.d(getClass().getSimpleName(), "Read: " + model.toString());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
