package me.stuntguy3000.java.quotesbot.command;

import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.object.Command;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

// @author Luke Anderson | stuntguy3000
public class VersionCommand extends Command {
    public VersionCommand() {
        super(QuotesBot.getInstance(), "version", "/version View the bot's current version");
    }

    public void processCommand(CommandMessageReceivedEvent event) {
        Chat chat = event.getChat();

        respond(chat, "Telegames by @stuntguy3000 build " + QuotesBot.BUILD + ".");
    }
}