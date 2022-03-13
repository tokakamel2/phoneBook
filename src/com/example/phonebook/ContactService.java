package com.example.phonebook;

import java.io.*;
import java.lang.reflect.Array;

import java.util.*;

public class ContactService {

    public TreeSet<Contact> contacts = new TreeSet<>(new NameComparator());
    public void saveToFile() throws IOException {
        String rawData="";
        Iterator<Contact> it = contacts.iterator();
        while (it.hasNext()){
            Contact current = it.next();
            String phoneNumbers = "";
            for(int i=0; i<current.getPhoneNumbers().size();i++){
                phoneNumbers= phoneNumbers+current.getPhoneNumbers().get(i).getPhone()+"__"+current.getPhoneNumbers().get(i).getType()+",,";
            }
            rawData = rawData+ current.getName() +"}}"+phoneNumbers+"}}" +current.isFavourite()+"@@";
        }
        FileWriter myWriter = new FileWriter("data.txt",false);
        myWriter.write(rawData);
        myWriter.close();


    }
    public void readFromFile() throws IOException {

        contacts.clear();
        File myObj = new File("data.txt");
        Scanner myReader = new Scanner(myObj);
        String data ="";
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();


        String[] contactsRawData = data.split("@@",0);
        for(String a : contactsRawData){
            String[] elements = a.split("}}");
            String contactName = elements[0];
            String phoneNumbersString = elements[1];
            boolean isFavourite;
            if(elements[2].equals("true")){
                 isFavourite = true;
            }else{
                 isFavourite = false;
            }

            List<PhoneNumber> phoneNumbers=new ArrayList<>();
           for(String b : phoneNumbersString.split(",,",0)){
            String[] phoneNumberString= b.split("__",0);
             String currentNumber= phoneNumberString[0];
             String currentType = phoneNumberString[1];
             PhoneNumber phoneNumber = new PhoneNumber(currentNumber,currentType);
             phoneNumbers.add(phoneNumber);
          }
           Contact contact = new Contact(contactName,isFavourite,phoneNumbers);
           contacts.add(contact);

        }}
        myReader.close();

    }


    public Contact addNewContact(String name,String phone, String type) throws IOException {
        if(!checkExistence(name,phone)){
        PhoneNumber phoneNumber= new PhoneNumber(phone,type);
        List<PhoneNumber> phoneNumbers= new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        Contact newContact = new Contact(name,false,phoneNumbers);
        contacts.add(newContact);
        saveToFile();
        //fileSystemContacts();
        return newContact;
        }else {
            return null;
        }
    }
    public void savePhoneNumber(Contact contact) throws IOException {
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
                contacts.remove(contact);
                contacts.add(current);
                saveToFile();
                break;
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
    public boolean checkExistence(String name,String phone) throws IOException {
        readFromFile();
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
    public boolean checkExistence(String phone) throws IOException {
        readFromFile();
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
        readFromFile();
        System.out.println("number of contacts: "+contacts.size());
        System.out.println("-------");
        Iterator<Contact> it = contacts.iterator();
        for(int i=0; i<contacts.size(); i++){
            Contact current=it.next();
            System.out.println(i+1);
            System.out.println("name: "+current.getName());
            for (int y=0; y<current.getPhoneNumbers().size();y++) {
                System.out.println(current.getPhoneNumbers().get(y).getType() + ": " + current.getPhoneNumbers().get(y).getPhone());

            }
            System.out.println("favourite: " + current.isFavourite());

        }
        System.out.println("-------");

    }
    public void displayNumbers(Contact contact){
        for(int i=0;i < contact.getPhoneNumbers().size();i++){
            System.out.println(i+3+". "+"edit/delete "+contact.getPhoneNumbers().get(i).getType()+": " +contact.getPhoneNumbers().get(i).getPhone());
        }
    }
    public void editNumber(Contact contact, String chosenNumber) throws IOException {
        int index = Integer.valueOf(chosenNumber)-3;
        System.out.println("1.edit "+contact.getPhoneNumbers().get(index).getPhone());
        System.out.println("2.delete "+contact.getPhoneNumbers().get(index).getPhone());
        Scanner scanner = new Scanner(System.in);
        String delete = scanner.nextLine();
        if(delete.equals("2")){
            contact.getPhoneNumbers().remove(index);
            contacts.remove(contact);
            contact.setPhoneNumbers(contact.getPhoneNumbers());
            contacts.add(contact);
            saveToFile();
        }else{
        System.out.println("Enter new number");
        String newNumber = scanner.nextLine();
        if(!checkExistence(newNumber)) {
            contact.getPhoneNumbers().get(index).setPhone(newNumber);
            contacts.remove(contact);
            contact.setPhoneNumbers(contact.getPhoneNumbers());
            contacts.add(contact);
            saveToFile();
        }else{
            System.out.println("Number already exists");
            editNumber(contact, chosenNumber);
        }}

    }


    public void editContact() throws IOException {
        listAllContacts();
        System.out.println("Enter the name of contact you want to edit");
        Scanner scanner = new Scanner(System.in);
        String contactName = scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact current =iterator.next();
            if(current.getName().equals(contactName)){
                System.out.println("Choose what you want to edit");
                System.out.println("1. edit name");
                System.out.println("2. add extra number");

                displayNumbers(current);
                String editChoose = scanner.nextLine();
                if (editChoose.equals("1")){
                    System.out.println("Enter new name");
                    String newName = scanner.nextLine();
                    contacts.remove(current);
                    current.setName(newName);
                    contacts.add(current);
                    saveToFile();

                }else if(editChoose.equals("2")){
                    savePhoneNumber(current);
                }
                else{
                    editNumber(current,editChoose);
                }

                break;
            }
            if(!iterator.hasNext() && !current.getName().equals(contactName)){
                System.out.println("the name you entered doesn't exist");
            }
        }
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
        readFromFile();
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
                saveToFile();
                break;
            }
        }

    }
    public void listFavourites() throws IOException {
        readFromFile();
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
    public void searchByName() throws IOException {
        readFromFile();
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
    public void searchByNumber() throws IOException {
        readFromFile();
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
