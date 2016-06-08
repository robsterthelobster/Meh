package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by robin on 6/2/2016.
 */
public class Bullet {

    private static int MAX_LIFE = 2000;
    private Vector2f mPosition;
    private Vector2f mSpeed;
    private int life = 0;
    private int mRadius = 10;

    private boolean active = true;

    public Bullet(Vector2f position, Vector2f speed){
        mPosition = position;
        mSpeed = speed;
    }

    public Bullet(){
        active = false;
    }

    public void update(int delta){
        if(active){
            Vector2f realSpeed = mSpeed.copy();
            realSpeed.scale((delta/1000f));
            mPosition.add(realSpeed);

            life += delta;
            if(life > MAX_LIFE){
                active = false;
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.setColor(Color.red);
        g.fillOval(mPosition.getX() -  mRadius, mPosition.getY() - mRadius, mRadius*2, mRadius*2);
    }

    public boolean isActive(){
        return active;
    }

}
