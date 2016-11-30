package utils;

import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class RandomGenerator {
	
	private int min;
	private int max;
	private int randomInt;
	
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

	public int getRandomInt() {
		return randomInt;
	}

	public void setRandomInt(int randomInt) {
		this.randomInt = randomInt;
	}

	public RandomGenerator(int min, int max){
		this.min = min;
		this.max = max;
		this.randomInt = genRandomNumber();
	}
	
	private int genRandomNumber(){
		Random ran = new Random();
		return ran.nextInt(this.max) + this.min;

	}

}
