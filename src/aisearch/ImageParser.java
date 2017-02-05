package aisearch;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
    
    public ImageParser(int size) {
        this.size = size;
        
        try {
           // get the BufferedImage, using the ImageIO class
            BufferedImage image = ImageIO.read( new File("omg.jpg"));
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
      System.out.println(scaling);
    BufferedImage after = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    at.scale(1.0/scaling, 1.0/scalingY);
    AffineTransformOp scale = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    after = scale.filter(image, after);
    //BufferedImage afterTwo = resize(image, 20,20);
    int w = after.getWidth();
    int h = after.getHeight();
    
    System.out.println("width, height: " + w + ", " + h);
    
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        //System.out.println("x,y: " + j + ", " + i);
        int pixel = after.getRGB(j, i);
        printPixelARGB(pixel);
        //System.out.println("");
      }
    }

   File outputfile = new File("image.png");
   ImageIO.write(after, "png", outputfile);

    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(new FlowLayout());
    frame.getContentPane().add(new JLabel(new ImageIcon(after)));
    frame.pack();
    frame.setVisible(true);
  }
  public BufferedImage resize(BufferedImage image, int areaWidth, int areaHeight)
    {
        float scaleX = (float) areaWidth / image.getWidth();
        float scaleY = (float) areaHeight / image.getHeight();
        float scale = Math.min(scaleX, scaleY);
        int w = Math.round(image.getWidth() * scale);
        int h = Math.round(image.getHeight() * scale);

        int type = image.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        boolean scaleDown = scale < 1;

        if (scaleDown) {
            // multi-pass bilinear div 2
            int currentW = image.getWidth();
            int currentH = image.getHeight();
            BufferedImage resized = image;
            while (currentW > w || currentH > h) {
                currentW = Math.max(w, currentW / 2);
                currentH = Math.max(h, currentH / 2);

                BufferedImage temp = new BufferedImage(currentW, currentH, type);
                Graphics2D g2 = temp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(resized, 0, 0, currentW, currentH, null);
                g2.dispose();
                resized = temp;
            }
            return resized;
        } else {
            Object hint = scale > 2 ? RenderingHints.VALUE_INTERPOLATION_BICUBIC : RenderingHints.VALUE_INTERPOLATION_BILINEAR;

            BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            return resized;
        }
    }
}
