/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop.Logica;

/**
 *
 * @author Maku
 */
public class Filtros {
    //pasa bajas
    final int[][] promedio = {{1,1,1} ,{1,1,1} ,{1,1,1}};
    final int[][] gauss = {{1,2,1},{2,4,2},{1,2,1}};
    
    //pasa altas
    final int[][] diferenciaPixelX = {{0,0,0}, {0,1,-1}, {0,0,0}};
    final int[][] diferenciaPixelY = {{0,-1,0}, {0,1,0}, {0,0,0}};

    final int[][] separadoX = {{0,0,0}, {1,0,-1}, {0,0,0}};
    final int[][] separadoY = {{0,-1,0}, {0,0,0}, {0,1,0}};

    final int[][] robertsX = {{0,0,-1}, {0,1,0}, {0,0,0}};
    final int[][] robertsY = {{-1,0,0}, {0,1,0}, {0,0,0}};

    final int[][] prewittX = {{1,0,-1}, {1,0,-1}, {1,0,-1}};
    final int[][] prewittY = {{-1,-1,-1}, {0,0,0}, {1,1,1}};

    final int[][] sobelX = {{,,}, {,,}, {,,}};
    final int[][] sobelY = {{,,}, {,,}, {,,}};
    
    
    
    public int[][][] convolucion(int[][][] argb, int[][] kernel, int cosaK, int simetrico)
        {
            if(simetrico == 0) //no es simetrico
                {
                    kernel = rotaKernel(kernel);
                }
            int[][][] expandido, sinCeros;
            expandido = expande(argb);
            sinCeros = new int[argb.length][argb[0].length][4];
            
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            expandido[x][y][1] = multiplica(x, y, kernel, argb, cosaK, 1);
                            expandido[x][y][2] = multiplica(x, y, kernel, argb, cosaK, 2);                                   
                            expandido[x][y][3] = multiplica(x, y, kernel, argb, cosaK, 3);
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
                    
                    largo[largo[0].length - 1][y][1] = 0;
                    largo[largo[0].length - 1][y][2] = 0;
                    largo[largo[0].length - 1][y][3] = 0;                       
                }
        
            for(int x = 0; x < largo.length; x++) //horizontales
                {
                    largo[x][0][1] = 0;
                    largo[x][0][2] = 0;
                    largo[x][0][3] = 0;   
                    
                    largo[x][largo.length - 1][1] = 0;
                    largo[x][largo.length - 1][2] = 0;
                    largo[x][largo.length - 1][3] = 0;                       
                }
            for(int x = 1; x < argb.length; x++)
                {
                    for(int y = 1; y < argb[0].length; y++)
                        {
                            largo[x][y][0] = argb[x-1][y-1][1];
                            largo[x][y][1] = argb[x-1][y-1][2];
                            largo[x][y][2] = argb[x-1][y-1][3];
                        }
                }
            return largo;
        }
    
    
    public int multiplica(int x, int y, int[][] kernel, int[][][] argb, int k, int canal)
        {
            int suma = 0;
            
            suma+= argb[x-1][y-1][canal] * kernel[0][0]; //[0][0]
            suma+= argb[x][y-1][canal] * kernel[0][1]; //[0][1]
            suma+= argb[x+1][y-1][canal] * kernel[0][2]; //[0][2]
            
            suma+= argb[x-1][y][canal] * kernel[1][0];            
            suma+= argb[x][y][canal] * kernel[1][1];
            suma+= argb[x+1][y][canal] * kernel[1][2];
            
            suma+= argb[x-1][y+1][canal] * kernel[2][0];
            suma+= argb[x][y+1][canal] * kernel[2][1];
            suma+= argb[x+1][y+1][canal] * kernel[2][2];
            
            return suma/canal;
        }
}
