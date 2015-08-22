import imagereader.IImageProcessor;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class ImageProcessor implements IImageProcessor {

    public Image showChanelR(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        BufferedImage result = new BufferedImage(bufImage.getWidth(null), bufImage.getHeight(null), bufImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufImage.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int newPixel = colorToRGB(255, r, 0, 0);
                result.setRGB(i, j, newPixel);
            }
        }
        return (Image)result;
    }

    public Image showChanelG(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        BufferedImage result = new BufferedImage(bufImage.getWidth(null), bufImage.getHeight(null), bufImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufImage.getRGB(i, j);
                int g = (rgb >> 8) & 0xFF;
                int newPixel = colorToRGB(255, 0, g, 0);
                result.setRGB(i, j, newPixel);
            }
        }
        return (Image)result;
    }

    public Image showChanelB(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        BufferedImage result = new BufferedImage(bufImage.getWidth(null), bufImage.getHeight(null), bufImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufImage.getRGB(i, j);
                int b = rgb & 0xFF;
                int newPixel = colorToRGB(255, 0, 0, b);
                result.setRGB(i, j, newPixel);
            }
        }
        return (Image)result;
    }

    public Image showGray(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        BufferedImage grayImage = new BufferedImage(bufImage.getWidth(null), bufImage.getHeight(null), bufImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufImage.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayImage.setRGB(i, j, newPixel);
            }
        }
        return (Image)grayImage;
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }

}