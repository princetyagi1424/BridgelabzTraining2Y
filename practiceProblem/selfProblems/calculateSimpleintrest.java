import java.util.*;
class calculatesimpleintrest{
	public static void main(String [] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter principal: ");
		float principal=sc.nextFloat();
		System.out.print("Enter rate: ");
		float rate=sc.nextFloat();
		System.out.print("Enter time: ");
		float time=sc.nextFloat();
		System.out.print("Simple Intrest = "+(principal*rate*time)/100);
		
	}
}