package me.stuntguy3000.java.quotesbot.util;

import me.stuntguy3000.java.quotesbot.QuotesBot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int maxRows, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            int rowCount = 1;

            if (words.length > 1) {
                String currentLine = words[0];
                for (int i = 1; i < words.length; i++) {
                    if (rowCount > maxRows) {
                        break;
                    }

                    if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                        currentLine += " " + words[i];
                    } else {
                        g.drawString(currentLine, x, y);
                        y += m.getHeight();
                        rowCount++;
                        currentLine = words[i];
                    }
                }
                if (currentLine.trim().length() > 0) {
                    g.drawString(currentLine, x, y);
                }
            } else {
                char[] characters = text.toCharArray();
                StringBuilder currentLine = new StringBuilder();

                int lineSize = 0;
                for (char c : characters) {
                    if (rowCount > maxRows) {
                        break;
                    }

                    lineSize += m.stringWidth(String.valueOf(c));
                    currentLine.append(c);

                    if (lineSize > lineWidth) {
                        lineSize = 0;
                        g.drawString(currentLine.toString(), x, y);

                        y += m.getHeight();
                        rowCount++;
                        currentLine = new StringBuilder();
                    }
                }
                if (currentLine.toString().trim().length() > 0) {
                    g.drawString(currentLine.toString(), x, y);
                }
            }
        }
    }

    public static File addText(Image baseImage, String text, int fontSize, int lineWidth, int maxRows, int x, int y) {
        File output = new File(QuotesBot.getInstance().getOutputFolder() + File.separator + new RandomString(5).nextString() + ".png");
        try {
            Font font = new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, fontSize);
            BufferedImage image = imageToBufferedImage(baseImage);

            Graphics g = image.getGraphics();

            g.setFont(font);
            ImageUtil.drawStringMultiLine(g, text, lineWidth, maxRows, x, y);
            g.dispose();

            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static File borderImage(Image baseImage, int fontSize, int lineSize, int maxRows, int x, int y) {
        File output = new File(QuotesBot.getInstance().getOutputFolder() + File.separator + new RandomString(5).nextString() + ".png");
        try {
            Font font = new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, fontSize);
            BufferedImage image = imageToBufferedImage(baseImage);
            Graphics g = image.getGraphics();

            g.setColor(Color.RED);
            g.drawRect(x, y, lineSize, 3);

            g.setFont(font);
            ImageUtil.drawStringMultiLine(g, "abcdefghijklmnoprstuvwxyz0123456789", lineSize, maxRows, x, y);

            g.dispose();

            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static BufferedImage imageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
                (im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

    /*public static File addText(File baseImage, String message, int fontSize, int lineSize, int x, int y) {
        File output = new File(QuotesBot.getInstance().getOutputFolder() + File.separator + new RandomString(5).nextString() + ".png");
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static File borderImage(File baseImage, int fontSize, int lineSize, int x, int y) {
        File output = new File(QuotesBot.getInstance().getOutputFolder() + File.separator + new RandomString(5).nextString() + ".png");
        try {
            BufferedImage image = ImageIO.read(baseImage);
            Graphics2D g = (Graphics2D) image.getGraphics();
            Font font = new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, fontSize);
            FontRenderContext frc = g.getFontRenderContext();
            TextLayout layout = new TextLayout("This is a really long string. It will totally word wrap, it may be super long or super short. You know what else is super short? Ask mazen.", font, frc);
            layout.draw(g, x, y);

            Rectangle2D bounds = layout.getBounds();
            bounds.setRect(x,
                    y,
                    lineSize,
                    120);
            g.draw(bounds);

            g.dispose();
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }*/
}
    