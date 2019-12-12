package C;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class Catalog
{
  public static class Book
  {
    String autor = "";
    String name = "";
    int year;
  }

  private boolean noError;

  TreeMap< Integer, Book > catalog;

  public Catalog()
  {
    catalog = new TreeMap<>();
  }

  public void insert(int id, Book book) throws Exception
  {
    if (!catalog.containsKey(id))
    {
      catalog.put(id, book);
    }
    else
    {
      throw new Exception("Error: book with id " + Integer.toString(id) + " already");
    }
  }

  public Book select(int id) throws Exception
  {
    if (catalog.containsKey(id))
    {
      return catalog.get(id);
    }
    else
    {
      throw new Exception("Error: book with id " + Integer.toString(id) + " not found");
    }
  }

  public void update(int id, Book book) throws Exception
  {
    if (catalog.containsKey(id))
    {
      catalog.replace(id, book);
    }
    else
    {
      throw new Exception("Error: book with id " + Integer.toString(id) + " not found");
    }
  }

  public void delete(int id) throws Exception
  {
    if (catalog.containsKey(Integer.valueOf(id)))
    {
      catalog.remove(Integer.valueOf(id));
    }
    else
    {
      throw new Exception("Error: book with id " + Integer.toString(id) + " not found");
    }
  }

  public Catalog findByName(String name)
  {
    Catalog result = new Catalog();
    if (catalog.isEmpty()) return result;
    catalog.forEach(new BiConsumer<Integer, Book>()
    {
      @Override
      public void accept(Integer integer, Book bookInfo)
      {
        if (bookInfo.name.equals(name))
        {
          try
          {
            result.insert(integer, bookInfo);
          }
          catch (Exception ex)
          {
          }
        }
      }
    });
    return result;
  }

  public Catalog findByAutor(String autor)
  {
    Catalog result = new Catalog();
    if (catalog.isEmpty()) return result;
    catalog.forEach(new BiConsumer<Integer, Book>()
    {
      @Override
      public void accept(Integer integer, Book bookInfo)
      {
        if (bookInfo.autor.equals(autor))
        {
          try
          {
            result.insert(integer, bookInfo);
          }
          catch (Exception ex)
          {
          }
        }
      }
    });
    return result;
  }

  public Catalog findByYear(int year)
  {
    Catalog result = new Catalog();
    if (catalog.isEmpty()) return result;
    catalog.forEach(new BiConsumer<Integer, Book>()
    {
      @Override
      public void accept(Integer integer, Book bookInfo)
      {
        if (bookInfo.year == year)
        {
          try
          {
            result.insert(integer, bookInfo);
          }
          catch (Exception ex)
          {
          }
        }
      }
    });
    return result;
  }

  public void printInStream(OutputStream output) throws Exception
  {
    final OutputStreamWriter stream = new OutputStreamWriter(output);
    noError = true;
    if (catalog.isEmpty())
    {
      stream.write("<EMPTY>\n");
    }
    else
    {
      catalog.forEach(new BiConsumer<Integer, Book>()
      {
        @Override
        public void accept(Integer integer, Book bookInfo)
        {
          try
          {
            stream.write("id: " + integer.toString() + "\n");
            stream.write("name: " + bookInfo.name + "\n");
            stream.write("autor: " + bookInfo.autor + "\n");
            stream.write("year: " + bookInfo.year + "\n\n");
          }
          catch (IOException ex)
          {
            noError = false;
          }
        }
      });
    }
    stream.flush();
    if (!noError)
    {
      throw new Exception("Error: printing failure");
    }
  }
  public int size()
  {
    return catalog.size();
  }

  public static Catalog readFromStream(InputStream input) throws Exception
  {
    Catalog catalog = new Catalog();
    Scanner sc = new Scanner(input);
    boolean noErr = true;
    while (sc.hasNext())
    {
      String curr = sc.nextLine();
      if (curr.length() == 0) continue;
      Integer id = Integer.valueOf(curr.substring(4));
      if (!sc.hasNext())
      {
        noErr = false;
        break;
      }
      Book book = new Book();
      curr = sc.nextLine();
      book.name = curr.substring(6);
      if (!sc.hasNext())
      {
        noErr = false;
        break;
      }
      curr = sc.nextLine();
      book.autor = curr.substring(7);
      if (!sc.hasNext())
      {
        noErr = false;
        break;
      }
      curr = sc.nextLine();
      book.year = Integer.valueOf(curr.substring(6));
      catalog.insert(id, book);
    }
    if (noErr)
    {
      return catalog;
    }
    else
    {
      throw new Exception("входные данные имеют неверный формат");
    }
  }
}
