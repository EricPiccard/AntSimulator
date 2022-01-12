import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
public class Bala extends Ant
{   /*Bala constructor*/
    public Bala(int ID, int turn, Node[][] Grid)
  {
    Grid[0][0].setBala(Grid[0][0].getBala()+1);  
    IDNum = ID;
    this.turns = turn;  
    xy[0] = 0;
    xy[1] = 0;
     setLifeSpan(this.lifeSpan + this.turns);
  }
    /*check for enemy*/
   public boolean checkSpot(Node[][] Grid)
  {
    if(Grid[this.getX()][this.getY()].getSc()>0 || Grid[this.getX()][this.getY()].getSol()>0 || Grid[this.getX()][this.getY()].getQ()>0 || Grid[this.getX()][this.getY()].getFg()>0)
    {
      return true;
    }
    return false;
  }
   /*check adjacent for ants*/
  public  ArrayList<Node> checkAdjacent(ArrayList<Node> adjacent)
  {
    
    ArrayList<Node> adjacentAnts = new ArrayList<Node>();
    
    for(int i = 0; i < adjacent.size(); i++)
    {
      if(adjacent.get(i).getSc() > 0 || adjacent.get(i).getSol() > 0 || adjacent.get(i).getFg() > 0 || adjacent.get(i).getQ() > 0)
      {
        Node k = new Node(adjacent.get(i).getX(), adjacent.get(i).getY());
        adjacentAnts.add(k);
        
      }
    }
   
    return adjacentAnts;
  }
  /*Attack*/
  public void attack(LinkedList<Ant> antList, Node[][] Grid)
  {
   
    Random rand = new Random();
    int x = rand.nextInt(2);
    if(x == 0)
    {
      for(int i = 0; i < antList.size(); i++)
      {
        
        if((antList.get(i).getX() == this.getX()) && (antList.get(i).getY() == this.getY()))
        {
          antList.get(i).die(antList, i, Grid);
          break;
        }
      }
    }
  }
  /*update*/
   public void turn(Node[][] Grid, LinkedList<Ant> antList, LinkedList<Ant> balaList, int index)
 {

   //Random random = new Random();
   ArrayList<Node> adjacent = checkAdjacent(this.getAdjacentShown(Grid));
   Grid[this.getX()][this.getY()].setBala(Grid[this.getX()][this.getY()].getBala()-1);
   if(checkSpot(Grid))
   {
     this.attack(antList, Grid);
   }
   else if(adjacent.size() > 0)
   {
     this.setX(adjacent.get(0).getX());
     this.setY(adjacent.get(0).getY());
   }
   else
   {
     sMove(Grid);
   }   
    Grid[this.getX()][this.getY()].setBala(Grid[this.getX()][this.getY()].getBala()+1);
    this.setTurns(this.getTurns()+1);
    checkAge(balaList, index, Grid);
   }
  /*die*/
public void die(LinkedList<Ant> balaList, int index, Node[][] Grid)
  {
  balaList.remove(index);
  Grid[this.getX()][this.getY()].setBala(Grid[this.getX()][this.getY()].getBala()-1);
  }
/*check age*/
 public void checkAge(LinkedList<Ant> balaList, int index, Node[][] Grid)
 {
   if(getTurns() > getLifeSpan())
   {
     this.die(balaList, index, Grid);
   }
}
}
