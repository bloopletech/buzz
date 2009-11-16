package com.buzz;

import java.io.*;
import java.net.*;
public class AsynchronousURL extends Thread
{
   private BuzzPanel owner = null;
   private String url = null;

   public AsynchronousURL(BuzzPanel inOwner, String inUrl)
   {
      owner = inOwner;
      url = inUrl;

      this.start();
   }

   public void run()
   {
      String output = "";
      URL url1 = null;
      try
      {
         url1 = new URL(url);

         BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));
         String currentLine = in.readLine();
         while(currentLine != null)
         {
            output = output + currentLine;
            currentLine = in.readLine();
         }
         in.close();

         owner.doneUrl(url1, output, true);
      }
      catch(Exception exception)
      {
         owner.doneUrl(url1, output, false);
      }
   }
}