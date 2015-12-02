package me.stuntguy3000.java.quotesbot.person;

import me.stuntguy3000.java.quotesbot.handler.LogHandler;
import me.stuntguy3000.java.quotesbot.hook.TelegramHook;
import me.stuntguy3000.java.quotesbot.object.Person;
import me.stuntguy3000.java.quotesbot.util.ImageUtil;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.chat.message.send.ChatAction;
import pro.zackpollard.telegrambot.api.chat.message.send.InputFile;
import pro.zackpollard.telegrambot.api.chat.message.send.SendableChatAction;
import pro.zackpollard.telegrambot.api.chat.message.send.SendablePhotoMessage;

import java.io.File;

// @author Luke Anderson | stuntguy3000
public class NeildeGrasseTyson extends Person {

    private File baseImage = new File("images/neil.jpg");

    @Override
    public void generateImage(String text, Chat chat) {
        chat.sendMessage(SendableChatAction.builder().chatAction(ChatAction.UPLOADING_PHOTO).build(), TelegramHook.getBot());
        File quote = ImageUtil.addText(baseImage, text, 900, 100, 240);
        InputFile inputFile = new InputFile(quote);
        chat.sendMessage(SendablePhotoMessage.builder()
            .photo(inputFile)
                .caption("test")
            .build()
        , TelegramHook.getBot());
        LogHandler.log("Sent image");
    }

    @Override
    public String getShortID() {
        return "Neil";
    }

    @Override
    public String getPersonName() {
        return "Neil deGrasse Tyson";
    }
}
    