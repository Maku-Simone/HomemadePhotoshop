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
            int ancho = 0, alto = 0;            
            alto =  menor(A.getAlto(), B.getAlto());
            ancho = menor(A.getAncho(), B.getAncho());
            int sumados[][][] = new int[ancho][alto][4];
            
            int imagenA[][][] = A.getArgb();
            int imagenB[][][] = B.getArgb();
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            sumados[x][y][1] = imagenA[x][y][1] + imagenB[x][y][1];
                            sumados[x][y][2] = imagenA[x][y][2] + imagenB[x][y][2];
                            sumados[x][y][3] = imagenA[x][y][3] + imagenB[x][y][3];
                        }
                }
                
                
            return sumados;
        }
    
    public int[][][] resta(Imagen A, Imagen B)
        {
            int ancho = 0, alto = 0;
            alto =  menor(A.getAlto(), B.getAlto());
            ancho = menor(A.getAncho(), B.getAncho());
            int sumados[][][] = new int[ancho][alto][4];
            
                       int imagenA[][][] = A.getArgb();
            int imagenB[][][] = B.getArgb();
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            sumados[x][y][1] = imagenA[x][y][1] - imagenB[x][y][1];
                            sumados[x][y][2] = imagenA[x][y][2] - imagenB[x][y][2];
                            sumados[x][y][3] = imagenA[x][y][3] - imagenB[x][y][3];
                        }
                }
            
            return sumados;
        }

    public int[][][] multi(Imagen A, Imagen B)
        {
            int ancho = 0, alto = 0;
            alto =  menor(A.getAlto(), B.getAlto());
            ancho = menor(A.getAncho(), B.getAncho());
            int sumados[][][] = new int[ancho][alto][4];
            
           int imagenA[][][] = A.getArgb();
            int imagenB[][][] = B.getArgb();
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            sumados[x][y][1] = imagenA[x][y][1] * imagenB[x][y][1];
                            sumados[x][y][2] = imagenA[x][y][2] * imagenB[x][y][2];
                            sumados[x][y][3] = imagenA[x][y][3] * imagenB[x][y][3];
                        }
                }            
            
            return sumados;
        }    
    public int[][][] divi(Imagen A, Imagen B)
        {
            int ancho = 0, alto = 0;
            alto =  menor(A.getAlto(), B.getAlto());
            ancho = menor(A.getAncho(), B.getAncho());
            int sumados[][][] = new int[ancho][alto][4];
            
            int imagenA[][][] = A.getArgb();
            int imagenB[][][] = B.getArgb();
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            sumados[x][y][1] = imagenA[x][y][1] / (imagenB[x][y][1] + 1);
                            sumados[x][y][2] = imagenA[x][y][2] / (imagenB[x][y][2] + 1);
                            sumados[x][y][3] = imagenA[x][y][3] / (imagenB[x][y][3] + 1);
                        }
                }
            
            return sumados;
        }    
    
    public  static int mayor(int a,  int b)
        {
            if(a >= b) 
                {
                    return a;
                }
            else
                {
                    return b;
                }
        }
    
    public  static int menor(int a,  int b)
        {
            if(a <= b) 
                {
                    return a;
                }
            else
                {
                    return b;
                }
        }    
    
    public int[][][] ajusta(int[][][] rgb)
        {
            for(int x = 0; x < rgb.length; x++)
                {
                    for(int y = 0; y < rgb[0].length; y++)
                        {
                            if(rgb[x][y][1] > 255)
                                {
                                    rgb[x][y][1] = 255;
                                }
                            if(rgb[x][y][2] > 255)
                                {
                                    rgb[x][y][2] = 255;
                                }
                            if(rgb[x][y][3] > 255)
                                {
                                    rgb[x][y][3] = 255;
                                }
                           if(rgb[x][y][1] > 255)
                                {
                                    rgb[x][y][1] = 255;
                                }
                            if(rgb[x][y][2] > 255)
                                {
                                    rgb[x][y][2] = 255;
                                }
                            //menores
                            if(rgb[x][y][1] < 0)
                                {
                                    rgb[x][y][1] = 0;
                                }  
                            if(rgb[x][y][2] < 0)
                                {
                                    rgb[x][y][2] = 0;
                                }                   
                            if(rgb[x][y][3] < 0)
                                {
                                    rgb[x][y][3] = 0;
                                }                   
                            
                        } //y
                }//x
            return rgb;
        }
     public int[][][] logicos(Imagen A, Imagen B, int oper)
        {
            int ancho = 0, alto = 0;
            alto =  menor(A.getAlto(), B.getAlto());
            ancho = menor(A.getAncho(), B.getAncho());
            int sumados[][][] = new int[ancho][alto][4];
            
            int imagenA[][][] = A.getArgb();
            int imagenB[][][] = B.getArgb();
            
            switch(oper)
                {
                    case 0: //and
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = and(binario(imagenA[x][y][1]), binario(imagenB[x][y][1]));
                                        sumados[x][y][2] = and(binario(imagenA[x][y][2]), binario(imagenB[x][y][2]));
                                        sumados[x][y][3] = and(binario(imagenA[x][y][3]), binario(imagenB[x][y][3]));
                                    }
                            }
                        break;
                    case 1: //or
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = or(binario(imagenA[x][y][1]), binario(imagenB[x][y][1]));
                                        sumados[x][y][2] = or(binario(imagenA[x][y][2]), binario(imagenB[x][y][2]));
                                        sumados[x][y][3] = or(binario(imagenA[x][y][3]), binario(imagenB[x][y][3]));
                                    }
                            }                        
                        break;
                    case 2: //xor
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = xor(binario(imagenA[x][y][1]), binario(imagenB[x][y][1]));
                                        sumados[x][y][2] = xor(binario(imagenA[x][y][2]), binario(imagenB[x][y][2]));
                                        sumados[x][y][3] = xor(binario(imagenA[x][y][3]), binario(imagenB[x][y][3]));
                                    }
                            }                        
                        break;
                    case 3: //nand
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = negador(and(binario(imagenA[x][y][1]), binario(imagenB[x][y][1])));
                                        sumados[x][y][2] = negador(and(binario(imagenA[x][y][2]), binario(imagenB[x][y][2])));
                                        sumados[x][y][3] = negador(and(binario(imagenA[x][y][3]), binario(imagenB[x][y][3])));
                                    }
                            }                        
                        break;    
                    case 4: //nor
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = negador(or(binario(imagenA[x][y][1]), binario(imagenB[x][y][1])));
                                        sumados[x][y][2] = negador(or(binario(imagenA[x][y][2]), binario(imagenB[x][y][2])));
                                        sumados[x][y][3] = negador(or(binario(imagenA[x][y][3]), binario(imagenB[x][y][3])));
                                    }
                            }                                                
                        break;
                    case 5: //xnor
                        for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = negador(xor(binario(imagenA[x][y][1]), binario(imagenB[x][y][1])));
                                        sumados[x][y][2] = negador(xor(binario(imagenA[x][y][2]), binario(imagenB[x][y][2])));
                                        sumados[x][y][3] = negador(xor(binario(imagenA[x][y][3]), binario(imagenB[x][y][3])));
                                    }
                            }                                                
                        break;
                    case 6: //not
                       for(int x = 0; x < ancho; x++)
                            {
                                for(int y = 0; y < alto; y++)
                                    {
                                        sumados[x][y][1] = negador(imagenA[x][y][1]);
                                        sumados[x][y][2] = negador(imagenA[x][y][2]);
                                        sumados[x][y][3] = negador(imagenA[x][y][3]);
                                    }
                            }                        
                        break;                        
                    default:    
                }            
            
            return sumados;
        }    
    public int[][][] inverso(Imagen img)
        {
            int ancho = 0, alto = 0;
            ancho = img.getAncho();
            alto = img.getAlto();
            int[][][] inverso = new int[ancho][alto][4];
            int[][][] rgb = img.getArgb();
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            inverso[x][y][1] = 255 - rgb[x][y][1];
                            inverso[x][y][2] = 255 - rgb[x][y][2];
                            inverso[x][y][3] = 255 - rgb[x][y][3];
                        }
                }
            return inverso;
        }
    public int[] binario(int num)
        {
            int[] bin = {0,0,0,0,0,0,0,0};
            String binary = Integer.toBinaryString(num);
            for(int i = 0; i < binary.length(); i++)
                {
                    if( (binary.charAt(i) + "").equals("1") )
                        {
                            bin[i] = 1;
                        }
                }
            return bin;
        }
    public int and(int[] a, int[] b)
        {
            String num = "";
            
            for(int i = 0; i < 8; i++)
                {
                    if(a[i] == 1 && b[i] == 1)
                        {
                            num += 1;
                        }
                     else
                        {
                            num += 0;
                        }
                }            
            return Integer.parseInt(num, 2);        
        }

   public int or(int[] a, int[] b)
        {
            String num = "";
            
            for(int i = 0; i < 8; i++)
                {
                    if(a[i] == 0 && b[i] == 0)
                        {
                            num += 0;
                        }
                     else
                        {
                            num += 1;
                        }
                }            
            return Integer.parseInt(num, 2);         
        }    
   
   public int xor(int[] a, int[] b)
        {
            String num = "";
            
            for(int i = 0; i < 8; i++)
                {
                    if(a[i] == b[i])
                        {
                            num += 0;
                        }
                     else
                        {
                            num += 1;
                        }
                }            
            return Integer.parseInt(num, 2);        
        }   
   
   public int negador(int a)
    {        
        String binary = Integer.toBinaryString(a);        
        String neg = "";        
        for(int i = 0; i < binary.length(); i++)
            {
                neg += (binary.charAt(i) == '0') ? "1" : "0";
            }
        return Integer.parseInt(neg, 2);
    }
    
      public int[][][] multiUmbral(int numUmb, String umbralesCadena, Imagen img) throws IOException
        {
            int[] umbrales = new int[numUmb];
            String[] partes = umbralesCadena.split(",");
            
            
            for(int i = 0; i < numUmb; i++)
                {
                    umbrales[i] = Integer.parseInt(partes[i]);
                }
            Arrays.sort(umbrales); //aqui ya tengo ordenados mis umbrales y separados en un arreglo
            
            
            
            int[][][] imagenRGB = img.getModificado();            
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
                         for(int k = 0; k < numUmb; k+=2)   // con este for verifico que el pixel esté dentro de los umbrales o no
                         {
                             int fin = 0;
                             if(k == (numUmb - 1)) //umbrales impar y último umbral
                                {
                                    if(numUmb %2 != 0) //impar
                                       {
                                           fin = 255;
                                       }                                    
                                }
                             else
                                {
                                    fin = umbrales[k+1];
                                }
                             if(imagenRGB[x][y][2] >= umbrales[k] && imagenRGB[x][y][2] <= fin)
                                {
                                    newImagen.setRGB(x, y, Color.WHITE.getRGB());
                                }
                             else
                                {
                                    newImagen.setRGB(x, y, Color.BLACK.getRGB());
                                }
                         }
                            int p = newImagen.getRGB(x,y); 
                        //retorno[x][y][0] = (p>>24)&0xff; //a
                            retorno[x][y][1] = (p>>16)&0xff;//r
                            retorno[x][y][2] = (p>>8)&0xff;//g
                            retorno[x][y][3] = p&0xff;  //b                         
                    }
                }                        
            return retorno;
        }   
   
   
   
      public int[][][] multiUmbralDos(Imagen img) throws IOException
        {
            int[][][] binario = img.getArgb();            
            int ancho = img.getAncho();
            int alto = img.getAlto();
            File f = new File(img.getRuta());
            BufferedImage newImagen = null;
            newImagen = ImageIO.read(f); 
            int[][][] retorno = new int[ancho][alto][4];
            int[] umbral = new int[2];
            
            umbral[0] = binario[img.getUmbral1().x][img.getUmbral1().y][2];
            umbral[1] = binario[img.getUmbral2().x][img.getUmbral2().y][2];
            
            Arrays.sort(umbral);
            System.out.println("Solo lo que esté entre " + umbral[0] + " y " + umbral[1]);
            for (int x = 0; x < ancho; x++)
                {
                    for (int y = 0; y < alto; y++)
                    {
                        //obtengo las coordenadas x,y y los canales argb                                                                           
                        
                        if(binario[x][y][2] >= umbral[0] && binario[x][y][2] <= umbral[1]) //
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
      
      
      public int[][][] addRuido(int[][][] argb, int porcentaje, int tipo)
        {
        
        }
   
}
