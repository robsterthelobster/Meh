package game;

import game.physics.AABoundingRect;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by robin on 6/7/2016.
 */
public class Enemy extends LevelObject{

    public Shape rect;

    public Enemy(float x, float y){
        super(x, y);

        this.rect = new Rectangle(this.x, this.y, 32, 32);
        x_velocity = -0.1f;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fill(this.rect);
    }

    /*
        Override the update to draw the temp art
     */
    public void updateBoundingShape(){
        super.updateBoundingShape();
        rect.setX(getX());
        rect.setY(getY());
    }
}
