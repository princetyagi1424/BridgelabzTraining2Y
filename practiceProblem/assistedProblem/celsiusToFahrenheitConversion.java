import java.util.*;
class celsiustofahrenheitconversion{
	public static void main (String[] args){
		Scanner sc= new Scanner(System.in);
		System.out.println("ENTER TEMPRATURE IN CELSIUS");
		float c=sc.nextFloat();
		System.out.println("Fahrenheit ="+((c*9/5)+32));
	}
}