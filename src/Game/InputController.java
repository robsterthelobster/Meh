package game;

import org.newdawn.slick.Input;

 
public class InputController extends PlayerController {

    public InputController(Player player) {
        super(player);
    }
 
    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i,delta);
    }
 
    private void handleKeyboardInput(Input input, int delta){
        //Move up
        if(input.isKeyPressed(Input.KEY_UP)){
            player.movePlayer(Player.MOVE_UP);
        }
        //Move down
        if(input.isKeyPressed(Input.KEY_DOWN)){
            player.movePlayer(Player.MOVE_DOWN);
        }
        //Move left
        if(input.isKeyPressed(Input.KEY_LEFT)){
            player.movePlayer(Player.MOVE_LEFT);
        }
        //Move right
        if(input.isKeyPressed(Input.KEY_RIGHT)){
            player.movePlayer(Player.MOVE_RIGHT);
        }
        //Reload
        if(input.isKeyPressed(Input.KEY_R)){
            System.out.println("reload");
        }
        //Shoot
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            System.out.println("shoot");
        }

    }
 
}