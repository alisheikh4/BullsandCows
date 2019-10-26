// Ali Sheikh ID : 260864038
import java.util.Random;
import java.util.Scanner;
public class BullsAndCows {
  public static void main (String [] args) {
    
    playBullsAndCows(1);
    
  }
  
  public static boolean contains(int [] x, int y) {  
    
    for (int i = 0; i< x.length; i++) {
      if (x[i] == y) {
        return true;
      }
      
    }
    
    return false;  
  }
  //still need to figure out how to use method contains()
  public static int [] generateSecretDigits(int seed) {
    int [] x = new int [4];
    Random randomGenerator = new Random(seed);
    for (int i = 0; i < x.length; i++) {
      x[i] = randomGenerator.nextInt(10);  
      //System.out.print(x[i]);
      //if (contains(x,x[i]) == true) {
      //x[i] = randomGenerator.nextInt(10);  
      //  System.out.print(x[i]);
      // }
      // System.out.print(x[i]); 
    }
    
    if  (x[0] == x[1]) {
      x[0] = randomGenerator.nextInt(10);
    }
    if  (x[2]== x[0] || x[2] == x[1]) {
      x[2] = randomGenerator.nextInt(10);
    }
    if  ( x[3] == x[0]|| x[3] == x[1]|| x[3] == x[2]) {
      x[3] = randomGenerator.nextInt(10);
    } 
    for (int i = 0; i<x.length; i++) {
      //System.out.print(x[i]); 
    }
    
    
    return x;
  }
  
  public static int [] extractDigits(int y) {
    int number = Math.abs(y);
    
    String s = "" + number;
    if (s.length() == 0) {
      int [] x = {0,0,0,0};
      return x;
    }
    if (s.length()< 4) {
      int [] x = new int [4];
      int a = 4-s.length();
      for (int i = 0; i <a; i++) {
        x[i] = 0;
        
      }
      
      int k = 0;
      for (int i = a ; i < 4; i++) {
        
        x[i] = (s.charAt(k)-'0');
        k = k + 1;
        
      }
      /* for (int i = 0; i<x.length; i++) {
       System.out.print(x[i]); } 
       return x; */
    }
    int k = 0;
    int [] x = new int [s.length()];
    for (int i = 0 ; i < s.length(); i++) {
      
      x[i] = (s.charAt(k)-'0');
      k = k + 1;
      //  System.out.print(x[i]); 
    }
    
    
    
    
    return x;
  }
  
  public static int getNumOfBulls(int [] x, int [] y) {
    int bulls = 0;
    if (x.length != y.length) {
      throw new IllegalArgumentException("Please provide an input of the appropriate size, you have too few numbers!");
    }
    for (int i = 0; i < x.length; i++) {
      if ( x[i] == y[i]) {
        bulls = bulls+1;
      }
      
      
    }
    
    
    return  bulls;
  }
  public static int getNumOfCows(int [] x, int [] y) {
    int cows = 0;
    if (x.length != y.length) {
      throw new IllegalArgumentException("Please provide an input of the appropriate size, you have either too many or too few numbers!");
    }
    for (int i = 0; i < x.length; i++) {
      if (x[i] == y[i]) {
        continue;
      }
      for ( int k = 0; k<y.length; k++) {
        
        
        if (i!=k && x[i] == y[k]) {
          cows = cows+1;
        }
        
        
      }
    }
    
    
    return  cows;
  }
  
  public static void playBullsAndCows (int x) {
    
    int [] secret = generateSecretDigits(x);
    Scanner read = new Scanner(System.in);
    int guess;
    System.out.println("Hey, you! Want to play a game?");
    System.out.println("Do you think you can crack the code?");
    for (int a = 1; a<100; a++){
      int guessNumber = a;
      System.out.println("");
      System.out.print("Guess #"+guessNumber+". Enter a four digit number :");
      guess = Integer.parseInt(read.nextLine());
      if (validInputSign(guess)==false) {
        System.out.println("You must enter a positive integer with at most 4 digits. Try again!");
        continue;
      }
      if (validInputSize(extractDigits(guess))==false) {
        System.out.println("You must enter a positive integer with at most 4 digits. Try again!");
        continue;
      }
      int [] myGuess = extractDigits(guess);
      int bulls;
      int cows;
      bulls =getNumOfBulls(secret,myGuess);
      cows =getNumOfCows(secret,myGuess);
      System.out.println("Bulls : "+bulls+", Cows: "+cows);
      if (bulls == 4) {
        if (a ==1) {
          System.out.println("Congratulations! You cracked the code in only "+ a+" try.");
          break;
        }
        if (a!=1) {
          System.out.println("Congratulations! You cracked the code in only "+ a+" tries.");
          break;
        }
      }
      if (a >= 5) {
        System.out.println("Might be time for you to give up. Do you want to quit? (y/n)");
        Scanner scan = new Scanner(System.in);
        String quit = scan.nextLine();
        if (quit.charAt(0) == 'y') {
          System.out.println("You gave up after "+a+" tries. Better luck next time!");
          break;
        }
        
      }
    }
  }
  
  public static boolean validInputSize (int [] x) {
    if (x.length>4) {
      return false;
    }
    return true;
  }
  public static boolean validInputSign (int x) {
    if (x < 0) {
      return false;
    }
    return true;
  }
}