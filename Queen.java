import java.util.LinkedList;
import java.util.Random;

public class Queen extends Ant
{
  /*id for queen*/
  final int id = 0;
  /*assigning id to ants*/
  int assignedID; 
  /*Constructor*/
  public Queen(Node[][] Grid)
  {
    Grid[13][13].setQ(Grid[13][13].getQ()+1);
    assignedID = 1;
     setLifeSpan(73000 + this.turns);
  }
  /*update*/
  public void turn(Node[][] Grid, LinkedList<Ant> antList, LinkedList<Ant> balaList, int index)
  {
    
    Grid[13][13].getQ();
    if(!eat(Grid))
    {
      die(antList, index, Grid);
    }
    if(turns%10 == 0)
    {
      hatch(Grid, antList);
    }
     //ending of turn
   
    this.setTurns(this.getTurns()+1);
    checkAge(antList, index, Grid);
  }
  /*hatching ants*/
  public void hatch(Node[][] Grid, LinkedList<Ant> antList)
  {
    Random random = new Random();
    int rValue = random.nextInt(10); // move random to ant
    if(rValue <= 2)
    {
      antList.add(new Soldier(assignedID, this.getTurns(), Grid));
    }
     else if(rValue <= 5)
    {
      antList.add(new Scout(assignedID, this.getTurns(), Grid));
    }
    else
    {
      antList.add(new Forager(assignedID, this.getTurns(), Grid));
    }
    //update id count
    assignedID++;
  }
  
  /*these are used for calling from the cant: used for intial ants*/
   public void hatchScout(Node[][] Grid, LinkedList<Ant> antList)
  {
     antList.add(new Scout(assignedID, turns, Grid));
     assignedID++;
   }
   public void hatchSoldier(Node[][] Grid, LinkedList<Ant> antList)
  {
     antList.add(new Soldier(assignedID, turns, Grid));
     assignedID++;
   }
      public void hatchForager(Node[][] Grid, LinkedList<Ant> antList)
  {
     antList.add(new Forager(assignedID, turns, Grid));
     assignedID++;
   }
        public void hatchBala(Node[][] Grid, LinkedList<Ant> balaList)
  {
     balaList.add(new Bala(assignedID, turns, Grid));
     assignedID++;
   }
     
     /*Eat*/
  public boolean eat(Node[][] Grid)
  {
    
    if(Grid[this.getX()][this.getY()].getFood() != 0)
    {
     
      Grid[this.getX()][this.getY()].setFood(Grid[this.getX()][this.getY()].getFood()-1);
      return true;
    }
    else
    {
      return false;
    }
  }
  /*check age*/
   public void checkAge(LinkedList<Ant> antList, int index, Node[][] Grid)
 {
     
   if(getTurns() > getLifeSpan())
   {
     this.die(antList, index, Grid);
   }
 }
   /*die*/
public void die(LinkedList<Ant> antList, int index, Node[][] Grid)
  {
   antList.remove(0);
   Grid[this.getX()][this.getY()].setQ(Grid[this.getX()][this.getY()].getQ()-1);
  System.exit(0);
  }


}