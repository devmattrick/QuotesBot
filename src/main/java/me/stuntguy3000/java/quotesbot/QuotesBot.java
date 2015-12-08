package me.stuntguy3000.java.quotesbot;

import lombok.Getter;
import me.stuntguy3000.java.quotesbot.handler.*;
import me.stuntguy3000.java.quotesbot.hook.TelegramHook;
import org.apache.commons.io.FileUtils;
import pro.zackpollard.telegrambot.api.TelegramBot;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

// @author Luke Anderson | stuntguy3000
public class QuotesBot {
    public static int BUILD = 0;
    @Getter
    public static QuotesBot instance;
    @Getter
    private CacheHandler cacheHandler = new CacheHandler();
    @Getter
    private CommandHandler commandHandler = new CommandHandler();
    @Getter
    private ConfigHandler configHandler = new ConfigHandler();
    @Getter
    private boolean mazenMode;
    @Getter
    private File outputFolder;
    @Getter
    private PersonHandler personHandler = new PersonHandler();
    @Getter
    private Thread updaterThread;

    private void connectTelegram() {
        LogHandler.log("Connecting to Telegram...");
        new TelegramHook(configHandler.getBotSettings().getTelegramKey(), this);
    }

    public void main() {
        instance = this;

        File build = new File("build");

        if (!build.exists()) {
            try {
                build.createNewFile();
                PrintWriter writer = new PrintWriter(build, "UTF-8");
                writer.print(0);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BUILD = Integer.parseInt(FileUtils.readFileToString(build));
        } catch (IOException e) {
            e.printStackTrace();
        }

        outputFolder = new File("output");

        if (outputFolder.exists()) {
            for (File file : outputFolder.listFiles()) {
                file.delete();
            }
        } else {
            outputFolder.mkdirs();
        }

        LogHandler.log("======================================");
        LogHandler.log(" QuotesBot build " + BUILD + " by @stuntguy3000");
        LogHandler.log("======================================");

        connectTelegram();

        if (this.getConfigHandler().getBotSettings().getAutoUpdater()) {
            LogHandler.log("Starting auto updater...");
            Thread updater = new Thread(new UpdateHandler(this, "QuotesBot"));
            updater.start();
            updaterThread = updater;
        } else {
            LogHandler.log("** Auto Updater is set to false **");
        }

        while (true) {
            String in = System.console().readLine();
            switch (in.toLowerCase()) {
                case "botfather": {
                    LogHandler.log(getCommandHandler().getBotFatherString());
                    continue;
                }
                case "fonts": {
                    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (String font : fonts) {
                        stringBuilder.append(font).append(", ");
                    }

                    System.out.println(stringBuilder.toString());
                }
                case "quit":
                case "stop":
                case "exit": {
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        new QuotesBot().main();
    }

    public void sendToAdmins(String message) {
        for (int admin : configHandler.getBotSettings().getTelegramAdmins()) {
            TelegramBot.getChat(admin).sendMessage(message, TelegramHook.getBot());
        }
    }

    public void stopUpdater() {
        updaterThread.interrupt();
    }

    public void toggleMazenMode() {
        mazenMode = !mazenMode;
    }
}
    