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
	
	private Pacman pacman;
	
	World world;
	
	public GameScreen(PacmanGame pacmanGame) {
		this.pacmanGame = pacmanGame;
		
		pacmanImg = new Texture("pacman.png");
		
		world = new World(pacmanGame);
	}
	
	public void update(float delta) {
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			world.getPacman().move(Pacman.DIRECTION_UP);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			world.getPacman().move(Pacman.DIRECTION_DOWN);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			world.getPacman().move(Pacman.DIRECTION_LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			world.getPacman().move(Pacman.DIRECTION_RIGHT);
		}
	}
	
	@Override
    public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
		SpriteBatch batch = pacmanGame.batch;
		batch.begin();
		Vector2 pos = world.getPacman().getPosition();
		batch.draw(pacmanImg, pos.x, pos.y);
		batch.end();
    }
}
