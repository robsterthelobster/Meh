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
        buttonList = new ArrayList<>();
        buttonList.add(new Button(gc.getWidth()/8, gc.getHeight()/4, "Resume", 32, Color.white, buttonList.size()));
        buttonList.add(new Button(gc.getWidth()/8, gc.getHeight()/4, "Options", 32, Color.white, buttonList.size()));
        buttonList.add(new Button(gc.getWidth()/8, gc.getHeight()/4, "Back to Main Menu", 32, Color.white, buttonList.size()));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Font f = new Font("Arial", Font.PLAIN, 40);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
            
        ttf.drawString(gc.getWidth()/3, gc.getHeight()/5, "Paused");
            
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
