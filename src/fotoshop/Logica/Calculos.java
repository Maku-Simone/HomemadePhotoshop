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
public class Calculos {
    public double media(int[] data)
        {
            double suma = 0.0;
            int total = data.length;
            for(int i = 0; i < total; i++)
                {
                    suma += data[i];
                }
            suma /= total;
            return suma;
        }
    
    public double varianza(int[] data, double media)
        {
            double suma = 0.0;
            int total = data.length;
            for(int i = 0; i < total; i++)
                {
                    suma += Math.pow(data[i] - media, 2);
                }
            suma /= (total - 1);
            return suma;
        }    

    public int[] burbuja(int[] n)
        {
            int temp;
            int t = n.length;
            for (int i = 1; i < t; i++) 
            {
                 for (int k = t- 1; k >= i; k--) 
                 {
                        if(n[k] < n[k-1])
                        {
                            temp = n[k];
                            n[k] = n[k-1];
                            n[k-1]=  temp;
                        }
                 }
            }
            return n;
        }
    
    
    public double mediana (int[] v) 
        {
            int pos = 0, n = v.length;
            double temp = 0, mediana = 0;    
            // ordenar de menor a mayor
            v = burbuja(v);
            temp = n / 2;
            if (n % 2 == 0) //par
                {
                    pos = (int)temp;      
                    mediana = (double)(v[pos] / v[pos - 1]);
                }
            if (n % 2 == 1) //impar
                {
                    pos = (int)(temp - 0.5);
                    mediana = (double)(v[pos]);  
                }

            return mediana;
        }
}
