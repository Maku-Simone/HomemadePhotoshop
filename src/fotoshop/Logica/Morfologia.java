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
public class Morfologia {


    public int[][][] dilatacion(int[][][] binario, int[][] EE, int centroX, int centroY)
        {
            int[][][] expandido, sinCeros,exp2;
            EE = ajustaEE(EE);
            expandido = expande(binario);
            exp2 = expande(binario);
            int a = centroX, b = centroY;
            sinCeros = new int[binario.length][binario[0].length][4];

            for(int x = 2; x < binario.length + 2; x++)
                {
                    for(int y = 2; y < binario[0].length + 2; y++)
                        {
                            
                            if(binario[x-2][y-2][1] == 255)
                                {
                                    if(centroX == 0 && centroY == 0)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x+2][y][1] = or(expandido[x+2][y][1], EE[a+2][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x+1][y+1][1] = or(expandido[x+1][y+1][1], EE[a+1][b+1]);
                                            exp2[x+2][y+1][1] = or(expandido[x+2][y+1][1], EE[a+2][b+1]);

                                            exp2[x][y+2][1] = or(expandido[x][y+2][1], EE[a][b+2]);
                                            exp2[x+1][y+2][1] = or(expandido[x+1][y+2][1], EE[a+1][b+2]);
                                            exp2[x+2][y+2][1] = or(expandido[x+2][y+2][1], EE[a+2][b+2]);
                                        }
                                    if(centroX == 1 && centroY == 0)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x+1][y+1][1] = or(expandido[x+1][y+1][1], EE[a+1][b+1]);
                                            exp2[x-1][y+1][1] = or(expandido[x-1][y+1][1], EE[a-1][b+1]);

                                            exp2[x][y+2][1] = or(expandido[x][y+2][1], EE[a][b+2]);
                                            exp2[x+1][y+2][1] = or(expandido[x+1][y+2][1], EE[a+1][b+2]);
                                            exp2[x-1][y+2][1] = or(expandido[x-1][y+2][1], EE[a-1][b+2]);
                                        }
                                    if(centroX == 2 && centroY == 0)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x-2][y][1] = or(expandido[x-2][y][1], EE[a-2][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x-2][y+1][1] = or(expandido[x-2][y+1][1], EE[a-2][b+1]);
                                            exp2[x-1][y+1][1] = or(expandido[x-1][y+1][1], EE[a-1][b+1]);

                                            exp2[x][y+2][1] = or(expandido[x][y+2][1], EE[a][b+2]);
                                            exp2[x-2][y+2][1] = or(expandido[x-2][y+2][1], EE[a-2][b+2]);
                                            exp2[x-1][y+2][1] = or(expandido[x-1][y+2][1], EE[a-1][b+2]);
                                        }

                                    //////////////

                                    if(centroX == 0 && centroY == 1)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x+2][y][1] = or(expandido[x+2][y][1], EE[a+2][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x+1][y+1][1] = or(expandido[x+1][y+1][1], EE[a+1][b+1]);
                                            exp2[x+2][y+1][1] = or(expandido[x+2][y+1][1], EE[a+2][b+1]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x+1][y-1][1] = or(expandido[x+1][y-1][1], EE[a+1][b-1]);
                                            exp2[x+2][y-1][1] = or(expandido[x+2][y-1][1], EE[a+2][b-1]);
                                        }
                                    if(centroX == 1 && centroY == 1)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x+1][y+1][1] = or(expandido[x+1][y+1][1], EE[a+1][b+1]);
                                            exp2[x-1][y+1][1] = or(expandido[x-1][y+1][1], EE[a-1][b+1]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x+1][y-1][1] = or(expandido[x+1][y-1][1], EE[a+1][b-1]);
                                            exp2[x-1][y-1][1] = or(expandido[x-1][y-1][1], EE[a-1][b-1]);
                                        }
                                    if(centroX == 2 && centroY == 1)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x-2][y][1] = or(expandido[x-2][y][1], EE[a-2][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y+1][1] = or(expandido[x][y+1][1], EE[a][b+1]);
                                            exp2[x-2][y+1][1] = or(expandido[x-2][y+1][1], EE[a-2][b+1]);
                                            exp2[x-1][y+1][1] = or(expandido[x-1][y+1][1], EE[a-1][b+1]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x-2][y-1][1] = or(expandido[x-2][y-1][1], EE[a-2][b-1]);
                                            exp2[x-1][y-1][1] = or(expandido[x-1][y-1][1], EE[a-1][b-1]);
                                        }



                                    ////////////////
                                    if(centroX == 0 && centroY == 2)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x+2][y][1] = or(expandido[x+2][y][1], EE[a+2][b]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x+1][y-1][1] = or(expandido[x+1][y-1][1], EE[a+1][b-1]);
                                            exp2[x+2][y-1][1] = or(expandido[x+2][y-1][1], EE[a+2][b-1]);

                                            exp2[x][y-2][1] = or(expandido[x][y-2][1], EE[a][b-2]);
                                            exp2[x+1][y-2][1] = or(expandido[x+1][y-2][1], EE[a+1][b-2]);
                                            exp2[x+2][y-2][1] = or(expandido[x+2][y-2][1], EE[a+2][b-2]);
                                        }
                                    if(centroX == 1 && centroY == 2)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x+1][y][1] = or(expandido[x+1][y][1], EE[a+1][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x+1][y-1][1] = or(expandido[x+1][y-1][1], EE[a+1][b-1]);
                                            exp2[x-1][y-1][1] = or(expandido[x-1][y-1][1], EE[a-1][b-1]);

                                            exp2[x][y-2][1] = or(expandido[x][y-2][1], EE[a][b-2]);
                                            exp2[x+1][y-2][1] = or(expandido[x+1][y-2][1], EE[a+1][b-2]);
                                            exp2[x-1][y-2][1] = or(expandido[x-1][y-2][1], EE[a-1][b-2]);
                                        }
                                    if(centroX == 2 && centroY == 2)
                                        {
                                            exp2[x][y][1] = or(expandido[x][y][1], EE[a][b]);
                                            exp2[x-2][y][1] = or(expandido[x-2][y][1], EE[a-2][b]);
                                            exp2[x-1][y][1] = or(expandido[x-1][y][1], EE[a-1][b]);

                                            exp2[x][y-1][1] = or(expandido[x][y-1][1], EE[a][b-1]);
                                            exp2[x-2][y-1][1] = or(expandido[x-2][y-1][1], EE[a-2][b-1]);
                                            exp2[x-1][y-1][1] = or(expandido[x-1][y-1][1], EE[a-1][b-1]);

                                            exp2[x][y-2][1] = or(expandido[x][y-2][1], EE[a][b-2]);
                                            exp2[x-2][y-2][1] = or(expandido[x-2][y-2][1], EE[a-2][b-2]);
                                            exp2[x-1][y-2][1] = or(expandido[x-1][y-2][1], EE[a-1][b-2]);
                                        }
                                }
                        }
                }
            for(int x = 0; x < binario.length; x++)
                {
                    for(int y = 0; y < binario[0].length; y++)
                        {
                            sinCeros[x][y][1] = exp2[x+2][y+2][1];
                            sinCeros[x][y][2] = exp2[x+2][y+2][1];
                            sinCeros[x][y][3] = exp2[x+2][y+2][1];
                        }
                }
            return sinCeros;
        }

    public int[][][] erosion(int[][][] binario, int[][] EE, int centroX, int centroY)
        {
            EE = ajustaEE(EE);
            int[][][] expandido, exp2, sinCeros;
            exp2 = expande(binario);
            expandido = expande(binario);
            int a = centroX, b = centroY;
            sinCeros = new int[binario.length][binario[0].length][4];                          
            
            for(int x = 2; x < binario.length + 2; x++)
                {
                    for(int y = 2; y < binario[0].length + 2; y++)
                        {
                            if(binario[x-2][y-2][1] == 255)
                                {
                                  //  System.out.println("255 en " + x + ", " + y);
                                    
                                    int[][] mm = new int[3][3];
                                    if(centroX == 0 && centroY == 0)
                                        {
                                            mm[0][0] = expandido[x][y][1];
                                            mm[1][0] = expandido[x+1][y][1];
                                            mm[2][0] = expandido[x+2][y][1];

                                            mm[0][1] = expandido[x][y+1][1];
                                            mm[1][1] = expandido[x+1][y+1][1];
                                            mm[2][1] = expandido[x+2][y+1][1];

                                            mm[0][2] = expandido[x][y+2][1];
                                            mm[1][2] = expandido[x+1][y+2][1];
                                            mm[2][2] = expandido[x+2][y+2][1];
                                        }
                                    if(centroX == 0 && centroY == 1)
                                        {
                                            mm[0][0] = expandido[x][y-1][1];
                                            mm[0][1] = expandido[x][y][1];
                                            mm[0][2] = expandido[x][y+1][1];

                                            mm[1][0] = expandido[x+1][y-1][1];
                                            mm[1][1] = expandido[x+1][y][1];
                                            mm[1][2] = expandido[x+1][y+1][1];

                                            mm[2][0] = expandido[x+2][y-1][1];
                                            mm[2][1] = expandido[x+2][y][1];
                                            mm[2][2] = expandido[x+2][y+1][1];
                                        }
                                    if(centroX == 0 && centroY == 2)
                                        {
                                            mm[0][0] = expandido[x][y-2][1];
                                            mm[0][1] = expandido[x][y-1][1];
                                            mm[0][2] = expandido[x][y][1];

                                            mm[1][0] = expandido[x+1][y-2][1];
                                            mm[1][1] = expandido[x+1][y-1][1];
                                            mm[1][2] = expandido[x+1][y][1];

                                            mm[2][0] = expandido[x+2][y-2][1];
                                            mm[2][1] = expandido[x+2][y-1][1];
                                            mm[2][2] = expandido[x+2][y][1];
                                        }

                                    //////////////

                                    if(centroX == 1 && centroY == 0)
                                        {
                                            mm[0][0] = expandido[x-1][y][1];
                                            mm[0][1] = expandido[x-1][y+1][1];
                                            mm[0][2] = expandido[x-1][y+2][1];

                                            mm[1][0] = expandido[x][y][1];
                                            mm[1][1] = expandido[x][y+1][1];
                                            mm[1][2] = expandido[x][y+2][1];

                                            mm[2][0] = expandido[x+1][y][1];
                                            mm[2][1] = expandido[x+1][y+1][1];
                                            mm[2][2] = expandido[x+1][y+2][1];
                                        }
                                    if(centroX == 1 && centroY == 1)
                                        {                                   
                                            mm[0][0] = expandido[x-1][y-1][1];
                                            mm[0][1] = expandido[x-1][y][1];
                                            mm[0][2] = expandido[x-1][y+1][1];

                                            mm[1][0] = expandido[x][y-1][1];
                                            mm[1][1] = expandido[x][y][1];
                                            mm[1][2] = expandido[x][y+1][1];

                                            mm[2][0] = expandido[x+1][y-1][1];
                                            mm[2][1] = expandido[x+1][y][1];
                                            mm[2][2] = expandido[x+1][y+1][1];
                                        /*for(int i = 0; i < 3; i++)
                                            {
                                                for(int j = 0; j < 3; j++)
                                                    {
                                                        System.out.print(((mm[i][j] == 255 ) ? "☻": mm[i][j]) + "|");
                                                    }
                                                System.out.println("");
                                            }*/

                                        }
                                    if(centroX == 1 && centroY == 2)
                                        {
                                            mm[0][0] = expandido[x-1][y-2][1];
                                            mm[0][1] = expandido[x-1][y-1][1];
                                            mm[0][2] = expandido[x-1][y][1];

                                            mm[1][0] = expandido[x][y-2][1];
                                            mm[1][1] = expandido[x][y-1][1];
                                            mm[1][2] = expandido[x][y][1];

                                            mm[2][0] = expandido[x+1][y-2][1];
                                            mm[2][1] = expandido[x+1][y-1][1];
                                            mm[2][2] = expandido[x+1][y][1];
                                        }



                                    ////////////////
                                    if(centroX == 2 && centroY == 0)
                                        {
                                            mm[0][0] = expandido[x-2][y][1];
                                            mm[0][1] = expandido[x-2][y+1][1];
                                            mm[0][2] = expandido[x-2][y+2][1];

                                            mm[1][0] = expandido[x-1][y][1];
                                            mm[1][1] = expandido[x-1][y+1][1];
                                            mm[1][2] = expandido[x-1][y+2][1];

                                            mm[2][0] = expandido[x][y][1];
                                            mm[2][1] = expandido[x][y+1][1];
                                            mm[2][2] = expandido[x][y+2][1];
                                        }
                                    if(centroX == 2 && centroY == 1)
                                        {
                                            mm[0][0] = expandido[x-2][y-1][1];
                                            mm[0][1] = expandido[x-2][y][1];
                                            mm[0][2] = expandido[x-2][y+1][1];

                                            mm[1][0] = expandido[x-1][y-1][1];
                                            mm[1][1] = expandido[x-1][y][1];
                                            mm[1][2] = expandido[x-1][y+1][1];

                                            mm[2][0] = expandido[x][y-1][1];
                                            mm[2][1] = expandido[x][y][1];
                                            mm[2][2] = expandido[x][y+1][1];
                                        }
                                    if(centroX == 2 && centroY == 2)
                                        {
                                            mm[0][0] = expandido[x-2][y-2][1];
                                            mm[0][1] = expandido[x-2][y-1][1];
                                            mm[0][2] = expandido[x-2][y][1];

                                            mm[1][0] = expandido[x-1][y-2][1];
                                            mm[1][1] = expandido[x-1][y-1][1];
                                            mm[1][2] = expandido[x-1][y][1];

                                            mm[2][0] = expandido[x][y-2][1];
                                            mm[2][1] = expandido[x][y-1][1];
                                            mm[2][2] = expandido[x][y][1];
                                        }

                                    exp2[x][y][1] = compara(EE, mm);
                                }

                        }
                }
          /*  for(int x = 0; x < expandido.length; x++)
                {
                    for(int y = 0; y < expandido[0].length; y++)
                        {
                            System.out.print((exp2[x][y][1] == 255) ? "-|" : exp2[x][y][1] + "|");
                        }
                    System.out.println("");
                }*/
            for(int x = 0; x < binario.length; x++)
                {
                    for(int y = 0; y < binario[0].length; y++)
                        {
                            sinCeros[x][y][1] = exp2[x+2][y+2][1];
                            sinCeros[x][y][2] = exp2[x+2][y+2][1];
                            sinCeros[x][y][3] = exp2[x+2][y+2][1];
                        }
                }
            return sinCeros;
        }

    public int[][][] expande(int[][][] argb)
        {
            int[][][] largo = new int[argb.length + 4][argb[0].length + 4][4];

            for(int y = 0; y < largo[0].length; y++) //verticales
                {
                    largo[0][y][1] = 0;
                    largo[0][y][2] = 0;
                    largo[0][y][3] = 0;

                    largo[1][y][1] = 0;
                    largo[1][y][2] = 0;
                    largo[1][y][3] = 0;

                    largo[largo.length - 1][y][1] = 0;
                    largo[largo.length - 1][y][2] = 0;
                    largo[largo.length - 1][y][3] = 0;

                    largo[largo.length - 2][y][1] = 0;
                    largo[largo.length - 2][y][2] = 0;
                    largo[largo.length - 2][y][3] = 0;
                }

            for(int x = 0; x < largo.length; x++) //horizontales
                {
                    largo[x][0][1] = 0;
                    largo[x][0][2] = 0;
                    largo[x][0][3] = 0;

                    largo[x][1][1] = 0;
                    largo[x][1][2] = 0;
                    largo[x][1][3] = 0;

                    largo[x][largo[0].length - 1][1] = 0;
                    largo[x][largo[0].length - 1][2] = 0;
                    largo[x][largo[0].length - 1][3] = 0;

                    largo[x][largo[0].length - 2][1] = 0;
                    largo[x][largo[0].length - 2][2] = 0;
                    largo[x][largo[0].length - 2][3] = 0;
                }
                for(int y = 2; y < (argb[0].length + 2); y++)                                
                {
                    for(int x = 2; x < (argb.length + 2); x++)
                        {
                            largo[x][y][1] = argb[x-2][y-2][1];
                            largo[x][y][2] = argb[x-2][y-2][2];
                            largo[x][y][3] = argb[x-2][y-2][3];
                        }
                }
            return largo;
        }

    public int[][][] expande2(int[][][] argb)
        {
            int[][][] largo = new int[argb.length + 4][argb[0].length + 4][4];

            for(int y = 0; y < largo[0].length; y++) //verticales
                {
                    largo[0][y][1] = 255;
                    largo[0][y][2] = 255;
                    largo[0][y][3] = 255;

                    largo[1][y][1] = 255;
                    largo[1][y][2] = 255;
                    largo[1][y][3] = 255;

                    largo[largo.length - 1][y][1] = 255;
                    largo[largo.length - 1][y][2] = 255;
                    largo[largo.length - 1][y][3] = 255;

                    largo[largo.length - 2][y][1] = 255;
                    largo[largo.length - 2][y][2] = 255;
                    largo[largo.length - 2][y][3] = 255;
                }

            for(int x = 0; x < largo.length; x++) //horizontales
                {
                    largo[x][0][1] = 255;
                    largo[x][0][2] = 255;
                    largo[x][0][3] = 255;

                    largo[x][1][1] = 255;
                    largo[x][1][2] = 255;
                    largo[x][1][3] = 255;

                    largo[x][largo[0].length - 1][1] = 255;
                    largo[x][largo[0].length - 1][2] = 255;
                    largo[x][largo[0].length - 1][3] = 255;

                    largo[x][largo[0].length - 2][1] = 255;
                    largo[x][largo[0].length - 2][2] = 255;
                    largo[x][largo[0].length - 2][3] = 255;
                }
            for(int x = 2; x < argb.length + 2; x++)
                {
                    for(int y = 2; y < argb[0].length + 2; y++)
                        {
                            largo[x][y][1] = argb[x-2][y-2][1];
                            largo[x][y][2] = argb[x-2][y-2][2];
                            largo[x][y][3] = argb[x-2][y-2][3];
                        }
                }
            return largo;
        }
    
    public int or(int a, int b) //b es el ee
        {

            if(a == 0 && (b == 0 || b == 2))
                {
                    return 0;
                }
            else
                {
                    return 255;
                }
        }

    public int compara(int[][] EE, int[][] mm)
        {
          /*  System.out.println("");System.out.println("\n\n");         
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                        {
                            System.out.print(((mm[i][j] == 255 ) ? "☻": mm[i][j]) + "|");
                        }
                    System.out.println("");
                }*/
            
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                        {                        
                            if(EE[i][j] == 2)
                                {
                                   // System.out.println(i + " + " + j + " - [2] ");
                                }
                            else
                                {
                                   if(EE[i][j] != mm[i][j])
                                    {                                          
                                        return 0;
                                    }
                                }
                        }
                }
            //System.out.println("Todo bien si cupo");
            return 255;
        }
    public int[][] ajustaEE(int[][] ee)
        {
            int[][] ee2 = new int[3][3];
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                        {
                            ee2[i][j] = ee[j][i];
                        }
                }
            return ee2;
        }
}
