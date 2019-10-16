package A;

import java.util.Scanner;

public class A implements Runnable
{

  int[][] matrix;

  @Override
  public void run()
  {
    System.out.print("введите кол-во строк и столбцов\n");
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    if (N <= 0 || M <= 0)
    {
      throw new RuntimeException();
    }
    matrix = new int[N][M];
    for (int i = 0; i < N; i++)
    {
      matrix[i][0] = 1;
    }
    for (int i = 0; i < N; i++)
    {
      for (int j = 0; j < M; j++)
      {
        System.out.print(matrix[i][j]);
        System.out.print(' ');
      }
      System.out.print('\n');
    }
  }
}
