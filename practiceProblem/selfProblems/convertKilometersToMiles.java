import java.util.*;
class convertKilometersToMiles{
	public static void main(String [] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Kilometers: ");
		float kilometers=sc.nextFloat();
		System.out.print("Miles = "+(kilometers*0.621371));
	}
}