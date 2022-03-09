package com.example.phonebook;

import java.io.Serializable;
import java.util.Comparator;



class NameComparator implements Comparator<Contact>, Serializable
{
    public int compare(Contact s1, Contact s2)
    {
        return s1.getName().compareTo(s2.getName());
    }
}