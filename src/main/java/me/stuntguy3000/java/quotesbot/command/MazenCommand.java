package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.hook.TelegramHook;
import me.stuntguy3000.java.quotesbot.object.Command;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class MazenCommand extends Command {
    public MazenCommand() {
        super(QuotesBot.getInstance(), "mazen", "/mazen");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        QuotesBot.getInstance().toggleMazenMode();
        //event.getChat().sendMessage("Mazen mode: " + QuotesBot.getInstance().isMazenMode(), TelegramHook.getBot());
        event.getChat().sendMessage("Mazen mode cannot be disabled.", TelegramHook.getBot());
    }
}