package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class fulk extends Ellipse{
    //String filePath
    public fulk(){
        super(150, 10, 20);
    }
    public BufferedImage readImage() throws IOException {
            return ImageIO.read(new File())
    }
}
