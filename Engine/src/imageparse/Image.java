package imageparse;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gardir on 27.05.17.
 */
public class Image extends JPanel {

    private static String image_directory = "/home/gardir/programmering/java/box-of-letters-words-discover/img/";
    public static File frame_only = new File(image_directory + "frame_only.jpg");
    private static File whole = new File(image_directory + "whole.jpg");
    private static File words_only = new File(image_directory + "words_only.jpg");
    private static int BORDER_TOLERANCE = 20;

    public void paint(Graphics g) {
        BufferedImage img = findBorders(Image.frame_only);
        g.drawImage(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, this);
    }

    private BufferedImage findBorders(File image) {
        BufferedImage img = null;
        BufferedImage editimg = null;
        try {
            img = ImageIO.read(image);
            editimg = ImageIO.read(image);
            drawBiggestSquare(img, editimg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return editimg;
    }

    private void drawBiggestSquare(BufferedImage img, BufferedImage editimg) {
        int height = img.getHeight(); int width = img.getWidth();
        Square = null;
        for (int y=0; y < height; y++) {
            for (int x=0; x < width; x++) {
                if ( checkBorderRight(x, y, img) ) {

                }
            }
        }
    }

    private void editAllEdges(BufferedImage img, BufferedImage editimg) {
        int height = img.getHeight(); int width = img.getWidth();
        for (int y=0; y < height; y++) {
            for (int x=0; x < width; x++) {
                if ( isBorder(x, y, img) ) {
                    editimg.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }
    }

    private static boolean isBorder(int x, int y, BufferedImage img) {
        if (checkBorderLeft(x, y, img)) return true;
        if (checkBorderRight(x, y, img)) return true;
        if (checkBorderUp(x, y, img)) return true;
        if (checkBorderBelow(x, y, img)) return true;
        return false;
    }

    private static boolean checkBorderBelow(int x, int y, BufferedImage img) {
        Color pixel_color = new Color( img.getRGB(x, y) );
        if ( y+1 < img.getHeight() ) {
            // Check below
            Color below_color = new Color( img.getRGB( x, y + 1) );
            if ( validColorTolerance(pixel_color, below_color) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkBorderUp(int x, int y, BufferedImage img) {
        Color pixel_color = new Color( img.getRGB(x, y) );
        if ( y > 0 ) {
            // Check up
            Color top_color = new Color( img.getRGB( x, y - 1) );
            if ( validColorTolerance(pixel_color, top_color) ) {
                return true;
            }

        }
        return false;
    }

    private static boolean checkBorderRight(int x, int y, BufferedImage img) {
        Color pixel_color = new Color( img.getRGB(x, y) );
        if ( x+1 < img.getWidth() ) {
            // Check right
            Color right_color = new Color( img.getRGB( x + 1, y) );
            if ( validColorTolerance(pixel_color, right_color) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkBorderLeft(int x, int y, BufferedImage img) {
        Color pixel_color = new Color( img.getRGB(x, y) );
        if ( x > 0 ) {
            // Check left
            Color left_color = new Color( img.getRGB( x - 1, y) );
            if ( validColorTolerance(pixel_color, left_color) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean validColorTolerance(Color color_one, Color color_two) {
        return Math.abs( colorValue( color_one ) - colorValue(color_two) ) > BORDER_TOLERANCE;
    }

    private static int colorValue(Color c) {
        return (int)(c.getRed() + c.getGreen() + c.getBlue() / 3f);
    }

}
