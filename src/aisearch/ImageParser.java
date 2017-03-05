package aisearch;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

/**
 *
 * @author SDX
 */
public class ImageParser {

    private int size;
    private BufferedImage resized;
    
    public ImageParser(int size) {
        this.size = size;
        
        try {
           // get the BufferedImage, using the ImageIO class
            BufferedImage image = ImageIO.read( new File("lab3.png"));
            marchThroughImage(image);
          } catch (IOException e) {
            System.err.println(e.getMessage());
          }
        }

     
    public void printPixelARGB(int pixel) {
    int alpha = (pixel >> 24) & 0xff;
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    //if (blue != 255 && green != 255 && red != 255)
        //System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
  }

  private void marchThroughImage(BufferedImage image) throws IOException {
    double scaling = image.getWidth()/this.size;
    double scalingY = image.getHeight()/this.size;
      //System.out.println(scaling);
    BufferedImage after = resize(image);
    //AffineTransform at = new AffineTransform();
    //at.scale(1.0/scaling, 1.0/scalingY);
    //AffineTransformOp scale = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    //after = scale.filter(image, after);
    
    //BufferedImage afterTwo = resize(image, 20,20);
    int w = after.getWidth();
    int h = after.getHeight();
    
  //  System.out.println("width, height: " + w + ", " + h);
    
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        //System.out.println("x,y: " + j + ", " + i);
        int pixel = after.getRGB(j, i);
        printPixelARGB(pixel);
        //System.out.println("");
      }
    }

   File outputfile = new File("image.jpg");
   ImageIO.write(after, "png", outputfile);
   this.resized = after;
   
  }

    public BufferedImage resize(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, this.size, this.size, null);
	g.dispose();
	g.setComposite(AlphaComposite.Src);

	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }
  
    public BufferedImage getResized() {
        return resized;
    }
  
    
}
