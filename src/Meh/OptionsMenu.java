/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.OPTIONSSTATE;
import static Meh.MainGame.app;
import static Meh.MainGame.changeState;
import static Meh.MainGame.downKey;
import static Meh.MainGame.editOptions;
import static Meh.MainGame.isFullscreen;
import static Meh.MainGame.leftKey;
import static Meh.MainGame.resList;
import static Meh.MainGame.previousState;
import static Meh.MainGame.reloadKey;
import static Meh.MainGame.rightKey;
import static Meh.MainGame.setResolution;
import static Meh.MainGame.shootButton;
import static Meh.MainGame.upKey;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.lwjgl.input.Keyboard;
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
public class OptionsMenu extends BasicGameState{
    public ArrayList<Button> buttonList;
    private static int displayIter;
    public static boolean isWaiting;
    public int lastKey;
    public Button currButton;
    
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
        
        isWaiting = false;
        
        buttonList = new ArrayList<>();
        String screenState = "Fullscreen";
        if(!app.isFullscreen())
            screenState = "Windowed";
        
        //Relative distance from window edges
        int rX = 8;
        int rY = 9;
        int offsetY = 60;
        int size = 30;
        
        String shootText = Input.getKeyName(shootButton);
        
        if(shootText.equals("NONE")){
            shootText = getMouseText(shootButton);
        }
        
        buttonList.add(new Button(rX, rY, 0, 0, screenState, size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Resolution: " + gc.getWidth() + "x" + gc.getHeight(), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Up Key: " + Input.getKeyName(upKey), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Down Key: " + Input.getKeyName(downKey), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Left Key: " + Input.getKeyName(leftKey), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Right Key: " + Input.getKeyName(rightKey), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Reload Key: " + Input.getKeyName(reloadKey), size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, offsetY * buttonList.size(), "Shoot Button: " + shootText, size, Color.white, buttonList.size(), gc));
        buttonList.add(new Button(rX, rY, 0, (offsetY+10) * buttonList.size(), "Back", size, Color.white, buttonList.size(), gc));
        //buttonList.add(new Button(2, rY, size*2, (offsetY+10) * (buttonList.size()-1), "Default", size, Color.white, buttonList.size(), gc));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for(Button b : buttonList){
            b.render(gc, sbg, grphcs);
        }
        if(isWaiting){
            String text = "Please enter a key";
            Font f = new Font("Arial", Font.PLAIN, 40);
            TrueTypeFont ttf = new TrueTypeFont(f, true);
            
            ttf.drawString(gc.getWidth()/2 - ttf.getWidth(text)/2, gc.getHeight()/8, text);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Input input = gc.getInput();
        
        for(Button b : buttonList){
            b.update(gc, sbg, i);
                
            if(buttonList.get(0).text.equals("Fullscreen")){
                buttonList.get(1).changeColor(Color.gray);
                buttonList.get(1).isPressable = false;
            }
            
            if(b.isPressed && !isWaiting){
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
                                buttonList.get(1).changeText("Resolution: " + gc.getWidth() + "x" + gc.getHeight());
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
                                b.changeText("Resolution: " + gc.getWidth() + "x" + gc.getHeight());        
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        isWaiting = true;
                        currButton = b;
                        break;
                    case 7:
                        
                        break;
                    case 8:
                        changeState(sbg, previousState, this.getID());
                        break;
                    case 9:
                        
                        break;
                    default:
                        System.out.println(b.index + " Button not implemented!");
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
    
    public static String getMouseText(int mouseBtn){
        String text = "NONE";
        switch (mouseBtn) {
            case Input.MOUSE_LEFT_BUTTON:
                text = "Left Button";
                break;
            case Input.MOUSE_MIDDLE_BUTTON:
                text = "Middle Button";
                break;
            case Input.MOUSE_RIGHT_BUTTON:
                text = "Right Button";
                break;
            default:
                break;
        }
        return text;
    }
    
    public void setKey(String key){
        switch(key){
            case "Up":
                upKey = lastKey;
                try{
                    editOptions("Up_Key",Integer.toString(upKey));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case "Down":
                downKey = lastKey;
                try{
                    editOptions("Down_Key",Integer.toString(downKey));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case "Left":
                leftKey = lastKey;
                try{
                    editOptions("Left_Key",Integer.toString(leftKey));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case "Right":
                rightKey = lastKey;
                try{
                    editOptions("Right_Key",Integer.toString(rightKey));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case "Reload":
                reloadKey = lastKey;
                try{
                    editOptions("Reload_Key",Integer.toString(reloadKey));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            /*
            case "Shoot":
                shootButton = lastKey;
                try{
                    editOptions("Shoot_Button",Integer.toString(shootButton));
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            */
            default:
                System.out.println("Not a key.");
                break;
        }
    }
    
    @Override
    public void keyPressed(int key, char c){
        if(isWaiting){
            lastKey = key;
            setKey(currButton.text.split(" ")[0]);
            String[] tmp = currButton.text.split(":");
            currButton.text = tmp[0] + ": " + Input.getKeyName(key);
            isWaiting = false;
        }
    }
}
    
