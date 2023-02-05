import java.util.*;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                                  // character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to a game of hangman!\nPress Enter to begin!");
        scanner.nextLine();

        String randomWord = generateRandomWord();
        // System.out.println(randomWord);

        String blanks = "_ ".repeat(randomWord.length());
        int incorrectGuessCount = 0;

        ArrayList<Character> incorrectGuesses = new ArrayList<Character>();

        while (incorrectGuessCount < 6) {
            System.out.println(gallows[incorrectGuessCount]);
            System.out.println("Word:\t" + blanks);
            System.out.println("Misses:\t" + incorrectGuessString(incorrectGuesses));

            if (blanks.replace(" ", "").equals(randomWord)) {
                System.out.println("GOOD JOB! You Win!");
                System.exit(0);
            }

            System.out.println("Guess:\t");
            String userInput = scanner.next();

            while (userInput.length() > 1) {
                System.out.println("Please enter only 1 letter:\t");
                userInput = scanner.next();
            }

            char guess = userInput.charAt(0);
            ArrayList<Integer> guessIndexes = checkGuess(guess, randomWord);
            if (guessIndexes.size() == 0) {
                incorrectGuessCount += 1;
                incorrectGuesses.add(guess);
            } else {
                blanks = updateBlanks(randomWord, guessIndexes, blanks);
            }

        }

        System.out.println("\nRIP!");
        System.out.println("The word was: '" + randomWord + "'");

    }

    public static String generateRandomWord() {
        return words[(int) (Math.random() * (words.length))];
    }

    public static String updateBlanks(String word, ArrayList<Integer> guessIndexes, String blanks) {
        String updatedBlanks = blanks;
        for (Integer guessIndex : guessIndexes) {
            if (guessIndex == 0) {
                updatedBlanks = word.charAt(guessIndex) + updatedBlanks.substring(1);
            } else {
                updatedBlanks = updatedBlanks.substring(0, guessIndex * 2) + word.charAt(guessIndex)
                        + updatedBlanks.substring(guessIndex * 2 + 1);
            }
        }
        return updatedBlanks;
    }

    public static String incorrectGuessString(ArrayList<Character> incorrectGuesses) {
        String incorrectGuessesString = "";
        for (char letter : incorrectGuesses) {
            incorrectGuessesString += letter;
        }
        return incorrectGuessesString;
    }

    public static ArrayList<Integer> checkGuess(char guess, String word) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                result.add(i);
            }
        }

        return result;
    }

}
