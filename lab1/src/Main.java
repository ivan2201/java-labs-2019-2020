import A.A;
import B.B;
import C.C;

public class Main {
    public static void main(String[] args)
    {
      try
      {
        if (args.length == 1)
        {
          switch (args[0])
          {
          case "A":
            A a = new A();
            a.run();
            break;
          case "B":
            B b = new B();
            b.run();
            break;
          case "C":
            C c = new C();
            c.run();
          }
        }
      }
      catch (Exception ex)
      {
        System.out.println("Произошла ошибка:");
        System.out.println(ex.getMessage());
      }
    }
}
