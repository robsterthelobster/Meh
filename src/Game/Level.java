package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by robin on 6/1/2016.
 */
public class Level {

    private TiledMap map;
    boolean gridOn = true;
    int mRow;
    int mCol;
    int tileSize;
    int tileOffset = 0;

    public Level() throws SlickException{
        map = new TiledMap("data/levels/test.tmx");

        mCol = map.getWidth();
        mRow = map.getHeight();
        tileSize = map.getTileHeight();
    }

    public void render(Graphics g){
        map.render(0,tileSize*tileOffset,0,0,32,32);

        if(gridOn){
            drawGrid(g);
        }
    }

    private void drawGrid(Graphics g){
        g.setColor(Color.white);
        g.setLineWidth(2f);
        for(int i = 0; i < mRow+1; i++){
            g.drawLine(0, (i+tileOffset)*tileSize, mCol*tileSize, (i+tileOffset)*tileSize);
        }
        for(int i = 0; i < mCol+1; i++){
            g.drawLine(i*tileSize, tileOffset*tileSize, i*tileSize, (mRow+tileOffset)*tileSize);
        }

    }

}
