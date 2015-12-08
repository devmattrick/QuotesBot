package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.object.Command;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class MazenCommand extends Command {
    public MazenCommand() {
        super(QuotesBot.getInstance(), "mazen", "/mazen");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        QuotesBot.getInstance().toggleMazenMode();
    }
}