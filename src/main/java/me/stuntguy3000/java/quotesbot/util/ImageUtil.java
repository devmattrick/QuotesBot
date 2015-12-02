package me.stuntguy3000.java.quotesbot.util;

import me.stuntguy3000.java.quotesbot.QuotesBot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++) {
                if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                    currentLine += " " + words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if (currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
    }

    public static File addText(File baseImage, String text, int lineWidth, int x, int y) {
        File output = new File(QuotesBot.getInstance().getOutputFolder() + File.separator + new RandomString(5).nextString() + ".png");
        try {
            Font font = new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 80);
            BufferedImage image = ImageIO.read(baseImage);

            Graphics g = image.getGraphics();
            g.setFont(font);

            ImageUtil.drawStringMultiLine(g, text, lineWidth, x, y);

            g.dispose();

            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
    