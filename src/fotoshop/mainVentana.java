/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop;

import fotoshop.Logica.Dibujo;
import fotoshop.Logica.Imagen;
import fotoshop.Logica.Transformaciones;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


/**
 *
 * @author Maku
 */
public class mainVentana extends javax.swing.JFrame {

    /**
     * Creates new form mainVentana
     */
    ArrayList<Imagen> listaImagenes = new ArrayList<Imagen>();
    
    public mainVentana() {                       
        initComponents();            
        cerrarTab.setContentAreaFilled(false);                      
        saveBoton.setContentAreaFilled(false);          
        abrirBoton.setContentAreaFilled(false);   
        redoBoton.setContentAreaFilled(false);   
        grisesBoton.setContentAreaFilled(false); 
        
        
        cerrarTab.addActionListener(new ActionListener()  //BOTON CERRAR IMAGEN
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int r = panelTabs.getSelectedIndex();
                    System.out.println("index es " + r);
                    if(r >= 0)
                        {
                            panelTabs.remove(r);
                            System.out.println("remuevo " + r);
                            listaImagenes.remove(r);
                        }
                }
            });

        
        saveBoton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int r = panelTabs.getSelectedIndex();
                    System.out.println("index es " + r);
                    if(r >= 0)
                        {
                            Imagen im = listaImagenes.get(r);
                            String rutaGuardar = "";
                            int[][][] rgbGuardar = im.getModificado();
                           // panelTabs.remove(r);
                            //System.out.println("remoevuo " + r);
                            im = listaImagenes.get(r);
                            System.out.println("Tengo: " + im.toString());
                            JFrame parentFrame = new JFrame();
                            JFileChooser fileChooser = new JFileChooser();
                            //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                            
                            fileChooser.setDialogTitle("Especifice nomrbe y extensión para guardar");   
                            int userSelection = fileChooser.showSaveDialog(parentFrame);
                            if (userSelection == JFileChooser.APPROVE_OPTION) 
                                {
                                    rutaGuardar = fileChooser.getSelectedFile().getAbsolutePath();
                                    System.out.println("RUTA " +  rutaGuardar);                                                                                                    
                                    try
                                        {
                                            File f = new File(rutaGuardar + "." + im.getExtension()); //donde voy a guardar
                                            File readImg = new File(im.getRuta());
                                            //BufferedImage img = ImageIO.read(readImg);
                                            BufferedImage img = new BufferedImage(im.getAncho(), im.getAlto(), BufferedImage.TYPE_INT_RGB);
                                            for(int x = 0; x < im.getAncho(); x++)
                                                {
                                                    for(int y = 0; y < im.getAlto(); y++)
                                                       {
                                                           //System.out.println("Guardando rgb " + rgbGuardar[x][y][1] + " - " +  rgbGuardar[x][y][2] + " - " +rgbGuardar[x][y][3]);
                                                           img.setRGB(x, y, (new Color(rgbGuardar[x][y][1],rgbGuardar[x][y][2],rgbGuardar[x][y][3])).getRGB());
                                                       }
                                                }
                                            ImageIO.write(img, im.getExtension().replace(".",""), f);
                                            JFrame mensaje = new JFrame("Mensajito de algo");
                                            JOptionPane.showMessageDialog(mensaje, "Imagen Guardada exitosamente");
                                        }
                                    catch(IOException err)
                                        {
                                            System.out.println(err);
                                        }
                                }                            
                        }
                }
            });

    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrirBoton = new javax.swing.JButton();
        cerrarTab = new javax.swing.JButton();
        panelTabs = new javax.swing.JTabbedPane();
        saveBoton = new javax.swing.JButton();
        redoBoton = new javax.swing.JButton();
        sliderUmbral = new javax.swing.JSlider();
        labelUmbral = new javax.swing.JLabel();
        Binarización = new javax.swing.JLabel();
        grisesBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 102, 255));

        abrirBoton.setBackground(new java.awt.Color(204, 204, 204));
        abrirBoton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        abrirBoton.setForeground(javax.swing.UIManager.getDefaults().getColor("netbeans.ps.background"));
        abrirBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/file.png"))); // NOI18N
        abrirBoton.setAlignmentY(0.0F);
        abrirBoton.setBorderPainted(false);
        abrirBoton.setFocusCycleRoot(true);
        abrirBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abrirBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        abrirBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        abrirBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        abrirBoton.setPreferredSize(new java.awt.Dimension(50, 66));
        abrirBoton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        abrirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirBotonActionPerformed(evt);
            }
        });

        cerrarTab.setBackground(new java.awt.Color(204, 204, 204));
        cerrarTab.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cerrarTab.setForeground(new java.awt.Color(255, 255, 255));
        cerrarTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/x.png"))); // NOI18N
        cerrarTab.setAlignmentY(0.0F);
        cerrarTab.setBorderPainted(false);
        cerrarTab.setFocusCycleRoot(true);
        cerrarTab.setFocusable(false);
        cerrarTab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cerrarTab.setIconTextGap(0);
        cerrarTab.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cerrarTab.setMaximumSize(new java.awt.Dimension(50, 66));
        cerrarTab.setMinimumSize(new java.awt.Dimension(50, 66));
        cerrarTab.setPreferredSize(new java.awt.Dimension(50, 66));
        cerrarTab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cerrarTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarTabActionPerformed(evt);
            }
        });

        saveBoton.setBackground(new java.awt.Color(204, 204, 204));
        saveBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/save.png"))); // NOI18N
        saveBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        saveBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        saveBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        saveBoton.setPreferredSize(new java.awt.Dimension(50, 66));

        redoBoton.setBackground(new java.awt.Color(204, 204, 204));
        redoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/redo.png"))); // NOI18N
        redoBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        redoBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        redoBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        redoBoton.setPreferredSize(new java.awt.Dimension(50, 66));
        redoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoBotonActionPerformed(evt);
            }
        });

        sliderUmbral.setMaximum(255);
        sliderUmbral.setValue(128);
        sliderUmbral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderUmbralStateChanged(evt);
            }
        });

        labelUmbral.setText("Umbral:");

        Binarización.setText("Binarización");
        Binarización.setAlignmentX(0.5F);
        Binarización.setAutoscrolls(true);
        Binarización.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        grisesBoton.setBackground(new java.awt.Color(204, 204, 204));
        grisesBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/Gris.png"))); // NOI18N
        grisesBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        grisesBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        grisesBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        grisesBoton.setPreferredSize(new java.awt.Dimension(50, 66));
        grisesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grisesBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabs)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(abrirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cerrarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sliderUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(labelUmbral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Binarización, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grisesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 590, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abrirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cerrarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(redoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grisesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Binarización)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sliderUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(panelTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirBotonActionPerformed
        // ABRIR IMAGEN :3
         // leer image	
        String formatoImg = "";
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) //abriendo el JFile
            {
                File file = fileChooser.getSelectedFile();
                formatoImg = file.getName(); //obtengo nombre.extension                
                formatoImg = formatoImg.substring(formatoImg.lastIndexOf("."), formatoImg.length());
                System.out.println("Abriendo: " + file.getPath() + "    -    " + file.getName() + " de extension " + formatoImg);       
                if(checaExtensionImg(formatoImg)) //si está admitido
                    {
                        System.out.println("Si :3");
                        try 
                            {
                                Imagen img = new Imagen(file.getPath(), file.getName(), formatoImg);                                
                                listaImagenes.add(img);                                                                                               
                                JFrame f = new JFrame();                                   
                                JScrollPane scroll = new JScrollPane();
                                scroll.getViewport().add(new Dibujo(img.getArgb(), img.getAncho(), img.getAlto()));                                
                                f.add(scroll);                                                                   
                                panelTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                                panelTabs.addTab(img.getNombreImagen(),null, f.getContentPane(), ":3");
                                
                            }
                        catch (IOException ex) 
                            {
                                Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       }
                else
                    {
                        JFrame mensaje = new JFrame("Mensajito de algo");
                        JOptionPane.showMessageDialog(mensaje, "Formato de imagen no permitido");
                    }
            }
	else 
            {
                System.out.println("Open command cancelled by user.");
            //    System.exit(0);
            }		        		
    }//GEN-LAST:event_abrirBotonActionPerformed

    private void cerrarTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarTabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cerrarTabActionPerformed

    private void sliderUmbralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderUmbralStateChanged
        // TODO add your handling code here:
        labelUmbral.setText("Umbral: " + sliderUmbral.getValue());
        int r = panelTabs.getSelectedIndex();
        System.out.println("index es " + r);
        int[][][] binario;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
                System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                try 
                    {
                        binario = tra.binarizado(sliderUmbral.getValue(), listaImagenes.get(r));                           
                        listaImagenes.get(r).setModificado(binario);

                        JFrame f = new JFrame();                                   
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(binario, img.getAncho(), img.getAlto()));                                       
                        f.add(scroll);  
                       // f.setVisible(true);
                        panelTabs.setComponentAt(r, f.getContentPane());
//                                    panelTabs.addTab(img.getNombreImagen(),null, f.getContentPane(), ":3");

                    } 
                catch (IOException ex) 
                    {
                        Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
                    }

            }
    }//GEN-LAST:event_sliderUmbralStateChanged

    private void redoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        System.out.println("index es " + r);
        if(r >= 0)
            {                                                         
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                JFrame f = new JFrame();                                   
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(img.getArgb(), img.getAncho(), img.getAlto()));                                       
                listaImagenes.get(r).setModificado(listaImagenes.get(r).getArgb());
                f.add(scroll);          
                panelTabs.setComponentAt(r, f.getContentPane());
            }
        
        
    }//GEN-LAST:event_redoBotonActionPerformed

    private void grisesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grisesBotonActionPerformed
        int r = panelTabs.getSelectedIndex();
        System.out.println("index es " + r);
        int[][][] gris;     
        if(r >= 0)
            {                                         
                System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                try 
                    {
                        gris = tra.grises(listaImagenes.get(r));                                                   
                        JFrame f = new JFrame();                                   
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(gris, img.getAncho(), img.getAlto()));                                       
                        f.add(scroll);  
                        listaImagenes.get(r).setModificado(gris);
                        panelTabs.setComponentAt(r, f.getContentPane());//                    
                    } 
                catch (IOException ex) 
                    {
                        Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
                    }

            }
    }//GEN-LAST:event_grisesBotonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainVentana().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Binarización;
    private javax.swing.JButton abrirBoton;
    private javax.swing.JButton cerrarTab;
    private javax.swing.JButton grisesBoton;
    private javax.swing.JLabel labelUmbral;
    private javax.swing.JTabbedPane panelTabs;
    private javax.swing.JButton redoBoton;
    private javax.swing.JButton saveBoton;
    private javax.swing.JSlider sliderUmbral;
    // End of variables declaration//GEN-END:variables


    static boolean checaExtensionImg(String extension)
        {            
            String admitidos[] = {".jpg", ".png", ".bmp", ".gif", ".jpeg"};
            for(int i = 0; i < admitidos.length; i++)
                {
                    if(extension.equals(admitidos[i])) //iguales
                        {
                            return true; //si está en el rango de formatos permitidos
                        }   
                }
            return false; //no estuvo dentro de los formatos admitidos
        }

}
