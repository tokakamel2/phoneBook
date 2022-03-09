package com.example.phonebook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;

import java.util.*;

public class ContactService {

    public TreeSet<Contact> contacts = new TreeSet<>(new NameComparator());
//    public void fileSystemContacts() throws IOException {
//       // Set<Contact> contacts = new TreeSet<Contact>();
//        FileOutputStream fos = new FileOutputStream("set.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(contacts);
//        oos.close();
//    }


    public Contact addNewContact(String name,String phone, String type) throws IOException {
        if(!checkExistence(name,phone)){
        PhoneNumber phoneNumber= new PhoneNumber(phone,type);
        List<PhoneNumber> phoneNumbers= new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        Contact newContact = new Contact(name,false,phoneNumbers);
        contacts.add(newContact);
        //fileSystemContacts();
        return newContact;
        }else {
            return null;
        }
    }
    public void savePhoneNumber(Contact contact){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Phone number");
        String phone = scanner.nextLine();
        if(!checkExistence(phone)){
        System.out.println("Choose the number type");
        System.out.println("1.Mobile");
        System.out.println("2.Home");
        System.out.println("3.Work");
        String typeNumber = scanner.nextLine();
        String type="";
        switch (typeNumber){
            case "2":
                type = "Home";
                break;
            case "3":
                type = "Work";
                break;
            default:
                type = "Mobile";
        }
        Iterator<Contact> it = contacts.iterator();
        while (it.hasNext()){
            Contact current = it.next();
           if(current.getName().equals(contact.getName())){
               PhoneNumber phoneNumber = new PhoneNumber(phone,type);
                current.getPhoneNumbers().add(phoneNumber);
            }
        }
        System.out.println("Do you want to add extra number?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String answer= scanner.nextLine();
        if(answer.equals("1")){
            savePhoneNumber(contact);
        }}

    }
    public void AddNewContactInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter contact name");
        String name = scanner.nextLine();
        System.out.println("Enter Phone number");
        String phone = scanner.nextLine();
        System.out.println("Choose the number type");
        System.out.println("1.Mobile");
        System.out.println("2.Home");
        System.out.println("3.Work");
        String typeNumber = scanner.nextLine();
        String type="";
        switch (typeNumber){
            case "2":
                type = "Home";
                break;
            case "3":
                type = "Work";
                break;
            default:
                type = "Mobile";
        }
        Contact contact= addNewContact(name,phone,type);
        System.out.println("Do you want to add extra number?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String answer= scanner.nextLine();
        if(answer.equals("1")){
            savePhoneNumber(contact);
        }

    }
    public boolean checkExistence(String name,String phone){
        for(int i=0; i<contacts.size();i++){
            if(contacts.iterator().next().getName().equals(name)){
                System.out.println("Name Already exists");
                return true;
            }else {
                for(int y=0; y<contacts.iterator().next().getPhoneNumbers().size();y++){
                    if(contacts.iterator().next().getPhoneNumbers().get(y).getPhone().equals(phone)){
                        System.out.println("Number Already exists");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkExistence(String phone){
        for(int i=0; i<contacts.size();i++){
                for(int y=0; y<contacts.iterator().next().getPhoneNumbers().size();y++){
                    if(contacts.iterator().next().getPhoneNumbers().get(y).getPhone().equals(phone)){
                        System.out.println("Number Already exists");
                        return true;
                    }
                }
        }
        return false;
    }
    public void listAllContacts() throws IOException {
        System.out.println("number of contacts: "+contacts.size());
        System.out.println("-------");
        Iterator<Contact> it = contacts.iterator();
        for(int i=0; i<contacts.size(); i++){
            Contact current=it.next();
            System.out.println(i+1);
            System.out.println("name: "+current.getName());
            for (int y=0; y<current.getPhoneNumbers().size();y++) {
                System.out.println(current.getPhoneNumbers().get(y).getType() + ": " + current.getPhoneNumbers().get(y).getPhone());
                System.out.println("favourite: " + current.isFavourite());
            }

        }
        System.out.println("-------");

    }

    public void editContact(){

    }
    public void deleteContact() throws IOException {
        listAllContacts();
        System.out.println("Choose contact name you want to delete");
        Scanner scanner = new Scanner(System.in);
        String contactName = scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact current =iterator.next();
            if(current.getName().equals(contactName)){
                contacts.remove(current);
                break;
            }
        }


    }
    public void markAsFavourite() throws IOException {
        System.out.println("Enter name of the contact you want to mark as favourite");
        Scanner scanner = new Scanner(System.in);
        String favName= scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact current =iterator.next();
            if(current.getName().equals(favName)){
                contacts.remove(current);
                current.setFavourite(true);
                contacts.add(current);
               // fileSystemContacts();
                break;
            }
        }

    }
    public void listFavourites(){
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact current =iterator.next();
            System.out.println("********");
            if(current.isFavourite()){

                System.out.println("name: "+current.getName());
                for (int y=0; y<current.getPhoneNumbers().size();y++) {
                    System.out.println(current.getPhoneNumbers().get(y).getType() + ": " + current.getPhoneNumbers().get(y).getPhone());
                }
                System.out.println("favourite: "+ current.isFavourite());
                System.out.println("********");

            }
        }
    }
    public void searchByName(){
        System.out.println("Enter the name you want to search with: ");
        Scanner scanner = new Scanner(System.in);
        String name= scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()){
            Contact current =iterator.next();
            if(current.getName().contains(name)){
                System.out.println("name: "+current.getName());
                for (int y=0; y<current.getPhoneNumbers().size();y++) {
                    System.out.println(current.getPhoneNumbers().get(y).getType() + ": " + current.getPhoneNumbers().get(y).getPhone());
                }
                System.out.println("favourite: "+ current.isFavourite());
                System.out.println("*********");
            }
        }
    }
    public void searchByNumber(){
        System.out.println("Enter the number you want to search with: ");
        Scanner scanner = new Scanner(System.in);
        String number= scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()){
            Contact current =iterator.next();
            for (int i =0; i < current.getPhoneNumbers().size();i++){
            if(current.getPhoneNumbers().get(i).getPhone().equals(number)){
                System.out.println("*********");
                System.out.println("name: "+current.getName());
                for (int y=0; y<current.getPhoneNumbers().size();y++) {
                    System.out.println(current.getPhoneNumbers().get(y).getType() + ": " + current.getPhoneNumbers().get(y).getPhone());
                }
                System.out.println("favourite: "+ current.isFavourite());
                System.out.println("*********");
                break;
            }
        }
        }
    }
}
