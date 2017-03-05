/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;

/**
 *
 * @author SDX
 */
public class ImageColor {

    public int pixelRGB(int pixel) {
        
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int sum = red + green + blue;
        
        if ((red > 200 && green < 200 && blue < 200)) {
            return 2; //red
        }
        if ((sum) > 750) {
            return 1; //white
        }
        if (green > 100 && red > 20 && blue < 80 || (red <= 15 && green > 200 && blue <= 15)) {
       
            return 3;  //green
         }
        if (red < 150 && green < 150 && blue < 150) {
            return 0; //black
        }
        if (red == green && green == blue) {
            return 0; //black
        }
        return 1; //green
   
    }
}
