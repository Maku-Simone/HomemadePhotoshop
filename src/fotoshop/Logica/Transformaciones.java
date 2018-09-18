/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop.Logica;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author Maku
 */
public class Transformaciones {
    
    public int[][][] binarizado(int umbral, Imagen img) throws IOException
        {
            int[][][] binario = img.getArgb();            
            int ancho = img.getAncho();
            int alto = img.getAlto();
            File f = new File(img.getRuta());
            BufferedImage newImagen = null;
            newImagen = ImageIO.read(f); 
            int[][][] retorno = new int[ancho][alto][4];
            for (int x = 0; x < ancho; x++)
                {
                    for (int y = 0; y < alto; y++)
                    {
                        //obtengo las coordenadas x,y y los canales argb                                                                           
                        
                        if(binario[x][y][2] > umbral) //
                            {
                               // System.out.println(binario[x][y][2] + " > umbral:" + umbral);
                                newImagen.setRGB(x, y, Color.WHITE.getRGB());
                            }
                        else
                            {
                                newImagen.setRGB(x, y, Color.black.getRGB());
                            }
                        
                        int p = newImagen.getRGB(x,y); 
                        retorno[x][y][0] = (p>>24)&0xff; //a
                        retorno[x][y][1] = (p>>16)&0xff;//r
                        retorno[x][y][2] = (p>>8)&0xff;//g
                        retorno[x][y][3] = p&0xff;  //b
                    }
                }                        
            return retorno;
        }
        
    public int[][][] grises(Imagen imgClase) throws IOException
    {
       int[][][] gris = imgClase.getModificado();            
       int ancho = imgClase.getAncho();
       int alto = imgClase.getAlto();
       File f = new File(imgClase.getRuta());                                   
       BufferedImage img = null;
       img = ImageIO.read(f);        
       
       int argb[][][] = new int[ancho][alto][4];
       for (int y = 0; y < alto; y++)
        {
            for (int x = 0; x < ancho; x++)
            {
                //obtengo las coordenadas x,y y los canales argb
                int p;// = img.getRGB(x,y);
 
                int a = gris[x][y][0];
                int r = gris[x][y][1];
                int g = gris[x][y][2];
                int b = gris[x][y][3];          
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
                
                //le doy sus valores ya en grises para guardarlos en el arreglo que voy a regresar
                argb[x][y][0] = a;
		argb[x][y][1] = r;
		argb[x][y][2] = g;
                argb[x][y][3] = b;
            }
        }
       return argb;
    }
        
    public int[][][] canales(int canal, Imagen img)
        {
            int[][][] canalrgb = img.getArgb();  
            int ancho = img.getAncho();
            int alto = img.getAlto();                                               
            int argb[][][] = new int[ancho][alto][4];
            
            for (int[][] square : argb) 
                {
                    for (int[] line : square) 
                        {
                            Arrays.fill(line, 0);
                        }
                }

            switch(canal)
                {                                                                                       
                    case 0: //rojo
                        for (int y = 0; y < alto; y++)
                            {
                                for (int x = 0; x < ancho; x++)
                                {
                                    argb[x][y][1] = canalrgb[x][y][1];
                                }
                            }
                    break;
                    case 1: //verde
                        for (int y = 0; y < alto; y++)
                            {
                                for (int x = 0; x < ancho; x++)
                                {
                                    argb[x][y][2] = canalrgb[x][y][1];
                                }
                            }
                    break;
                    case 2: //azul
                        for (int y = 0; y < alto; y++)
                            {
                                for (int x = 0; x < ancho; x++)
                                {
                                    argb[x][y][3] = canalrgb[x][y][1];
                                }
                            }                        
                    break;
                    default: //alfa
                }
            return argb;
        }
 
    public int[][][] suma(Imagen A, Imagen B)
        {
            int sumados[][][] = new int[1][2][3];
                System.out.println("Suma :3");
            return sumados;
        }
    
    public int[][][] resta(Imagen A, Imagen B)
        {
            int sumados[][][] = new int[1][2][3];
                System.out.println("Resta :3");
            return sumados;
        }

    public int[][][] multi(Imagen A, Imagen B)
        {
            int sumados[][][] = new int[1][2][3];
                System.out.println("Multi :3");
            return sumados;
        }    
    public int[][][] divi(Imagen A, Imagen B)
        {
            int sumados[][][] = new int[1][2][3];
                System.out.println("Divide :3");
            return sumados;
        }    
}
