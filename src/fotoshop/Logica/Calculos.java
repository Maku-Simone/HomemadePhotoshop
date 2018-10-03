/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop.Logica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Maku
 */
public class Calculos {
    public float media(int[] data)
        {
            double suma = 0.0;
            int total = data.length;
            for(int i = 0; i < total; i++)
                {
                    suma += data[i];
                }
          //  System.out.println("Suma: " + suma + " total " + total + " media " + suma/total);            
            return (float) (suma/total);
        }
    
    public float varianza(int[] data, double media)
        {
            double suma = 0.0;
            int total = data.length;
            for(int i = 0; i < total; i++)
                {
                    suma += Math.pow(data[i] - media, 2);
                }
            suma /= (total - 1);
            return (float) suma;
        }    
  
  
    
    public float mediana(int[] v) 
        {
            int pos = 0, total = v.length;                
            float mitad = 0, mediana = 0;    
            // ordenar de menor a mayor
            Arrays.sort(v);
  
            mitad = total / 2;
            if (total % 2 == 0) //par
                {
                    pos = (int)mitad;    
                    //System.out.println("Mediana de " + total + " mitad en " + pos + " es " + v[pos] + " y " + v[pos - 1]);
                    mediana = (float) ( (v[pos] + v[pos - 1]) / 2 ) > 0 ? v[pos - 1] : 1;
                }
            if (total % 2 == 1) //impar
                {
                   // System.out.println("Mediana impar de " + total + " mitad en " + pos + " es " + v[pos] + " y " + v[pos - 1]);
                    pos = (int)(mitad - 0.5);
                    mediana = (float)(v[pos]);  
                }

            return mediana;
        }
    public float[] modaYenergiaYentropia(int[] m, int opc)
        {
            float[] retorno = new float[3]; //[moda, energia, entropia]            
            float energia = (float) 0.0;
            Map<Integer, Integer> mapa = new HashMap<>(); //mapa<numero, frecuencia>
            Set<Integer> numeros = new HashSet();
            for(int i = 0; i < m.length; i++)
                {
                   if( numeros.add(m[i]) )
                    {
                        mapa.put(m[i], 1);
                    }
                   else
                   {
                       int val = 0;
                       val = mapa.get(m[i]);
                       mapa.put(m[i], val + 1);
                   }                   
                }

            if(opc == 0) //solo la moda
                {
                 retorno[0] = (float) (moda(mapa, m) * 1.0);
                 retorno[1] = 0;
                 retorno[2] = 0;
                }
            if(opc == 1) //solo la energia
                {
                    retorno[0] = 0;
                    retorno[1] = energia(mapa, m.length);
                    retorno[2] = 0;
                }
            if(opc == 2) // solo la entropia
                {
                    retorno[0] = 0;
                    retorno[1] = 0;
                    retorno[2] = entropia(mapa, m.length);
                }
            if(opc == 3) //las 3
                {
                    retorno[0] = (float) (moda(mapa, m) * 1.0);
                    retorno[1] = energia(mapa, m.length);
                    retorno[2] = entropia(mapa, m.length);                    
                }
           
            return retorno;
        }
        public int moda(Map mapa, int[] m)
            {
                int moda = 0;
                for(int  i = 0; i < 255; i++)
                    {
                        if(mapa.get(i) != null)
                            {
                                int existe = (mapa.get(moda) != null) ? (int)mapa.get(moda) : 0;
                                if((int)mapa.get(i) > existe)
                                    {
                                        //System.out.println((int)mapa.get(i)  + " > " + mapa.get(moda));    
                                        moda = i; 
                                    }                                                            
                            }
                    }
                return moda;
            }
    
        public float asimetria(double media, double mediana, double desvEstandar)
            {
                return (float) (3*(media - mediana) / (desvEstandar));
            }
        
        public float energia(Map nivelesGris, int totalPixeles)
            {
                float energia = 0;
                for(int i = 0; i < 255; i++)
                    {
                        energia += nivelesGris.get(i) != null ? (float)((int)nivelesGris.get(i)) : 0;
                    }
                return (energia /= Math.pow(totalPixeles, 2) );
            }
        public float entropia(Map ng, int totalPx)
            {
                float ent = (float) 0.0;
                for(int i = 0; i < 255; i++)
                    {
                        float pg = ng.get(i) != null ? (float)(int)ng.get(i) / totalPx : 1;
                        ent += (pg * (Math.log(pg)/Math.log(2)));
                    }
                return (-1) * ent;
            }
                
}
