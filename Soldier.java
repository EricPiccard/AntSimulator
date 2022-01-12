import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
public class Soldier extends Ant
{
  /*constructor*/
  public Soldier(int ID, int turn, Node[][] Grid)
  {
    Grid[13][13].setSol(Grid[13][13].getSol()+1);
    IDNum = ID;
    this.turns = turn;  
    setLifeSpan(this.lifeSpan + this.turns);
  }
  /*check for enemy*/
  public boolean checkSpot(Node[][] Grid)
  {
    if(Grid[this.getX()][this.getY()].getBala()>0)
    {
      return true;
    }
    return false;
  }
  /*check adjacent node for enemies*/
  public  ArrayList<Node> checkAdjacent(ArrayList<Node> adjacent)
  {
    ArrayList<Node> adjacentBalas = new ArrayList<Node>();
    for(int i = 0; i < adjacent.size(); i++)
    {
      if(adjacent.get(i).getBala() > 0 )
      {
        Node k = new Node(adjacent.get(i).getX(), adjacent.get(i).getY());
        adjacentBalas.add(k);
        
      }
    }
    return adjacentBalas;
  }
  /*attack*/
  public void attack(LinkedList<Ant> balaList, Node[][] Grid)
  {
    
    Random ranSoldier = new Random();
    int x = ranSoldier.nextInt(2);
    if(x == 0)
    {
      for(int i = 0; i < balaList.size(); i++)
      {
        if(balaList.get(i).getX() == this.getX() && balaList.get(i).getY() == this.getY())
        {
          
          balaList.get(i).die(balaList, i, Grid);
          break;
        }
      }
    }
  }
  /*die*/
public void die(LinkedList<Ant> antList, int index, Node[][] Grid)
  {
   antList.remove(index);
  // decrment Sol value
   Grid[this.getX()][this.getY()].setSol(Grid[this.getX()][this.getY()].getSol()-1);
  }
/*check age*/
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
   Grid[this.getX()][this.getY()].setSol(Grid[this.getX()][this.getY()].getSol()-1);
   //Random random = new Random();
   ArrayList<Node> adjacent = checkAdjacent(this.getAdjacentShown(Grid));
   
   if(checkSpot(Grid))
   {
     
     this.attack(balaList, Grid);
   }
   
   else if(adjacent.size() > 0)
   {
     this.setX(adjacent.get(0).getX());
     this.setY(adjacent.get(0).getY());
     //---------- decrement.

   }
   else
   {
     rMove(Grid);
   }
       
    Grid[this.getX()][this.getY()].setSol(Grid[this.getX()][this.getY()].getSol()+1);
   this.setTurns(this.getTurns()+1);
    checkAge(antList, index, Grid);
}
}