package com.kliky;

public class ScrollingThread extends Thread
{
   private JScrollingTextField owner = null;

   public ScrollingThread(JScrollingTextField inOwner)
   {
      owner = inOwner;
   }

   public void run()
   {
      while(true)
      {
         while(owner.isMoving() && owner.isVisible() && !owner.hasHitEdge())
         {
            owner.setIndex(owner.getIndex() - 7);
            owner.repaint();

            try
            {
               Thread.sleep(40);
            }
            catch(InterruptedException exception)
            {
            }
         }
      }
   }
}