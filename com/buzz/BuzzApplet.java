package com.buzz;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class BuzzApplet extends JApplet
{
   public BuzzApplet()
   {
      BuzzPanel main = new BuzzPanel();
      this.setContentPane(main);

      this.show();
   }
}