package com.example.phonebook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        ContactService contactService = new ContactService();

        displayMainMenu(contactService);
    }
    public static void displayMainMenu(ContactService contactService) throws IOException {
        //contactService.fileSystemContacts();
        List<String> mainMenu = Arrays.asList(
                "please choose one of the below: (*Hint: Enter only the number of your choice)",
                "1. List all contacts",
                "2. Add new contact",
                "3. edit existing contact",
                "4. Mark contact as favourite",
                "5. Favourites list",
                "6. search for a contact by name",
                "7. search for a contact by phone number",
                "8. delete contact"
        );
        for(int i=0; i<mainMenu.size();i++){
            System.out.println(mainMenu.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();

        switch (sc){
            case "1":
               contactService.listAllContacts();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
               displayMainMenu(contactService);
              break;
            case "2":
                contactService.AddNewContactInput();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
                break;
            case "3":
                System.out.println(1);
            case "4":
                contactService.markAsFavourite();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
                break;
            case "5":
                contactService.listFavourites();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
                break;
            case "6":
                contactService.searchByName();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
                break;
            case "7":
                contactService.searchByNumber();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
                break;
            case "8":
                contactService.deleteContact();
                System.out.println("Press any button to go to main menu");
                scanner.nextLine();
                displayMainMenu(contactService);
        }

    }



}
