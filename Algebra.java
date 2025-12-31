// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if(x2 < 0)
		{
			for(int i = 0; i > x2; i--) {
				x1--;
			}
		}
		else {
			for(int i = 0; i < x2; i++) {
				x1++;
			}	
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if(x2 < 0)
		{
			for(int i = 0; i > x2; i--) {
				x1++;
			}
		}
		else {
			for(int i = 0; i < x2; i++) {
			x1--;
			}	
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if(x1 == 0 || x2 == 0) { // If one of the numbers is "zero" then the times should be zero
			return 0;
		}
		int newNum = x1;
		if(x1 > 0 && x2 > 0) { //Checks if both are positive 
			for(int i = 1; i < x2; i++) {
				newNum = plus(newNum, x1);
			}
		}
		else if( x1 < 0 && x2 < 0) { // Checks if both numbers are negetive
			for(int i = 1; i > x2; i--) { // The loop in a negetive number should be done one additional time to make the number positive
				newNum = minus(newNum, x1);
			}
		}
		else { //That means the numbers are opposite signs
			if(x1 < 0) { // If the first number is the negetive one
				newNum = x2;
				for(int i = 1; i > x1; i--) { //We'll neeed to reduce the amount one time over the normal amount the make sure the number becomes negetive first
					newNum = minus(newNum, x2);
				}
			}
			else { // That means the second number is the negetive one
				for(int i = 1; i > x2; i--) { //We'll neeed to reduce the amount one time over the normal amount the make sure the number becomes negetive first
					newNum = minus(newNum, x1);
				}
			}
		}
		return newNum;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if(n == 0) {
			return 1;
		}
		else if(n < 0) { //Checks if exponent is negative
			n = times(n, -1); // If so I'd like to first make it negative and do the Pow the normal way
			int newNum = pow(x, n);
			return div(1,newNum); // And after that do the dividing by 1 to reverse it like a negative exponent should.
		}
		else {
			int newNum = x;
			for(int i = 1; i < n; i++) {
				newNum = times(newNum, x);
			}
			return newNum;
		}
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x2 == 0) { // If divider is '0' we should send a syntax error but I will return -1 for now
			return -1;
		}
		boolean negative = false;
		if(x2 < 0) { // If the number is negative, for the sake of convinience let's make it possitive but still remember is was negative
			negative = true;
			x2 = times (x2, -1);
		}
		if (x1 < 0) { // If the number is negative, for the sake of convinience let's make it possitive but still remember is was negative
			if(negative) { // If we are deviding 2 negative numbers then the division should be a positive so make sure the negative is false
				negative = false;
			}
			else {
				negative = true;
			}
			x1 = times(x1, -1);
		}
		int counter = 0; // Counts the number of times our denominator gets into a nominator
		while(x1 >= x2)
		{
			counter ++;
			x1 = minus(x1, x2);
		}
		if(negative) { //If one of the numbers (and only one) was a negative one, then we should return a negative answer, and so at the end we will make the counter a negative one
			counter = times(counter, -1);
		}
		return counter;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (x1 < 0) { //If the first number is a negative one
			while (x1 < 0) { // Make a loop that makes the number a positive one inside the modulo
				x1 = plus(x1, x2);
			}
		}
		int div = div(x1,x2);
		int mul = times(x2,div);
		return minus(x1, mul);
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if(x < 0) { // Not a valid enternce so return defult -1
			return -1;
		}
		else {
			int counter = 0;
			while (times(counter,counter) < x) {
				counter++;
			}
			if(times(counter,counter) > x) { // If we surpassed x too much then we should negate one
				counter--;
			}
			return counter;
		}
	}	  	  
}