package Game.tiles;

/**
 * Created by robin on 6/2/2016.
 */
public class Tile {

    protected int x;
    protected int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}