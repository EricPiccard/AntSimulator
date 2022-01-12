public class Node
{
  /*Reference to x and y location*/
  int[] xy = new int[2];
  /*Reference to if node is shown*/
  boolean shown;
  /*Refence to scout count*/
  public int sc;
  /*Reference to Bala count*/
  public int bala;
  /*Reference to forager count*/
  public int fg;
  /*Reference to soldier count*/
  public int sol;
  /*Reference to Queen count, probably should be boolean*/
  public int q;
  //----------
  /*Reference to food count*/
  public int food;
  /*Reference to pheromone count*/
  public int pherm;
  //-----------
  /*Node Constructor*/
  public Node(int x, int y)
  {
    sc = 0;
    bala = 0;
    fg = 0;
    sol = 0;
    q = 0;
    xy[0] = x;
    xy[1] = y;
    shown = false;
    food = 0;
    pherm = 0;
  }
  /*get pheromones*/
  public int getPherm()
  {
    return pherm;
  }
  /*set pheromones*/
  public void setPherm(int value)
  {
    pherm = value;
  }
  /*get X*/
  public int getX()
  {
   return xy[0];
  }
  /*get Y*/
  public int getY()
  {
   return xy[1];
  }
  /*get shown*/
  public boolean getShown()
  {
    return shown;
  }
  /*set shown*/
   public void setShown(boolean value)
   {
     shown = value;
   }
   /*set scout count*/
   public void setSc(int count)
   {
     sc = count;
   }
   /*get scout count*/
   public int getSc()
   {
     return sc;
   }
   /*set forager count*/
      public void setFg(int count)
   {
     fg = count;
   }
      /*get forager count*/
   public int getFg()
   {
     return fg;
   }
   /*set bala count*/
    public void setBala(int count)
   {
     bala = count;
   }
    /*get bala count*/
   public int getBala()
   {
     return bala;
   }
   /*set soldier count*/
      public void setSol(int count)
   {
     sol = count;
   }
      /*get bala count*/
   public int getSol()
   {
     return sol;
   }
   /*set Queen shown*/
      public void setQ(int count)
   {
     q = count;
   }
      /*get Queen shown*/
   public int getQ()
   {
     return q;
   }
   /*get food*/
   public int getFood()
   {
     return food;
   }
   /*set food*/
   public void setFood(int value)
   {
     food = value;
   }
}