package game.physics;

import game.Enemy;
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

	/*
		move zombies toward <<<<<
	 */
	private void handleCharacters(Level level, int delta){

		for(LevelObject entity : level.getEntities()){
			if(entity instanceof Enemy){
				Enemy enemy = (Enemy) entity;
				handleGameObject(enemy, level, delta);
			}
		}
	}

	/*
		move individual zombie to <<<<<
		called from handleCharacters
	 */
	private void handleGameObject(LevelObject obj, Level level, int delta){
		//calculate how much we actually have to move
		float x_movement = obj.getXVelocity()*delta;
		float y_movement = obj.getYVelocity()*delta;

		//we have to calculate the step we have to take
		float step_y = 0;
		float step_x = 0;

		if(x_movement != 0){
			step_y = Math.abs(y_movement)/Math.abs(x_movement);
			if(y_movement < 0)
				step_y = -step_y;

			if(x_movement > 0)
				step_x = 1;
			else
				step_x = -1;

			if((step_y > 1 || step_y < -1) && step_y != 0){
				step_x = Math.abs(step_x)/Math.abs(step_y);
				if(x_movement < 0)
					step_x = -step_x;
				if(y_movement < 0)
					step_y = -1;
				else
					step_y = 1;
			}
		}else if(y_movement != 0){
			//if we only have vertical movement, we can just use a step of 1
			if(y_movement > 0)
				step_y = 1;
			else
				step_y = -1;
		}

		//and then do little steps until we are done moving
		while(x_movement != 0 || y_movement != 0){

			//we first move in the x direction
			if(x_movement != 0){
				//when we do a step, we have to update the amount we have to move after this
				if((x_movement > 0 && x_movement < step_x) || (x_movement > step_x  && x_movement < 0)){
					step_x = x_movement;
					x_movement = 0;
				}else
					x_movement -= step_x;

				//then we move the object one step
				obj.setX(obj.getX()+step_x);

				//if we collide with any of the bounding shapes of the tiles we have to revert to our original position
//				if(checkCollision(obj,level.getTiles())){
//
//					//undo our step, and set the velocity and amount we still have to move to 0, because we can't move in that direction
//					obj.setX(obj.getX()-step_x);
//					obj.setXVelocity(0);
//					x_movement = 0;
//				}

			}
			//same thing for the vertical, or y movement
			if(y_movement != 0){
				if((y_movement > 0 && y_movement < step_y) || (y_movement > step_y  && y_movement < 0)){
					step_y = y_movement;
					y_movement = 0;
				}else
					y_movement -= step_y;

				obj.setY(obj.getY()+step_y);

//				if(checkCollision(obj,level.getTiles())){
//					obj.setY(obj.getY()-step_y);
//					obj.setYVelocity(0);
//					y_movement = 0;
//					break;
//				}
			}
		}

	}
}