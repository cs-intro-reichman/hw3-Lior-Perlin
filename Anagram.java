/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		if (str1.length() != str2.length()) {
			return false;
		}
		else {
			boolean found = false;
			while(!str1.isEmpty() && !str2.isEmpty()) {
				for (int i = 0; i < str2.length(); i++) {
					if(str2.charAt(i) == str1.charAt(0)) { //If we found the same letter
						found = true; // We make sure to note we found the letter
						str1 = str1.substring(1); // remove said letter from both strings
						str2 = str2.substring(0, i) + str2.substring(i + 1);
						break; // Get out of the loop because we removed the letter and we want to start searching for the next one from the beginning
					}
				}
				if(!found) { // If we went though the entirty of the second string and we did not find it, that means we have a letter that does not exist in both and so it is not an anagram
					return false;
				}
				found = false; // if it was found, lets reset the found boolean and go check for the next character
			}
		}
		if(str1.isEmpty() && str2.isEmpty()) { // Makes sure both strings are empty
			return true;
		}
		else { // That means one of the string is not Empty while the other is (because we went out of the while loop before)
			return true;
		}
		
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		for(int i = 0; i < str.length(); i++) { // A loop that runs through the entire string and removes the unwanted characters and spaces and changes the upper-case into lower case
			if(str.charAt(i) > 64 && str.charAt(i) < 91) { //It means we are looking at an upper case letter and we should change it
				str = str.substring(0, i) + (char)(str.charAt(i) + 32 ) + str.substring(i + 1);
			}
			if(str.charAt(i) > 122 || str.charAt(i) < 97) { //Checks if our currect char is not a letter (note that the upper cast letters are already changed to lower cast so they are safe)
				str = str.substring(0, i) + str.substring(i + 1); // It will delete the character
				i--;
			}
		}
		return str;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
		char ch;
		int rnd, counter = 0;
		while(counter < str.length()) {
			ch = str.charAt(0);
			rnd = (int)(Math.random() * (str.length())); // generates a random character at the string to change locations wit
			if(rnd != 0) {
				str = str.charAt(rnd) + str.substring(1, rnd) + ch + str.substring(rnd + 1);
			}
			counter ++;
		}
		return str;
	}
}
