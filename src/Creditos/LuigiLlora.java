/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creditos;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Maku
 */
public class LuigiLlora {
    
        public static void main() throws IOException
            {
                JPanel panel = new JPanel();
 
                BufferedImage image = ImageIO.read(new File("src/Creditos/LuigiLlora.jpg"));
                JLabel label = new JLabel(new ImageIcon(image));
                panel.add(label);

                // main window
                JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new JFrame("NO ME SALIO :c");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                // add the Jpanel to the main window
                frame.add(panel); 

                frame.pack();
                frame.setVisible(true);
            }
}
