package com.example.phonebook;

public class PhoneNumber {

    private String phone;
    private String type;

    public PhoneNumber( String phone, String type) {

        this.phone = phone;
        this.type = type;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
