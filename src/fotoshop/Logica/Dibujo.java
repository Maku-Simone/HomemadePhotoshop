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
import javax.swing.JPanel;

public class Dibujo extends JPanel {

    private int[][][] canales;
    private int alto, ancho;
    
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
            for(int y = 0; y < alto; y++)
                {
                    for(int x = 0; x < ancho; x++)
                        {
                            Color c = new Color(canales[x][y][1], canales[x][y][2], canales[x][y][3]); //creo color del rgb
                            g.setColor(c);
                            g.drawLine(x, y, x, y);
                        }
                }
        }
}