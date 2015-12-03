package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.object.Command;
import me.stuntguy3000.java.quotesbot.object.Person;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class DebugCommand extends Command {
    public DebugCommand() {
        super(QuotesBot.getInstance(), "debug", "/debug <person>");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        String[] args = event.getArgs();

        if (QuotesBot.getInstance().getConfigHandler().getBotSettings().getTelegramAdmins()
                .contains(event.getMessage().getSender().getId())) {
            if (args.length < 1) {
                respond(event.getChat(), "Syntax: /debug <person>");
            } else {
                String personName = args[0];
                Person person = getInstance().getPersonHandler().getPerson(personName);

                if (person == null) {
                    respond(event.getChat(), "Invalid person specified!");
                } else {
                    person.sendBorderImage(event.getChat());
                }
            }
        }
    }
}