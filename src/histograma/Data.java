/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histograma;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import fotoshop.Logica.Calculos;
import fotoshop.Logica.Imagen;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Maku
 */
public class Data{         

    public JTable Data(Imagen img) {       
        int[][][] argb = img.getArgb();
        int ancho = img.getAncho();
        int alto = img.getAlto();
        int tam = ancho * alto;
        int[] R = new int[tam];
        int[] G = new int[tam];
        int[] B = new int[tam];
        
        int z = 0;
        for(int x = 0; x < ancho; x++)
            {
                for(int y = 0; y < alto; y++)
                    {
                        R[z] = argb[x][y][1];
                        G[z] = argb[x][y][2];
                        B[z] = argb[x][y][3];
                        z++;
                    }
            }
        
        Calculos C = new Calculos();        
        float mediaR, mediaG, mediaB, medianaR, medianaG, medianaB, varianzaR, varianzaG, varianzaB, asymR, asymG, asymB;    
        float[] modaR, modaG, modaB;                
        
        mediaR = C.media(R);
        mediaG = C.media(G);
        mediaB = C.media(B);
        
        medianaR = C.mediana(R);
        medianaG = C.mediana(G);
        medianaB = C.mediana(B);
        
        modaR = C.modaYenergiaYentropia(R, 3); //[moda, energia, entropia]
        modaG = C.modaYenergiaYentropia(G, 3); //0 solo moda, 1 solo energia, 2 solo entropia, 3 todo
        modaB = C.modaYenergiaYentropia(B, 3);        
        
        varianzaR = C.varianza(R, mediaR);
        varianzaG = C.varianza(G, mediaG);
        varianzaB = C.varianza(B, mediaB);
                
        asymR = C.asimetria(mediaR, medianaR, varianzaR);
        asymG = C.asimetria(mediaG, medianaG, varianzaG);
        asymB = C.asimetria(mediaB, medianaB, varianzaB);
        
        Object[][] data = {            
            {"","Canal Rojo", "Canal Verde", "Canal Azul"},
            {"Media", mediaR, mediaG, mediaB},
            {"Moda", modaR[0], modaG[0], modaB[0]},          
            {"Mediana", medianaR, medianaG, medianaB},
            {"Varianza", varianzaR, varianzaG, varianzaB},
            {"Asimetria", asymR, asymG, asymB},            
            {"Energia", modaR[1], modaG[1], modaB[1]},
            {"Entropia", modaR[2], modaG[2], modaB[2]}            
        };

        String[] nombreColumnas = {"Propiedad", 
                                "Canal Rojo",
                                "Canal Verde",
                                "Canal Azul"};

        JTable table = new JTable(data, nombreColumnas);
        table.setPreferredSize(new Dimension(1500, 150));        
        table.setDefaultEditor(Object.class, null);


        return table;
       
    }
     

   
}
