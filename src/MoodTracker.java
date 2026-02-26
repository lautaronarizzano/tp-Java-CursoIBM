import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoodTracker {
    public static void main(String[] args) {

        List<Mood> moods = new ArrayList<Mood>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Press 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");
            String menuOption = scanner.nextLine();
            switch (menuOption) {
                case "a":
                    addMood(scanner, moods);
                    continue;
                case "d":
                    deleteMood(scanner, moods);
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
                default:
                    System.out.println("Not a valid input!");
                    continue;
            }
        }

    }

    public void addMood(Scanner scanner, ArrayList<Mood> moodsList) {
        System.out.println("Enter the mood name");
        String moodName = scanner.nextLine();
        System.out.println("Are you tracking the mood for a current day? y/n");
        String isForCurrentDate = scanner.nextLine();
        Mood moodToAdd = null;
        if (isForCurrentDate.equalsIgnoreCase("n")) {
            try {
                System.out.println("Input the date in MM/dd/yyyy format:");
                String moodDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                System.out.println("Input the time in HH:mm:ss format:");
                String moodTimeStr = scanner.nextLine();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                System.out.println("Add notes about this mood");
                String moodNotes = scanner.nextLine();
                if (moodNotes.equalsIgnoreCase("")) {
                    moodToAdd = new Mood(moodName, moodDate, moodTime);
                } else {
                    moodToAdd = new Mood(moodName, moodDate, moodTime, moodNotes);
                }
            } catch (DateTimeParseException dfe) {
                System.out.println("Incorrect format of date or time. Cannot create mood.\n" + dfe);
            }
        } else {
            System.out.println("Add notes about this mood");
            String moodNotes = scanner.nextLine();
            if (moodNotes.equalsIgnoreCase("")) {
                moodToAdd = new Mood(moodName);
            } else {
                moodToAdd = new Mood(moodName, moodNotes);
            }
        }
        try {
            boolean isValid = isMoodValid(moodToAdd, moodsList);
            if (isValid) {
                moodsList.add(moodToAdd);
                System.out.println("The mood has been added to the tracker");
            }
        } catch (InvalidMoodException ime) {
            System.out.println("The mood is not valid");
        }

    }

    public void deleteMood(Scanner scanner, ArrayList<Mood> moodsList) {
        System.out.println(
                "Enter 1. if you want to delete all the moods of any date \n 2. if you want to delete an mood");
        int deleteOption = Integer.parseInt(scanner.nextLine());
        if (deleteOption == 1) {
            System.out.println("Enter the date in dd/MM/yyyy");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateToDelete = LocalDate.parse(scanner.nextLine(), dateFormatter);
            for (Mood tempMood : moodsList) {
                if (tempMood.getDate().equals(dateToDelete)) {
                    moodsList.remove(tempMood);
                }
            }
            System.out.println("The moods have been deleted");
        } else if (deleteOption == 2) {
             System.out.println("Enter the name of the mood");
             String moodName = scanner.nextLine();
             System.out.println("Enter the date in dd/MM/yyyy");
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             LocalDate dateToDelete = LocalDate.parse(scanner.nextLine(), dateFormatter);
             DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
             System.out.println("Enter the time of the mood in HH:mm:ss");
             LocalTime timeToDelete = LocalTime.parse(scanner.nextLine(), timeFormatter);
             for(Mood tempMood : moodsList) {
                if (tempMood.getDate().equals(dateToDelete) && tempMood.getTime().equals(timeToDelete) && tempMood.getName().equals(moodName)) {
                    moodsList.remove(tempMood);
                }
             }


        } else {
            System.out.println("Date not valid");
        }
    }

    public static boolean isMoodValid(Mood mood, ArrayList<Mood> moodsList) throws InvalidMoodException {
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(mood)) {
                throw new InvalidMoodException();
            }
        }
        return true;
    }

}
