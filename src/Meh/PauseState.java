/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.MAINMENUSTATE;
import static Meh.MainGame.GAMESTATE;
import static Meh.MainGame.OPTIONSSTATE;
import static Meh.MainGame.PAUSESTATE;
import static Meh.MainGame.changeState;
import static Meh.MainGame.previousState;
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
public class PauseState extends BasicGameState{

    public ArrayList<Button> buttonList;
    
    @Override
    public int getID() {
        return PAUSESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        int rX = 8;
        int rY = 4;
        int offsetY = 100;
        int size = 32;
        
        buttonList = new ArrayList<>();
        buttonList.add(new Button(rX, rY, 0, 0, "Resume", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Options", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Back to Main Menu", size, Color.white, buttonList.size(), gc));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Font f = new Font("Arial", Font.PLAIN, 40);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
            
        ttf.drawString(gc.getWidth()/2 - ttf.getWidth("Paused")/2, gc.getHeight()/8, "Paused");
            
        for(Button b : buttonList){
            b.render(gc, sbg, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(GAMESTATE);
            previousState = this.getID();
        }
        
        for(Button b : buttonList){
            b.update(gc, sbg, i);
                
            if(b.isPressed){
                b.isPressed = false;
                switch(b.index){
                    case 0:
                        changeState(sbg, GAMESTATE, this.getID());
                        break;
                    case 1:
                        changeState(sbg, OPTIONSSTATE, this.getID());
                        break;
                    case 2:
                        changeState(sbg, MAINMENUSTATE, this.getID());
                        break;
                    default:
                        System.out.println("Error");
                    }
                }
            }
    }
    
}
