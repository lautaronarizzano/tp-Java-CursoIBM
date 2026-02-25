import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MoodTracker {
    public static void main(String[] args) {

        List<Mood> moods = new ArrayList<Mood>();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Press 'a' to add mood\n" +
								"'d' to delete mood(s)\n" +
								"'e' to edit mood\n" +
								"'s' to search for moods\n" +
								"'M' to get all moods\n" +
								"'w' to write the moods to a file\n" +
								"Type 'Exit' to exit");
            String menuOption = scanner.nextLine();
            switch(menuOption) {
                case "a":
                    continue;
                case "d":
                    continue;
                case "e":
                    continue;
                case "s":
                    continue;
                case "m":
                    continue;
                case "w":
                    continue;
                case "exit":
                    continue;
                default: System.out.println("Not a valid input!");
						continue;
            }
        }

    }
}
