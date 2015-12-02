package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.hook.TelegramHook;
import me.stuntguy3000.java.quotesbot.object.Command;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class PersonListCommand extends Command {
    public PersonListCommand() {
        super(QuotesBot.getInstance(), "personlist", "/personlist View the list of profiles.");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        event.getChat().sendMessage("People List:\n" + getInstance().getPersonHandler().getPersonList(), TelegramHook.getBot());
    }
}