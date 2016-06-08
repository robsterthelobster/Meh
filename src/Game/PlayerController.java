package game;

import org.newdawn.slick.Input;


public abstract class PlayerController {

    protected Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public abstract void handleInput(Input i, int delta);

}