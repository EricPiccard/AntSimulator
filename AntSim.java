import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AntSim implements SimulationEventListener
{
  /*Refence to timers*/
 Timer timer = new Timer();
TimerTask task = new TimerTask()
{
  public void run()
  {
    updateTurn();
  }
};
public void start()
{
  timer.scheduleAtFixedRate(task, 1000, 100);
}

  /*Reference to Random*/
  Random random = new Random();
  /*Reference to Colony View*/
  public ColonyView colonyView = new ColonyView(27, 27);
  /*Reference to Colony Node View*/
  public ColonyNodeView[][] colonyNodeView = new ColonyNodeView[27][27];
  /*Reference to Ant GUI*/
  public AntSimGUI gui = new AntSimGUI();
  /*Reference to Turns*/
  int turn;
  /*Reference to day*/
  int day;
  /*Reference to List of ants*/
 LinkedList<Ant> antList = new LinkedList<Ant>();
 /*Reference to List of balas*/
 LinkedList<Ant> balaList = new LinkedList<Ant>();
 /*Reference to nodes/map*/
 Node[][] grid = new Node[27][27];
/*Standard Constructor for ant sim*/
 public AntSim() 
 {
   turn =0;
   day = 0;
   gui.initGUI(colonyView);
   gui.addSimulationEventListener(this);
  
 }
/*Sets up intial nodes and food*/
 public void initialState()
 {
  /* Sets up nodes*/
  for (int i = 0; i <= 26 ; ++i) 
     {
       for (int j = 0; j <= 26; ++j) 
       {
         grid[i][j] = new Node(i, j);
         
         colonyNodeView[i][j] = new ColonyNodeView();
   
         colonyNodeView[i][j].setID(i + ", " + j);
    
         colonyView.addColonyNodeView(colonyNodeView[i][j], i, j);
         if(random.nextInt(4) == 0)
         {
           grid[i][j].setFood(random.nextInt(501)+500);
         }
       }    
  }
     /* Sets up colony codes*/
     for (int i = -1; i <= 1; ++i) 
     {
       for (int j = -1; j <= 1; ++j) 
       {
         grid[i + 13][j + 13].setShown(true);
         grid[i + 13][j + 13].setFood(0);
       }
     }
     grid[13][13].setFood(1000);
     updateGUI();
  }
 /*Sets up initial colony ants*/
 public void initialAnts()
 {
   
   antList.add(new Queen(grid));
   colonyNodeView[13][13].setQueen(true);
   colonyNodeView[13][13].showQueenIcon();
   for (int i = 0; i < 10; i++)
    {
     antList.get(0).hatchSoldier(grid, antList);
    }  
   for (int i = 0; i < 50; i++)
    {
     antList.get(0).hatchForager(grid, antList);
    }  
   for (int i = 0; i < 4; i++)
    {
     antList.get(0).hatchScout(grid, antList);
    }
   // used for testing 
   /*for (int i = 0; i < 4; i++)
    {
     antList.get(0).hatchBala(grid, balaList);
    }*/
   updateGUI();
 }
 /*Updates GUI*/
   public void updateGUI()
   { /*This isnt need really since the program will end when the queen dies, used for testing*/
    /*
     if (grid[13][13].getQ()>0)
     {
       colonyNodeView[13][13].setQueen(true);
       colonyNodeView[13][13].showQueenIcon();
     }
     else
     {
       colonyNodeView[13][13].setQueen(false);
       colonyNodeView[13][13].hideQueenIcon();
     }*/
       //update turn and day
     day = turn/10;
     gui.setTime("Day " + day + ", Turn " + turn);
     for (int i = 0; i < 27 ; i++)
     {
       for (int j = 0; j < 27; j++)
       {
     //udpates shown ~ could change, list for updated shown?
         if (grid[i][j].getShown())
         {
           colonyNodeView[i][j].showNode();
         }
         //updating ant counts / show icons
         colonyNodeView[i][j].setForagerCount(grid[i][j].getFg());
         colonyNodeView[i][j].setScoutCount(grid[i][j].getSc());
         colonyNodeView[i][j].setSoldierCount(grid[i][j].getSol());
         colonyNodeView[i][j].setBalaCount(grid[i][j].getBala());
         if (grid[i][j].getFg() > 0)
         {
           colonyNodeView[i][j].showForagerIcon();
         }
         else
         {
           colonyNodeView[i][j].hideForagerIcon();
         }
         if (grid[i][j].getSc() > 0)
         {
           colonyNodeView[i][j].showScoutIcon();
         }
         else
         {
           colonyNodeView[i][j].hideScoutIcon();
         }
         if (grid[i][j].getSol() > 0)
         {
           colonyNodeView[i][j].showSoldierIcon();
         }
         else
         {
           colonyNodeView[i][j].hideSoldierIcon();
         }
         if (grid[i][j].getBala() > 0)
         {
           colonyNodeView[i][j].showBalaIcon();
         }
         else
         {
           colonyNodeView[i][j].hideBalaIcon();
         }
         //updating food/pheromone counts
         colonyNodeView[i][j].setFoodAmount(grid[i][j].getFood());
         colonyNodeView[i][j].setPheromoneLevel(grid[i][j].getPherm());
       }
     }
     
   }
   /*Updates all turns for ants*/
     public void updateTurn()
     {
       if(random.nextInt(100) < 3)
       {
         antList.get(0).hatchBala(grid, antList);
       }
       
       /*ant turns*/
             for (int i = balaList.size() - 1; i >= 0; i--)
     {
         
    if(i < balaList.size())
    {
       balaList.get(i).turn(grid, antList, balaList, i);
       }
       }
       for (int i = antList.size() - 1 ; i >= 0; i--)
     {
         
         if(i < antList.size())
         {
       antList.get(i).turn(grid, antList, balaList, i);
         }
     }
 
     
       
       if (turn%10 == 0)
   {
         for (int i = 0; i < 27; i++)
         {
           for (int j = 0; j < 27; j++)
           {
             grid[i][j].setPherm(grid[i][j].getPherm()/2);
           }
         }
       }
       turn++;
       updateGUI();
      // System.out.println(turn);
     }


     /*Event System*/
     public void simulationEventOccurred(SimulationEvent simEvent)
     {
       
            if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) 
            {
              initialState();
              initialAnts();

            updateGUI();
            }
            if (simEvent.getEventType() == SimulationEvent.RUN_EVENT) 
            {
              this.start(); 
            }
            
            if (simEvent.getEventType() == SimulationEvent.STEP_EVENT) 
            {
              updateTurn();
            }
            if (simEvent.getEventType()== SimulationEvent.QUEEN_TEST_EVENT)
            {
              initialState();
              antList.add(new Queen(grid));
              updateGUI();
            }
             if (simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT)
             {
               initialState();
               antList.add(new Scout(0, 0, grid));
               
               updateGUI();
             }
             if (simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT)
             {
               initialState();
               antList.add(new Soldier(0, 0, grid));
               balaList.add(new Bala(1, 0, grid));
               updateGUI();
             }
             if (simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT)
             {
               initialState();
               antList.add(new Forager(0, 0, grid));
               
               grid[13][13].setShown(true);
               grid[14][14].setShown(true);
               grid[14][14].setPherm(20);
               grid[15][15].setShown(true);
               grid[15][15].setPherm(30);
               grid[16][16].setShown(true);
               grid[17][17].setShown(true);
               grid[17][17].setFood(1000);
               updateGUI();
             }
             
             
     } 
}
     
     
 

     
   
   

 

  

 
 
 /*Dont forget that thing*/



