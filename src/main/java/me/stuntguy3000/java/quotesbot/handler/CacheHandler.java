package me.stuntguy3000.java.quotesbot.handler;

import me.stuntguy3000.java.quotesbot.object.Person;
import me.stuntguy3000.java.quotesbot.object.Quote;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// @author Luke Anderson | stuntguy3000
public class CacheHandler {
    private HashMap<Quote, String> cachedQuotes = new HashMap<>();

    public void addQuote(String message, Person person, File file) {
        cachedQuotes.put(new Quote(person, message), file.getAbsolutePath());
    }

    public File getCached(String message, Person person) {
        for (Map.Entry<Quote, String> quote : cachedQuotes.entrySet()) {
            if (quote.getKey().getMessage().equals(message) &&
                    quote.getKey().getPerson().getShortID().equals(person.getShortID())) {
                return new File(quote.getValue());
            }
        }

        return null;
    }
}
    