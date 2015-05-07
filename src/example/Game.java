package example;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by CandyFace on 01/04/15.
 *
 * SimpleSlickGame has been splitted into Main and Game
 * use this class for future implementations
 */

public class Game extends BasicGameState {

    private StateBasedGame game;

    Player playerObject = new Player();
    Asteroids[] asteroids = new Asteroids[10];
    public Asteroids asteroid;
	public Projectile projectile;
    private int gameId;

    private int pointCount; 
    
    

    /**
     * @param gameId
     * defines gamestate
     */
    public Game(int gameId) {
        this.gameId = gameId;

    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        
        pointCount = 0;
        
        for(int i = 0; i < asteroids.length; i++){
			asteroids[i] = new Asteroids(2f, i);
        }
        playerObject.init(); //Call init method from Player class
        playerObject.input = gc.getInput(); //Init input class

    	projectile = new Projectile();
		projectile.input = gc.getInput(); //Init input class
        
    }


    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        //time += delta;

        //System.out.println(time);
        playerObject.update(playerObject.playerSprite);
        projectile.update(playerObject.getPosition(), playerObject.getRotation());
        if(!Menu.paused) {
            playerObject.update(); // Call update method from Player class
    
            
            
            
  
            for(int i = 0; i < asteroids.length; i++){//collision detection and moving asteroids
                asteroids[i].update(asteroids[i].asteroidSprite);
                asteroids[i].update();
                
         
                //player collision
                if (asteroids[i].getCollisionBox(asteroids[i].asteroidSprite,10,10,-20,-20).intersects(playerObject.getCollisionBox(playerObject.playerSprite,5,5,-25,-25)))
                {
            //    System.out.println("COLLISION");
                }
            //projectile collision, link through projectiles, get projectile list size...
           
             
            //ArrayList<Integer> removeProjectileList = new ArrayList<Integer>();
                
                
                ProjectileObj[] projectileObjList = projectile.getProjectileObjList();
                int numProjectiles = projectile.projectileCount();
                for(int j = 0;j< numProjectiles;j++){
                	
                //	System.out.println("projectiles in linked list : "+ projectile.projectileCount());
                  // System.out.println("projectiles in array : "+ projectileObjList.length);
                   
                    if(asteroids[i].getCollisionBox(asteroids[i].asteroidSprite,10,10,-20,-20).intersects(projectileObjList[j].getCollisionBox(projectileObjList[j].projectileSprite,5,5,-25,-25))){
                    	pointCount++;                	
                    	System.out.println("You hit an ASTEROID!!!!! point : "+pointCount);
//making list of projectiles to remove
                	//removeProjectileList.add(0, j);
                    	
                    	if(numProjectiles == 1){//case of one projectile
                    	//	System.out.println("assteroid");
                    		
                    		projectile.firstObj = null;//kill that projectile
                    		
                    	}
                    	
                    	/*
                    	else if(projectileObjList[j].getNext() != null && projectileObjList[j].getPrev() == null)//projectile is first in list
                    	{
                    		System.out.println("assteroid!!");
                    		projectileObjList[j].getNext().setPrev(null);
                    		
                    	}
                    	
                  else if(projectileObjList[j].getNext() != null && projectileObjList[j].getPrev() != null){ //projectile is in middle of list
                	  projectileObjList[j].getPrev().setNext(projectileObjList[j].getNext());
                	  projectileObjList[j].getNext().setPrev(projectileObjList[j].getPrev());
                  }
                     	else projectileObjList[j].getPrev().setNext(null);// projectile is last in list
                    
                    	    */
                    }
                
               
            
                }
            }
        }
        else
            Menu.paused = false;
    }
    
    
    
    
    

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {

        playerObject.render(playerObject.playerSprite);
        for(int i = 0; i < asteroids.length; i++){

            asteroids[i].render(asteroids[i].asteroidSprite);
          //Collision detection

         
          g.drawRect(asteroids[i].position.x+10, asteroids[i].position.y+10, asteroids[i].asteroidSprite.getWidth()-20, asteroids[i].asteroidSprite.getHeight()-20);
          g.drawRect(playerObject.position.x+5, playerObject.position.y+5, playerObject.playerSprite.getWidth()-25, playerObject.playerSprite.getHeight()-25);
          }

        g.drawString("Score: " + playerObject.score, (Main.WIDTH / 3) - 200, (Main.HEIGHT / 3) - 100);

        projectile.render();
        //playerObject.render();//Call render method from Player class
    }

    @Override
    public int getID()
    {
        return this.gameId;
    }

    /**
     *
     * @param key
     * @param c
     * returns gameState to menu
     */
    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_ESCAPE:
                game.enterState(Main.menu);
                Menu.paused = true;
                break;
            default:
                break;
        }
    }
}
