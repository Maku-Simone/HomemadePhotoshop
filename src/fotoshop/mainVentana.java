/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotoshop;

import Creditos.Creditos;
import Creditos.LuigiLlora;
import fotoshop.Logica.Dibujo;
import fotoshop.Logica.Filtros;
import fotoshop.Logica.Imagen;
import fotoshop.Logica.Morfologia;
import fotoshop.Logica.MorfologiaLatices;
import fotoshop.Logica.Transformaciones;
import histograma.AjusteDeBrillo;
import histograma.Data;
import histograma.Histogram;
import histograma.dobleHistograma;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import org.jfree.util.StringUtils;


/**
 *
 * @author Maku
 */
    
public class mainVentana extends javax.swing.JFrame {

    /**
     * Creates new form mainVentana
     */
    
    
    
    ArrayList<Imagen> listaImagenes = new ArrayList<Imagen>();
    static int[][][] operacionesAritmeticas, morfologiaOperado;
    static int[] centroEE;
    static boolean centroDefinido = false;
    Point umbral1 = new Point(0,0), umbral2  = new Point(0,0);
    
    public mainVentana() {                       
        initComponents();     
        panelTabs.setBackground(Color.white);
        cerrarTab.setContentAreaFilled(false);                      
        saveBoton.setContentAreaFilled(false);          
        abrirBoton.setContentAreaFilled(false);   
        redoBoton.setContentAreaFilled(false);   
        grisesBoton.setContentAreaFilled(false);                        
        rBoton.setContentAreaFilled(false);  
        gBoton.setContentAreaFilled(false);  
        bBoton.setContentAreaFilled(false);  
        
        cerrarTab.addActionListener(new ActionListener()  //BOTON CERRAR IMAGEN
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int r = panelTabs.getSelectedIndex();
                  //  System.out.println("index es " + r);
                    if(r >= 0)
                        {
                            panelTabs.remove(r);
                          //  System.out.println("remuevo " + r);
                            listaImagenes.remove(r);
                        }
                }
            });

        
        saveBoton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int r = panelTabs.getSelectedIndex();
                 //   System.out.println("index es " + r);
                    if(r >= 0)
                        {
                            Imagen im = listaImagenes.get(r);
                            String rutaGuardar = "";
                            int[][][] rgbGuardar = im.getModificado();
                           // panelTabs.remove(r);
                            //System.out.println("remoevuo " + r);
                            im = listaImagenes.get(r);
                           // System.out.println("Tengo: " + im.toString());
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
                                            //File readImg = new File(im.getRuta());
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
        rBoton = new javax.swing.JButton();
        gBoton = new javax.swing.JButton();
        bBoton = new javax.swing.JButton();
        sumaBotom = new javax.swing.JButton();
        multiBoton = new javax.swing.JButton();
        restaBoton = new javax.swing.JButton();
        diviBoton = new javax.swing.JButton();
        inversoBoton = new javax.swing.JButton();
        andBoton = new javax.swing.JButton();
        orBoton = new javax.swing.JButton();
        xorBoton = new javax.swing.JButton();
        xnorBoton = new javax.swing.JButton();
        norBoton = new javax.swing.JButton();
        nanBoton = new javax.swing.JButton();
        histogramaBoton = new javax.swing.JButton();
        opersHistogramaBoton = new javax.swing.JButton();
        filtroPromedioBoton = new javax.swing.JButton();
        filtroPromedioPesadoBoton = new javax.swing.JButton();
        gaussianoBoton = new javax.swing.JButton();
        pixSepBoton = new javax.swing.JButton();
        robertsBoton = new javax.swing.JButton();
        sobelBoton = new javax.swing.JButton();
        difPixBoton = new javax.swing.JButton();
        prewittBoton = new javax.swing.JButton();
        laplacianoBoton = new javax.swing.JButton();
        modaBoton = new javax.swing.JButton();
        medianaBoton = new javax.swing.JButton();
        maxMinBoton = new javax.swing.JButton();
        multiUmbralBoton = new javax.swing.JButton();
        multiUmbLabel = new javax.swing.JLabel();
        dobleUmbralBoton = new javax.swing.JButton();
        addRuidoBoton = new javax.swing.JButton();
        DilatacionBoton = new javax.swing.JButton();
        erosionBoton = new javax.swing.JButton();
        aperturaBoton = new javax.swing.JButton();
        clausuraBoton = new javax.swing.JButton();
        fronteraBoton = new javax.swing.JButton();
        DilatacionLaticesBoton = new javax.swing.JButton();
        erosionLaticesBoton = new javax.swing.JButton();
        clausuraLaticesBoton = new javax.swing.JButton();
        aperturaLaticesBoton = new javax.swing.JButton();
        alisamientoBoton = new javax.swing.JButton();
        botHatBoton = new javax.swing.JButton();
        topHatBoton = new javax.swing.JButton();
        gradienteEBoton = new javax.swing.JButton();
        gradienteDBoton = new javax.swing.JButton();
        gradSimBoton = new javax.swing.JButton();
        adelgazamientoBoton = new javax.swing.JButton();
        thomBoton = new javax.swing.JButton();
        esqueletoBoton = new javax.swing.JButton();
        creditosBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setIconImage(getIconImage());

        abrirBoton.setBackground(new java.awt.Color(204, 204, 204));
        abrirBoton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        abrirBoton.setForeground(javax.swing.UIManager.getDefaults().getColor("netbeans.ps.background"));
        abrirBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/file.png"))); // NOI18N
        abrirBoton.setToolTipText("Abrir nueva imagen");
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
        cerrarTab.setToolTipText("Cerrar Imagen actual");
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

        panelTabs.setBackground(new java.awt.Color(255, 255, 255));
        panelTabs.setForeground(new java.awt.Color(255, 255, 255));

        saveBoton.setBackground(new java.awt.Color(204, 204, 204));
        saveBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/save.png"))); // NOI18N
        saveBoton.setToolTipText("Guardar Imagen actual");
        saveBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        saveBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        saveBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        saveBoton.setPreferredSize(new java.awt.Dimension(50, 66));

        redoBoton.setBackground(new java.awt.Color(204, 204, 204));
        redoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/redo.png"))); // NOI18N
        redoBoton.setToolTipText("Deshacer todo");
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
        grisesBoton.setToolTipText("Escala de grises");
        grisesBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        grisesBoton.setMaximumSize(new java.awt.Dimension(50, 66));
        grisesBoton.setMinimumSize(new java.awt.Dimension(50, 66));
        grisesBoton.setPreferredSize(new java.awt.Dimension(50, 66));
        grisesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grisesBotonActionPerformed(evt);
            }
        });

        rBoton.setBackground(new java.awt.Color(255, 255, 255));
        rBoton.setForeground(new java.awt.Color(204, 0, 0));
        rBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/red.png"))); // NOI18N
        rBoton.setToolTipText("Canal Rojo");
        rBoton.setAlignmentY(0.0F);
        rBoton.setBorderPainted(false);
        rBoton.setContentAreaFilled(false);
        rBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        rBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBotonActionPerformed(evt);
            }
        });

        gBoton.setBackground(new java.awt.Color(255, 255, 255));
        gBoton.setForeground(new java.awt.Color(0, 204, 0));
        gBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/green.png"))); // NOI18N
        gBoton.setToolTipText("Canal Verde");
        gBoton.setBorderPainted(false);
        gBoton.setContentAreaFilled(false);
        gBoton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gBotonActionPerformed(evt);
            }
        });

        bBoton.setBackground(new java.awt.Color(255, 255, 255));
        bBoton.setForeground(new java.awt.Color(0, 204, 255));
        bBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/blue.png"))); // NOI18N
        bBoton.setToolTipText("Canal Azul");
        bBoton.setBorderPainted(false);
        bBoton.setContentAreaFilled(false);
        bBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBotonActionPerformed(evt);
            }
        });

        sumaBotom.setBackground(new java.awt.Color(255, 255, 255));
        sumaBotom.setForeground(new java.awt.Color(204, 0, 0));
        sumaBotom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/suma.png"))); // NOI18N
        sumaBotom.setToolTipText("Suma");
        sumaBotom.setAlignmentY(0.0F);
        sumaBotom.setBorderPainted(false);
        sumaBotom.setContentAreaFilled(false);
        sumaBotom.setMargin(new java.awt.Insets(2, 1, 2, 1));
        sumaBotom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumaBotomActionPerformed(evt);
            }
        });

        multiBoton.setBackground(new java.awt.Color(255, 255, 255));
        multiBoton.setForeground(new java.awt.Color(204, 0, 0));
        multiBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/multi.png"))); // NOI18N
        multiBoton.setToolTipText("Multiplicación");
        multiBoton.setAlignmentY(0.0F);
        multiBoton.setBorderPainted(false);
        multiBoton.setContentAreaFilled(false);
        multiBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        multiBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiBotonActionPerformed(evt);
            }
        });

        restaBoton.setBackground(new java.awt.Color(255, 255, 255));
        restaBoton.setForeground(new java.awt.Color(204, 0, 0));
        restaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/resta.png"))); // NOI18N
        restaBoton.setToolTipText("Resta");
        restaBoton.setAlignmentY(0.0F);
        restaBoton.setBorderPainted(false);
        restaBoton.setContentAreaFilled(false);
        restaBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        restaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaBotonActionPerformed(evt);
            }
        });

        diviBoton.setBackground(new java.awt.Color(255, 255, 255));
        diviBoton.setForeground(new java.awt.Color(204, 0, 0));
        diviBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/divi.png"))); // NOI18N
        diviBoton.setToolTipText("División");
        diviBoton.setAlignmentY(0.0F);
        diviBoton.setBorderPainted(false);
        diviBoton.setContentAreaFilled(false);
        diviBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        diviBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diviBotonActionPerformed(evt);
            }
        });

        inversoBoton.setBackground(new java.awt.Color(255, 255, 255));
        inversoBoton.setForeground(new java.awt.Color(0, 204, 255));
        inversoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/inverso.png"))); // NOI18N
        inversoBoton.setToolTipText("Inverso");
        inversoBoton.setBorderPainted(false);
        inversoBoton.setContentAreaFilled(false);
        inversoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inversoBotonActionPerformed(evt);
            }
        });

        andBoton.setBackground(new java.awt.Color(255, 255, 255));
        andBoton.setForeground(new java.awt.Color(204, 0, 0));
        andBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/and.png"))); // NOI18N
        andBoton.setToolTipText("AND");
        andBoton.setAlignmentY(0.0F);
        andBoton.setBorderPainted(false);
        andBoton.setContentAreaFilled(false);
        andBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        andBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andBotonActionPerformed(evt);
            }
        });

        orBoton.setBackground(new java.awt.Color(255, 255, 255));
        orBoton.setForeground(new java.awt.Color(204, 0, 0));
        orBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/or.png"))); // NOI18N
        orBoton.setToolTipText("OR");
        orBoton.setAlignmentY(0.0F);
        orBoton.setBorderPainted(false);
        orBoton.setContentAreaFilled(false);
        orBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        orBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orBotonActionPerformed(evt);
            }
        });

        xorBoton.setBackground(new java.awt.Color(255, 255, 255));
        xorBoton.setForeground(new java.awt.Color(204, 0, 0));
        xorBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/xor.png"))); // NOI18N
        xorBoton.setToolTipText("XOR");
        xorBoton.setAlignmentY(0.0F);
        xorBoton.setBorderPainted(false);
        xorBoton.setContentAreaFilled(false);
        xorBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        xorBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xorBotonActionPerformed(evt);
            }
        });

        xnorBoton.setBackground(new java.awt.Color(255, 255, 255));
        xnorBoton.setForeground(new java.awt.Color(204, 0, 0));
        xnorBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/xnor.png"))); // NOI18N
        xnorBoton.setToolTipText("XNOR");
        xnorBoton.setAlignmentY(0.0F);
        xnorBoton.setBorderPainted(false);
        xnorBoton.setContentAreaFilled(false);
        xnorBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        xnorBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xnorBotonActionPerformed(evt);
            }
        });

        norBoton.setBackground(new java.awt.Color(255, 255, 255));
        norBoton.setForeground(new java.awt.Color(204, 0, 0));
        norBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/nor.png"))); // NOI18N
        norBoton.setToolTipText("NOR");
        norBoton.setAlignmentY(0.0F);
        norBoton.setBorderPainted(false);
        norBoton.setContentAreaFilled(false);
        norBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        norBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                norBotonActionPerformed(evt);
            }
        });

        nanBoton.setBackground(new java.awt.Color(255, 255, 255));
        nanBoton.setForeground(new java.awt.Color(204, 0, 0));
        nanBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/nand.png"))); // NOI18N
        nanBoton.setToolTipText("NAND");
        nanBoton.setAlignmentY(0.0F);
        nanBoton.setBorderPainted(false);
        nanBoton.setContentAreaFilled(false);
        nanBoton.setMargin(new java.awt.Insets(2, 1, 2, 1));
        nanBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nanBotonActionPerformed(evt);
            }
        });

        histogramaBoton.setBackground(new java.awt.Color(255, 255, 255));
        histogramaBoton.setForeground(new java.awt.Color(0, 204, 255));
        histogramaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/histogram.png"))); // NOI18N
        histogramaBoton.setToolTipText("Histograma");
        histogramaBoton.setBorderPainted(false);
        histogramaBoton.setContentAreaFilled(false);
        histogramaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogramaBotonActionPerformed(evt);
            }
        });

        opersHistogramaBoton.setBackground(new java.awt.Color(255, 255, 255));
        opersHistogramaBoton.setForeground(new java.awt.Color(0, 204, 255));
        opersHistogramaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/histoOpers.png"))); // NOI18N
        opersHistogramaBoton.setToolTipText("Operaciones sobre el Histograma");
        opersHistogramaBoton.setBorderPainted(false);
        opersHistogramaBoton.setContentAreaFilled(false);
        opersHistogramaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opersHistogramaBotonActionPerformed(evt);
            }
        });

        filtroPromedioBoton.setBackground(new java.awt.Color(255, 255, 255));
        filtroPromedioBoton.setForeground(new java.awt.Color(0, 204, 255));
        filtroPromedioBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/promedio.png"))); // NOI18N
        filtroPromedioBoton.setToolTipText("Filtro Promedio");
        filtroPromedioBoton.setBorderPainted(false);
        filtroPromedioBoton.setContentAreaFilled(false);
        filtroPromedioBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroPromedioBotonActionPerformed(evt);
            }
        });

        filtroPromedioPesadoBoton.setBackground(new java.awt.Color(255, 255, 255));
        filtroPromedioPesadoBoton.setForeground(new java.awt.Color(0, 204, 255));
        filtroPromedioPesadoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/promedioPesado.png"))); // NOI18N
        filtroPromedioPesadoBoton.setToolTipText("Filtro Promedio Pesado");
        filtroPromedioPesadoBoton.setBorderPainted(false);
        filtroPromedioPesadoBoton.setContentAreaFilled(false);
        filtroPromedioPesadoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroPromedioPesadoBotonActionPerformed(evt);
            }
        });

        gaussianoBoton.setBackground(new java.awt.Color(255, 255, 255));
        gaussianoBoton.setForeground(new java.awt.Color(0, 204, 255));
        gaussianoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/gauss.png"))); // NOI18N
        gaussianoBoton.setToolTipText("Filtro Gaussiano");
        gaussianoBoton.setBorderPainted(false);
        gaussianoBoton.setContentAreaFilled(false);
        gaussianoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussianoBotonActionPerformed(evt);
            }
        });

        pixSepBoton.setBackground(new java.awt.Color(255, 255, 255));
        pixSepBoton.setForeground(new java.awt.Color(0, 204, 255));
        pixSepBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/pixel.png"))); // NOI18N
        pixSepBoton.setToolTipText("Filtro de diferencia de pixel separado");
        pixSepBoton.setBorderPainted(false);
        pixSepBoton.setContentAreaFilled(false);
        pixSepBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pixSepBotonActionPerformed(evt);
            }
        });

        robertsBoton.setBackground(new java.awt.Color(255, 255, 255));
        robertsBoton.setForeground(new java.awt.Color(0, 204, 255));
        robertsBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/Roberts.png"))); // NOI18N
        robertsBoton.setToolTipText("Filtro de Roberts");
        robertsBoton.setBorderPainted(false);
        robertsBoton.setContentAreaFilled(false);
        robertsBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                robertsBotonActionPerformed(evt);
            }
        });

        sobelBoton.setBackground(new java.awt.Color(255, 255, 255));
        sobelBoton.setForeground(new java.awt.Color(0, 204, 255));
        sobelBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/Sobel.png"))); // NOI18N
        sobelBoton.setToolTipText("Filtro de Sobel");
        sobelBoton.setBorderPainted(false);
        sobelBoton.setContentAreaFilled(false);
        sobelBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobelBotonActionPerformed(evt);
            }
        });

        difPixBoton.setBackground(new java.awt.Color(255, 255, 255));
        difPixBoton.setForeground(new java.awt.Color(0, 204, 255));
        difPixBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/difPixel.png"))); // NOI18N
        difPixBoton.setToolTipText("Filtro de diferencia de pixeles");
        difPixBoton.setBorderPainted(false);
        difPixBoton.setContentAreaFilled(false);
        difPixBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difPixBotonActionPerformed(evt);
            }
        });

        prewittBoton.setBackground(new java.awt.Color(255, 255, 255));
        prewittBoton.setForeground(new java.awt.Color(0, 204, 255));
        prewittBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/Prewitt.png"))); // NOI18N
        prewittBoton.setToolTipText("Filtro de Prewitt");
        prewittBoton.setBorderPainted(false);
        prewittBoton.setContentAreaFilled(false);
        prewittBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prewittBotonActionPerformed(evt);
            }
        });

        laplacianoBoton.setBackground(new java.awt.Color(255, 255, 255));
        laplacianoBoton.setForeground(new java.awt.Color(0, 204, 255));
        laplacianoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/laplaciano.png"))); // NOI18N
        laplacianoBoton.setToolTipText("Filtro Laplaciano");
        laplacianoBoton.setBorderPainted(false);
        laplacianoBoton.setContentAreaFilled(false);
        laplacianoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laplacianoBotonActionPerformed(evt);
            }
        });

        modaBoton.setBackground(new java.awt.Color(255, 255, 255));
        modaBoton.setForeground(new java.awt.Color(0, 204, 255));
        modaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/moda.png"))); // NOI18N
        modaBoton.setToolTipText("Filtro moda");
        modaBoton.setBorderPainted(false);
        modaBoton.setContentAreaFilled(false);
        modaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modaBotonActionPerformed(evt);
            }
        });

        medianaBoton.setBackground(new java.awt.Color(255, 255, 255));
        medianaBoton.setForeground(new java.awt.Color(0, 204, 255));
        medianaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/mediana.png"))); // NOI18N
        medianaBoton.setToolTipText("Filtro Mediana");
        medianaBoton.setBorderPainted(false);
        medianaBoton.setContentAreaFilled(false);
        medianaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medianaBotonActionPerformed(evt);
            }
        });

        maxMinBoton.setBackground(new java.awt.Color(255, 255, 255));
        maxMinBoton.setForeground(new java.awt.Color(0, 204, 255));
        maxMinBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/maxMin.png"))); // NOI18N
        maxMinBoton.setToolTipText("Filtro máixmo y mínimo");
        maxMinBoton.setBorderPainted(false);
        maxMinBoton.setContentAreaFilled(false);
        maxMinBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxMinBotonActionPerformed(evt);
            }
        });

        multiUmbralBoton.setBackground(new java.awt.Color(255, 255, 255));
        multiUmbralBoton.setForeground(new java.awt.Color(0, 204, 255));
        multiUmbralBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/multiUmb.png"))); // NOI18N
        multiUmbralBoton.setToolTipText("Multiumbralización");
        multiUmbralBoton.setBorderPainted(false);
        multiUmbralBoton.setContentAreaFilled(false);
        multiUmbralBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiUmbralBotonActionPerformed(evt);
            }
        });

        multiUmbLabel.setText("U1: 123 - U2: 456");

        dobleUmbralBoton.setBackground(new java.awt.Color(255, 255, 255));
        dobleUmbralBoton.setForeground(new java.awt.Color(0, 204, 255));
        dobleUmbralBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/2multiUmb.png"))); // NOI18N
        dobleUmbralBoton.setToolTipText("Multiumbralización de 2 umbrales");
        dobleUmbralBoton.setBorderPainted(false);
        dobleUmbralBoton.setContentAreaFilled(false);
        dobleUmbralBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobleUmbralBotonActionPerformed(evt);
            }
        });

        addRuidoBoton.setBackground(new java.awt.Color(255, 255, 255));
        addRuidoBoton.setForeground(new java.awt.Color(0, 204, 255));
        addRuidoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/ruido.png"))); // NOI18N
        addRuidoBoton.setToolTipText("Añadir Ruido");
        addRuidoBoton.setBorderPainted(false);
        addRuidoBoton.setContentAreaFilled(false);
        addRuidoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRuidoBotonActionPerformed(evt);
            }
        });

        DilatacionBoton.setBackground(new java.awt.Color(255, 255, 255));
        DilatacionBoton.setForeground(new java.awt.Color(0, 204, 255));
        DilatacionBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/dilatacion.png"))); // NOI18N
        DilatacionBoton.setToolTipText("Dilatación");
        DilatacionBoton.setBorderPainted(false);
        DilatacionBoton.setContentAreaFilled(false);
        DilatacionBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DilatacionBotonActionPerformed(evt);
            }
        });

        erosionBoton.setBackground(new java.awt.Color(255, 255, 255));
        erosionBoton.setForeground(new java.awt.Color(0, 204, 255));
        erosionBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/erosion.png"))); // NOI18N
        erosionBoton.setToolTipText("Erosión");
        erosionBoton.setBorderPainted(false);
        erosionBoton.setContentAreaFilled(false);
        erosionBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erosionBotonActionPerformed(evt);
            }
        });

        aperturaBoton.setBackground(new java.awt.Color(255, 255, 255));
        aperturaBoton.setForeground(new java.awt.Color(0, 204, 255));
        aperturaBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/apertura.png"))); // NOI18N
        aperturaBoton.setToolTipText("Apertura");
        aperturaBoton.setBorderPainted(false);
        aperturaBoton.setContentAreaFilled(false);
        aperturaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aperturaBotonActionPerformed(evt);
            }
        });

        clausuraBoton.setBackground(new java.awt.Color(255, 255, 255));
        clausuraBoton.setForeground(new java.awt.Color(0, 204, 255));
        clausuraBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/cierre.png"))); // NOI18N
        clausuraBoton.setToolTipText("Clausura");
        clausuraBoton.setBorderPainted(false);
        clausuraBoton.setContentAreaFilled(false);
        clausuraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clausuraBotonActionPerformed(evt);
            }
        });

        fronteraBoton.setBackground(new java.awt.Color(255, 255, 255));
        fronteraBoton.setForeground(new java.awt.Color(0, 204, 255));
        fronteraBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/cerco.png"))); // NOI18N
        fronteraBoton.setToolTipText("Cerco Convexo | Frontera");
        fronteraBoton.setBorderPainted(false);
        fronteraBoton.setContentAreaFilled(false);
        fronteraBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fronteraBotonActionPerformed(evt);
            }
        });

        DilatacionLaticesBoton.setBackground(new java.awt.Color(255, 255, 255));
        DilatacionLaticesBoton.setForeground(new java.awt.Color(0, 204, 255));
        DilatacionLaticesBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/lldilatacion.png"))); // NOI18N
        DilatacionLaticesBoton.setToolTipText("Dilatación Latices");
        DilatacionLaticesBoton.setBorderPainted(false);
        DilatacionLaticesBoton.setContentAreaFilled(false);
        DilatacionLaticesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DilatacionLaticesBotonActionPerformed(evt);
            }
        });

        erosionLaticesBoton.setBackground(new java.awt.Color(255, 255, 255));
        erosionLaticesBoton.setForeground(new java.awt.Color(0, 204, 255));
        erosionLaticesBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/llerosion.png"))); // NOI18N
        erosionLaticesBoton.setToolTipText("Erosión Latices");
        erosionLaticesBoton.setBorderPainted(false);
        erosionLaticesBoton.setContentAreaFilled(false);
        erosionLaticesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erosionLaticesBotonActionPerformed(evt);
            }
        });

        clausuraLaticesBoton.setBackground(new java.awt.Color(255, 255, 255));
        clausuraLaticesBoton.setForeground(new java.awt.Color(0, 204, 255));
        clausuraLaticesBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/llcierre.png"))); // NOI18N
        clausuraLaticesBoton.setToolTipText("Clausura Latices");
        clausuraLaticesBoton.setBorderPainted(false);
        clausuraLaticesBoton.setContentAreaFilled(false);
        clausuraLaticesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clausuraLaticesBotonActionPerformed(evt);
            }
        });

        aperturaLaticesBoton.setBackground(new java.awt.Color(255, 255, 255));
        aperturaLaticesBoton.setForeground(new java.awt.Color(0, 204, 255));
        aperturaLaticesBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/llapertura.png"))); // NOI18N
        aperturaLaticesBoton.setToolTipText("Apertura Latices");
        aperturaLaticesBoton.setBorderPainted(false);
        aperturaLaticesBoton.setContentAreaFilled(false);
        aperturaLaticesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aperturaLaticesBotonActionPerformed(evt);
            }
        });

        alisamientoBoton.setBackground(new java.awt.Color(255, 255, 255));
        alisamientoBoton.setForeground(new java.awt.Color(0, 204, 255));
        alisamientoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/alisamiento.png"))); // NOI18N
        alisamientoBoton.setToolTipText("Alisamiento");
        alisamientoBoton.setBorderPainted(false);
        alisamientoBoton.setContentAreaFilled(false);
        alisamientoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alisamientoBotonActionPerformed(evt);
            }
        });

        botHatBoton.setBackground(new java.awt.Color(255, 255, 255));
        botHatBoton.setForeground(new java.awt.Color(0, 204, 255));
        botHatBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/bottomhat.png"))); // NOI18N
        botHatBoton.setToolTipText("Bot Hat");
        botHatBoton.setBorderPainted(false);
        botHatBoton.setContentAreaFilled(false);
        botHatBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botHatBotonActionPerformed(evt);
            }
        });

        topHatBoton.setBackground(new java.awt.Color(255, 255, 255));
        topHatBoton.setForeground(new java.awt.Color(0, 204, 255));
        topHatBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/tophat.png"))); // NOI18N
        topHatBoton.setToolTipText("Top Hat");
        topHatBoton.setBorderPainted(false);
        topHatBoton.setContentAreaFilled(false);
        topHatBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topHatBotonActionPerformed(evt);
            }
        });

        gradienteEBoton.setBackground(new java.awt.Color(255, 255, 255));
        gradienteEBoton.setForeground(new java.awt.Color(0, 204, 255));
        gradienteEBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/gradEro.png"))); // NOI18N
        gradienteEBoton.setToolTipText("Gradiente por Erosión");
        gradienteEBoton.setBorderPainted(false);
        gradienteEBoton.setContentAreaFilled(false);
        gradienteEBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradienteEBotonActionPerformed(evt);
            }
        });

        gradienteDBoton.setBackground(new java.awt.Color(255, 255, 255));
        gradienteDBoton.setForeground(new java.awt.Color(0, 204, 255));
        gradienteDBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/graddila.png"))); // NOI18N
        gradienteDBoton.setToolTipText("Gradiente por Dilatación");
        gradienteDBoton.setBorderPainted(false);
        gradienteDBoton.setContentAreaFilled(false);
        gradienteDBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradienteDBotonActionPerformed(evt);
            }
        });

        gradSimBoton.setBackground(new java.awt.Color(255, 255, 255));
        gradSimBoton.setForeground(new java.awt.Color(0, 204, 255));
        gradSimBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/gardSym.png"))); // NOI18N
        gradSimBoton.setToolTipText("Gradiente Simetrico");
        gradSimBoton.setBorderPainted(false);
        gradSimBoton.setContentAreaFilled(false);
        gradSimBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradSimBotonActionPerformed(evt);
            }
        });

        adelgazamientoBoton.setBackground(new java.awt.Color(255, 255, 255));
        adelgazamientoBoton.setForeground(new java.awt.Color(0, 204, 255));
        adelgazamientoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/adelgazar.png"))); // NOI18N
        adelgazamientoBoton.setToolTipText("Adelgazamiento");
        adelgazamientoBoton.setBorderPainted(false);
        adelgazamientoBoton.setContentAreaFilled(false);
        adelgazamientoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adelgazamientoBotonActionPerformed(evt);
            }
        });

        thomBoton.setBackground(new java.awt.Color(255, 255, 255));
        thomBoton.setForeground(new java.awt.Color(0, 204, 255));
        thomBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/hotormiss.png"))); // NOI18N
        thomBoton.setToolTipText("Transformada Hit or Miss");
        thomBoton.setBorderPainted(false);
        thomBoton.setContentAreaFilled(false);
        thomBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thomBotonActionPerformed(evt);
            }
        });

        esqueletoBoton.setBackground(new java.awt.Color(255, 255, 255));
        esqueletoBoton.setForeground(new java.awt.Color(0, 204, 255));
        esqueletoBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/esqueleto.png"))); // NOI18N
        esqueletoBoton.setToolTipText("Esqueleto Morfológico");
        esqueletoBoton.setBorderPainted(false);
        esqueletoBoton.setContentAreaFilled(false);
        esqueletoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esqueletoBotonActionPerformed(evt);
            }
        });

        creditosBoton.setBackground(new java.awt.Color(255, 255, 255));
        creditosBoton.setForeground(new java.awt.Color(0, 204, 255));
        creditosBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotoshop/Icon/not.png"))); // NOI18N
        creditosBoton.setToolTipText("Créditos");
        creditosBoton.setBorderPainted(false);
        creditosBoton.setContentAreaFilled(false);
        creditosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditosBotonActionPerformed(evt);
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
                            .addComponent(Binarización, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grisesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sumaBotom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(multiBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inversoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(andBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(histogramaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtroPromedioBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(diviBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nanBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(norBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xnorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(opersHistogramaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(difPixBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filtroPromedioPesadoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gaussianoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prewittBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(laplacianoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medianaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(multiUmbralBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dobleUmbralBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addRuidoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pixSepBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(robertsBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sobelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxMinBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(multiUmbLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(creditosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(aperturaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clausuraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thomBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(esqueletoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DilatacionBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(erosionBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fronteraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adelgazamientoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(aperturaLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clausuraLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(topHatBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradienteEBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradSimBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DilatacionLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(erosionLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alisamientoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botHatBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradienteDBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Binarización)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sliderUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(abrirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cerrarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(saveBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(redoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(grisesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inversoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(andBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(xorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(histogramaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filtroPromedioBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filtroPromedioPesadoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gaussianoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prewittBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(laplacianoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(medianaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(multiUmbralBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dobleUmbralBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addRuidoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DilatacionBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(erosionBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fronteraBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sumaBotom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(multiBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(restaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(diviBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nanBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(norBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(xnorBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(opersHistogramaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pixSepBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(robertsBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sobelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(difPixBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(modaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maxMinBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(multiUmbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(aperturaBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clausuraBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thomBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(creditosBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DilatacionLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(erosionLaticesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(alisamientoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botHatBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gradienteDBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adelgazamientoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aperturaLaticesBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clausuraLaticesBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(topHatBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gradienteEBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gradSimBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(esqueletoBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(panelTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
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
              //  System.out.println("Abriendo: " + file.getPath() + "    -    " + file.getName() + " de extension " + formatoImg);       
                if(checaExtensionImg(formatoImg)) //si está admitido
                    {
                        //System.out.println("Si :3");
                        try 
                            {
                                Imagen img = new Imagen(file.getPath(), file.getName(), formatoImg);                                
                                listaImagenes.add(img);                                                                                               
                                JFrame f = new JFrame();                                   
                                JScrollPane scroll = new JScrollPane();
                                scroll.getViewport().add(new Dibujo(img.getArgb(), img.getAncho(), img.getAlto()));                                
                                scroll = umb(scroll, img.getAncho(), img.getAncho());
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
                //System.out.println("Open command cancelled by user.");
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
        //System.out.println("index es " + r);
        int[][][] binario;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
                
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                try 
                    {
                        binario = tra.binarizado(sliderUmbral.getValue(), listaImagenes.get(r));                           
                        listaImagenes.get(r).setModificado(binario);
                     //   System.out.println("repintaremos " + sliderUmbral.getValue());
                        JFrame f = new JFrame();                                   
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(binario, img.getAncho(), img.getAlto()));      
                        scroll = umb(scroll, img.getAncho(), img.getAncho());
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
        //System.out.println("index es " + r);
        if(r >= 0)
            {                                                         
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                JFrame f = new JFrame();                                   
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(img.getArgb(), img.getAncho(), img.getAlto()));                                       
                listaImagenes.get(r).setModificado(listaImagenes.get(r).getArgb());
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);          
                panelTabs.setComponentAt(r, f.getContentPane());
            }
        
        
    }//GEN-LAST:event_redoBotonActionPerformed

    private void grisesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grisesBotonActionPerformed
        int r = panelTabs.getSelectedIndex();
        //System.out.println("index es " + r);
        int[][][] gris;     
        if(r >= 0)
            {                                         
               // System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                try 
                    {
                        gris = tra.grises(listaImagenes.get(r));                                                   
                        JFrame f = new JFrame();                                   
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(gris, img.getAncho(), img.getAlto()));       
                        scroll = umb(scroll, img.getAncho(), img.getAncho());
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

    private void rBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBotonActionPerformed
        // BOTOM ROJO
        
        int r = panelTabs.getSelectedIndex();
        //System.out.println("index es " + r);
        int[][][] canal;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
                //System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                canal = tra.canales(0, listaImagenes.get(r));
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(canal, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                listaImagenes.get(r).setModificado(canal);
                panelTabs.setComponentAt(r, f.getContentPane());

            }
    }//GEN-LAST:event_rBotonActionPerformed

    private void gBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
       // System.out.println("index es " + r);
        int[][][] canal;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
               // System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);
                canal = tra.canales(1, listaImagenes.get(r));
                listaImagenes.get(r).setModificado(canal);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(canal, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }
    }//GEN-LAST:event_gBotonActionPerformed

    private void bBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
      //  System.out.println("index es " + r);
        int[][][] canal;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
             //   System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);                
                canal = tra.canales(2, listaImagenes.get(r));
                listaImagenes.get(r).setModificado(canal);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(canal, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }
    }//GEN-LAST:event_bBotonActionPerformed

    private void sumaBotomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumaBotomActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 0);        
    }//GEN-LAST:event_sumaBotomActionPerformed

    private void multiBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 2);  
    }//GEN-LAST:event_multiBotonActionPerformed

    private void restaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 1);  
    }//GEN-LAST:event_restaBotonActionPerformed

    private void diviBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diviBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 3);  
    }//GEN-LAST:event_diviBotonActionPerformed

    private void inversoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inversoBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
      //  System.out.println("index es " + r);
        int[][][] canal;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
             //   System.out.println("repintaremos " + r);
                Transformaciones tra = new Transformaciones();       
                Imagen img = listaImagenes.get(r);                
                canal = tra.inverso(listaImagenes.get(r));
                listaImagenes.get(r).setModificado(canal);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(canal, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }
    }//GEN-LAST:event_inversoBotonActionPerformed

    private void andBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 4);  
    }//GEN-LAST:event_andBotonActionPerformed

    private void orBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 5);  
    }//GEN-LAST:event_orBotonActionPerformed

    private void xorBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xorBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 6);  
    }//GEN-LAST:event_xorBotonActionPerformed

    private void xnorBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xnorBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 7);  
    }//GEN-LAST:event_xnorBotonActionPerformed

    private void norBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_norBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 8);  
    }//GEN-LAST:event_norBotonActionPerformed

    private void nanBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nanBotonActionPerformed
        // TODO add your handling code here:
        initOperacion(listaImagenes, 9);  
    }//GEN-LAST:event_nanBotonActionPerformed

    private void histogramaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histogramaBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                    
                new Histogram().display(listaImagenes.get(r));
            }
    }//GEN-LAST:event_histogramaBotonActionPerformed

    private void opersHistogramaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opersHistogramaBotonActionPerformed
        // TODO add your handling code here:
        
         
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        boolean cerrar = false;
        if(r >= 0)
            {                                                                                                              
                AjusteDeBrillo A = new AjusteDeBrillo();
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                                         
                Object[] buttons = {"Desplazamiento", "Expansión", "Contracción", "Ecualización"};      
                int result = JOptionPane.showOptionDialog(null, "Elige una operación sobre el histograma", ":3",
                                JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
                int[] max, min;
                img.setPreHistograma(img.getModificado());
                switch(result)                         
                    {
                    case 0:
                        int des = verificaEntero(JOptionPane.showInputDialog("Introduzca el valor del desplazamiento [-255, 255]"));                                                         
                        filtro = A.desplazamientoHist(img.getModificado(), des);
                        break;
                    case 1:
                        max = A.maximoNG(img.getModificado());
                        min = A.minimoNG(img.getModificado());
                        filtro = A.expansion(img.getModificado(), max, min, 255, 0);
                        break;
                    case 2:
                        int Cmax = verificaEntero(JOptionPane.showInputDialog("Introduzca el valor de brillo maximo [0, 255]"));                                                         
                        int Cmin = verificaEntero(JOptionPane.showInputDialog("Introduzca el valor de brillo minimo [0, 255]"));                                                         
                        max = A.maximoNG(img.getModificado());
                        min = A.minimoNG(img.getModificado());
                        
                        filtro = A.contraccion(img.getModificado(), min, max, Cmax, Cmin);
                        break;
                    case 3:
                        filtro = A.ecualizacion(img.getModificado());
                        break;               
                    default:
                        System.out.println("cerrado");
                        cerrar = true; //cerré la seleccion
                        filtro = A.desplazamientoHist(img.getArgb(), 0);
                    }
                if(!cerrar)
                    {
                        T.ajusta(filtro);
                        listaImagenes.get(r).setModificado(filtro);                
                        new dobleHistograma().display(listaImagenes.get(r));                
                        JFrame f = new JFrame();
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                        scroll = umb(scroll, img.getAncho(), img.getAncho());
                        f.add(scroll);
                        panelTabs.setComponentAt(r, f.getContentPane());
                    }               

            }              
        
        
        
    }//GEN-LAST:event_opersHistogramaBotonActionPerformed

    private void filtroPromedioBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroPromedioBotonActionPerformed
        // TODO add your handling code here:
        
        int r = panelTabs.getSelectedIndex();
      //  System.out.println("index es " + r);
        int[][][] filtrado;
     //   binario = binarizacion(100, listaImagenes.get(r));
        if(r >= 0)
            {                            
             //   panelTabs.remove(r);
             //   System.out.println("repintaremos " + r);
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtrado = T.ajusta(F.convolucion(img.getModificado(), Filtros.PROMEDIO, 9, 1));
                listaImagenes.get(r).setModificado(filtrado);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtrado, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }
        
        
    }//GEN-LAST:event_filtroPromedioBotonActionPerformed

    private void filtroPromedioPesadoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroPromedioPesadoBotonActionPerformed
        // TODO add your handling code here:                
        int r = panelTabs.getSelectedIndex();
        int[][][] filtrado;
        if(r >= 0)
            {                            
              
                int N = verificaEntero(JOptionPane.showInputDialog("Introduzca el valor del filtro pesado:"));                                                         
                
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtrado = T.ajusta(F.convolucion(img.getModificado(), Filtros.PROMEDIO, N > 0 ? N + 8: 8, 1));
                listaImagenes.get(r).setModificado(filtrado);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtrado, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }        
    }//GEN-LAST:event_filtroPromedioPesadoBotonActionPerformed

    private void gaussianoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussianoBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int[][][] filtrado;
        if(r >= 0)
            {                                                                                                             
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtrado = T.ajusta(F.convolucion(img.getModificado(), Filtros.GAUSS,16, 1));
                listaImagenes.get(r).setModificado(filtrado);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtrado, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            } 
        
    }//GEN-LAST:event_gaussianoBotonActionPerformed

    private void pixSepBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pixSepBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int[][][] filtroX, filtroXY, filtroY;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtroX = F.convolucion(img.getModificado(), Filtros.SEP_X, 1, 0);
                filtroY = F.convolucion(img.getModificado(), Filtros.SEP_Y, 1, 0);
                filtroXY = T.ajusta(F.magnitud(filtroX, filtroY));//T.ajusta(F.convolucion(filtroX, Filtros.SEP_Y, 1, 0));
                listaImagenes.get(r).setModificado(filtroXY);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtroXY, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            } 
    }//GEN-LAST:event_pixSepBotonActionPerformed

    private void robertsBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_robertsBotonActionPerformed
        // TODO add your handling code here:
                int r = panelTabs.getSelectedIndex();
                int[][][] filtroX, filtroY, filtroXY;
                if(r >= 0)
                    {                                                                                                              
                        Filtros F = new Filtros(); 
                        Transformaciones T = new Transformaciones();
                        Imagen img = listaImagenes.get(r);                
                        filtroX = F.convolucion(img.getModificado(), Filtros.ROBERTS_X, 1, 0);
                        filtroY = F.convolucion(img.getModificado(), Filtros.ROBERTS_Y, 1, 0);
                        filtroXY = T.ajusta(F.magnitud(filtroX, filtroY));//T.ajusta(F.convolucion(filtroX, Filtros.ROBERTS_Y, 1, 0));
                        listaImagenes.get(r).setModificado(filtroXY);
                        JFrame f = new JFrame();
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(filtroXY, img.getAncho(), img.getAlto()));
                        scroll = umb(scroll, img.getAncho(), img.getAncho());
                        f.add(scroll);
                        panelTabs.setComponentAt(r, f.getContentPane());

                    } 
        
    }//GEN-LAST:event_robertsBotonActionPerformed

    private void sobelBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobelBotonActionPerformed
        // TODO add your handling code here:
        
        int r = panelTabs.getSelectedIndex();
        int[][][] filtroX, filtroXY, filtroY;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtroX = F.convolucion(img.getModificado(), Filtros.SOBEL_X, 4, 0);
                filtroY = F.convolucion(img.getModificado(), Filtros.SOBEL_Y, 4, 0);
                filtroXY = T.ajusta(F.magnitud(filtroX, filtroY)); // T.ajusta(F.convolucion(filtroX, Filtros.SOBEL_Y, 1, 0));
                listaImagenes.get(r).setModificado(filtroXY);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtroXY, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            } 
    }//GEN-LAST:event_sobelBotonActionPerformed

    private void difPixBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difPixBotonActionPerformed
        // TODO add your handling code here:
        
        int r = panelTabs.getSelectedIndex();
        int[][][] filtroX, filtroXY, filtroY;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtroX = F.convolucion(img.getModificado(), Filtros.DIF_PIX_X, 1, 0);
                filtroY = F.convolucion(img.getModificado(), Filtros.DIF_PIX_Y, 1, 0);
                filtroXY = T.ajusta(F.magnitud(filtroX, filtroY));// T.ajusta(F.convolucion(filtroX, Filtros.DIF_PIX_Y, 1, 0));
                listaImagenes.get(r).setModificado(filtroXY);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtroXY, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            } 
        
    }//GEN-LAST:event_difPixBotonActionPerformed

    private void prewittBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prewittBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int[][][] filtroX, filtroY, filtroXY;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtroX = F.convolucion(img.getModificado(), Filtros.PREWITT_X, 3, 0);
                filtroY = F.convolucion(img.getModificado(), Filtros.PREWITT_Y, 3, 0);
                filtroXY = T.ajusta(F.magnitud(filtroX, filtroY));//T.ajusta(F.convolucion(filtroX, Filtros.PREWITT_Y, 2, 0));
                listaImagenes.get(r).setModificado(filtroXY);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                scroll.getViewport().add(new Dibujo(filtroXY, img.getAncho(), img.getAlto()));
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }         
        
    }//GEN-LAST:event_prewittBotonActionPerformed

    private void laplacianoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laplacianoBotonActionPerformed
        // TODO add your handling code here:
        
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);         
                
                Object[] buttons = {"Tipo A", "Tipo B", "Tipo C", "Tipo D"};      
                int result = JOptionPane.showOptionDialog(null, "Elige un filtro laplaciano", ":3",
                                JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
                switch(result)                         
                    {
                    case 0:
                        filtro = F.convolucion(img.getModificado(), Filtros.LAPLACIANO_A, 1, 1);
                        break;
                    case 1:
                        filtro = F.convolucion(img.getModificado(), Filtros.LAPLACIANO_B, 1, 1);
                        break;
                    case 2:
                        filtro = F.convolucion(img.getModificado(), Filtros.LAPLACIANO_C, 1, 1);
                        break;
                    case 3:
                        filtro = F.convolucion(img.getModificado(), Filtros.LAPLACIANO_D, 1, 1);
                        break;               
                    default:
                        filtro = img.getModificado();
                    }
                T.ajusta(filtro);
                listaImagenes.get(r).setModificado(filtro);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }              
        
        
    }//GEN-LAST:event_laplacianoBotonActionPerformed

    private void modaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modaBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtro = F.moda(img.getModificado());                
                listaImagenes.get(r).setModificado(filtro);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }   
        
    }//GEN-LAST:event_modaBotonActionPerformed

    private void medianaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medianaBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);                
                filtro = F.mediana(img.getModificado());                
                listaImagenes.get(r).setModificado(filtro);
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }          
    }//GEN-LAST:event_medianaBotonActionPerformed

    private void maxMinBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxMinBotonActionPerformed
        // TODO add your handling code here:
        
        
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        if(r >= 0)
            {                                                                                                              
                Filtros F = new Filtros(); 
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);         
                boolean cerrar = false;
                Object[] options1 = {"Filtro máximo", "Filtro minimo"};      
                int result = JOptionPane.showOptionDialog(null, null, "Elige un filtro",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options1, null);

                if (result == JOptionPane.YES_OPTION) //maximo
                    {
                        filtro = F.maximoMinimo(img.getModificado(), 1);    
                    }
                else //minimo
                    {
                        if(result == JOptionPane.NO_OPTION)
                            {
                                filtro = F.maximoMinimo(img.getModificado(), 0);
                            }   
                        else
                            {
                                cerrar = true;
                                filtro = img.getModificado();
                            }
                    }                            
                if(!cerrar)
                    {
                        listaImagenes.get(r).setModificado(filtro);
                        JFrame f = new JFrame();
                        JScrollPane scroll = new JScrollPane();
                        scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                        scroll = umb(scroll, img.getAncho(), img.getAncho());
                        f.add(scroll);
                        panelTabs.setComponentAt(r, f.getContentPane());

                    }                
            }               
        
        
    }//GEN-LAST:event_maxMinBotonActionPerformed

    private void multiUmbralBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiUmbralBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        int numUmbrales = 0;
        String umbrales = "";
        //System.out.println("index es " + r);
        int[][][] binario = null;
        Transformaciones tra = new Transformaciones();       
        Imagen img = listaImagenes.get(r);        
        
        numUmbrales = verificaEntero(JOptionPane.showInputDialog("Introduzca el número de umbrales"));
        
        if(numUmbrales > 0)
            {
                umbrales = JOptionPane.showInputDialog("Introduzca los umbrales separados por comas");
            }
        if(verificaNumUmbrales(numUmbrales, umbrales) == 1)
        {
            try 
                {
                    binario =tra.multiUmbral(numUmbrales, umbrales, listaImagenes.get(r));
                }
            catch (IOException ex) 
                {
                    Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
                }
            listaImagenes.get(r).setModificado(binario);
            JFrame f = new JFrame();
            JScrollPane scroll = new JScrollPane();
            scroll.getViewport().add(new Dibujo(binario, img.getAncho(), img.getAlto()));
            scroll = umb(scroll, img.getAncho(), img.getAncho());
            f.add(scroll);
            // f.setVisible(true);
            panelTabs.setComponentAt(r, f.getContentPane());
        }
        else
        {
            if(numUmbrales != -2)
                {
                 JOptionPane.showMessageDialog(this, "Mete el número correcto de umbrales");   
                }            
        }

        
    }//GEN-LAST:event_multiUmbralBotonActionPerformed

    private void dobleUmbralBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobleUmbralBotonActionPerformed
        
            int r = panelTabs.getSelectedIndex();
            //System.out.println("index es " + r);
            int[][][] binario = null;
            Transformaciones tra = new Transformaciones();       
            Imagen img = listaImagenes.get(r);    
            img.setUmbral1(umbral1);
            img.setUmbral2(umbral2);
            int[] umbral = new int[2];
            
            umbral[0] = img.getModificado()[img.getUmbral1().x][img.getUmbral1().y][2];
            umbral[1] = img.getModificado()[img.getUmbral2().x][img.getUmbral2().y][2];    
            Arrays.sort(umbral);
            multiUmbLabel.setText("U1: " + umbral[0] + " - U2: " + umbral[1]);
            try 
                {
                    binario =tra.multiUmbralDos(listaImagenes.get(r));
                }
            catch (IOException ex) 
                {
                    Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
                }
            listaImagenes.get(r).setModificado(binario);
            JFrame f = new JFrame();
            JScrollPane scroll = new JScrollPane();
            scroll.getViewport().add(new Dibujo(binario, img.getAncho(), img.getAlto()));
            scroll = umb(scroll, img.getAncho(), img.getAncho());
            f.add(scroll);
            // f.setVisible(true);
            panelTabs.setComponentAt(r, f.getContentPane());   
    }//GEN-LAST:event_dobleUmbralBotonActionPerformed

    private void addRuidoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRuidoBotonActionPerformed
        // TODO add your handling code here:
        
        int r = panelTabs.getSelectedIndex();
        int[][][] filtro;
        if(r >= 0)
            {                                                                                                              
                Transformaciones T = new Transformaciones();
                Imagen img = listaImagenes.get(r);         
                
                Object[] buttons = {"Tipo Sal", "Tipo Pimienta", "Combinado"};      
                int result = JOptionPane.showOptionDialog(null, "Elige el tipo de ruido", ":3",
                                JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
                
                int porcentaje = verificaEntero(JOptionPane.showInputDialog("Introduzca el porcentaje de ruido"));
                if(porcentaje == 0)
                    {
                        result = 10;
                    }
                switch(result)                         
                    {
                    case 0:
                        filtro = T.addRuido(img, porcentaje, 0);
                        break;
                    case 1:
                        filtro = T.addRuido(img, porcentaje, 1);
                        break;
                    case 2:
                        filtro = T.addRuido(img, porcentaje, 2);
                        break;
                    default:
                        filtro = img.getModificado();
                    }
                T.ajusta(filtro);
                listaImagenes.get(r).setArgb(img.getArgb());
                listaImagenes.get(r).setModificado(filtro);                
                JFrame f = new JFrame();
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(filtro, img.getAncho(), img.getAlto()));
                scroll = umb(scroll, img.getAncho(), img.getAncho());
                f.add(scroll);
                panelTabs.setComponentAt(r, f.getContentPane());

            }              
        
    }//GEN-LAST:event_addRuidoBotonActionPerformed

    private void DilatacionBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DilatacionBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 0);                                                          
            }    
    }//GEN-LAST:event_DilatacionBotonActionPerformed

    private void erosionBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erosionBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 1);                                                          
            }    
    }//GEN-LAST:event_erosionBotonActionPerformed

    private void aperturaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aperturaBotonActionPerformed
        // TODO add your handling code here:
                int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 2);                                                          
            }    
    }//GEN-LAST:event_aperturaBotonActionPerformed

    private void clausuraBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clausuraBotonActionPerformed
        // TODO add your handling code here:
                int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 3);                                                          
            }    
    }//GEN-LAST:event_clausuraBotonActionPerformed

    private void fronteraBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fronteraBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 4);                                                          
            }    
    }//GEN-LAST:event_fronteraBotonActionPerformed

    private void DilatacionLaticesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DilatacionLaticesBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 0);                                                          
            }    
    }//GEN-LAST:event_DilatacionLaticesBotonActionPerformed

    private void erosionLaticesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erosionLaticesBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 1);                                                          
            }    
    }//GEN-LAST:event_erosionLaticesBotonActionPerformed

    private void clausuraLaticesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clausuraLaticesBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 3);                                                          
            }    
    }//GEN-LAST:event_clausuraLaticesBotonActionPerformed

    private void aperturaLaticesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aperturaLaticesBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 2);                                                          
            }    
    }//GEN-LAST:event_aperturaLaticesBotonActionPerformed

    private void alisamientoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alisamientoBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 4);                                                          
            }    
    }//GEN-LAST:event_alisamientoBotonActionPerformed

    private void botHatBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botHatBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 7);                                                          
            }    
    }//GEN-LAST:event_botHatBotonActionPerformed

    private void topHatBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topHatBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 5);                                                          
            }    
    }//GEN-LAST:event_topHatBotonActionPerformed

    private void gradienteEBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradienteEBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 8);                                                          
            }    
    }//GEN-LAST:event_gradienteEBotonActionPerformed

    private void gradienteDBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradienteDBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 9);                                                          
            }    
    }//GEN-LAST:event_gradienteDBotonActionPerformed

    private void gradSimBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradSimBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                LL(r, 10);                                                          
            }    
    }//GEN-LAST:event_gradSimBotonActionPerformed

    private void adelgazamientoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adelgazamientoBotonActionPerformed
        // TODO add your handling code here:
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 6);                                                          
            }    
    }//GEN-LAST:event_adelgazamientoBotonActionPerformed

    private void thomBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thomBotonActionPerformed
        try {
            // TODO add your handling code here:
            LuigiLlora.main();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No me salió :c");   
            Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
        int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 5);                                                          
            }    
    }//GEN-LAST:event_thomBotonActionPerformed

    private void esqueletoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esqueletoBotonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            LuigiLlora.main();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No me salió :c");   
            Logger.getLogger(mainVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
         int r = panelTabs.getSelectedIndex();
        if(r >= 0)
            {                                                        
                MM(r, 7);                                                          
            }    
    }//GEN-LAST:event_esqueletoBotonActionPerformed

    private void creditosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditosBotonActionPerformed
        // TODO add your handling code here:
        Creditos.main();
    }//GEN-LAST:event_creditosBotonActionPerformed

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
    private javax.swing.JButton DilatacionBoton;
    private javax.swing.JButton DilatacionLaticesBoton;
    private javax.swing.JButton abrirBoton;
    private javax.swing.JButton addRuidoBoton;
    private javax.swing.JButton adelgazamientoBoton;
    private javax.swing.JButton alisamientoBoton;
    private javax.swing.JButton andBoton;
    private javax.swing.JButton aperturaBoton;
    private javax.swing.JButton aperturaLaticesBoton;
    private javax.swing.JButton bBoton;
    private javax.swing.JButton botHatBoton;
    private javax.swing.JButton cerrarTab;
    private javax.swing.JButton clausuraBoton;
    private javax.swing.JButton clausuraLaticesBoton;
    private javax.swing.JButton creditosBoton;
    private javax.swing.JButton difPixBoton;
    private javax.swing.JButton diviBoton;
    private javax.swing.JButton dobleUmbralBoton;
    private javax.swing.JButton erosionBoton;
    private javax.swing.JButton erosionLaticesBoton;
    private javax.swing.JButton esqueletoBoton;
    private javax.swing.JButton filtroPromedioBoton;
    private javax.swing.JButton filtroPromedioPesadoBoton;
    private javax.swing.JButton fronteraBoton;
    private javax.swing.JButton gBoton;
    private javax.swing.JButton gaussianoBoton;
    private javax.swing.JButton gradSimBoton;
    private javax.swing.JButton gradienteDBoton;
    private javax.swing.JButton gradienteEBoton;
    private javax.swing.JButton grisesBoton;
    private javax.swing.JButton histogramaBoton;
    private javax.swing.JButton inversoBoton;
    private javax.swing.JLabel labelUmbral;
    private javax.swing.JButton laplacianoBoton;
    private javax.swing.JButton maxMinBoton;
    private javax.swing.JButton medianaBoton;
    private javax.swing.JButton modaBoton;
    private javax.swing.JButton multiBoton;
    private javax.swing.JLabel multiUmbLabel;
    private javax.swing.JButton multiUmbralBoton;
    private javax.swing.JButton nanBoton;
    private javax.swing.JButton norBoton;
    private javax.swing.JButton opersHistogramaBoton;
    private javax.swing.JButton orBoton;
    private javax.swing.JTabbedPane panelTabs;
    private javax.swing.JButton pixSepBoton;
    private javax.swing.JButton prewittBoton;
    private javax.swing.JButton rBoton;
    private javax.swing.JButton redoBoton;
    private javax.swing.JButton restaBoton;
    private javax.swing.JButton robertsBoton;
    private javax.swing.JButton saveBoton;
    private javax.swing.JSlider sliderUmbral;
    private javax.swing.JButton sobelBoton;
    private javax.swing.JButton sumaBotom;
    private javax.swing.JButton thomBoton;
    private javax.swing.JButton topHatBoton;
    private javax.swing.JButton xnorBoton;
    private javax.swing.JButton xorBoton;
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

    int[][][] MM(int r, int oper)
        {
            Imagen img = listaImagenes.get(r);
            int[][] ee = {{2,2,2}, {2,2,2}, {2,2,2}};
            int[][][] operado;
            
            JFrame f = new JFrame();
            JPanel p = new JPanel();
            JLabel label = new JLabel("Determine el Elemento de Estructura");
            JButton operar = new JButton("Hacer");          
            operar.setBounds(175, 350, 150, 30);
            label.setBounds(150, 50, 300, 20);
            p.add(label);
            p.setPreferredSize(new Dimension(500,500));
            
            JButton[][] eeBotones = new JButton[3][3];
            
            
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                    {
                        eeBotones[i][j] = new JButton("-");
                        eeBotones[i][j].setName("");
                        eeBotones[i][j].setBackground(Color.gray);
                        eeBotones[i][j].setForeground(Color.gray);
                        eeBotones[i][j].setBounds(175 + (50 * j), 100 + (50 * i), 50, 50);       
                        eeBotones[i][j].addMouseListener(new MouseAdapter()
                            {
                                @Override
                                public void mouseClicked(MouseEvent e){
                                     if (e.getButton() == 3) 
                                        { // if right click
                                           // System.out.println("le picaste al derecho");
                                            int i, j;                                    
                                            JButton x = (JButton) e.getSource();
                                            j = (x.getBounds().x - 175)/50;
                                            i = (x.getBounds().y - 100)/50;        
                                           // System.out.println("le picaste al " + i + " " + j);
                                            for(int a = 0; a < 3; a++)
                                                {
                                                    for(int b = 0; b < 3; b++)
                                                        {
                                                            if(eeBotones[a][b].getName().equals("centro"))
                                                                {
                                                                    int c;
                                                                    Color col;
                                                                    String car = eeBotones[a][b].getText();
                                                                    
                                                                    c = (car.equals("-") ? 2: Integer.parseInt(car));
                                                                    col = (c == 2) ? Color.gray : new Color(c*255,c*255,c*255);
                                                                    
                                                                    eeBotones[a][b].setName("");                                                                    
                                                                    eeBotones[a][b].setBackground(col); 
                                                                    ee[a][b] += 1; //le quitamos sus propiedades de centro
                                                                }
                                                        }
                                                }
                                            //le ponemos propiedaddes de centro al nuevo boton centro
                                            eeBotones[i][j].setName("centro");
                                            eeBotones[i][j].setBackground(Color.red);
                                            eeBotones[i][j].setForeground(Color.white);
                                            ee[i][j] -= 1;
                                            centroDefinido = true;
                                            /*
                                                En el EE vienen los valores 0, 255 y 2
                                                Si son centros se les resta 1 y luego se ajusta
                                            
                                            */
                                            
                                            
                                        }                                                                                 
                                }
                            });
                        
                        eeBotones[i][j].addActionListener(new ActionListener()                 
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) 
                                {
                                    String valor = "";
                                    int i, j;
                                    valor = e.getActionCommand();
                                    JButton x = (JButton) e.getSource();
                                    j = (x.getBounds().x - 175)/50;
                                    i = (x.getBounds().y - 100)/50;                                    
                                    
                                   switch(valor)
                                    {
                                        case "0":
                                            eeBotones[i][j].setText("1");
                                            eeBotones[i][j].setBackground(Color.white);
                                            eeBotones[i][j].setForeground(Color.black);
                                            ee[i][j] = 255;
                                           break;
                                        case "1":
                                            eeBotones[i][j].setText("-");
                                            eeBotones[i][j].setBackground(Color.gray);
                                            eeBotones[i][j].setForeground(Color.gray);
                                            ee[i][j] = 2;
                                           break;
                                        case "-":
                                            eeBotones[i][j].setText("0");
                                            eeBotones[i][j].setBackground(Color.black);
                                            eeBotones[i][j].setForeground(Color.white);
                                            ee[i][j] = 0;
                                            break;
                                        default:                                            
                                    }
                                   centroDefinido = false;
                                }
                            });
                        
                        p.add(eeBotones[i][j]);
                    }                                        
                }

            operar.addActionListener(new ActionListener()                 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        if(centroDefinido)
                            {
                                Morfologia M = new Morfologia();
                                centroEE = getCentro(ee);
                                switch(oper)
                                    {
                                        case 0: //dilatacion
                                            morfologiaOperado = M.dilatacion(img.getModificado(), ee, centroEE[0], centroEE[1]);                                                                                                                                
                                            break;
                                        case 1: //erosion
                                            morfologiaOperado = M.erosion(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            break;
                                        case 2: //apertura
                                            int[][][] tempOperado ;
                                            tempOperado = M.erosion(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            morfologiaOperado = M.dilatacion(tempOperado, ee, centroEE[0], centroEE[1]);
                                            break;        
                                        case 3: //cierre
                                            int[][][] tempCerrado;
                                            tempCerrado = M.dilatacion(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            morfologiaOperado = M.erosion(tempCerrado, ee, centroEE[0], centroEE[1]);
                                            break;          
                                        case 4: //frontera
                                            Transformaciones T = new Transformaciones();
                                            int[][][] tempEro = M.erosion(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgErosionada = new Imagen(); // A☻B
                                            imgErosionada.setAlto(img.getAlto());
                                            imgErosionada.setAncho(img.getAncho());                                                          
                                            imgErosionada.setArgb(tempEro);
                                            imgErosionada.setModificado(tempEro);
                                            
                                            morfologiaOperado = T.resta(img, imgErosionada); //A - (A☻B)
                                        break;         
                                        case 5:
                                            morfologiaOperado = M.hitOrMiss(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                        break;    
                                        case 6://adelgazamiento
                                            int k;
                                            k = verificaEntero(JOptionPane.showInputDialog("Introduzca el número de rotaciones k"));
                                                if(k > 0)
                                                    {                                                
                                                        morfologiaOperado = M.adelgazamiento(img.getModificado(), ee, centroEE[0], centroEE[1], k);
                                                    }                                                                                            
                                        break;    
                                        case 7: //esquelero
                                            int K ; 
                                            int[][][] x;
                                            Imagen huesos = new Imagen();
                                            Transformaciones Tr = new Transformaciones();
                                            K = verificaEntero(JOptionPane.showInputDialog("Introduzca el número de erosiones K"));
                                            Imagen[] Sk = new Imagen[K];
                                                if(K > 0)
                                                    {        
                                                        int[][][] A = img.getModificado(), B = img.getModificado();
                                                        int contador = 0;
                                                        while(contador < K)
                                                            {
                                                                //A (+) kB
                                                                A = M.erosion(A, ee, centroEE[0], centroEE[1]);
                                                                
                                                                //A (-) kB
                                                                int[][][] tempB = (M.erosion(B, ee, centroEE[0], centroEE[1]));
                                                                
                                                                //[A (-) kB) ° B
                                                                B = M.erosion(tempB, ee, centroEE[0], centroEE[1]);
                                                                B = M.dilatacion(B, ee, centroEE[0], centroEE[1]);
                                                                
                                                                //hago la resta para obtener el sk
                                                                Imagen imgD = new Imagen(); // A [+] B
                                                                Imagen imgE = new Imagen(); // A [-] B

                                                                imgD.setAlto(img.getAlto());
                                                                imgD.setAncho(img.getAncho());                                                          
                                                                imgD.setArgb(A);
                                                                imgD.setModificado(A);

                                                                imgE.setAlto(img.getAlto());
                                                                imgE.setAncho(img.getAncho());                                                          
                                                                imgE.setArgb(B);
                                                                imgE.setModificado(B);
                                                                Sk[contador] =  new Imagen();
                                                                Sk[contador].setAncho(img.getAncho());                                                          
                                                                Sk[contador].setAlto(img.getAlto());                                                          
                                                                Sk[contador].setModificado(Tr.resta(imgD, imgE) ); //(A [+] b) - [(A [-] b) ° B]                                                                
                                                                Sk[contador].setArgb(Tr.resta(imgD, imgE) );           
                                                               // System.out.println("sk " + contador + " mide "  + Sk[contador].getModificado().length);
                                                                contador++;
                                                            }
                                                        huesos.setArgb(Sk[0].getModificado());
                                                        huesos.setAncho(img.getAncho());                                                          
                                                        huesos.setAlto(img.getAlto());  
                                                        huesos.setModificado(Sk[0].getModificado());                                                        
                                                        //System.out.println("huesos mide " + huesos.getModificado().length + " y sk" + Sk[1].getModificado().length);
                                                        for(int i = 1; i < K; i++)
                                                            {
                                                                huesos.setModificado(Tr.suma(huesos, Sk[i]));
                                                               // System.out.println("x " + i + " mide " + x.length);
                                                            }
                                                        huesos.setArgb(huesos.getModificado());
                                                        x = huesos.getModificado();
                                                    /*    for(int i = 0; i < img.getAlto(); i++)
                                                            {
                                                                for(int j = 0; j < img.getAncho(); j++)
                                                                    {
                                                                        System.out.println("[" + i  + "," + j + "] " + " - " + x[i][j][1] + " | " + x[i][j][2] + " | " + x[i][j][3] + " | "  ); 
                                                                    }
                                                            }*/
                                                        morfologiaOperado = Tr.ajusta(huesos.getModificado());//M.adelgazamiento(img.getModificado(), ee, centroEE[0], centroEE[1], k);
                                                    }    
                                        break;        
                                        default:    
                                    }
                                listaImagenes.get(r).setModificado(morfologiaOperado);   
                                JFrame f = new JFrame();
                                JScrollPane scroll = new JScrollPane();
                                scroll.getViewport().add(new Dibujo(morfologiaOperado, img.getAncho(), img.getAlto()));
                                scroll = umb(scroll, img.getAncho(), img.getAncho());
                                f.add(scroll);
                                panelTabs.setComponentAt(r, f.getContentPane());
                            }
                        else
                            {
                                JFrame mensaje = new JFrame("Mensajito de algo malo");
                                JOptionPane.showMessageDialog(mensaje, "Define un centro :c");
                            }
                    }
                });
            
            p.add(operar);
            p.setLayout(null);
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
            
            
            return morfologiaOperado;
        }
    
    int[][][] LL(int r, int oper)
        {
            Imagen img = listaImagenes.get(r);
            int[][] ee = {{2,2,2}, {2,2,2}, {2,2,2}};
            int[][][] operado;
            
            JFrame f = new JFrame();
            JPanel p = new JPanel();
            JLabel label = new JLabel("Determine el Elemento de Estructura");
            JButton operar = new JButton("Hacer");          
            operar.setBounds(175, 350, 150, 30);
            label.setBounds(150, 50, 300, 20);
            p.add(label);
            p.setPreferredSize(new Dimension(500,500));
            
            JButton[][] eeBotones = new JButton[3][3];
            
            
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                    {
                        eeBotones[i][j] = new JButton("-");
                        eeBotones[i][j].setName("");
                        eeBotones[i][j].setBackground(Color.gray);
                        eeBotones[i][j].setForeground(Color.gray);
                        eeBotones[i][j].setBounds(175 + (50 * j), 100 + (50 * i), 50, 50);       
                        eeBotones[i][j].addMouseListener(new MouseAdapter()
                            {
                                @Override
                                public void mouseClicked(MouseEvent e){
                                     if (e.getButton() == 3) 
                                        { // if right click
                                           // System.out.println("le picaste al derecho");
                                            int i, j;                                    
                                            JButton x = (JButton) e.getSource();
                                            j = (x.getBounds().x - 175)/50;
                                            i = (x.getBounds().y - 100)/50;        
                                           // System.out.println("le picaste al " + i + " " + j);
                                            for(int a = 0; a < 3; a++)
                                                {
                                                    for(int b = 0; b < 3; b++)
                                                        {
                                                            if(eeBotones[a][b].getName().equals("centro"))
                                                                {
                                                                    int c;
                                                                    Color col;
                                                                    String car = eeBotones[a][b].getText();
                                                                    
                                                                    c = (car.equals("-") ? 2: Integer.parseInt(car));
                                                                    col = (c == 2) ? Color.gray : new Color(c*255,c*255,c*255);
                                                                    
                                                                    eeBotones[a][b].setName("");                                                                    
                                                                    eeBotones[a][b].setBackground(col); 
                                                                    ee[a][b] += 1; //le quitamos sus propiedades de centro
                                                                }
                                                        }
                                                }
                                            //le ponemos propiedaddes de centro al nuevo boton centro
                                            eeBotones[i][j].setName("centro");
                                            eeBotones[i][j].setBackground(Color.red);
                                            eeBotones[i][j].setForeground(Color.white);
                                            ee[i][j] -= 1;
                                            centroDefinido = true;
                                            /*
                                                En el EE vienen los valores 0, 255 y 2
                                                Si son centros se les resta 1 y luego se ajusta
                                            
                                            */
                                            
                                            
                                        }                                                                                 
                                }
                            });
                        
                        eeBotones[i][j].addActionListener(new ActionListener()                 
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) 
                                {
                                    String valor = "";
                                    int i, j;
                                    valor = e.getActionCommand();
                                    JButton x = (JButton) e.getSource();
                                    j = (x.getBounds().x - 175)/50;
                                    i = (x.getBounds().y - 100)/50;                                    
                                    
                                   switch(valor)
                                    {
                                        case "0":
                                            eeBotones[i][j].setText("1");
                                            eeBotones[i][j].setBackground(Color.white);
                                            eeBotones[i][j].setForeground(Color.black);
                                            ee[i][j] = 255;
                                           break;
                                        case "1":
                                            eeBotones[i][j].setText("-");
                                            eeBotones[i][j].setBackground(Color.gray);
                                            eeBotones[i][j].setForeground(Color.gray);
                                            ee[i][j] = 2;
                                           break;
                                        case "-":
                                            eeBotones[i][j].setText("0");
                                            eeBotones[i][j].setBackground(Color.black);
                                            eeBotones[i][j].setForeground(Color.white);
                                            ee[i][j] = 0;
                                            break;
                                        default:                                            
                                    }
                                   centroDefinido = false;
                                }
                            });
                        
                        p.add(eeBotones[i][j]);
                    }                                        
                }

            operar.addActionListener(new ActionListener()                 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        if(centroDefinido)
                            {
                                Transformaciones T = new Transformaciones();
                                MorfologiaLatices M = new MorfologiaLatices();
                                centroEE = getCentro(ee);
                                switch(oper)
                                    {
                                        case 0: //dilatacion
                                            morfologiaOperado = M.dilatacionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);                                                                                                                                
                                            break;
                                        case 1: //erosion
                                            morfologiaOperado = M.erosionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            break;
                                        case 2: //apertura
                                            morfologiaOperado = M.aperturaLL(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            break;        
                                        case 3: //cierre
                                            morfologiaOperado = M.cierreLL(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            break;          
                                        case 4: //Alisamiento                                            
                                            int[][][] abie = M.aperturaLL(img.getModificado(), ee, centroEE[0], centroEE[1]);                                          
                                            morfologiaOperado = M.cierreLL(abie, ee, centroEE[0], centroEE[1]);
                                        break;                                                                                          
                                        case 5: //top hat                                            
                                            int[][][] abierta = M.aperturaLL(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgErosionada = new Imagen(); // A ° B
                                            imgErosionada.setAlto(img.getAlto());
                                            imgErosionada.setAncho(img.getAncho());                                                          
                                            imgErosionada.setArgb(abierta);
                                            imgErosionada.setModificado(abierta);
                                            
                                            morfologiaOperado = T.resta(img, imgErosionada); //A - (A ° B)
                                            break; 
                                        case 7: //bottom hat
                                            int[][][] cerrado = M.cierreLL(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgCerrada = new Imagen(); // A ° B
                                            imgCerrada.setAlto(img.getAlto());
                                            imgCerrada.setAncho(img.getAncho());                                                          
                                            imgCerrada.setArgb(cerrado);
                                            imgCerrada.setModificado(cerrado);
                                            
                                            morfologiaOperado = T.resta(imgCerrada, img); //(A • B) - A
                                            break; 
                                        case 8: //gradiente erosion
                                            int[][][] ero = M.erosionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgero = new Imagen(); // A ° B
                                            imgero.setAlto(img.getAlto());
                                            imgero.setAncho(img.getAncho());                                                          
                                            imgero.setArgb(ero);
                                            imgero.setModificado(ero);
                                            
                                            morfologiaOperado = T.resta(img, imgero); //A - (A [-] b)
                                        break;
                                        case 9: //gardiente dilatacion
                                            int[][][] dilat = M.dilatacionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgDil = new Imagen(); // A ° B
                                            imgDil.setAlto(img.getAlto());
                                            imgDil.setAncho(img.getAncho());                                                          
                                            imgDil.setArgb(dilat);
                                            imgDil.setModificado(dilat);
                                            
                                            morfologiaOperado = T.resta(imgDil, img); //(A [+] b) - A
                                        break;    
                                        case 10: //gradiente simetrico
                                            int[][][] dilatada, erosionada;
                                            
                                            dilatada = M.dilatacionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);
                                            erosionada = M.erosionLL(img.getModificado(), ee, centroEE[0], centroEE[1]);

                                            Imagen imgD = new Imagen(); // A [+] B
                                            Imagen imgE = new Imagen(); // A [-] B
                                            
                                            
                                            imgD.setAlto(img.getAlto());
                                            imgD.setAncho(img.getAncho());                                                          
                                            imgD.setArgb(dilatada);
                                            imgD.setModificado(dilatada);
                                            
                                            imgE.setAlto(img.getAlto());
                                            imgE.setAncho(img.getAncho());                                                          
                                            imgE.setArgb(erosionada);
                                            imgE.setModificado(erosionada);
                                            
                                            morfologiaOperado = T.resta(imgD, imgE); //(A [+] b) - (A [-] b)
                                            
                                        break;
                                        default:    
                                    }
                                listaImagenes.get(r).setModificado(morfologiaOperado);   
                                JFrame f = new JFrame();
                                JScrollPane scroll = new JScrollPane();
                                scroll.getViewport().add(new Dibujo(morfologiaOperado, img.getAncho(), img.getAlto()));
                                scroll = umb(scroll, img.getAncho(), img.getAncho());
                                f.add(scroll);
                                panelTabs.setComponentAt(r, f.getContentPane());
                            }
                        else
                            {
                                JFrame mensaje = new JFrame("Mensajito de algo malo");
                                JOptionPane.showMessageDialog(mensaje, "Define un centro :c");
                            }
                    }
                });
            
            p.add(operar);
            p.setLayout(null);
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
            
            
            return morfologiaOperado;
        }
    
    int[] getCentro(int[][] ee)
        {
            int[] ce = new int[2];
            for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 3; j++)
                        {
                            if(ee[i][j] == -1 || ee[i][j] ==  254|| ee[i][j] == 1)
                                {
                                    ce[0] = i;
                                    ce[1] = j;
                                }
                        }
                }
            return ce;
        }
    
    int initOperacion(ArrayList<Imagen> listaImagenes, int operacion)
        {
            final int numImagenes = listaImagenes.size();
            Imagen[] limiteArray = new Imagen[2];
            JFrame f = new JFrame();
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(600,600));
            JCheckBox invertir = new JCheckBox("Invertir");  
            invertir.setBounds(100,416 , 100, 100);
            JButton operar = new JButton("Hacer Operación");                            
            operar.setBounds(220, 450, 200, 30);
            JLabel texto = new JLabel("Seleccione las imágenes sobre las que desea trabajar");
            texto.setBounds(150, 50, 350, 50);
            
            String[] nomImagenes = new String[numImagenes];        
            JCheckBox[] cajita = new JCheckBox[numImagenes];
            p.add(texto);
            Transformaciones tra = new Transformaciones();

            operar.addActionListener(new ActionListener()                 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        int limite = 0;
                        String oper = "";
                        for(int i = 0; i < numImagenes; i++)
                            {
                                if(cajita[i].isSelected() && limite < 2)
                                    {
                                        limiteArray[limite] = listaImagenes.get(i);
                                        //System.out.println("++" + limiteArray[limite]);
                                        limite++;
                                    }
                            }
                        if(invertir.isSelected())
                            {
                                Imagen L = limiteArray[0];
                                Imagen L2 = limiteArray[1];
                                limiteArray[0] = L2;
                                limiteArray[1] = L;
                            }
                        switch(operacion)
                            {
                                case 0: // +
                                    operacionesAritmeticas = tra.ajusta(tra.suma(limiteArray[0], limiteArray[1]));
                                    oper = " + ";
                                break;
                                case 1: // -
                                    operacionesAritmeticas = tra.ajusta(tra.resta(limiteArray[0], limiteArray[1]));
                                    oper = " - ";
                                break;
                                case 2: // *
                                    operacionesAritmeticas = tra.ajusta(tra.multi(limiteArray[0], limiteArray[1]));
                                    oper = " * ";
                                break;
                                case 3: //divi
                                    operacionesAritmeticas = tra.ajusta(tra.divi(limiteArray[0], limiteArray[1]));
                                    oper = " / ";
                                break;
                                case 4: //and
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 0);
                                    oper = " ^ ";
                                break;
                                case 5: //or
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 1);
                                    oper = " v ";
                                break;
                                case 6: //xor
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 2);
                                    oper = " -V- ";
                                break;
                                case 7: //nand
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 3);
                                    oper = " ¬^ ";
                                break;         
                                case 8: //nor
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 4);
                                    oper = " ¬v ";
                                break;
                                case 9:  //xnor
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], limiteArray[1], 5);
                                    oper = " ¬(-V-) ";
                                break;    
                                case 10: 
                                    Imagen vacia = new Imagen();
                                    vacia.setAlto(1000000);
                                    vacia.setAncho(1000000);                                    
                                    operacionesAritmeticas = tra.logicos(limiteArray[0], vacia, 6);
                                    oper = " negado";
                                break;    
                                default:
                            }
                        
                        Imagen img = new Imagen();
                        img.setArgb(operacionesAritmeticas);
                        img.setModificado(operacionesAritmeticas);
                        img.setAncho(operacionesAritmeticas.length);
                        img.setAlto(operacionesAritmeticas[0].length);
                        img.setExtension(".png");
                        img.setNombreImagen(limiteArray[0].getNombreImagen() + oper + ((operacion == 10)?"":limiteArray[1].getNombreImagen())  ) ;      
                        pinta(img);
                        f.dispose();
                    }
                });

            int k = 0, j = 0;
            for (int i = 0; i < numImagenes; i++) 
                {
                    nomImagenes[i] = listaImagenes.get(i).getNombreImagen();       
                    //System.out.println("["+nomImagenes[i]+"]");
                    cajita[i] = new JCheckBox(nomImagenes[i]);
                    cajita[i].setBounds(50 + k, 100 + (40*j) , 100, 30);
                    if((i+1) % 5 == 0)
                        {
                            k += 120;
                            j = -1;
                        }
                    j++;
                    p.add(cajita[i]);
                }
            p.add(invertir);
            p.add(operar);            
            p.setLayout(null);                        
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
            return 0;
        }
    
        private int pinta(Imagen img)
            {                                                
                listaImagenes.add(img);                                                                                               
                JFrame f = new JFrame();                                   
                JScrollPane scroll = new JScrollPane();
                scroll.getViewport().add(new Dibujo(img.getArgb(), img.getAncho(), img.getAlto()));      
                f.add(scroll);                                                                   
                panelTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                panelTabs.addTab(img.getNombreImagen(),null, f.getContentPane(), ":3");
                return 0;
            }
        
        public int verificaEntero(String string) 
                {
                    if(string == null)
                        {
                            return -2;
                        }
                    try 
                        {
                            return Integer.parseInt(string);
                        }
                    catch (NumberFormatException e) 
                        {
                            JOptionPane.showMessageDialog(null,"Te dije que un numero >:c \n Ahora le pondré 0 :3");
                            return -1;
                        }
                }   
        
        public void setUmbrales(int u1, int u2)
            {
              multiUmbLabel.setText("U1: " + u1 + " - " + "U2: " + u2);
            } 
        
        public JScrollPane umb(JScrollPane j, int ancho, int alto)
            {
                    j.addMouseListener(new MouseAdapter() 
                    {
                        @Override
                        public void mousePressed(MouseEvent e) 
                        {
                            int x = e.getX();
                            int y = e.getY();
                            //System.out.println("Le picaste en " + new Point(e.getX(), e.getY()) );
                            if(x <= ancho && y <= alto)
                                {
                                   // System.out.println("esta en rango :3");                                    
                                    //mainVentana v = mn;
                                    //mn.setUmbrales(x, y);
                                    umbral1 = umbral2;
                                    umbral2 = new Point(x, y);
                                }
                            
                        }
                    });
                    return j;
            }
        
        public int verificaNumUmbrales(int numUmb, String umb)
            {
                int c = 0;
                c = umb.length() - umb.replace(",", "").length();
                System.out.println("C es " + c + " y hay " + numUmb + " umbrales");
                if((c + 1) == numUmb)
                    {
                        return 1;
                    }
                else
                    {
                        return 0;
                    }
            }
}
