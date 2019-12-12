package C;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class C implements Runnable
{
  public void run()
  {
    loop1: while (true)
    {
      Catalog catalog;
      char choice = '0';
      Scanner sc = new Scanner(System.in);
      while (choice < '1' || choice > '2')
      {
        System.out.print("1. создать новый каталог\n2. загрузить из файла\n");
        String str = sc.next();
        if (str.length() ==  1)
        {
          choice = str.charAt(0);
        }
      }
      if (choice == '1')
      {
        catalog = new Catalog();
      }
      else
      {
        System.out.println("Введите имя файла\n");
        String filename = sc.next();
        File file = new File(filename);
        try
        {
          FileInputStream FIS = new FileInputStream(file);
          catalog = Catalog.readFromStream(FIS);
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
        System.out.println("1. добавить книгу\n2. заменить книгу по Id,\n3. найти книгу\n4. вывести каталог \n5.выйти");
        while (choice < '1' || choice > '5')
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
            System.out.println("введите id");
            int id = sc.nextInt();
            Catalog.Book book = new Catalog.Book();
            System.out.println("введите название книги");
            while (book.name.length() == 0) book.name = sc.nextLine();
            System.out.println("введите автора книги");
            while (book.autor.length() == 0) book.autor = sc.nextLine();
            System.out.println("введите год издания");
            book.year = sc.nextInt();
            try
            {
              catalog.insert(id, book);
            }
            catch (Exception ex)
            {
              System.out.println(ex.getMessage());
            }
          }
          break;
        case '2':
          {
            System.out.println("введите id");
            int id = sc.nextInt();
            Catalog.Book book = new Catalog.Book();
            System.out.println("введите название книги");
            while (book.name.length() == 0) book.name = sc.nextLine();
            System.out.println("введите автора книги");
            while (book.autor.length() == 0) book.autor = sc.nextLine();
            System.out.println("введите год издания");
            book.year = sc.nextInt();
            try
            {
              catalog.update(id, book);
              System.out.println("книга удачно заменена");
            }
            catch (Exception ex)
            {
              System.out.println(ex.getMessage());
            }
          }
          break;
        case '3':
          {
            choice = '0';
            System.out.println("1. по id\n2. по имени\n3. по автору\n4.по году");
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
                System.out.println("введите id");
                int id = sc.nextInt();
                try
                {
                  Catalog.Book book = catalog.select(id);
                  System.out.println("название: " + book.name + "\nавтор: " + book.autor + "\nгод издания: "
                      + Integer.toString(book.year));
                }
                catch (Exception ex)
                {
                  System.out.println("книга не найдена");
                }
              }
              break;
            case '2':
              {
                System.out.println("введите название");
                String name = "";
                while (name.length() == 0) name = sc.nextLine();
                try
                {
                  Catalog books = catalog.findByName(name);
                  if (catalog.size() < 1)
                  {
                    System.out.println("книг не найдено");
                  }
                  else
                    {
                    catalog.printInStream(System.out);
                  }
                }
                catch (Exception ex)
                {
                  System.out.println(ex.getMessage());
                }

              }
              break;
            case '3':
              {
                System.out.println("введите имя автора");
                String autor = sc.nextLine();
                while (autor.length() == 0)
                {
                  autor = sc.nextLine();
                }
                try
                {
                  Catalog books = catalog.findByAutor(autor);
                  if (catalog.size() < 1)
                  {
                    System.out.println("книг не найдено");
                  }
                  else
                  {
                    catalog.printInStream(System.out);
                  }
                }
                catch (Exception ex)
                {
                  System.out.println(ex.getMessage());
                }
              }
            break;
            case '4':
              {
                System.out.println("введите год");
                int year = sc.nextInt();
                try
                {
                  Catalog books = catalog.findByYear(year);
                  if (catalog.size() < 1)
                  {
                    System.out.println("книг не найдено");
                  }
                  else
                  {
                    System.out.print("Найдено книг: " + Integer.toString(catalog.size()) + "/n");
                    catalog.printInStream(System.out);
                  }
                }
                catch (Exception ex)
                {
                  System.out.println(ex.getMessage());
                }
              }
            }
          }
          break;
        case '4':
          System.out.println("Каталог содержит следующие книги:");
          try
          {
            catalog.printInStream(System.out);
          }
          catch (Exception ex)
          {
            System.out.println("ошибка вывода");
          }
          break;
        case '5':
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
                catalog.printInStream(FOS);
              }
              catch (FileNotFoundException ex)
              {
                choice = '0';
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

