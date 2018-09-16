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
public class Imagen 
    {
        private String ruta;
        private String nombreImagen;
        private String extension;
        private int ancho;
        private int alto;
        private int argb[][][];// = new int[1][1][4]; //argb[x][y][valores argb]	
        private int modificado[][][];// = new int[1][1][4]; //argb[x][y][valores argb]	
        
    public String getExtension() {
        return extension;
    }

    public int[][][] getModificado() {
        return modificado;
    }

    public void setModificado(int[][][] modificado) {
        this.modificado = modificado;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }       	
    
        public Imagen(String rutaImg, String nombreImg, String extension) throws IOException
            {
                this.ruta = rutaImg;
                this.extension = extension;
                this.nombreImagen = nombreImg;
                File f = new File(rutaImg);
		BufferedImage img = ImageIO.read(f);
                this.ancho = img.getWidth(); //ancho
                this.alto = img.getHeight(); //alto
                this.argb = new int[1][1][4]; //argb[x][y][valores argb]		
                argb = initCanales(img, ancho, alto);
                this.modificado = this.argb;
            }

    @Override
    public String toString() {
        return "Imagen{" + "ruta=" + ruta + ", nombreImagen=" + nombreImagen + ", extension=" + extension + ", ancho=" + ancho + ", alto=" + alto + ", argb=" + argb + '}';
    }


        
        public Imagen(){}
        
        
        static int[][][] initCanales(BufferedImage img, int ancho, int alto)
                {
                    int argb[][][] = new int[ancho][alto][4];
                    for (int y = 0; y < alto; y++)
                        {
                            for (int x = 0; x < ancho; x++)
                                    {			
                                        int p = img.getRGB(x,y);	//Obtengo argb del pixel xy			
                                        argb[x][y][0] = (p>>24)&0xff;// Alfa
                                        argb[x][y][1] = (p>>16)&0xff; // R
                                        argb[x][y][2] = (p>>8)&0xff; // G 
                                        argb[x][y][3] = p&0xff;	// B			
                                    }
                        }
                    return argb;	
                }
        
	int imprimeCanales(int[][][] argb, int ancho, int alto)
            {
                for(int y = 0; y < alto; y++)
                    {
                        for(int x = 0; x < ancho; x++)
                                {
                                    int a = argb[x][y][0];
                                    int r = argb[x][y][1];
                                    int g = argb[x][y][2];
                                    int b = argb[x][y][3]; 
                                    System.out.println("RGB("+x+","+y+") | A = "+a+",  R = "+r+",  G = "+g+",  B = "+b+" ----- ");
                                }
                    }
                return 1;	
            }      
        

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int[][][] getArgb() {
        return argb;
    }

    public void setArgb(int[][][] argb) {
        this.argb = argb;
    }
        
        
    }
