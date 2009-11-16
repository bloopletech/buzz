package com.kliky;

class SplashScreenThread extends Thread
{
   private String filename = "";
   private int showTime = 0;

   public SplashScreenThread(String fn, int st)
   {
      filename = fn;
      showTime = st;
   }
   

   public void run()
   {
      SplashScreen splashScreen = new SplashScreen(filename, showTime);
   }
}