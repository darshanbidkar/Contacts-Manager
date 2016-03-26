package contactmanager.hci.com.contactsmanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by darshanbidkar on 3/20/16.
 */
public class ContactModel implements Parcelable {
    private String mFirstName;
    private String mLastName;
    private String mPhone;
    private String mEmailId;
    private String mDateAdded;

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    protected ContactModel(Parcel in) {
        mFirstName = in.readString();
        mLastName = in.readString();
        mPhone = in.readString();
        mEmailId = in.readString();
        mDateAdded = in.readString();
    }

    public ContactModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mPhone);
        dest.writeString(mEmailId);
        dest.writeString(mDateAdded);
    }

    @Override
    public String toString() {

        return new StringBuffer().append(mFirstName).append("|").
                append(mLastName).append("|").
                append(mPhone).append("|").
                append(mEmailId).append("|").
                append(mDateAdded).toString();
    }

    public boolean isEqual(ContactModel contactModel) {
        if (!this.mFirstName.equalsIgnoreCase(contactModel.getmFirstName())) {
            return false;
        }
        if (!this.mLastName.equalsIgnoreCase(contactModel.getmLastName())) {
            return false;
        }
        if (!this.mEmailId.equalsIgnoreCase(contactModel.getmEmailId())) {
            return false;
        }
        if (!this.mDateAdded.equalsIgnoreCase(contactModel.getmDateAdded())) {
            return false;
        }
        if (!this.mPhone.equalsIgnoreCase(contactModel.getmPhone())) {
            return false;
        }

        return true;
    }
    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmEmailId() {
        return mEmailId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public String getmDateAdded() {
        return mDateAdded;
    }

    public void setmDateAdded(String mDateAdded) {
        mDateAdded = mDateAdded.substring(mDateAdded.lastIndexOf(" "));
        String dateSplit[] = mDateAdded.split("/");
        int month = Integer.valueOf(dateSplit[0].trim());
        int day = Integer.valueOf(dateSplit[1].trim());
        int year = Integer.valueOf(dateSplit[2].trim());
        mDateAdded = "Date added: " + (month + 1) + "/" + day + "/" + year;
        this.mDateAdded = mDateAdded;
    }
}
