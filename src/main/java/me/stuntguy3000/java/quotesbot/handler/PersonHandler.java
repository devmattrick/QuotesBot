package me.stuntguy3000.java.quotesbot.handler;

import me.stuntguy3000.java.quotesbot.object.Person;

// @author Luke Anderson | stuntguy3000
public class PersonHandler {
    public Person getPerson(String personName) {
        for (Person person : Person.values()) {
            if (personName.equalsIgnoreCase(person.name())) {
                return person;
            }
        }
        return null;
    }

    public String getPersonList() {
        StringBuilder sb = new StringBuilder();
        for (Person person : Person.values()) {
            sb.append(person.getFullName()).append(" (ID: ").append(person.name()).append(")\n");
        }

        return sb.toString();
    }
}
    