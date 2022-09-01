package com.vipagepharma.corriere.gestioneConsegne.firmaConsegna.drawFirma;

import com.itextpdf.text.DocumentException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class SwingPaint {
 
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
    new SwingPaint().show();
  }
 
  public void show() {
    // create main frame
    JFrame frame = new JFrame("Swing Paint");
    theframe = frame;
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setUndecorated(true);
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
    firmaBtn = new JButton("Firma");
    firmaBtn.addActionListener(actionListener);
 
    // add to panel
    controls.add(firmaBtn);
    controls.add(clearBtn);
 
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(600, 600);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    frame.setLocationRelativeTo(null);


    // show the swing paint result
    frame.setVisible(true);
 
    // Now you can try our Swing Paint !!! Enjoy :D
  }
 
}
