package com.example.phonebook;


import java.util.List;

public class Contact {

    private String name;
    private boolean favourite;
    private List<PhoneNumber> phoneNumbers;




    public Contact( String name, boolean favourite, List<PhoneNumber> phoneNumbers) {

        this.name = name;
        this.favourite = favourite;
        this.phoneNumbers = phoneNumbers;
    }

    public Contact(String name, List<PhoneNumber> phoneNumber, boolean b) {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }



}
