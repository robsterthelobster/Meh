/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import java.awt.*;
import java.io.*;
import static java.lang.Boolean.parseBoolean;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jason
 */
class MainGame extends StateBasedGame{
    public static String gameTitle = "My game";
    public static AppGameContainer app;
    public static int windowWidth;
    public static int windowHeight;
    
    //Player Controls
    public static int upKey;
    public static int downKey;
    public static int leftKey;
    public static int rightKey;
    public static int reloadKey;
    public static int shootButton;
    
    public static String optionsFilename = "options.txt";
    public static String optionsDirectory = "options/";
    public static File optionsFile;
    
    public static ArrayList<ArrayList<Integer>> resList;
    
    public static boolean isFullscreen;
    
    public static int previousState;
    
    public static final int MAINMENUSTATE = 1;
    public static final int GAMESTATE = 2;
    public static final int OPTIONSSTATE = 3;
    public static final int PAUSESTATE = 4;
    
    public MainGame() throws IOException{
        super(gameTitle);
        
        optionsFile = findFile(optionsFilename, new File(optionsDirectory));
        if(optionsFile != null){
            setOptionsFromFile();
        }else{
            setOptionsDefault();
        }
    }
    
    public static void main(String[] args) throws IOException{
        try{
            resList = new ArrayList<>();
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            DisplayMode[] allModes = gd.getDisplayModes();
            for(DisplayMode mode : allModes){
                //if !(am contains mode.width && mode.height) add to list
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(mode.getWidth());
                tmp.add(mode.getHeight());
                if(!resList.contains(tmp) && (tmp.get(0) >= 1024 && tmp.get(1) >= 768))
                    resList.add(tmp);
            }
            previousState = 0;
            app = new AppGameContainer(new MainGame());
            app.setDisplayMode(windowWidth, windowHeight, isFullscreen);
            app.start();
            
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
    
    private static File findFile(String filename, File directory){
        File[] list = directory.listFiles();
        if(list != null){
            for(File f : list){
                if(filename.equalsIgnoreCase(f.getName())){
                    return f;
                }
            }
        }
        return null;
    }
    
    private static void setOptionsFromFile() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(optionsDirectory + optionsFilename);
        BufferedReader br = new BufferedReader(fr);
                
        String line = null;
        while((line = br.readLine()) != null){
            String lines[] = line.split(" ");
            switch(lines[0]){
                case "isFullscreen":
                    isFullscreen = parseBoolean(lines[2]);
                    break;
                case "screenWidth":
                    windowWidth = Integer.parseInt(lines[2]);
                    break;
                case "screenHeight":
                    windowHeight = Integer.parseInt(lines[2]);
                    break;
                case "Up_Key":
                    upKey = Integer.parseInt(lines[2]);
                    break;
                case "Down_Key":
                    downKey = Integer.parseInt(lines[2]);
                    break;
                case "Left_Key":
                    leftKey = Integer.parseInt(lines[2]);
                    break;
                case "Right_Key":
                    rightKey = Integer.parseInt(lines[2]);
                    break;
                case "Reload_Key":
                    reloadKey = Integer.parseInt(lines[2]);
                    break;
                case "Shoot_Button":
                    shootButton = Integer.parseInt(lines[2]);
                    break;
            }
        }
        fr.close();
        br.close();
    }
    
    private static void setOptionsDefault() throws IOException{
        isFullscreen = true;
                
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        windowWidth = gd.getDisplayMode().getWidth();
        windowHeight = gd.getDisplayMode().getHeight();
        
        upKey = Input.KEY_W;
        downKey = Input.KEY_S;
        leftKey = Input.KEY_A;
        rightKey = Input.KEY_D;
        reloadKey = Input.KEY_R;
        shootButton = Input.MOUSE_LEFT_BUTTON;
        
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("isFullscreen = " + isFullscreen);
        lines.add("screenWidth = " + windowWidth);
        lines.add("screenHeight = " + windowHeight);
        lines.add("Up_Key = " + upKey);
        lines.add("Down_Key = " + downKey);
        lines.add("Left_Key = " + leftKey);
        lines.add("Right_Key = " + rightKey);
        lines.add("Reload_Key = " + reloadKey);
        lines.add("Shoot_Button = " + shootButton);
        Path file = Paths.get(optionsDirectory + optionsFilename);
        Files.write(file,lines,Charset.forName("UTF-8"));
    }
    
    public static void editOptions(String toEdit, String editString) throws FileNotFoundException, IOException{
        ArrayList<String> lines = new ArrayList<>();
        String line = null;
        
        FileReader fr = new FileReader(optionsFile);
        BufferedReader br = new BufferedReader(fr);
        while((line = br.readLine()) != null){
            if(line.contains(toEdit)){
                String[] tmp = line.split(" ");
                line = line.replace(tmp[2], editString);
            }
            lines.add(line);
        }
        fr.close();
        br.close();
        
        Path file = Paths.get(optionsDirectory + optionsFilename);
        Files.write(file,lines,Charset.forName("UTF-8"));
    }
    
    public static void setResolution(int width, int height) throws SlickException{
        windowWidth = width;
        windowHeight = height;
        app.setDisplayMode(windowWidth, windowHeight, isFullscreen);
    }

    public static void changeState(StateBasedGame sbg, int enterID, int prevID){
        sbg.enterState(enterID);
        previousState = prevID;
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MainMenu());
        addState(new GameState());
        addState(new OptionsMenu());
        addState(new PauseState());
    }
}
