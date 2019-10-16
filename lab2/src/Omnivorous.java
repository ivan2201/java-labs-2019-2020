public class Omnivorous extends Animal
{
  private Food m_food;
  private String m_name;
  private String m_id;

  Omnivorous(String name)
  {
    m_name = name;
    m_food = new Animal.Food(Food.ALL_FOOD);
    m_id = Integer.toString(Integer.MAX_VALUE - m_food.number, 10)+ m_name;
  }

  Omnivorous(String name, String id, int numFood)
  {
    m_name = name;
    m_food = new Animal.Food(Food.ALL_FOOD);
    m_food.number = numFood;
    m_id = id;
  }

  @Override
  String getID()
  {
    return m_id;
  }

  @Override
  String getName()
  {
    return m_name;
  }

  @Override
  String getTypeOfAnimal()
  {
    return "Omnivorous";
  }

  @Override
  Food getInfoAboutFood()
  {
    return m_food;
  }
}

