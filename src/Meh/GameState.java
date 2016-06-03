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

import Game.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jason
 */
public class GameState extends BasicGameState{
    
    public Player player;
    Level level;

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
        
        int playerSize = 32;
        level = new Level();
        player = new Player(level.getPlayerStartX(), level.getPlayerStartY(), playerSize);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        level.render(g);
        player.render(gc, sbg, g);

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
