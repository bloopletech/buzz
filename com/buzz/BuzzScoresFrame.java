package com.buzz;

import java.awt.*;
import javax.swing.*;

public class BuzzScoresFrame extends JFrame
{
   public BuzzScoresFrame(String[][] scores)
   {
      BuzzValues values = new BuzzValues(scores);

      JTable table = new JTable(values);
      
      JScrollPane sp = new JScrollPane(table);

      JPanel main = (JPanel)this.getContentPane();
      main.setLayout(new GridLayout(1, 1));
      main.add(sp);

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      this.setSize(400, 400);
      main.setPreferredSize(new Dimension(400, 400));

      this.setTitle("buzz - high scores");
      this.setLocation(250, 200);

      this.pack();
      this.show();
   }
}