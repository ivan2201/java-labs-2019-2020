import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalCollection
{
  private ArrayList< Animal > zoo;

  public static class MyException extends Exception
  {
    public MyException(String str)
    {
      super(str);
    }
  }
  public AnimalCollection()
  {
    zoo = new ArrayList< Animal >();
  }

  public void put(Animal animal)
  {
    if (animal == null)
      throw new NullPointerException();
    int i = 0;
    while (i < zoo.size() && zoo.get(i).getID().compareToIgnoreCase(animal.getID()) < 0)
    {
      i++;
    }
    if(i < zoo.size())
    {
      zoo.add(i, animal);
    }
    else
    {
      zoo.add(animal);
    }
  }

  public Animal get(int index)
  {
    return zoo.get(index);
  }

  private void print(OutputStream stream, List<Animal> list)
  {
    OutputStreamWriter OSW = new OutputStreamWriter(stream);
    for (Animal animal : list)
    {
      try
      {
        OSW.write(animal.getID() + "\n" + animal.getName() + "\n" + animal.getTypeOfAnimal() + "\n" +
                Integer.toString(animal.getInfoAboutFood().number) + "\n\n");
        OSW.flush();
      }
      catch(IOException ex)
      {
      }
    }
  }
  void print(OutputStream outputStream)
  {
    print(outputStream, zoo);
  }

  void print5First(OutputStream outputStream) throws MyException
  {
    if (zoo.size() > 4)
    {
      print(outputStream, zoo.subList(0, 5));
    }
    else
    {
      throw new MyException("ошибка, меньше 5 животных");
    }
  }

  void print3last(OutputStream outputStream) throws MyException
  {
    int size = zoo.size();
    if (size > 2)
    {
      print(outputStream, zoo.subList(size - 3, size));
    }
    else
    {
      throw new MyException("ошибка, меньше 3 животных");
    }
  }

  void read(InputStream stream) throws MyException, NumberFormatException
  {
    Scanner sc = new Scanner(stream);
    String str[] = new String[3];
    while (sc.hasNext())
    {
      str[0] = sc.nextLine();
      if (str[0].equals("")) continue;
      if (!sc.hasNext()) throw new MyException("input data have incorrect format");
      str[1] = sc.nextLine();
      if (!sc.hasNext()) throw new MyException("input data have incorrect format");
      str[2] = sc.nextLine();
      if (!sc.hasNext()) throw new MyException("input data have incorrect format");
      Animal animal = null;
      switch (str[2].charAt(0))
      {
      case 'H':
        {
          animal = new Herbivorous(str[1], str[0], sc.nextInt());
          break;
        }
      case 'O':
        {
          animal = new Omnivorous(str[1], str[0], sc.nextInt());
          break;
        }
      case 'P':
        {
          animal = new Predator(str[1], str[0], sc.nextInt());
        }
      }
      put(animal);
    }
  }
}
