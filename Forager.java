import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Forager extends Ant
{
  boolean rtnMode;
  LinkedList<Node> history = new LinkedList<Node>();
  /*Constructor*/
  public Forager(int ID, int turn, Node[][] Grid)
  {
    Grid[13][13].setFg(Grid[13][13].getFg()+1);
    IDNum = ID;
    this.turns = turn;
    rtnMode = false;
    Node k = new Node(this.getX(), this.getY());
    history.add(k);
    setLifeSpan(this.lifeSpan + this.turns);
    
  }
  /*die*/
  public void die(LinkedList<Ant> antList, int index, Node[][] Grid)
  {
   antList.remove(index);
   Grid[this.getX()][this.getY()].setFg(Grid[this.getX()][this.getY()].getFg()-1);
   if(rtnMode)dropFood(Grid);
   
  }
  /*check age of ant*/
 public void checkAge(LinkedList<Ant> antList, int index, Node[][] Grid)
 {
   if(getTurns() > getLifeSpan())
   {
     this.die(antList, index, Grid);
   }
 }
 /*update*/
 public void turn(Node[][] Grid, LinkedList<Ant> antList, LinkedList<Ant> balaList, int index)
 {
  
   Grid[this.getX()][this.getY()].setFg(Grid[this.getX()][this.getY()].getFg()-1);
   
    Random rand = new Random();
   /*RTN mode on*/
   if(rtnMode)
   {
     //move
    if(Grid[this.getX()][this.getY()].getPherm() < 1000 && this.getX() != 13 && this.getY() != 13)
     {
     dropPherm(Grid);
     }
     this.setX(history.getLast().getX());
     this.setY(history.getLast().getY());
     history.removeLast();
     
     if(this.getX() == 13 && this.getY() == 13)
     {
       dropFood(Grid);
       rtnMode = false;
       
       history.add(new Node(this.getX(), this.getY()));
     }
   }/*forage mode on*/
   else
   {
      history.add(new Node(this.getX(), this.getY()));
      ArrayList<Node> adjacent = checkAdjacent(this.getAdjacentShown(Grid));
   
     if(!adjacent.isEmpty())
     {
     int i = rand.nextInt(adjacent.size());
     
     this.setX(adjacent.get(i).getX());
     this.setY(adjacent.get(i).getY());
     
    // System.out.println("")
     rtnMode = checkSpot(Grid);
     }
     else
     {
       rMove(Grid);
       rtnMode = checkSpot(Grid);
     }
   }
    Grid[this.getX()][this.getY()].setFg(Grid[this.getX()][this.getY()].getFg()+1);
    this.setTurns(this.getTurns()+1);
    checkAge(antList, index, Grid);
 }
 /*drop pheromones for each square*/
 public void dropPherm(Node[][] Grid)
 {
   Grid[this.getX()][this.getY()].setPherm(Grid[this.getX()][this.getY()].getPherm()+10);
 }
 /*drop food for either death or colony*/
 public void dropFood(Node[][] Grid)
 {
   Grid[this.getX()][this.getY()].setFood(Grid[this.getX()][this.getY()].getFood()+1);
 }
 /*check location for food*/
   public boolean checkSpot(Node[][] Grid)
  {
    if(Grid[this.getX()][this.getY()].getFood()>0 && (this.getX() != 13 && this.getX() != 13))
    {
      Grid[this.getX()][this.getY()].setFood(Grid[this.getX()][this.getY()].getFood()-1);
      return true;
    }
    return false;
  }
   
   /*check for pheromones*/
  public  ArrayList<Node> checkAdjacent(ArrayList<Node> adjacent)
  {
    ArrayList<Node> adjacentPherm = new ArrayList<Node>();
    for(int i = 0; i < adjacent.size(); i++)
    {
      if(adjacent.get(i).getX() != history.get(history.size()-2).getX() && adjacent.get(i).getY() != history.get(history.size()-2).getY())
      {
        if(adjacentPherm.isEmpty())
        {
        Node k = new Node(adjacent.get(i).getX(), adjacent.get(i).getY());
        adjacentPherm.add(k);
        }
        else if(adjacentPherm.get(adjacentPherm.size()-1).getPherm() == adjacent.get(i).getPherm())
        {
          Node k = new Node(adjacent.get(i).getX(), adjacent.get(i).getY());
        adjacentPherm.add(k);
        }
       else if(adjacentPherm.get(adjacentPherm.size()-1).getPherm() < adjacent.get(i).getPherm()) 
       {
         adjacentPherm.clear();
         Node k = new Node(adjacent.get(i).getX(), adjacent.get(i).getY());
        adjacentPherm.add(k);
       }
        }
      }
    return adjacentPherm;
  }

}