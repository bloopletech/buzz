package com.buzz;

import javax.swing.table.AbstractTableModel;

public class BuzzValues extends AbstractTableModel
{
   private String[][] scores = null;

   public BuzzValues(String[][] inScores)
   {
      scores = inScores;
   }

   public int getRowCount()
   {
      for(int i = 0; i < scores.length; i++)
      {
         if(scores[i] == null) return i;
      }
      return scores.length;
   }

   public int getColumnCount()
   {
      return 3;
   }

   public String getColumnName(int col)
   {
      switch(col)
      {
         case 0: return "Username";
         case 1: return "Score";
         case 2: return "Date/Time of Score";
         default: return null;
      }
   }

   public Object getValueAt(int row, int col)
   {
      return scores[row][col];
   }

   
   
   
}