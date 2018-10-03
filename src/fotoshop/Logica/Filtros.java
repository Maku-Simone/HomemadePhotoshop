/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop.Logica;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Maku
 */
public class Filtros {
    //pasa bajas
    public final static int[][] PROMEDIO = {{1,1,1} ,{1,1,1} ,{1,1,1}};
    public final static int[][] GAUSS = {{1,2,1},{2,4,2},{1,2,1}};
    
    //pasa altas
    public final static int[][] DIF_PIX_X = {{0,0,0}, {0,1,-1}, {0,0,0}};
    public final static int[][] DIF_PIX_Y = {{0,-1,0}, {0,1,0}, {0,0,0}};

    public final static int[][] SEP_X = {{0,0,0}, {1,0,-1}, {0,0,0}};
    public final static int[][] SEP_Y = {{0,-1,0}, {0,0,0}, {0,1,0}};

    public final static int[][] ROBERTS_X = {{0,0,-1}, {0,1,0}, {0,0,0}};
    public final static int[][] ROBERTS_Y = {{-1,0,0}, {0,1,0}, {0,0,0}};

    public final static int[][] PREWITT_X = {{1,0,-1}, {1,0,-1}, {1,0,-1}};
    public final static int[][] PREWITT_Y = {{-1,-1,-1}, {0,0,0}, {1,1,1}};

    public final static int[][] SOBEL_X = {{1,0,-1}, {2,0,-2}, {1,0,-1}};
    public final static int[][] SOBEL_Y = {{-1,-2,-1}, {0,0,0}, {1,2,1}};
    
    public final static int[][] LAPLACIANO_A = {{0,-1,0}, {-1,4,-1}, {0,-1,0}};
    public final static int[][] LAPLACIANO_B = {{-1,-1,-1}, {-1,8,-1}, {-1,-1,-1}};    
    public final static int[][] LAPLACIANO_C = {{0,-1,0}, {-1,5,-1}, {0,-1,0}};
    public final static int[][] LAPLACIANO_D = {{-1,-1,-1}, {-1,9,-1}, {-1,-1,-1}};    
    
    
    public int[][][] convolucion(int[][][] argb, int[][] kernel, int cosaK, int simetrico)
        {
            if(simetrico == 0) //no es simetrico
                {
                    kernel = rotaKernel(kernel);
                }
            int[][][] expandido, copiaExp, sinCeros;
            expandido = expande(argb);
            copiaExp = expande(argb);
            sinCeros = new int[argb.length][argb[0].length][4];
            
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            expandido[x][y][1] = multiplica(x, y, kernel, copiaExp, cosaK, 1);
                            expandido[x][y][2] = multiplica(x, y, kernel, copiaExp, cosaK, 2);                                   
                            expandido[x][y][3] = multiplica(x, y, kernel, copiaExp, cosaK, 3);
                        }
                }
            for(int x = 0; x < argb.length; x++)
                {
                    for(int y = 0; y < argb[0].length; y++)
                        {
                            sinCeros[x][y][1] = expandido[x+1][y+1][1];
                            sinCeros[x][y][2] = expandido[x+1][y+1][2];
                            sinCeros[x][y][3] = expandido[x+1][y+1][3];
                        }
                }
            return sinCeros;
        }
    
    public int[][] rotaKernel(int[][] kernel)
        {
            int[][] arregloFiltro = new int[3][3];
            for(int i = 2; i >= 0; i--)
                {
                    for(int j = 2; j >= 0; j--)
                        {
                            arregloFiltro[2-i][2-j] = kernel[i][j];
                        }
                }            
            return arregloFiltro;    
        }
    
    
    public int[][][] expande(int[][][] argb)
        {
            int[][][] largo = new int[argb.length + 2][argb[0].length + 2][4];
            
            for(int y = 0; y < largo[0].length; y++) //verticales
                {
                    largo[0][y][1] = 0;
                    largo[0][y][2] = 0;
                    largo[0][y][3] = 0;   
                    
                    largo[largo.length - 1][y][1] = 0;
                    largo[largo.length - 1][y][2] = 0;
                    largo[largo.length - 1][y][3] = 0;                       
                }
        
            for(int x = 0; x < largo.length; x++) //horizontales
                {
                    largo[x][0][1] = 0;
                    largo[x][0][2] = 0;
                    largo[x][0][3] = 0;   
                    
                    largo[x][largo[0].length - 1][1] = 0;
                    largo[x][largo[0].length - 1][2] = 0;
                    largo[x][largo[0].length - 1][3] = 0;                       
                }
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            largo[x][y][1] = argb[x-1][y-1][1];
                            largo[x][y][2] = argb[x-1][y-1][2];
                            largo[x][y][3] = argb[x-1][y-1][3];
                        }
                }
            return largo;
        }
    
    
    public int multiplica(int x, int y, int[][] kernel, int[][][] argb, int k, int canal)
        {
            int suma = 0;
            x += 1;
            y += 1;
            
            suma+= argb[x-1][y-1][canal] * kernel[0][0]; //[0][0]
            suma+= argb[x][y-1][canal] * kernel[0][1]; //[0][1]
            suma+= argb[x+1][y-1][canal] * kernel[0][2]; //[0][2]
            
            suma+= argb[x-1][y][canal] * kernel[1][0];            
            suma+= argb[x][y][canal] * kernel[1][1];
            suma+= argb[x+1][y][canal] * kernel[1][2];
            
            suma+= argb[x-1][y+1][canal] * kernel[2][0];
            suma+= argb[x][y+1][canal] * kernel[2][1];
            suma+= argb[x+1][y+1][canal] * kernel[2][2];           
            
            return suma/k;
        }
    
    public int[][][] mediana(int[][][] argb)        
        {
            int[][][] expandido, copiaExp, sinCeros;
            expandido = expande(argb);
            copiaExp = expande(argb);
            sinCeros = new int[argb.length][argb[0].length][4];
            
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            expandido[x][y][1] = sacaMediana(copiaExp, x, y, 1);
                            expandido[x][y][2] = sacaMediana(copiaExp, x, y, 2);
                            expandido[x][y][3] = sacaMediana(copiaExp, x, y, 3);
                        }
                }
            for(int x = 0; x < argb.length; x++)
                {
                    for(int y = 0; y < argb[0].length; y++)
                        {
                            sinCeros[x][y][1] = expandido[x+1][y+1][1];
                            sinCeros[x][y][2] = expandido[x+1][y+1][2];
                            sinCeros[x][y][3] = expandido[x+1][y+1][3];
                        }
                }
            return sinCeros;
        }
    
    public int[][][] moda(int argb[][][])
        {
            int[][][] expandido, copiaExp, sinCeros;
            expandido = expande(argb);
            copiaExp = expande(argb);
            sinCeros = new int[argb.length][argb[0].length][4];
            
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            expandido[x][y][1] = sacaModa(copiaExp, x, y, 1);
                            expandido[x][y][2] = sacaModa(copiaExp, x, y, 2);
                            expandido[x][y][3] = sacaModa(copiaExp, x, y, 3);
                        }
                }
            for(int x = 0; x < argb.length; x++)
                {
                    for(int y = 0; y < argb[0].length; y++)
                        {
                            sinCeros[x][y][1] = expandido[x+1][y+1][1];
                            sinCeros[x][y][2] = expandido[x+1][y+1][2];
                            sinCeros[x][y][3] = expandido[x+1][y+1][3];
                        }
                }
            return sinCeros;
        }
    
    public int sacaMediana(int argb[][][], int x, int y, int canal)
        {
            
            int[] vecinos = new int[9];
            
            vecinos[0] = argb[x-1][y-1][canal];
            vecinos[1] = argb[x][y-1][canal];
            vecinos[2] = argb[x+1][y-1][canal];
            
            vecinos[3] = argb[x-1][y][canal];
            vecinos[4] = argb[x][y][canal];
            vecinos[5] = argb[x+1][y][canal];
            
            vecinos[6] = argb[x-1][y+1][canal];
            vecinos[7] = argb[x][y+1][canal];
            vecinos[8] = argb[x+1][y+1][canal];                        
            Arrays.sort(vecinos);           
            return vecinos[4];
        }
    
    
        public int sacaModa(int argb[][][], int x, int y, int canal)
        {
            
            int[] vecinos = new int[9];
            
            vecinos[0] = argb[x-1][y-1][canal];
            vecinos[1] = argb[x][y-1][canal];
            vecinos[2] = argb[x+1][y-1][canal];
            
            vecinos[3] = argb[x-1][y][canal];
            vecinos[4] = argb[x][y][canal];
            vecinos[5] = argb[x+1][y][canal];
            
            vecinos[6] = argb[x-1][y+1][canal];
            vecinos[7] = argb[x][y+1][canal];
            vecinos[8] = argb[x+1][y+1][canal];                        
            
            Calculos C = new Calculos();
            
            return (int)C.modaYenergiaYentropia(vecinos, 0)[0];
        }
        
        public int[][][] maximoMinimo(int[][][] argb, int sel)
            {
                //selector = 0 si es minimo
                int[][][] expandido, copiaExp, sinCeros;
                expandido = expande(argb); //arreglo con 0 en los bordes
                copiaExp = expande(argb); //arreglo con 0 en bores para trabajar datos
                sinCeros = new int[argb.length][argb[0].length][4];

                for(int x = 1; x < argb.length; x++)
                    {
                        for(int y = 1; y < argb[0].length; y++)
                            {
                                expandido[x][y][1] = (sel == 0) ? sacaMaxMin(copiaExp, x, y, 1, 0) : sacaMaxMin(copiaExp, x, y, 1, 1);
                                expandido[x][y][2] = (sel == 0) ? sacaMaxMin(copiaExp, x, y, 2, 0) : sacaMaxMin(copiaExp, x, y, 2, 1);
                                expandido[x][y][3] = (sel == 0) ? sacaMaxMin(copiaExp, x, y, 3, 0) : sacaMaxMin(copiaExp, x, y, 3, 1);
                            }
                    }
                for(int x = 0; x < argb.length; x++)
                    {
                        for(int y = 0; y < argb[0].length; y++)
                            {
                                sinCeros[x][y][1] = expandido[x+1][y+1][1];
                                sinCeros[x][y][2] = expandido[x+1][y+1][2];
                                sinCeros[x][y][3] = expandido[x+1][y+1][3];
                            }
                    }
                return sinCeros;
            }
        
        public int sacaMaxMin(int[][][] argb, int x, int y, int canal, int sel)            
            {
                            
                int[] vecinos = new int[9];

                vecinos[0] = argb[x-1][y-1][canal];
                vecinos[1] = argb[x][y-1][canal];
                vecinos[2] = argb[x+1][y-1][canal];

                vecinos[3] = argb[x-1][y][canal];
                vecinos[4] = argb[x][y][canal];
                vecinos[5] = argb[x+1][y][canal];

                vecinos[6] = argb[x-1][y+1][canal];
                vecinos[7] = argb[x][y+1][canal];
                vecinos[8] = argb[x+1][y+1][canal];                        
                
                /*for(int i = 0; i < 9; i++)
                    System.out.print("["+vecinos[i]+"] - ");
                System.out.print(x + " " + y + "\n");*/
                
                Arrays.sort(vecinos);    
                
                return (sel == 0) ? vecinos[0] : vecinos[8]; //0 es el minimo
            }
}

