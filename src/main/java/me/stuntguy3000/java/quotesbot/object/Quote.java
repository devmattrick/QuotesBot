package me.stuntguy3000.java.quotesbot.object;

import lombok.Getter;

// @author Luke Anderson | stuntguy3000
public class Quote {
    @Getter
    private final Person person;
    @Getter
    private final String message;

    public Quote(Person person, String message) {
        this.person = person;
        this.message = message;
    }
}
    