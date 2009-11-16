package com.buzz;

import javax.swing.*;

public class ReenableThread extends Thread
{
   JButton owner = null;
   public ReenableThread(JButton inOwner)
   {
      owner = inOwner;
   }

   public void run()
   {
      owner.setEnabled(true);
   }
}