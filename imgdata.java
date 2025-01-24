import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

public class ImagePreprocessor {

    public static float[] preprocessImage(String imagePath, int height, int width) throws IOException {
        BufferedImage img = ImageIO.read(new File(imagePath));
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImg.getGraphics().drawImage(img, 0, 0, width, height, null);

        float[] imgData = new float[height * width * 3];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = resizedImg.getRGB(x, y);
                imgData[index++] = ((pixel >> 16) & 0xFF) / 255.0f; // Red
                imgData[index++] = ((pixel >> 8) & 0xFF) / 255.0f;  // Green
                imgData[index++] = (pixel & 0xFF) / 255.0f;         // Blue
            }
        }
        return imgData;
    }
}
