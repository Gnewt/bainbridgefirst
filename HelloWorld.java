// @author Ronnie Nigash
// Our first push/pull test file
// Makes a list to add names to, then prints the list to the console with different greetings for each name
import java.util.ArrayList;
import java.util.*;
import java.util.Random;
public class HelloWorld {
	
	public static void main(String[] Args){
		ArrayList<String> nameList = new ArrayList<String>();
		Random generator = new Random();
		
        nameList.add("Ronnie Nigash");
        nameList.add("Mr. Chee");
        nameList.add("Joe Van Dyke");
        nameList.add("Binnur");
        nameList.add("Tarkan Al-Kazily");
        nameList.add("Faust");
        nameList.add("Ben DeVries");
        nameList.add("Keet Curtis");
		
		loopDaLoop(nameList, generator);
	}

	private static void loopDaLoop (ArrayList<String> list, Random generator){
		for(String name : list){
			
			List<String> greetList = Arrays.asList("hi!", "how are you?",
                                                   "did you do your homework?",
                                                   "good morning.", "what's up?",
                                                   "merhabalar!!", "welcome!",
                                                   "allons-y!", "geronimo!");
			int randNum = generator.nextInt(greetList.size());
			
			System.out.printf("%s says \"%s\"\n", name, greetList.get(randNum));
		}
	}
}
