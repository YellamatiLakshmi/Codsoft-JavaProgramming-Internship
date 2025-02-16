import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static ArrayList<Long> leaderboard = new ArrayList();
    private static boolean showConfetti = false;

    public NumberGuessingGame() {
    }

    public static void main(String[] var0) {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception var13) {
            System.out.println("\u26a0\ufe0f Error setting UTF-8 encoding.");
        }

        Scanner var1 = new Scanner(System.in);

        String var2;
        do {
            showConfetti = false;
            int var3 = selectDifficulty(var1);
            int var4 = (new Random()).nextInt(100) + 1;
            int var5 = 0;
            long var6 = System.currentTimeMillis();
            System.out.println("\n\ud83c\udfae Welcome to the Number Guessing Game!");
            System.out.println("Try to guess the number between 1 and 100.");
            int var8 = -1;

            label40: while (true) {
                while (true) {
                    if (var8 == var4) {
                        break label40;
                    }

                    if (var5 >= var3) {
                        System.out.println(
                                "\n\u274c You've used all " + var3 + " attempts! The correct number was " + var4 + ".");
                        break label40;
                    }

                    var8 = getValidNumber(var1);
                    if (var8 >= 1 && var8 <= 100) {
                        ++var5;
                        if (var8 < var4) {
                            System.out.println("\ud83d\udcc9 Too low! Try again.");
                        } else if (var8 > var4) {
                            System.out.println("\ud83d\udcc8 Too high! Try again.");
                        } else {
                            long var9 = System.currentTimeMillis();
                            long var11 = (var9 - var6) / 1000L;
                            System.out.println(
                                    "\n\ud83c\udf89 Congratulations! You guessed the number in " + var5 + " attempts.");
                            System.out.println("\u23f3 Time taken: " + var11 + " seconds");
                            leaderboard.add(var11);
                            Collections.sort(leaderboard);
                            showConfetti = true;
                            (new Thread(NumberGuessingGame::runConfettiEffect)).start();
                        }
                    } else {
                        System.out.println("\u26a0\ufe0f Please enter a number between 1 and 100.");
                    }
                }
            }

            displayLeaderboard();
            System.out.print("\n\ud83d\udd04 Do you want to play again? (yes/no): ");
            var2 = var1.next();
            showConfetti = false;
        } while (var2.equalsIgnoreCase("yes"));

        System.out.println("\n\ud83d\udc4b Thanks for playing! See you next time.");
        var1.close();
    }

    private static int selectDifficulty(Scanner var0) {
        System.out.println("\n\ud83c\udf1f Select Difficulty Level:");
        System.out.println("1\ufe0f\u20e3 Easy (Max 10 Attempts)");
        System.out.println("2\ufe0f\u20e3 Medium (Max 7 Attempts)");
        System.out.println("3\ufe0f\u20e3 Hard (Max 5 Attempts)");
        System.out.print("\ud83d\udc49 Enter your choice (1/2/3): ");

        while (!var0.hasNextInt()) {
            System.out.print("\u274c Invalid input! Please enter 1, 2, or 3: ");
            var0.next();
        }

        int var1 = var0.nextInt();
        switch (var1) {
            case 1:
                return 10;
            case 2:
                return 7;
            case 3:
                return 5;
            default:
                System.out.println("\u274c Invalid choice! Defaulting to Medium.");
                return 7;
        }
    }

    private static void displayLeaderboard() {
        System.out.println("\n\ud83c\udfc6 Leaderboard (Top 5 Fastest Times):");

        for (int var0 = 0; var0 < Math.min(5, leaderboard.size()); ++var0) {
            System.out.println(
                    "\ud83c\udf96\ufe0f " + (var0 + 1) + ". " + String.valueOf(leaderboard.get(var0)) + " seconds");
        }

    }

    private static int getValidNumber(Scanner var0) {
        while (!var0.hasNextInt()) {
            System.out.print("\u274c Invalid input! Please enter a number: ");
            var0.next();
        }

        return var0.nextInt();
    }

    private static void runConfettiEffect() {
        String[] var0 = new String[] { "\ud83c\udf8a", "\ud83c\udf89", "\u2728", "\ud83c\udf88", "\ud83e\udd73" };
        Random var1 = new Random();

        while (showConfetti) {
            try {
                System.out.print("\r" + var0[var1.nextInt(var0.length)] + " ");
                Thread.sleep(200L);
            } catch (InterruptedException var3) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
