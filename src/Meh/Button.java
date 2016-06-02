/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Meh;

import static Meh.MainGame.resList;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jason
 */
public class Button {
    public String text;
    public String font;
    public int x;
    public int y;
    public int defaultSize;
    public int fontSize;
    public int fontStyle;
    public int index;
    public Color color;
    
    public boolean isPressed;
    public boolean isPressable;
    
    public Font f;
    public TrueTypeFont ttf;
    
    Button(int x, int y, String text, int size, Color c, int index){
        this.x = x;
        this.y = y + index*100;
        this.text = text;
        this.defaultSize = size;
        this.fontSize = size;
        this.color = c;
        this.font = "Arial";
        this.fontStyle = Font.PLAIN;
        this.isPressed = false;
        this.isPressable = true;
        this.index = index;
        
        this.f = new Font(this.font, this.fontStyle, this.fontSize);
        this.ttf = new TrueTypeFont(this.f, true);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException{
        this.ttf.drawString(this.x, this.y, this.text, this.color);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if(this.x != gc.getWidth()/8){
            this.x = gc.getWidth()/8;
        }
        
        if(this.y != gc.getHeight()/4 + this.index*100){
            this.y = gc.getHeight()/4 + this.index*100;
        }
        
        if(input.getMouseX() >= this.x && input.getMouseX() <= this.x + this.ttf.getWidth(this.text) &&
           input.getMouseY() >= this.y && input.getMouseY() <= this.y + this.ttf.getHeight(this.text) && this.isPressable){
            this.color = Color.yellow;
            
            if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && this.isPressable){
                this.isPressed = true;
            }else{
                this.isPressed = false;
            }
        }else{
            this.color = Color.white;
        }
    }
    
    public void changeStyle(int style){
        this.f = new Font(this.font, style, this.fontSize);
        this.ttf = new TrueTypeFont(this.f, true);
    }
    
    public void changeSize(int size){
        this.f = new Font(this.font, this.fontStyle, size);
        this.ttf = new TrueTypeFont(this.f, true);
    }
    
    public void changeFont(String font){
        this.f = new Font(font, this.fontStyle, this.fontSize);
        this.ttf = new TrueTypeFont(this.f, true);
    }
    
    public void changeColor(Color color){
        this.color = color;
    }
    
    public void changeText(String text){
        this.text = text;
    }
}
