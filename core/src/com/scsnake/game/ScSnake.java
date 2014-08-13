package com.scsnake.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
/*The logic of the application is that the first value is modified and the values of elements from 
 * second element is swapped with the top.
 * */

public class ScSnake extends ApplicationAdapter implements  GestureListener {
	static int frame;
	static boolean flag;
	SpriteBatch batch;
	Texture body;
	Texture head;
	ArrayList<Vector2> arr;
	static int len=7;
	private static String upto;
	Texture back;
	OrthographicCamera camera;
	Vector2 food;
	static int w;
	static int h;
	
	public ScSnake(){
		arr=new ArrayList<Vector2>();
		for(int i=0;i<len;i++){
			arr.add(new Vector2(800/2-((i)*20),480/2));
			flag=false;
		}

	}
	@Override
	public void create () {
		ScSnake.upto="right";
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		back = new Texture(Gdx.files.internal("back.png"));
		body = new Texture(Gdx.files.internal("body.png"));
		head = new Texture(Gdx.files.internal("head.png"));
		GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0.2f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		frame++;
		MathSnake x=new MathSnake();
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			upto="left";
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			upto="right";
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			upto="up";
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			upto="down";
		}
		batch.begin();
		batch.draw(back,0,0,800,480);
		if(frame==6){
			x.nextCoord(arr,upto);
			frame=0;
		}
		
		if(!flag){
			food=x.randomFood(arr);
			flag=true;
		}
		batch.draw(body,food.x,food.y,20,20);
		for(int i=0;i<arr.size();i++)
		{
			if(i==0){
				batch.draw(head,arr.get(i).x, arr.get(i).y, 20, 20);
			}
			else
			batch.draw(body, arr.get(i).x, arr.get(i).y, 20, 20);
		}
		batch.end();
		if(x.checkSnakeCollision(arr))Gdx.app.exit();
		if(x.checkFoodEaten(arr,food)){flag=false;}//Gdx.app.exit();
	}
	public void dispose(){
		System.out.println(" dispose");
		batch.dispose();
	}
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return true;

	}

	@Override
	public boolean longPress(float x, float y) {
		return false;

	}
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (velocityX>0 && ((Math.abs(velocityX) >Math.abs(velocityY))))
		{
			upto="right";
		}
		if (velocityX<0 && ((Math.abs(velocityX) >Math.abs(velocityY))))
		{
			upto="left";
		}
		if (velocityY>0 && ((Math.abs(velocityY) >Math.abs(velocityX))))
		{
			upto="down";
		}
		if (velocityY<0 && ((Math.abs(velocityY) >Math.abs(velocityX))))
		{
			upto="up";
		}

		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		return true;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
