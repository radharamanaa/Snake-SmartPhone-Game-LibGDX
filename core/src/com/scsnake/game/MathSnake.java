package com.scsnake.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MathSnake {
	public boolean checkSnakeCollision(ArrayList<Vector2> arr){
		boolean result=false;
		for(int i=1;i<arr.size();i++){
			if((arr.get(0).x==arr.get(i).x) && arr.get(0).y==arr.get(i).y){
				result=true;
			}
		}return result;

	}

	public Vector2 randomFood(ArrayList<Vector2> arr){
		int x=0;
		int y=0;
		int count=0;
		for(int j=0;j<50;j++){
			x=(int) (Math.random()*(800/20))*20;
			y=(int) (Math.random()*(480/20))*20;
			for(int i=1;i<(arr.size());i++){
				if((arr.get(i).x==x) && arr.get(i).y==y){
					break;
				}
				if((arr.get(0).x!=x) || (arr.get(0).y!=y))
				{
					count++;
					if(arr.size()==count)
						break;
					continue;
				}
			}
		}
		int x1=(int) Gdx.graphics.getDeltaTime();
		return new Vector2(x1,y);
	}

	public void nextCoord(ArrayList<Vector2> arr,String upto){

		if(upto.equals("right"))
		{
			if(arr.get(0).x==0 && arr.get(1).x==800-20)
			{
				right(arr);
			}
			else if(arr.get(0).x==800-20 && arr.get(1).x==0)
			{
				left(arr);
			}
			else if(arr.get(0).y==0 && arr.get(1).y==480-20)
			{
				right(arr); 
			}
			else if((arr.get(0).y==480-20 && arr.get(1).y==0))
			{
				right(arr);
			}
			else if(arr.get(0).y!=arr.get(1).y)
			{
				right(arr);
			}
			else if(arr.get(0).x>arr.get(1).x)
			{
				right(arr);
			}
			else if(arr.get(0).x<arr.get(1).x){
				left(arr);
			}
		}


		if(upto.equals("left"))
		{
			if(arr.get(0).x==0 && arr.get(1).x==800-20)//going right
			{
				right(arr);
			}
			if(arr.get(0).x==800-20 && arr.get(1).x==0)//going left
			{
				left(arr);
			}
			if(arr.get(0).y==0 && arr.get(1).y==480-20)//going up
			{
				left(arr);
			}
			if((arr.get(0).y==480-20 && arr.get(1).y==0))
			{
				left(arr);
			}
			if(arr.get(0).y>arr.get(1).y)
			{
				left(arr);
			}
			else if(arr.get(0).y<arr.get(1).y)
			{
				left(arr);
			}
			else if(arr.get(0).x>arr.get(1).x)
			{
				right(arr);
			}
			else if(arr.get(0).x<arr.get(1).x){
				left(arr);
			}
		}
		if(upto.equals("up")){
			if(arr.get(0).x==0 && arr.get(1).x==800-20)//going right
			{
				up(arr);
			}
			if(arr.get(0).x==800-20 && arr.get(1).x==0)//going left
			{
				up(arr);
			}
			if(arr.get(0).y==0 && arr.get(1).y==480-20)//going up
			{
				up(arr); 
			}
			if((arr.get(0).y==480-20 && arr.get(1).y==0))
			{
				down(arr);
			}
			if(arr.get(0).y>arr.get(1).y)
			{
				up(arr);
			}
			else if(arr.get(0).y<arr.get(1).y)
			{
				down(arr);
			}
			else if(arr.get(0).x>arr.get(1).x)
			{
				up(arr);
			}
			else if(arr.get(0).x<arr.get(1).x){
				up(arr);
			}
		}
		if(upto.equals("down")){
			if(arr.get(0).x==0 && arr.get(1).x==800-20)//going right
			{
				down(arr);
			}
			if(arr.get(0).x==800-20 && arr.get(1).x==0)//going left
			{
				down(arr);
			}
			if(arr.get(0).y==0 && arr.get(1).y==480-20)//going up
			{
				up(arr);
			}
			if((arr.get(0).y==480-20 && arr.get(1).y==0))
			{
				down(arr);
			}
			if(arr.get(0).y>arr.get(1).y)
			{
				up(arr);
			}
			else if(arr.get(0).y<arr.get(1).y)
			{
				down(arr);
			}
			else if(arr.get(0).x!=arr.get(1).x)
			{
				down(arr);
			}
		}


	}


	public boolean checkFoodEaten(ArrayList<Vector2> arr, Vector2 food) {
		boolean result=false;
		if((arr.get(0).x == food.x) && (arr.get(0).y == food.y)) {
			result=true;
			updateSnake(arr);
		}
		return result;		
	}
	private void updateSnake(ArrayList<Vector2> arr) {
		int size=arr.size();
		Vector2 temp=new Vector2();
		if(arr.get(size-2).x == arr.get(size-1).x){
			if(arr.get(size-2).y>arr.get(size-1).y)//going up
			{
				temp.x=arr.get(size-1).x;
				temp.y=arr.get(size-1).y-20;
				if(temp.y<0)temp.y=480-20;
			}
			else
			{
				temp.x=arr.get(size-1).x;
				temp.y=arr.get(size-1).y+20;
				if(temp.y>480)temp.y=0;
			}
		}
			if(arr.get(size-2).y == arr.get(size-1).y){
				if(arr.get(size-2).x>arr.get(size-1).x)//going right
				{
					temp.y=arr.get(size-1).y;
					temp.x=arr.get(size-1).x-20;
					if(temp.x<0)temp.x=800-20;
				}
				else
				{
					temp.y=arr.get(size-1).y;
					temp.x=arr.get(size-1).x+20;
					if(temp.x>800)temp.x=0;
				}

			}
			arr.add(size-1, temp);

		}

		public void right(ArrayList<Vector2> arr) {
			for(int i=arr.size()-1;i>0;i--)
			{ 
				arr.get(i).set(arr.get(i-1).x, arr.get(i-1).y);
			}
			arr.get(0).add(20, 0);
			checkBounds(arr);
		}

		public void left(ArrayList<Vector2> arr) {

			for(int i=arr.size()-1;i>0;i--)
			{ 
				arr.get(i).set(arr.get(i-1).x, arr.get(i-1).y);
			}
			arr.get(0).sub(20, 0);
			checkBounds(arr);
		}
		public void up(ArrayList<Vector2> arr) {

			for(int i=arr.size()-1;i>0;i--)
			{ 
				arr.get(i).set(arr.get(i-1).x, arr.get(i-1).y);
			}
			arr.get(0).add(0, 20);
			checkBounds(arr);
		}
		public void down(ArrayList<Vector2> arr) {

			for(int i=arr.size()-1;i>0;i--)
			{ 
				arr.get(i).set(arr.get(i-1).x, arr.get(i-1).y);
			}
			arr.get(0).sub(0,20);
			checkBounds(arr);
		}
		public void checkBounds(ArrayList<Vector2> arr) {
			int temp1=0;
			int temp2=0;
			if(arr.get(0).x>=800)
			{
				temp1=0;
				temp2=(int) arr.get(0).y;
				arr.get(0).set(temp1,temp2);
			}
			if(arr.get(0).x<0)
			{
				temp1=800-20;
				temp2=(int) arr.get(0).y;
				arr.get(0).set(temp1,temp2);
			}
			if(arr.get(0).y>=480)
			{
				temp2=0;
				temp1=(int) arr.get(0).x;
				arr.get(0).set(temp1,temp2);
			}
			if(arr.get(0).y<0)
			{
				temp2=480-20;
				temp1=(int) arr.get(0).x;
				arr.get(0).set(temp1,temp2);
			}
		}

	}



