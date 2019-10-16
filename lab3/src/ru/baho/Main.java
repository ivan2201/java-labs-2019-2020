package ru.baho;

import java.util.Date;
import java.util.Random;

public class Main
{
  public static void main(String[] args)
  {
    MyStringBuilder stringBuilder = new MyStringBuilder();
    stringBuilder.append("begin balue");
    System.out.println("stringbulider has value: "+ stringBuilder.toString());
    stringBuilder.insert(0,"second ");
    System.out.println("stringbulider after insert has value: "+ stringBuilder.toString());
    stringBuilder.undo();
    System.out.println("stringbulider after undo has value: "+ stringBuilder.toString());
    Date date = new Date();
    Random random = new Random(date.getTime());
    for (int i = 0; i < 10; i++)
    {
      stringBuilder.append('A' + random.nextInt(26));
    }
    System.out.println("stringbulider after 10 appends has value: "+ stringBuilder.toString());
    for (int i = 0; i < 10; i++)
    {
      stringBuilder.undo();
      System.out.println("stringbulider after " + Integer.toString(i + 1) + " undo has value: "+ stringBuilder.toString());
    }

  }
}
