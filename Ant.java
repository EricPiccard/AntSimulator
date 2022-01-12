import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;


/*A simple ant constructor that is extended to the rest of the ants for simplicity*/

public class Ant 
{
/*Reference to how many turns an ant will live*/  
 int lifeSpan;
/*Reference to either the current or to used to find the birthday of the ant*/
 int turns;
/*Reference to the ID number of each ant*/
 int IDNum;
 /*Reference to both x and y locations of ant*/
 public int[] xy = new int[2];
 /*Reference to Random number generator*/
 public static Random random = new Random();
/*Standard Constructor for ants, used originally at the begining of project*/
 public Ant()
 {
  lifeSpan = 3650; //in turns, equal to 1 year
  xy[0] = 13;
  xy[1] = 13;
  turns = 0;
 }
 /*Standard Constructor for ants, used originally at the begining of project*/
 public Ant(int x)
 {
   IDNum = x;
  lifeSpan = 3650; //in turns, equal to 1 year
  xy[0] = 13;
  xy[1] = 13;
  turns = 0;
 }

/*Sets lifespan for ants*/
 public void setLifeSpan(int span)
 {
  lifeSpan = span;
 }
 /*Sets x*/
 public void setX(int x)
 {
   xy[0] = x;
 }
 /*Sets y*/
 public void setY(int y)
 {
   xy[1] =  y;
 }
 /*Sets ID*/
 public void setIDNum(int ID)
 {
  IDNum = ID;
 }
 /*Sets Turns*/
 public void setTurns(int turn)
 {
  turns = turn;
 }
 /*Gets lifespan*/
  public int getLifeSpan()
  {
   return lifeSpan;
  } 
  /*gets x*/
  public int getX()
  {
   return xy[0];
  }
  /*gets y*/
  public int getY()
  {
   return xy[1];
  }
  /*gets ID*/
  public int getIDNum()
  {
   return IDNum;
  }
  /*gets turns*/
  public int getTurns()
  {
   return turns;
  }
    //---------
    /*Stealth Move*/
    public void sMove(Node[][] Grid)
  {
    int[] rando = getRandomXY(getAdjacent(Grid));
    xy[0] = rando[0];
    xy[1] = rando[1];
  }
    /*Regular move*/
   public void rMove(Node[][] Grid)
  {
    int[] rando = getRandomXY(getAdjacentShown(Grid));
    xy[0] = rando[0];
    xy[1] = rando[1];
  }
  /*Returns random x y location from adjacent*/
  public int[] getRandomXY(ArrayList<Node> adjacent)
  {
    
    int[] randXY = new int[2];
    int rando =  random.nextInt(adjacent.size());
    randXY[0] = adjacent.get(rando).getX();
    randXY[1] = adjacent.get(rando).getY();
    return randXY;
  }
  /*Find and return AdjacentNodes*/
 public ArrayList<Node> getAdjacent(Node[][] Grid)
 {
   ArrayList<Node> adjacent = new ArrayList<Node>();
   // holder for x value
   int horz;
   //holder for y value
   int vert;
   for (int i = -1; i <= 1; ++i) 
     {
       for (int j = -1; j <= 1; ++j) 
       {
         horz = xy[0]+i;
         vert =  xy[1]+j;
         //block unavaible positions: same spot or out of bounds
         if(horz <= 26 && horz >=0 && vert <= 26 && vert >=0 && (horz != xy[0] || vert != xy[1]))
         {
         Node k = Grid[horz][vert];
         adjacent.add(k);
         }
       }
     }   
     return adjacent;
   }
 /*gets Adjacent Shown*/
 public ArrayList<Node> getAdjacentShown(Node[][] Grid)
 {
   ArrayList<Node> adjacent = new ArrayList<Node>();
   /*test*/  
   int horz;
   int vert;
   for (int i = -1; i <= 1; ++i) 
     {
       for (int j = -1; j <= 1; ++j) 
       {
         horz = xy[0]+i;
         vert =  xy[1]+j;
         //block unavaible positions: same spot or out of bounds
         if(horz <= 26 && horz >=0 && vert <= 26 && vert >=0 && (horz != xy[0] || vert != xy[1]) && Grid[horz][vert].getShown())
         {
         Node k = Grid[horz][vert];
         adjacent.add(k);
         }
       }
     }   
     return adjacent;
   }
 /*void consructors*/
public void turn(Node[][] Grid, LinkedList<Ant> antList, LinkedList<Ant> balaList, int index)
  {
  
}
 public void hatchScout(Node[][] Grid, LinkedList<Ant> antList)
  {

   }
   public void hatchSoldier(Node[][] Grid, LinkedList<Ant> antList)
  {

   }
      public void hatchForager(Node[][] Grid, LinkedList<Ant> antList)
  {

   }
        public void hatchBala(Node[][] Grid, LinkedList<Ant> antList)
  {

   }
        public void die(LinkedList<Ant> antList, int index, Node[][] Grid)
  {
        }
 }
 


