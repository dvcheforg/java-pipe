public class Rectangulator {

public static void main ( String [] args){

  int len1 = Integer.parseInt(args[0]);
  int width = Integer.parseInt(args[1]);

  Rectangle rect = new Rectangle(len1,width);
  String output = String.format(" Given Rectangle values are \n\n Length : %d \nWidth : %d \nArea : %d \n Perimeter : %d \n", rect.length,rect.width,rect.getArea(), rect.getPerimeter()");

   System.out.println(output);  
}

}
