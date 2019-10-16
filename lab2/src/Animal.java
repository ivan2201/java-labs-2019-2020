import java.util.Random;

abstract class Animal
{
  static class Food
  {
    Food(int type)
    {
      foodType = type;
      number = type * (new Random().nextInt(100000));
    }

    static final int VEGETABLE_FOOD = 0x02;
    static final int MEAT_FOOD = 0x01;
    static final int ALL_FOOD = VEGETABLE_FOOD | MEAT_FOOD;
    int number = 0;
    int foodType = 0;
  }
  abstract String getID();
  abstract String getName();
  abstract String getTypeOfAnimal();
  abstract Food getInfoAboutFood();
}
