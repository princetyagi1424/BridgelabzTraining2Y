import java.util.*;
class powerCalculation{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter base: ");
		int base=sc.nextInt();
		System.out.print("Enter exponent: ");
		int exponent=sc.nextInt();
		double result= Math.pow(base,exponent);
		System.out.print("Result= "+result);
		sc.close();
	}
} 