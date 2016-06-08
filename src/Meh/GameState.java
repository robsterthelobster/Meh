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

import game.InputController;
import game.Level;
import game.Player;
import game.PlayerController;
import game.physics.Physics;
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
    
    public Player player;
    Level level;
    Physics physics;
    PlayerController playerController;

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
        
        level = new Level();
        physics = new Physics();

        player = new Player(level.getPlayerStartX(), level.getPlayerStartY(), level.getTileSize());
        level.addPlayerToLevel(player);
        playerController = new InputController(player);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        level.render(g);

        Font f = new Font("Arial", Font.PLAIN, 40);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
            
        ttf.drawString(gc.getWidth()/2 - ttf.getWidth("GameState")/2, gc.getHeight()/8, "GameState");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        playerController.handleInput(input, delta);
        physics.handlePhysics(level, delta);
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            changeState(sbg, PAUSESTATE, this.getID());
        }
    }
    
}
