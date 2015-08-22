import java.awt.Toolkit;
import java.io.FileInputStream;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import imagereader.IImageIO;
import java.awt.image.BufferedImage;

public class FuckingImageIO implements IImageIO {

    public Image myRead(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        byte fourBytesBuffer[] = new byte[4];
        in.skip(18);
        in.read(fourBytesBuffer, 0, 4);
        int width = (((int)fourBytesBuffer[3] & 0xFF) << 24) | (((int)fourBytesBuffer[2] & 0xFF) << 16) |
            (((int)fourBytesBuffer[1] & 0xFF) << 8) | ((int)fourBytesBuffer[0] & 0xFF);
        in.read(fourBytesBuffer, 0, 4);
        int height = (((int)fourBytesBuffer[3] & 0xFF) << 24) | (((int)fourBytesBuffer[2] & 0xFF) << 16) |
            (((int)fourBytesBuffer[1] & 0xFF) << 8) | ((int)fourBytesBuffer[0] & 0xFF);
        in.skip(8);
        in.read(fourBytesBuffer, 0, 4);
        int imageSize = (((int)fourBytesBuffer[3] & 0xFF) << 24) | (((int)fourBytesBuffer[2] & 0xFF) << 16) |
            (((int)fourBytesBuffer[1] & 0xFF) << 8) | ((int)fourBytesBuffer[0] & 0xFF);
        in.skip(16);

        int zerosInEachRow = (imageSize / height - width * 3);
        int imageData[] = new int[height * width];
        byte sourceImage[] = new byte[imageSize];
        in.read(sourceImage, 0, imageSize);
        in.close();
        int sourceImageIndex = 0;
        int dataIndex;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                dataIndex = width * (height - i - 1) + j;
                imageData[dataIndex] = (255 << 24) | (((int)sourceImage[sourceImageIndex + 2] & 0xFF) << 16) |
                    (((int)sourceImage[sourceImageIndex + 1] & 0xFF) << 8) | ((int)sourceImage[sourceImageIndex] & 0xFF);
                sourceImageIndex += 3;
            }
            sourceImageIndex += zerosInEachRow;
        }

        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, imageData, 0, width)); 
    }

    public Image myWrite(Image image, String filePath) throws IOException {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        File dest = new File(filePath);
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        ImageIO.write(bufImage, "jpg", dest);
        return image;
    }

}
