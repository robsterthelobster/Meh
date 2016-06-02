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

    @Override
    public int getID() {
        return GAMESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        buttonList = new ArrayList<>();
        buttonList.add(new Button(gc.getWidth()/8, gc.getHeight()/4, "Options", 32, Color.white, buttonList.size()));
        buttonList.add(new Button(gc.getWidth()/8, gc.getHeight()/4 + 100, "Back", 32, Color.white, buttonList.size()));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Font f = new Font("Arial", Font.PLAIN, 40);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
            
        ttf.drawString(gc.getWidth()/3, gc.getHeight()/5, "GameState");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            changeState(sbg, PAUSESTATE, this.getID());
        }
    }
    
}
