package me.stuntguy3000.java.quotesbot.object;

import lombok.Getter;
import me.stuntguy3000.java.quotesbot.QuotesBot;
import me.stuntguy3000.java.quotesbot.handler.CacheHandler;
import me.stuntguy3000.java.quotesbot.hook.TelegramHook;
import me.stuntguy3000.java.quotesbot.util.ImageUtil;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.chat.message.send.ChatAction;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableChatAction;
import pro.zackpollard.telegrambot.api.chat.message.send.SendablePhotoMessage;

import java.io.File;

// @author Luke Anderson | stuntguy3000
public enum Person {
    Neil("Neil deGrasse Tyson", 20, 230, 6, 20, 30);

    @Getter
    private String fullName;
    @Getter
    private int fontSize;
    @Getter
    private int lineSize;
    @Getter
    private int maxRows;
    @Getter
    private int x;
    @Getter
    private int y;

    Person(String fullName, int fontSize, int lineSize, int maxRows, int x, int y) {
        this.fullName = fullName;
        this.fontSize = fontSize;
        this.lineSize = lineSize;
        this.maxRows = maxRows;
        this.x = x;
        this.y = y;
    }

    public void generateImage(String message, Chat chat) {
        CacheHandler cacheHandler = QuotesBot.getInstance().getCacheHandler();
        File quote = cacheHandler.getCached(message, this);

        if (quote == null) {
            quote = ImageUtil.addText(new File("images/" + this.name().toLowerCase() + ".jpg"),
                    message, fontSize, lineSize, maxRows, x, y);
            cacheHandler.addQuote(message, this, quote);
        }

        chat.sendMessage(SendableChatAction.builder().chatAction(ChatAction.UPLOADING_PHOTO).build(), TelegramHook.getBot());
        InputFile inputFile = new InputFile(quote);
        chat.sendMessage(SendablePhotoMessage.builder()
                .photo(inputFile)
                .build()
                , TelegramHook.getBot());
    }

    public void sendBorderImage(Chat chat) {
        File quote = ImageUtil.borderImage(new File("images/" + this.name().toLowerCase() + ".jpg"),
                fontSize, lineSize, maxRows, x, y);

        chat.sendMessage(SendableChatAction.builder().chatAction(ChatAction.UPLOADING_PHOTO).build(), TelegramHook.getBot());
        InputFile inputFile = new InputFile(quote);
        chat.sendMessage(SendablePhotoMessage.builder()
                .photo(inputFile)
                .caption("Text Area Border")
                .build()
                , TelegramHook.getBot());
    }
}
    