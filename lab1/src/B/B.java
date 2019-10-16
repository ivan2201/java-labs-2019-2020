package B;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;
import java.util.Date;

public class B implements Runnable
{
  public void run()
  {
    Scanner sc = new Scanner(System.in);
    String str = sc.next();
    String[] strings = new String[str.length() / 3];
    char[] strs = new char[3];
    Random random = new Random((new Date()).getTime());
    for (int i = 0; i < strings.length; i++)
    {
      strs[0] = str.charAt(3 * i);
      strs[1] = str.charAt(3 * i + 1);
      strs[2] = str.charAt(3 * i + 2);
      int rand = Math.abs(random.nextInt() % 56);
      int j = 0;
      for (j = 0; j < rand || ((strs[2] == ((char) ('A' + j))) || strs[0] == ((char) ('A' + j))); j++)
      {
        if (j==26) j=31;
      }
      strs[1] = ((char) ('A' + j));
      strings[i] = new String(strs);
    }
    Arrays.sort(strings, new Comparator<String>()
    {
      @Override
      public int compare(String o1, String o2)
      {
        int i;
        for (i = 0; i < o1.length() && i < o2.length(); i++)
        {
          if (o1.charAt(i) < o2.charAt(i)) return -1;
          else if (o1.charAt(i) > o2.charAt(i)) return 1;
        }
        if (o1.length() == o2.length())
        {
          return 0;
        }
        else if (o1.length() < o2.length())
          return -1;
        else return 1;
      }
    });
    for (int i = 0; i < strings.length; i++)
    {
      System.out.print(strings[i]);
      System.out.print('\n');
    }
  }
}
