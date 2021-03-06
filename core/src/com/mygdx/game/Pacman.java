package com.mygdx.game;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Pacman {
	private Vector2 position;
	
	private int currentDirection;
    private int nextDirection;

	private World world;
	
    public static final int SPEED = 5;
    
    private List<DotEattenListener> listeners;
    
	public Pacman(int x, int y, World world) {
		position = new Vector2(x,y);
		
		currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        
        this.world = world;
        listeners = new LinkedList<DotEattenListener>();
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	private static final int[][] DIR_OFFSETS = new int [][] {
		{0,0}, {0,-1}, {1,0}, {0,1}, {-1,0}
	};
	
	public void move(int dir) {
		position.x += SPEED * DIR_OFFSETS[dir][0];
		position.y += SPEED * DIR_OFFSETS[dir][1];
	}
	
	public void setNextDirection(int dir) {
		nextDirection = dir;
	}
	
	public boolean isAtCenter() {
        int blockSize = WorldRenderer.BLOCK_SIZE;
 
        return ((((int)position.x - blockSize/2) % blockSize) == 0) && 
        		((((int)position.y - blockSize/2) % blockSize) == 0);
    }
	
	private int getRow() {
        return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
    }
 
    private int getColumn() {
        return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
    }
	
	private boolean canMoveInDirection(int dir) {
        int newRow = getRow() + DIR_OFFSETS[dir][1];
        int newCol = getColumn() + DIR_OFFSETS[dir][0];
		
		return !world.getMaze().hasWallAt(newRow, newCol);  
    }
	
	public interface DotEattenListener {
        void notifyDotEatten();			
    }
	
	public void registerDotEattenListener(DotEattenListener l) {
        listeners.add(l);
    }
 
    private void notifyDotEattenListeners() {
        for(DotEattenListener l : listeners) {
            l.notifyDotEatten();
        }
    }
	
	public void update() {
		Maze maze = world.getMaze();
		if(isAtCenter()) {
            if(canMoveInDirection(nextDirection)) {
                currentDirection = nextDirection;    
            } else {
                currentDirection = DIRECTION_STILL;    
            }
            if (maze.hasDotAt(getRow(), getColumn())) {
            	maze.removeDotAt(getRow(), getColumn());
            	notifyDotEattenListeners();
            }
        }
        move(currentDirection);
    }
}
