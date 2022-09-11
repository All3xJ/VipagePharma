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
    JFrame frame = new JFrame("Swing Paint");
    theframe = frame;
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setUndecorated(true);
    frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    Container content = frame.getContentPane();
    content.setLayout(new BorderLayout());
    drawArea = new DrawArea();
 
    content.add(drawArea, BorderLayout.CENTER);
 
    JPanel controls = new JPanel();

    clearBtn = new JButton("Pulisci");
    clearBtn.addActionListener(actionListener);
    firmaBtn = new JButton("Conferma");
    firmaBtn.addActionListener(actionListener);
 
    controls.add(clearBtn);
    controls.add(firmaBtn);

    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    frame.setLocationRelativeTo(null);


    frame.setVisible(true);
 
  }
 
}

class DrawArea extends JComponent {

  private Image image;
  private Graphics2D g2;
  private int currentX, currentY, oldX, oldY;

  public DrawArea() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        oldX = e.getX();
        oldY = e.getY();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();

        if (g2 != null) {
          g2.drawLine(oldX, oldY, currentX, currentY);
          repaint();
          oldX = currentX;
          oldY = currentY;
        }
      }
    });
  }

  public void paintComponent(Graphics g) {
    if (image == null) {
      image = createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      clear();
    }

    g.drawImage(image, 0, 0, null);
  }

  public void clear() {
    g2.setPaint(Color.white);
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
