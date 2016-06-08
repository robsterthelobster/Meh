package game;

import game.tiles.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

import static sun.audio.AudioPlayer.player;

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
    private Tile[][] tiles;
    int playerStartX, playerStartY;
    private ArrayList<LevelObject> entities;

    public Level() throws SlickException{
        map = new TiledMap("data/levels/test.tmx");

        mCol = map.getWidth();
        mRow = map.getHeight();
        tileSize = map.getTileHeight();

        entities = new ArrayList<LevelObject>();
        Enemy enemy = new Enemy(600, 600);
        entities.add(enemy);

        loadTileMap();
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

        for(LevelObject entity : entities){
            if(entity instanceof Player){
                ((Player) entity).render(g);
            }
            if(entity instanceof Enemy){
                ((Enemy) entity).render(g);
            }
        }
    }

    private void loadTileMap(){
        //create an array to hold all the tiles in the map
        tiles = new Tile[map.getWidth()][map.getHeight()];

        int layerIndex = map.getLayerIndex("CollisionLayer");

        if(layerIndex == -1){
            System.err.println("Map does not have the layer \"CollisionLayer\"");
            System.exit(0);
        }

        //loop through the whole map
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++){

                //get the tile
                int tileID = map.getTileId(x, y, layerIndex);

                Tile tile = null;

                //and check what kind of tile it is (
                switch(map.getTileProperty(tileID, "tileType", "solid")){
                    case "player":
                        tile = new Tile(x,y);
                        playerStartX = x*tileSize;
                        playerStartY = y*tileSize;
                        break;
                    default:
                        tile = new Tile(x,y);
                        break;
                }
                tiles[x][y] = tile;
            }
        }
    }

    public int getPlayerStartX(){
        return playerStartX;
    }

    public int getPlayerStartY(){
        return playerStartY;
    }

    public int getTileSize(){
        return tileSize;
    }

    public ArrayList<LevelObject> getEntities(){
        return entities;
    }

    public boolean addPlayerToLevel(Player player){
        if(player != null){
            entities.add(player);
            return true;
        }
        return false;
    }

}
