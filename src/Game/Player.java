/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jason
 */
public class Player extends LevelObject{

    /*
        MOVEMENT STATES
     */
    public final static int MOVE_UP = 0;
    public final static int MOVE_DOWN = 1;
    public final static int MOVE_LEFT = 2;
    public final static int MOVE_RIGHT = 3;

    public float x;
    public float y;
    
    public int size = 32;
    public int tileSize;
    public Shape rect;
    
    public Player(float  x, float y, int tileSize){
        super(x, y);
        this.x = x;
        this.y = y;
        
        this.tileSize = tileSize;
        this.rect = new Rectangle(this.x + getOffset(), this.y  + getOffset(), this.size, this.size);
    }
    
    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fill(this.rect);
    }

    public void movePlayer(int state){
        switch(state){
            case MOVE_UP:
                System.out.println("up");
                y -= tileSize;
                break;
            case MOVE_DOWN:
                System.out.println("down");
                y += tileSize;
                break;
            case MOVE_LEFT:
                System.out.println("left");
                x -= tileSize;
                break;
            case MOVE_RIGHT:
                System.out.println("right");
                x += tileSize;
                break;
            default:
                System.out.println("No such state");
        }
        rect.setX(x + getOffset());
        rect.setY(y + getOffset());
    }

    /*
        Offset to correct center, assuming square
     */
    private int getOffset(){
        return (tileSize - size)/2;
    }

}
