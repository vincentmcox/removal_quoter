package indalosoftworks.removalquoter;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vincent on 02/09/2015.
 */
public class Client {
    private String name;
    private String address1;
    private String address2;
    private int fromRegionCode;
    private int fromCountryCode;
    private int toRegionCode;
    private int toCountryCode;
    private String mobileNumber;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getFromRegionCode() {
        return fromRegionCode;
    }

    public void setFromRegionCode(int fromRegionCode) {
        this.fromRegionCode = fromRegionCode;
    }

    public int getFromCountryCode() {
        return fromCountryCode;
    }

    public void setFromCountryCode(int fromCountryCode) {
        this.fromCountryCode = fromCountryCode;
    }

    public int getToRegionCode() {
        return toRegionCode;
    }

    public void setToRegionCode(int toRegionCode) {
        this.toRegionCode = toRegionCode;
    }

    public int getToCountryCode() {
        return toCountryCode;
    }

    public void setToCountryCode(int toCountryCode) {
        this.toCountryCode = toCountryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String emailAddress;

    public Client()
    {
        super();
    }
    public Client(String name, String address1, String address2, int fromRegionCode, int fromCountryCode, int toRegionCode, int toCountryCode, String mobileNumber, String emailAddress) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.fromRegionCode = fromRegionCode;
        this.fromCountryCode = fromCountryCode;
        this.toRegionCode = toRegionCode;
        this.toCountryCode = toCountryCode;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

}
