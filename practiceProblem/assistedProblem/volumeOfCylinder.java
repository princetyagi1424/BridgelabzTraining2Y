import java.util.*;
class volumeofcylinder{
public static void main(String[] args){
Scanner sc= new Scanner(System.in);
System.out.print("Enter radius of cylinder :");
float radius=sc.nextFloat();
	System.out.print("Enter height of cylinder :");
float height=sc.nextFloat();
System.out.print("Volume ="+(3.14*radius*radius*height));
}
}