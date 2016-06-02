/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.upKey;
import static Meh.MainGame.downKey;
import static Meh.MainGame.leftKey;
import static Meh.MainGame.rightKey;
import static Meh.MainGame.reloadKey;
import static Meh.MainGame.shootButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jason
 */
public class Player {
    public int x;
    public int y;
    
    public int size;
    public Shape rect;
    
    Player(int x, int y, int size){
        this.x = x;
        this.y = y;
        
        this.size = size;
        this.rect = new Rectangle(this.x, this.y, this.size, this.size); 
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs){
        grphcs.setColor(Color.blue);
        grphcs.fill(this.rect);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i){
        Input input = gc.getInput();
        
        //Move up
        if(input.isKeyPressed(upKey)){
            
        }
        //Move down
        if(input.isKeyPressed(downKey)){
        
        }
        //Move left
        if(input.isKeyPressed(leftKey)){
            
        }
        //Move right
        if(input.isKeyPressed(rightKey)){
            
        }
        //Reload
        if(input.isKeyPressed(reloadKey)){
            
        }
        //Shoot
        if(input.isKeyPressed(shootButton)){
            
        }
    }
}
