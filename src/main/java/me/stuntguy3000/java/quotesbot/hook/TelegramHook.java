package me.stuntguy3000.java.quotesbot.hook;

import lombok.Getter;
import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.handler.LogHandler;
import me.stuntguy3000.java.quotesbot.object.Command;
import me.stuntguy3000.java.quotesbot.object.Person;
import me.stuntguy3000.java.quotesbot.util.ClassGetter;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.List;

// @author Luke Anderson | stuntguy3000
public class TelegramHook implements Listener {
    @Getter
    private static TelegramBot bot;
    @Getter
    private final QuotesBot instance;

    public TelegramHook(String authKey, QuotesBot instance) {
        this.instance = instance;

        bot = TelegramBot.login(authKey);
        bot.startUpdates(false);
        bot.getEventsManager().register(this);
        LogHandler.log("Connected to Telegram.");

        instance.sendToAdmins("Bot has connected, running build #" + QuotesBot.BUILD);

        this.initializeCommands();
    }

    private void initializeCommands() {
        List<Class<?>> allCommands = ClassGetter.getClassesForPackage("me.stuntguy3000.java.quotesbot.command.");
        allCommands.stream().filter(Command.class::isAssignableFrom).forEach(clazz -> {
            try {
                Command command = (Command) clazz.newInstance();
                LogHandler.log("Registered command " + command.getName());
            } catch (InstantiationException | IllegalAccessException e) {
                LogHandler.log(clazz.getSimpleName() + " failed to instantiate:");
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onCommandMessageReceived(CommandMessageReceivedEvent event) {
        String command = event.getCommand();

        instance.getCommandHandler().executeCommand(command, event);
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {
        if (event.getMessage().getSender().getId() == 91845503 && getInstance().isMazenMode()) {
            Person person = getInstance().getPersonHandler().getPerson("mazen");
            person.generateImage(event.getContent().getContent(), event.getMessage().getChat());
        }
    }
}
    