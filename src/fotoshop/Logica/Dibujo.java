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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Dibujo extends JPanel {

    private int[][][] canales;
    private int alto, ancho;
    private Graphics2D g2d;
    
    public Dibujo(int[][][] argb, int ancho, int alto)
        {
            this.canales = argb;
            this.ancho = ancho;
            this.alto = alto;            
        }
    @Override               
    public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);                
            g2d = (Graphics2D) g;
            for(int y = 0; y < alto; y++)
                {
                    for(int x = 0; x < ancho; x++)
                        {
                            Color c = new Color(canales[x][y][1], canales[x][y][2], canales[x][y][3]); //creo color del rgb                            
                            g2d.setColor(c);
                            g2d.drawLine(x, y, x, y);
                        }
                }
        }
}