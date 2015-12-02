package me.stuntguy3000.java.quotesbot.object;

import pro.zackpollard.telegrambot.api.chat.Chat;

// @author Luke Anderson | stuntguy3000
public abstract class Person {
    public abstract void generateImage(String text, Chat chat);
    public abstract String getShortID();
    public abstract String getPersonName();
}
    