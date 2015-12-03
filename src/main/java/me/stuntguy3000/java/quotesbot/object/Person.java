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

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// @author Luke Anderson | stuntguy3000
public enum Person {
    Neil("Neil deGrasse Tyson", 20, 230, 6, 20, 30),
    Obama("President Barack Obama", 16, 150, 7, 20, 30),
    Hitler("Adolf Hitler", 22, 150, 7, 160, 25),
    Mandela("Nelson Mandela", 20, 270, 4, 160, 70),
    Cena("John Cena", 20, 200, 5, 170, 60);

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
            try {
                quote = ImageUtil.addText(ImageIO.read(getClass().getResource("/" + this.name().toLowerCase() + ".jpg")),
                        message, fontSize, lineSize, maxRows, x, y);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
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
        File quote = null;
        try {
            quote = ImageUtil.borderImage(ImageIO.read(getClass().getResource("/" + this.name().toLowerCase() + ".jpg")),
                    fontSize, lineSize, maxRows, x, y);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        chat.sendMessage(SendableChatAction.builder().chatAction(ChatAction.UPLOADING_PHOTO).build(), TelegramHook.getBot());
        InputFile inputFile = new InputFile(quote);
        chat.sendMessage(SendablePhotoMessage.builder()
                .photo(inputFile)
                .caption("Text Area Border")
                .build()
                , TelegramHook.getBot());
    }
}
    