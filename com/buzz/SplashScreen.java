package com.kliky;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class SplashScreen extends JFrame
{
   private KlikyPanel owner = null;
   private JLabel label = null;
   private ImageIcon image = null;

   public SplashScreen(KlikyPanel inOwner, Image inImage)
   {
      this(inOwner, inImage, 4500);
   }

   public SplashScreen(KlikyPanel inOwner, Image inImage, int inShowTime)
   {
      owner = inOwner;
      image = new ImageIcon(inImage);
      label = new JLabel(image);

      JPanel mainPane = (JPanel)this.getContentPane();

      mainPane.setLayout(new GridLayout(1, 1));
      mainPane.add(label);
      


      Dimension bounds = Toolkit.getDefaultToolkit().getScreenSize();

      this.setLocation((bounds.width / 2) - ((int)image.getIconWidth() / 2),
       (bounds.height / 2) - ((int)image.getIconHeight() / 2));





      this.setSize(image.getIconWidth(), image.getIconHeight());
      this.setResizable(false);
      this.setUndecorated(true);
      this.setTitle("");
      this.setIconImage((new ImageIcon("images/null.gif")).getImage());

      class StopListener extends MouseAdapter
      {
         public void mouseReleased(MouseEvent event)
         {
            hide();
            dispose();
            owner.focusBrowser();
         }
      }
      this.addMouseListener(new StopListener());

      pack();
      show();

      splash(inShowTime);
   }

   private void splash(int showTime)
   {
      try
      {
         Thread.sleep(showTime);
      }
      catch(InterruptedException exception)
      {
      }

      if(isVisible())
      {
         owner.focusBrowser();
         hide();
         dispose();
      }
   }
}