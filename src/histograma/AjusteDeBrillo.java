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
    
    public int[][][] expansion(int[][][] argb, int[] max, int[] min, int maxGris, int minGris) //max o min [R, G, B]
        {
            int ancho = argb.length;
            int alto = argb[0].length;
            int[][][] expandido = new int[ancho][alto][4];
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            expandido[x][y][1] = (int)( (float)( (float)(argb[x][y][1] - min[0]) / (max[0] - min[0]) ) * ( maxGris - minGris ) ) + minGris;
                            expandido[x][y][2] = (int)( (float)( (float)(argb[x][y][2] - min[1]) / (max[1] - min[1]) ) * ( maxGris - minGris ) ) + minGris;
                            expandido[x][y][3] = (int)( (float)( (float)(argb[x][y][3] - min[2]) / (max[2] - min[2]) ) * ( maxGris - minGris ) ) + minGris;
                            
                           /* if(x == 100)
                                {
                                    System.out.println("\n\n*******\nValores max " + maxGris + " min " + minGris + " maximo " + max[0] + " minimo " + min[0] + " y px = " + argb[x][y][1] + " = " + expandido[x][y][1]);
                                    System.out.println("max - min = " + (maxGris - minGris));
                                    System.out.println("max - min " + (max[0] - min[0]));
                                    System.out.println("argb - min = " + argb[x][y][1] + " - " + min[0]);
                                    System.out.println(" --- = " + (argb[x][y][1]  - min[0]) );                                    
                                    System.out.println(" --- = " + ((float)((float)(argb[x][y][1] - min[0]) / (max[0] - min[0]) )));
                                   
                                    System.out.println("todo junto " + (( (float)( (float)(argb[x][y][1] - min[0]) / (max[0] - min[0]) ) * ( maxGris - minGris ) ) + minGris));
                                }*/
                            
                        }
                }
            return expandido;
        }
    
    public int[][][] contraccion(int[][][] argb, int[] min, int[] max, int Cmax, int Cmin)//wip
        {
            int ancho = argb.length;
            int alto = argb[0].length;
            int[][][] contraido = new int[ancho][alto][4];
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            contraido[x][y][1] = (int)( (float)( (float)(Cmax - Cmin) / (max[0] - min[0]) ) * (argb[x][y][1]  - min[0]) ) + Cmin;
                            contraido[x][y][2] = (int)( (float)( (float)(Cmax - Cmin) / (max[1] - min[1]) ) * (argb[x][y][2]  - min[1]) ) + Cmin;
                            contraido[x][y][3] = (int)( (float)( (float)(Cmax - Cmin) / (max[2] - min[2]) ) * (argb[x][y][3]  - min[2]) ) + Cmin;
                           /* if(x == 100)
                                {
                                    System.out.println("\n\n*******\nValores Cmax " + Cmax + " Cmin " + Cmin + " maximo " + max[0] + " minimo " + min[0] + " y px = " + argb[x][y][1] + " = " + contraido[x][y][1]);
                                    System.out.println("Cmax - Cmin = " + (Cmax - Cmin));
                                    System.out.println("max - min " + (max[0] - min[0]));
                                    System.out.println(" --- = " + ((float)(Cmax - Cmin) / (max[0] - min[0])));
                                    System.out.println("argb - min = " + argb[x][y][1] + " - " + min[0]);
                                    System.out.println(" --- = " + (argb[x][y][1]  - min[0]) );
                                    System.out.println("todo junto " + (( (float)(Cmax - Cmin) / (max[0] - min[0]) ) * (argb[x][y][1]  - min[0])  +Cmin));
                                }*/
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
                        
            System.out.println("Tneog en mapaR " + mapaR.toString() + " y divido " + TOTAL_PX);
            
            for(int i = 0; i < 255; i++)
                {
                    if(mapaR.get(i) == null) //si no existe el nivel de gris
                        {
                            mapaR.put(i, 0);
                        }
                    if(mapaG.get(i) == null) //si no existe el nivel de gris
                        {
                            mapaG.put(i, 0);
                        }   
                    if(mapaB.get(i) == null) //si no existe el nivel de gris
                        {
                            mapaB.put(i, 0);
                        }                    
                }
            
            
            //Aqui ya tengo cuantas veces sale el ng [0 - 255] de cada canal y lo puse en los mapasRGB
            int rAcu = 0, gAcu = 0, bAcu = 0; //variables para la sumatoria
            for(int i = 0; i < 255; i++) //meto el ng y tambien el valor por el que va a ser reemplazado
                {
                    int r, g, b;
                    r = (int) ((mapaR.get(i) != null) ? Math.floor(255 * ((float)mapaR.get(i) / TOTAL_PX)) : rAcu);
                    g = (int) ((mapaG.get(i) != null) ? Math.floor(255 * ((float)mapaG.get(i) / TOTAL_PX)) : gAcu);
                    b = (int) ((mapaB.get(i) != null) ? Math.floor(255 * ((float)mapaB.get(i) / TOTAL_PX)) : bAcu);
                    
                    rAcu += r;
                    gAcu += g;
                    bAcu += b;
                    
                    reemplazoR.put(i, rAcu);
                    reemplazoG.put(i, gAcu);
                    reemplazoB.put(i, bAcu);
                } 
            
            System.out.println("Aca hay " + reemplazoR.toString());
            
            for(int x = 0; x < ancho; x++)
                {
                    for(int y = 0; y < alto; y++)
                        {
                            ecualizado[x][y][1] = (reemplazoR.get(argb[x][y][1]) != null) ? reemplazoR.get(argb[x][y][1]) : argb[x][y][1];                                                        
                            ecualizado[x][y][2] = (reemplazoG.get(argb[x][y][2]) != null) ? reemplazoG.get(argb[x][y][2]) : argb[x][y][2];                                                        
                            ecualizado[x][y][3] = (reemplazoB.get(argb[x][y][3]) != null) ? reemplazoB.get(argb[x][y][3]) : argb[x][y][3];                                                        
                        }
                }
            
            return ecualizado;
        }
    
    public int[] maximoNG(int[][][] argb)
    {
        int r = 0, g = 0, b = 0;
        int ancho = argb.length;
        int alto = argb[0].length;
        
        for(int x = 0; x < ancho; x++)
            {
                for(int y = 0; y < alto; y++)
                    {
                        if(argb[x][y][1] > r)
                            {
                                r = argb[x][y][1];
                            }
                        
                        if(argb[x][y][2] > g)
                            {
                                g = argb[x][y][2];
                            }

                        if(argb[x][y][3] > b)
                            {
                                b = argb[x][y][3];
                            }                        
                    }
            }
        int[] ret = new int[]{r, g, b};
        return ret;
    }
    
    public int[] minimoNG(int[][][] argb)
    {
        int r = 255, g = 255, b = 255;
        int ancho = argb.length;
        int alto = argb[0].length;
        
        for(int x = 0; x < ancho; x++)
            {
                for(int y = 0; y < alto; y++)
                    {
                        if(argb[x][y][1] < r)
                            {
                                r = argb[x][y][1];
                            }
                        
                        if(argb[x][y][2] < g)
                            {
                                g = argb[x][y][2];
                            }

                        if(argb[x][y][3] < b)
                            {
                                b = argb[x][y][3];
                            }                        
                    }
            }
        int[] ret = new int[]{r, g, b};
        return ret;
    }
    
}

