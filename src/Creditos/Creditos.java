/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creditos;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Maku
 */
public class Creditos {
     
    public static void main()
        {
            JFrame f = new JFrame();
            JPanel p = new JPanel();
            String[] texto = {"Desarrollado por:","Carlos Elliot Frías Mercado","Correo:","cefriasm@gmail.com","Última actualización:","05/12/2018",":3"};
            JLabel[] label = new JLabel[texto.length];
            
            for(int i = 0; i < texto.length; i++)
                {                    
                    label[i] =  new JLabel();
                    label[i].setText(texto[i]);
                    label[i].setBounds(150, 50 + (30 * i), 200, 45);
                    p.add(label[i]);
                }            
            p.setPreferredSize(new Dimension(500,500));    
            p.setLayout(null);
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
        }
}
