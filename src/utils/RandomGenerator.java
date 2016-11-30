package utils;

import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class RandomGenerator {
	
	private int min;
	private int max;
	private int randomInt;
	

	public RandomGenerator(){
	}
	
	private int genRandomNumber(int min, int max){
		Random ran = new Random();
		return ran.nextInt(max) + min;

	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getRandomInt(int min, int max) {
		genRandomNumber(min, max);
		return randomInt;
	}

	public void setRandomInt(int randomInt) {
		this.randomInt = randomInt;
	}

}
