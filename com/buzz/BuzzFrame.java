package com.buzz;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class BuzzFrame extends JFrame
{
   public BuzzFrame()
   {
      BuzzPanel main = new BuzzPanel();
      this.setContentPane(main);

      class CloseListener extends WindowAdapter
      {
         public void windowClosing(WindowEvent event)
         {
            System.exit(0);
         }

         public void windowClosed(WindowEvent event)
         {
            System.exit(0);
         }
      }

      this.addWindowListener(new CloseListener());

      this.setResizable(false);
      this.setLocation(250, 250);
      this.setTitle("buzz");

      this.pack();
      this.show();
   }
}