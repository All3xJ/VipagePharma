package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna;

import com.itextpdf.text.DocumentException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PannelloFirma {
 
  JButton clearBtn, firmaBtn;
  DrawArea drawArea;

  public static JFrame theframe;
  ActionListener actionListener = new ActionListener() {
 
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else if (e.getSource() == firmaBtn) {
        try {
          drawArea.firma();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        } catch (DocumentException ex) {
          throw new RuntimeException(ex);
        }
      }
    }
  };
 
  public static void main(String[] args) {
    new PannelloFirma().show();
  }
 
  public void show() {
    // create main frame
    JFrame frame = new JFrame("Swing Paint");
    theframe = frame;
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setUndecorated(true);
    frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    drawArea = new DrawArea();
 
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();

    clearBtn = new JButton("Pulisci");
    clearBtn.addActionListener(actionListener);
    firmaBtn = new JButton("Conferma");
    firmaBtn.addActionListener(actionListener);
 
    // add to panel
    controls.add(clearBtn);
    controls.add(firmaBtn);

    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(400, 400);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    frame.setLocationRelativeTo(null);


    // show the swing paint result
    frame.setVisible(true);
 
    // Now you can try our Swing Paint !!! Enjoy :D
  }
 
}

class DrawArea extends JComponent {

  // Image in which we're going to draw
  private Image image;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, oldX, oldY;

  public DrawArea() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        // save coord x,y when mouse is pressed
        oldX = e.getX();
        oldY = e.getY();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        // coord x,y when drag mouse
        currentX = e.getX();
        currentY = e.getY();

        if (g2 != null) {
          // draw line if g2 context not null
          g2.drawLine(oldX, oldY, currentX, currentY);
          // refresh draw area to repaint
          repaint();
          // store current coords x,y as olds x,y
          oldX = currentX;
          oldY = currentY;
        }
      }
    });
  }

  public void paintComponent(Graphics g) {
    if (image == null) {
      // image to draw null ==> we create
      image = createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // clear draw area
      clear();
    }

    g.drawImage(image, 0, 0, null);
  }

  // now we create exposed methods
  public void clear() {
    g2.setPaint(Color.white);
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    repaint();
  }

  public void firma() throws IOException, DocumentException {
    BufferedImage bi = (BufferedImage) this.image;
    g2.drawImage(bi, null, 0, 0);
    ImageIO.write(bi, "JPEG", new File("/tmp/foo.jpg"));
    PannelloFirma.theframe.dispose();
    FirmaConsegnaControl.firmConsCtrlRef.premutoConferma();
  }



}
