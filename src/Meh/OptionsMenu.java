/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.OPTIONSSTATE;
import static Meh.MainGame.app;
import static Meh.MainGame.changeState;
import static Meh.MainGame.editOptions;
import static Meh.MainGame.isFullscreen;
import static Meh.MainGame.resList;
import static Meh.MainGame.previousState;
import static Meh.MainGame.setResolution;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jason
 */
public class OptionsMenu extends BasicGameState{
    public ArrayList<Button> buttonList;
    private static int displayIter;
    
    @Override
    public int getID() {
        return OPTIONSSTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(gc.getWidth());
        tmp.add(gc.getHeight());
        displayIter = resList.indexOf(tmp);
        
        buttonList = new ArrayList<>();
        String screenState = "Fullscreen";
        if(!app.isFullscreen())
            screenState = "Windowed";
        
        //Relative distance from window edges
        int rX = 8;
        int rY = 9;
        int offsetY = 60;
        int size = 30;
        
        buttonList.add(new Button(rX, rY, 0, 0, screenState, size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), gc.getWidth() + "x" + gc.getHeight(), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Up Key", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Down Key", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Left Key", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Right Key", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Reload Key", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Shoot Button", size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, (offsetY+10) * buttonList.size(), "Back", size, Color.white, buttonList.size(), gc));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for(Button b : buttonList){
            b.render(gc, sbg, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        for(Button b : buttonList){
            b.update(gc, sbg, i);
            
            if(buttonList.get(0).text.equals("Fullscreen")){
                buttonList.get(1).changeColor(Color.gray);
                buttonList.get(1).isPressable = false;
            }
            
            if(b.isPressed){
                b.isPressed = false;
                switch(b.index){
                    case 0:
                        try{
                            if(b.text.equals("Fullscreen")){
                                isFullscreen = false;
                                b.changeText("Windowed");
                                editOptions("isFullscreen","false");
                                buttonList.get(1).changeColor(Color.white);
                                buttonList.get(1).isPressable = true;
                            }else if(b.text.equals("Windowed")){
                                isFullscreen = true;
                                b.changeText("Fullscreen");
                                editOptions("isFullscreen","true");
                                displayIter = resList.size()-1;
                                changeResolution();
                                buttonList.get(1).changeText(gc.getWidth() + "x" + gc.getHeight());
                            }
                            app.setFullscreen(isFullscreen);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        if(!isFullscreen){
                            try{
                                if(displayIter+1 >= resList.size())
                                    displayIter = 0;
                                else
                                    displayIter++;
                                changeResolution();
                                b.changeText(gc.getWidth() + "x" + gc.getHeight());        
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 8:
                        changeState(sbg, previousState, this.getID());
                        break;
                    default:
                        System.out.println("Error");
                }
            }
        }
    }
    
    public static void changeResolution() throws IOException, SlickException{
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp = resList.get(displayIter);
        editOptions("screenWidth", Integer.toString(tmp.get(0)));
        editOptions("screenHeight", Integer.toString(tmp.get(1)));
        setResolution(tmp.get(0),tmp.get(1));
    }
}
    
