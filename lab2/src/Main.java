import java.io.*;
import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    AnimalCollection zoo = new AnimalCollection();
    char choice='0';
    loop1: while (true)
    {
      Scanner sc = new Scanner(System.in);
      System.out.print("1. создать новый список\n2. загрузить из файла\n");
      while (choice < '1' || choice > '2')
      {
        String str = sc.next();
        if (str.length() ==  1)
        {
          choice = str.charAt(0);
        }
      }
      if (choice == '1')
      {
      }
      else
      {
        System.out.println("Введите имя файла\n");
        String filename = sc.next();
        File file = new File(filename);
        try
        {
          FileInputStream FIS = new FileInputStream(file);
          zoo.read(FIS);
        }
        catch (FileNotFoundException ex)
        {
          choice = '0';
          System.out.println("файл " + filename + " не найден");
          continue;
        }
        catch (Exception ex)
        {
          choice = '0';
          System.out.println(ex.getMessage());
          continue;
        }
      }
      while (true)
      {
        choice = '0';
        System.out.println("1. добавить животное\n2. вывести первых 5 животных,\n3. вывести 3 последних животных \n4.выйти");
        while (choice < '1' || choice > '4')
        {
          String str = sc.nextLine();
          if (str.length() ==  1)
          {
            choice = str.charAt(0);
          }
        }
        switch (choice)
        {
        case '1':
        {
          choice = '0';
          Animal animal = null;
          System.out.println("выберите тип: \n1. травоядное\n2. хищник\n3. всеядное");
          while (choice < '1' || choice > '4')
          {
            String str = sc.nextLine();
            if (str.length() ==  1)
            {
              choice = str.charAt(0);
            }
          }
          String string = "";
          System.out.println("введите имя животного");
          while (string.length() == 0) string = sc.nextLine();
          switch (choice)
          {
          case '1':
            animal = new Herbivorous(string);
            break;
          case '2':
            animal = new Predator(string);
            break;
          case '3':
            animal = new Omnivorous(string);
          }
          try
          {
            zoo.put(animal);
          }
          catch (Exception ex)
          {
            System.out.println(ex.getMessage());
          }
        }
        break;
        case '2':
        {
          try
          {
            zoo.print5First(System.out);
          }
          catch (Exception ex)
          {
            System.out.println(ex.getMessage());
          }
        }
        break;
        case '3':
        {
          try
          {
            zoo.print3last(System.out);
          }
          catch (Exception ex)
          {
            System.out.println(ex.getMessage());
          }
        }
        break;
        case '4':
        {
          choice = '0';
          while (choice != 'y' && choice != 'n')
          {
            System.out.print("сохранить каталог в файл?(y/n)\n");
            String str = sc.nextLine();
            if (str.length() ==  1)
            {
              choice = str.charAt(0);
            }
          }
          if (choice == 'y')
          {
            System.out.println("Введите имя файла\n");
            String filename = sc.next();
            File file = new File(filename);
            try
            {
              FileOutputStream FOS = new FileOutputStream(file,false);
              zoo.print(FOS);
            }
            catch (FileNotFoundException ex)
            {
              System.out.println("файл " + filename + " не найден");
              sc.next();
            }
            catch (Exception ex)
            {
              System.out.println(ex.getMessage());
            }
          }
          break loop1;
        }
        }
      }
    }
  }
}
