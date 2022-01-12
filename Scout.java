import java.util.LinkedList;
public class Scout extends Ant
{
  /*constructor*/
  public Scout(int ID, int turn, Node[][] Grid)
  {
    Grid[13][13].setSc(Grid[13][13].getSc()+1);
    IDNum = ID;
    this.turns = turn; 
     setLifeSpan(this.lifeSpan + this.turns);
  }
  /*move*/
  public void move(Node[][] Grid)
  {
    Grid[this.getX()][this.getY()].setSc(Grid[this.getX()][this.getY()].getSc()-1);
    sMove(Grid);    

    spotlight(Grid);
    
    Grid[this.getX()][this.getY()].setSc(Grid[this.getX()][this.getY()].getSc()+1);
    
  }
  /*update*/
  public void turn(Node[][] Grid, LinkedList<Ant> antList, LinkedList<Ant> balaList, int index)
  {

    move(Grid);
    this.setTurns(this.getTurns()+1);
    checkAge(antList, index, Grid);
  }

/*every location the ant steps on is shown*/
public void spotlight(Node[][] Grid)
{
  Grid[this.getX()][this.getY()].setShown(true);
}
/*die*/
public void die(LinkedList<Ant> antList, int index, Node[][] Grid)
  {
  
   antList.remove(index);
  // decrment Sc value
   Grid[this.getX()][this.getY()].setSc(Grid[this.getX()][this.getY()].getSc()-1);
  }
/*check age*/
 public void checkAge(LinkedList<Ant> antList, int index, Node[][] Grid)
 {
   if(getTurns() > getLifeSpan())
   {
     this.die(antList, index, Grid);
     System.out.println("bleh");
   }
 }
}
