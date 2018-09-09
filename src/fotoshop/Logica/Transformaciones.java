/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop.Logica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Maku
 */
public class Transformaciones {
    int[][][] grises(String imagen) throws IOException
    {
       File f = new File(imagen);
       BufferedImage img = null;
       img = ImageIO.read(f); 
       int ancho = img.getWidth();
       int alto = img.getHeight();
       int argb[][][] = new int[ancho][alto][4];
       for (int y = 0; y < alto; y++)
        {
            for (int x = 0; x < ancho; x++)
            {
                //obtengo las coordenadas x,y y los canales argb
                int p = img.getRGB(x,y);
 
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;                
                // calculo el promedio
                int avg = (r+g+b)/3;
 
                //reemplazamos el valor anterior con el promedio
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                
                //reemplazamos los canales ahora a escala de grises
                img.setRGB(x, y, p);
                
                //obtengo de nuevo los canales ya en escala de grises
                p = img.getRGB(x,y);
                a = (p>>24)&0xff;
                r = (p>>16)&0xff;
                g = (p>>8)&0xff;
                b = p&0xff;      
                
                //le doy sus valores ya en grises para guardarlos en el arreglo que voy a regersar
                argb[x][y][0] = a;
		argb[x][y][1] = r;
		argb[x][y][2] = g;
                argb[x][y][3] = b;
            }
        }
       return argb;
    }
    
    
    /*int[][][] nombreTransformacion()
        {
            int[][][] argb = new int[ancho][alto][4];
            return argb;
        }*/
}
