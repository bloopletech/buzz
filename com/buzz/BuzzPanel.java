package com.buzz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.sound.sampled.*;

public class BuzzPanel extends JPanel
{
   private boolean login = false;
   private boolean wait = false;
   private boolean go = false;

   private long start = 0L;
   private Random random = new Random();
   private javax.swing.Timer timer = null;

   private BuzzPanel thisFrame = this;

   private JButton button = new JButton("Login");
   private JHTextField score = new JHTextField(430);

   private javax.swing.Timer changeText = null;

   private Icon defaultIcon = new ImageIcon();
   private Icon waitIcon = new ImageIcon();
   private Icon goIcon = new ImageIcon();

   private JPopupMenu menu = new JPopupMenu();
   private int points = 0;
   private int largestPoints = 0;

   private int topScore = 0;
   private String topScorer = "";

   private boolean internetScoring = false;

   private Clip line = null;
   private Clip line3 = null;

   private ClassLoader loader = null;
   private String uname = "";

   public BuzzPanel()
   {
      loader = this.getClass().getClassLoader();

      try
      {
         AudioFileFormat format = AudioSystem.getAudioFileFormat(loader.getResource("com/buzz/click.wav"));

         AudioInputStream input = new AudioInputStream((loader.getResource("com/buzz/click.wav")).openStream(), format.getFormat(), format.getFrameLength());

         DataLine.Info info = new DataLine.Info(Clip.class, input.getFormat());
         if(AudioSystem.isLineSupported(info))
         {
            line = (Clip)AudioSystem.getLine(info);
            line.open(input);
         }
         else
         {
            line = null;
         }

         format = AudioSystem.getAudioFileFormat(loader.getResource("com/buzz/highscore.wav"));

         input = new AudioInputStream((loader.getResource("com/buzz/highscore.wav")).openStream(), format.getFormat(), format.getFrameLength());

         info = new DataLine.Info(Clip.class, input.getFormat());
         if(AudioSystem.isLineSupported(info))
         {
            line3 = (Clip)AudioSystem.getLine(info);
            line3.open(input);
         }
         else
         {
            line3 = null;
         }
      }
      catch(Exception exception)
      {
         line = null;
         line3 = null;
      }

      class EnableListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            wait = false;

            start = event.getWhen();

            button.setText("Click me!");
            button.setIcon(goIcon);
            score.setBackground2(Color.green);
            timer.stop();
         }
      }

      class ChangeTextListener implements ActionListener
      {
         private String first = "";

         public ChangeTextListener(String inFirst)
         {
            first = inFirst;
         }

         public void actionPerformed(ActionEvent event)
         {
            if(first.length() > 0)
            {
               score.setText(first);
            }

            changeText.stop();
         }
      } 

      defaultIcon = new ImageIcon(loader.getResource("com/buzz/default.png"));
      waitIcon = new ImageIcon(loader.getResource("com/buzz/wait.png"));
      goIcon = new ImageIcon(loader.getResource("com/buzz/go.png"));

      class ClickListener extends MouseAdapter
      {
         public void mouseReleased(MouseEvent event)
         {
            if(button.isEnabled() && (event.getButton() == MouseEvent.BUTTON1))
            {
               if(!login)
               {
                  try
                  {
                     uname = JOptionPane.showInputDialog(thisFrame, "Please enter your username and enter it into the textbox below:\n(if you don't have a username already, just make one up!)", "buzz - login user", JOptionPane.QUESTION_MESSAGE);
                     if(uname == null) throw new Exception();
                  }
                  catch(Exception exception2)
                  {
                     JOptionPane.showMessageDialog(thisFrame, "You couldn't be logged in. Please try again later.\nbuzz will continue anyway, but internet scoring won't work.", "buzz - Error", JOptionPane.ERROR_MESSAGE);
                  }

                  login = true;

                  button.setText("Start");

                  getTopDetails();
                  score.setText(getHook() + (largestPoints > 0 ? " Your high score is " + largestPoints + "." : ""));
               }
               else if(wait)
               {
                  wait = false;
                  go = false;

                  timer.stop();

                  if(line != null)
                  {
                     try
                     {
                        line.stop();
                        line.setMicrosecondPosition(0L);
                        line.start();
                     }
                     catch(Exception exception)
                     {
                        line = null;
                     }
                  }

                  score.setText("Too early! You got <b>0</b> points.");
                  button.setText("Start");
                  button.setIcon(defaultIcon);
                  score.setBackground2(Color.white);
                  //score.setBackground2(UIManager.get());
               }
               else if(go)
               {
                  go = false;

                  int rTime = (int)(event.getWhen() - start);
                  points = (int)Math.pow(1.01, (1000L - rTime));

                  if(changeText != null) changeText.stop();

                  String record = "";
                  if(topScore > 0) record = (largestPoints > 0 ? " /" : "That's") + " <b>" + Math.round((points / (double)topScore) * 100) + "%</b> of the world record.";

                  String line2 = "";
                  if(largestPoints > 0) line2 = "That's <b>" + Math.round((points / (double)largestPoints) * 100) + "%</b> of your "  + (points > largestPoints ? "old " : "") + "high score" + (topScore > 0 ? "" : ".");

                  if((1000L - rTime) >= 0) score.setText((points > largestPoints ? "<b>High Score!</b> " : "") + /*(points > topScore ? "<b><i>World Record!</i></b> " : "") +*/ "Reaction time is <b>" + rTime + "</b>ms. You got <b>" + points + "</b> points!" + ((largestPoints > 0) || (topScore > 0) ? " (Wait...)" : ""));
                  else score.setText("Too late! Reaction time is <b>1000+</b>ms. You got <b>0</b> points.");

                  changeText = new javax.swing.Timer(2500, new ChangeTextListener(line2 + record));
                  changeText.start();

                  button.setText("Start");
                  button.setIcon(defaultIcon);
                  score.setBackground2(Color.white);

                  if(line != null)
                  {
                     try
                     {
                        line.stop();
                        line.setMicrosecondPosition(0L);
                        line.start();
                     }
                     catch(Exception exception)
                     {
                        line = null;
                     }
                  }

                  if(points > topScore) topScore = points;
                  if(points > largestPoints)
                  {
                     largestPoints = points;

                     if(line3 != null)
                     {
                        try
                        {
                           line3.stop();
                           line3.setMicrosecondPosition(0L);
                           line3.start();
                        }
                        catch(Exception exception)
                        {
                           line3 = null;
                        }
                     }

                     if(internetScoring)
                     {
                        AsynchronousURL submit = new AsynchronousURL(thisFrame, "http://www.bloople.net/buzz/link.php?mode=scores&username=" + uname + "&score=" + rTime);
                     }
                  }

                  points = 0;
               }
               else
               {
                  button.setText("Wait...");
                  button.setIcon(waitIcon);
                  score.setText("");
                  score.setBackground2(Color.red);

                  if(changeText != null) changeText.stop();

                  timer = new javax.swing.Timer(random.nextInt(5000), new EnableListener());
                  timer.start();

                  wait = true;
                  go = true;
               }
            }

            if(event.getButton() == MouseEvent.BUTTON3)
            {
               menu.show(event.getComponent(), event.getX(), event.getY());
            }
         }
      }

      button.setIcon(defaultIcon);
      button.setDefaultCapable(false);
      button.addMouseListener(new ClickListener());

      Dimension size = button.getPreferredSize();
      button.setPreferredSize(new Dimension(140, size.height));

      this.setLayout(new BorderLayout());

      this.add(button, BorderLayout.WEST);
      this.add(score, BorderLayout.EAST);

      Dimension temp = this.getPreferredSize();
      this.setSize(temp.width, temp.height);

      score.setText("Click Login to begin.");

      JMenuItem item = new JMenuItem("high scores");
      if(!internetScoring) item.setEnabled(false);

      class HighScoresListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            AsynchronousURL url = new AsynchronousURL(thisFrame, "http://www.bloople.net/buzz/link.php?mode=gets");
         }
      }

      item.addActionListener(new HighScoresListener());

      menu.add(item);
      //System.out.println(UIManager.getColor("textBackground"));
   }

   private void getTopDetails()
   {
      ArrayList list = new ArrayList();
      try
      {
         URL details = new URL("http://www.bloople.net/buzz/link.php?mode=info&username=" + uname);
         BufferedReader in = new BufferedReader(new InputStreamReader(details.openStream()));

         String currentLine = in.readLine();
         while(currentLine != null)
         {
            list.add(currentLine);
            currentLine = in.readLine();
         }
         in.close();

         largestPoints = Integer.parseInt((String)list.get(0));
         topScore = Integer.parseInt((String)list.get(1));
         topScorer = (String)list.get(2);

         if(list.size() > 3)
         {
            double version = Double.parseDouble((String)list.get(3));
            if(version > 0.2)
            {
               JOptionPane.showMessageDialog(this, "There's a new version of buzz out, and you'll have to upgrade to play buzz again.\nThis is to ensure fair scoring for everybody.\nTo upgrade buzz, go to http://www.bloople.net/buzz/ in ai web browser.", "buzz - New version", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
            }
         }
      }
      catch(Exception exception)
      {
      }

      if(!topScorer.equals("")) internetScoring = true;
   }

   private String getHook()
   {
      Random random = new Random();

      String[] hooks = {
       "That button just wants to be clicked <i>so badly</i>.",
       "Flex those fingers and get ready!",
       "Let your hand get <i>intimate</i> with your mouse.",
       "Are you faster than your mouse?",
       "How fast is your mouse finger?",
       "Show up your friends and family with a top score!",
       "How quick can you click?",
       "Find out your finger power!",
       "Beat " + topScorer + " and get your 15 min of fame.",
       "Can you top " + topScorer + "'s score of " + topScore + "?" };

      String hook = "";
      if(internetScoring) hook = hooks[random.nextInt(hooks.length - 1)];
      else hook = hooks[random.nextInt(hooks.length - 3)];

      return hook;
   }

   protected void doneUrl(URL url, String input, boolean sucessful)
   {
      if(input.equals("902")) JOptionPane.showMessageDialog(this, "You couldn't be added to the list of buzz users. Please try again later.\nbuzz will continue anyway, but internet scoring won't work.", "buzz - Error", JOptionPane.ERROR_MESSAGE);
      else if(input.equals("200"));
      else
      {
         String[][] values = new String[100][3];
         int i = 0;
         StringTokenizer tkz = new StringTokenizer(input, "\\");

         while(tkz.hasMoreTokens())
         {
            StringTokenizer tok = new StringTokenizer(tkz.nextToken(), ",");

            if(tok.countTokens() > 1)
            {
               values[i++] = new String[] {tok.nextToken(), tok.nextToken(), tok.nextToken()};
            }
         }

         values[i] = null;

         BuzzScoresFrame frame = new BuzzScoresFrame(values);
      }
   }
}