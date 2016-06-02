/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.GAMESTATE;
import static Meh.MainGame.PAUSESTATE;
import static Meh.MainGame.changeState;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jason
 */
public class GameState extends BasicGameState{
    
    public ArrayList<Button> buttonList;
    public Player player;

    @Override
    public int getID() {
        return GAMESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        int rX = 8;
        int rY = 4;
        int offsetY = 100;
        int size = 32;
        
        buttonList = new ArrayList<>();
        buttonList.add(new Button(rX, rY, 0, 0, "Options", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Back", size, Color.white, buttonList.size(), gc));
        
        int playerSize = 32;
        player = new Player(gc.getWidth()/2 - playerSize/2, gc.getHeight()/2 - playerSize/2, playerSize);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        player.render(gc, sbg, grphcs);
        
        Font f = new Font("Arial", Font.PLAIN, 40);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
            
        ttf.drawString(gc.getWidth()/2 - ttf.getWidth("GameState")/2, gc.getHeight()/8, "GameState");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            changeState(sbg, PAUSESTATE, this.getID());
        }
        
        player.update(gc, sbg, i);
    }
    
}
