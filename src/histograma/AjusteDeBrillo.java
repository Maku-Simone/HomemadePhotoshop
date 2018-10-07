/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histograma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Maku
 */
public class AjusteDeBrillo {
    
    
    public int[][][] desplazamientoHist(int argb[][][], int des)
        {
            int ancho = argb.length;
            int alto = argb[0].length;
            int[][][] desplazado = new int[ancho][alto][4];
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            desplazado[x][y][1] = argb[x][y][1] + des;
                            desplazado[x][y][2] = argb[x][y][2] + des;
                            desplazado[x][y][3] = argb[x][y][3] + des;
                        }
                }
            return desplazado;
        }
    
    public int[][][] expansion(int[][][] argb, int min, int max)
        {
            int ancho = argb.length;
            int alto = argb[0].length;
            int[][][] expandido = new int[ancho][alto][4];
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            expandido[x][y][1] = ( ( (argb[x][y][1] - min) / (max - min) ) * ( max - min ) ) + min;
                            expandido[x][y][2] = ( ( (argb[x][y][2] - min) / (max - min) ) * ( max - min ) ) + min;
                            expandido[x][y][3] = ( ( (argb[x][y][3] - min) / (max - min) ) * ( max - min ) ) + min;
                        }
                }
            return expandido;
        }
    
    public int[][][] contraccion(int[][][] argb, int min, int max, int Cmax, int Cmin)//wip
        {
            int ancho = argb.length;
            int alto = argb[0].length;
            int[][][] contraido = new int[ancho][alto][4];
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            contraido[x][y][1] = ( ( (Cmax - Cmin) / max - min ) * (argb[x][y][1]  - min) ) + Cmin;
                            contraido[x][y][2] = ( ( (Cmax - Cmin) / max - min ) * (argb[x][y][2]  - min) ) + Cmin;
                            contraido[x][y][3] = ( ( (Cmax - Cmin) / max - min ) * (argb[x][y][3]  - min) ) + Cmin;
                        }
                }
            return contraido;
        }
    
    public int[][][] ecualizacion(int[][][] argb)
        {
            Map<Integer, Integer> mapaR = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            Map<Integer, Integer> mapaG = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            Map<Integer, Integer> mapaB = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            
            //los reemplazos
            Map<Integer, Integer> reemplazoR = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            Map<Integer, Integer> reemplazoG = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            Map<Integer, Integer> reemplazoB = new HashMap<>(); //mapa<nivel de gris, frecuencia>
            
            
            Set<Integer> numerosR = new HashSet();
            Set<Integer> numerosG = new HashSet();
            Set<Integer> numerosB = new HashSet();
            
            int ancho = argb.length;
            int alto = argb[0].length;
            
            final int TOTAL_PX = ancho * alto;
            
            int[][][] ecualizado = new int[ancho][alto][4];

            
            for(int x = 0; x < ancho; x++)//pára contar los pixeles
                {
                    for(int y = 0; y < alto; y++)
                        {
                            int r = 0, g = 0, b = 0;
                            r = argb[x][y][1];
                            g = argb[x][y][2];
                            b = argb[x][y][3];
                            
                            
                            
                            //////////////////PARA EL ROJO
                            if( numerosR.add(r) ) //no estaba el ng 
                                {
                                    mapaR.put(r, 1);
                                }
                            else //ya estaba y aumento cuantos hay
                                {
                                    int val = 0;
                                    val = mapaR.get(r); //obtengo la frecuencia de ng para r
                                    mapaR.put(r, val + 1); //aumento la frencuencia del ng r
                                }   
                            
                            
                            
                             //////////////////// PARA EL VERDE
                            if( numerosG.add(g) ) //no estaba el ng 
                                {
                                    mapaG.put(g, 1);
                                }
                            else //ya estaba y aumento cuantos hay
                                {
                                    int val = 0;
                                    val = mapaG.get(g); //obtengo la frecuencia de ng para r
                                    mapaG.put(g, val + 1); //aumento la frencuencia del ng r
                                }  
                                           
                            
                            
                            
                            /////////////////PARA EL AZUL
                            if( numerosB.add(b) ) //no estaba el ng 
                                {
                                    mapaB.put(b, 1);
                                }
                            else //ya estaba y aumento cuantos hay
                                {
                                    int val = 0;
                                    val = mapaB.get(b); //obtengo la frecuencia de ng para r
                                    mapaB.put(b, val + 1); //aumento la frencuencia del ng r
                                }                                                          
                        }
                }
            
            
            //Aqui ya tengo cuantas veces sale el ng [0 - 255] de cada canal y lo puse en los mapasRGB
            
            for(int i = 0; i < 255; i++) //meto el ng y tambien el valor por el que va a ser reemplazado
                {
                    int r, g, b;
                    r = (int) Math.floor(mapaR.get(i) / TOTAL_PX);
                    g = (int) Math.floor(mapaG.get(i) / TOTAL_PX);
                    b = (int) Math.floor(mapaB.get(i) / TOTAL_PX);
                    
                    reemplazoR.put(i, r);
                    reemplazoR.put(i, g);
                    reemplazoR.put(i, b);
                } 
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            ecualizado[x][y][1] = reemplazoR.get(argb[x][y][1]);                                                        
                            ecualizado[x][y][2] = reemplazoR.get(argb[x][y][2]);   
                            ecualizado[x][y][3] = reemplazoR.get(argb[x][y][3]);   
                        }
                }
            
            return ecualizado;
        }
}

