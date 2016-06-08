package game.physics;

import game.Level;
import game.LevelObject;
import game.tiles.Tile;

import java.util.ArrayList;

public class Physics {

	private final float gravity = 0.0008f;//was 0.0015f

	public void handlePhysics(Level level, int delta){
		handleCharacters(level,delta);
	}

	private boolean checkCollision(LevelObject obj, Tile[][] mapTiles){
		//get only the tiles that matter
		ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
		for(Tile t : tiles){
			//if this tile has a bounding shape
//			if(t instanceof SolidTile){
//				if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
//					return true;
//				}
//			}
		}
		return false;
	}

	private void handleCharacters(Level level, int delta){

	}

	private void handleGameObject(LevelObject obj, Level level, int delta){

	}
}