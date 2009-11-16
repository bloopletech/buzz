package com.buzz;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.geom.*;

public class JHTextField extends JPanel
{
   private JEditorPane pane = new JEditorPane();
   private JScrollPane sp = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
   private String text = null;

   private int right = 0;

   public static final String PREFIX = "<html>\n<head>\n<title>-</title>\n<style>\nbody { margin: 0px; padding: 0px; }\npre { font-family: Arial, sans-serif; padding: 0px; margin: 0px; vertical-align: middle; }\np { margin: 0px; }\n</style>\n</head>\n<body><pre nowrap>";
   public static final String SUFFIX = "<a name=\"endofpage\"></a></pre>\n</body>\n</html>";

   public JHTextField(int inWidth)
   {
      super();

      this.setPreferredSize(new Dimension(inWidth, this.getPreferredSize().height));
      pane.setContentType("text/html");
      pane.setEditable(false);
      HTMLEditorKit kit = (HTMLEditorKit)pane.getEditorKit();

      pane.setBackground(Color.white);
      pane.setMargin(new Insets(2, 2, 2, 2));

      this.setLayout(new GridLayout(1, 1));
      this.add(sp);
   }

   public void setText(String data)
   {
      text = data;
      pane.setText(PREFIX + data + SUFFIX);
      //pane.scrollToReference("#endofpage");
   }

   public String getText()
   {
      return text;
   }

   public void setBackground2(Color colour)
   {
      pane.setBackground(colour);
   }

   private int max(int a, int b)
   {
      return (a > b ? a : b);
   }
}