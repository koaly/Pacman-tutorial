package com.mygdx.game;

import java.awt.RenderingHints.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	private PacmanGame pacmanGame;
	
	private Texture pacmanImg;
	
	World world;
	
	WorldRenderer worldRenderer;
	
	public GameScreen(PacmanGame pacmanGame) {
		this.pacmanGame = pacmanGame;
		
		pacmanImg = new Texture("pacman.png");
		
		world = new World(pacmanGame);
		
		worldRenderer = new WorldRenderer(pacmanGame, world);
	}
	
	public void update(float delta) {
		updatePacmanDirection();
		world.update(delta);
	}
	
	private void updatePacmanDirection() {
		Pacman pacman = world.getPacman();
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			pacman.setNextDirection(Pacman.DIRECTION_UP);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			pacman.setNextDirection(Pacman.DIRECTION_DOWN);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			pacman.setNextDirection(Pacman.DIRECTION_LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pacman.setNextDirection(Pacman.DIRECTION_RIGHT);
		}
		if(!Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			pacman.setNextDirection(Pacman.DIRECTION_STILL);
		}
    }
	
	@Override
    public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		worldRenderer.render(delta);
		
    }
}
