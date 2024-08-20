package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        //blank canvas, width, height, image type
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        //what is drawn is saved in scaled image
        Graphics2D g2 = scaledImage.createGraphics();
         //draw image with scaled size
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
