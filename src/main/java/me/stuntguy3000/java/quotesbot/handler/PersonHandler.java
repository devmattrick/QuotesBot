package me.stuntguy3000.java.quotesbot.handler;

import me.stuntguy3000.java.quotesbot.object.Person;

import java.util.HashMap;

// @author Luke Anderson | stuntguy3000
public class PersonHandler {
    public HashMap<String, Person> people;

    public PersonHandler() {
        people = new HashMap<>();
    }

    public void registerPerson(Person person) {
        people.put(person.getShortID().toLowerCase(), person);
    }

    public Person getPerson(String personName) {
        for (Person person : people.values()) {
            if (personName.equalsIgnoreCase(person.getShortID())) {
                return person;
            }
        }
        return null;
    }

    public String getPersonList() {
        StringBuilder sb = new StringBuilder();
        for (Person person : people.values()) {
            sb.append(person.getPersonName()).append(" (ID: ").append(person.getShortID()).append(")\n");
        }

        return sb.toString();
    }
}
    