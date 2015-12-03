package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.object.Command;
import me.stuntguy3000.java.quotesbot.object.Person;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class QuoteCommand extends Command {
    public QuoteCommand() {
        super(QuotesBot.getInstance(), "quote", "/quote <person> <text> Generate a quote.");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        String[] args = event.getArgs();

        if (args.length < 2) {
            respond(event.getChat(), "Syntax: /quote <person> <text>");
        } else {
            String personName = args[0];
            StringBuilder quote = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                quote.append(args[i]);
                quote.append(" ");
            }

            Person person = getInstance().getPersonHandler().getPerson(personName);

            if (person == null) {
                respond(event.getChat(), "Invalid person specified!");
            } else {
                person.generateImage(quote.toString(), event.getMessage().getChat());
            }
        }
    }
}