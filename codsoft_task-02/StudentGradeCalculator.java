import java.util.Scanner;

public class StudentGradeCalculator {
    public StudentGradeCalculator() {
    }

    private static int getValidatedMarks(Scanner var0, int var1, int var2) {
        while (true) {
            System.out.print("Enter obtained marks for Subject " + var1 + " (0-" + var2 + "): ");
            if (var0.hasNextInt()) {
                int var3 = var0.nextInt();
                if (var3 >= 0 && var3 <= var2) {
                    return var3;
                }

                System.out.println("Invalid input! Marks should be between 0 and " + var2 + ".");
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                var0.next();
            }
        }
    }

    private static String determineGrade(double var0) {
        if (var0 >= 90.0) {
            return "A+ (Excellent)";
        } else if (var0 >= 80.0) {
            return "A (Very Good)";
        } else if (var0 >= 70.0) {
            return "B (Good)";
        } else if (var0 >= 60.0) {
            return "C (Average)";
        } else {
            return var0 >= 50.0 ? "D (Pass)" : "F (Fail)";
        }
    }

    private static double calculateGPA(double var0) {
        if (var0 >= 90.0) {
            return 4.0;
        } else if (var0 >= 80.0) {
            return 3.7;
        } else if (var0 >= 70.0) {
            return 3.0;
        } else if (var0 >= 60.0) {
            return 2.5;
        } else {
            return var0 >= 50.0 ? 2.0 : 0.0;
        }
    }

    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        System.out.println("\n======================================");
        System.out.println("      STUDENT GRADE CALCULATOR       ");
        System.out.println("======================================\n");

        while (true) {
            while (true) {
                System.out.print("Enter the number of subjects: ");
                if (var1.hasNextInt()) {
                    int var2 = var1.nextInt();
                    if (var2 > 0) {
                        int var3 = 0;
                        int var4 = 0;

                        label31: for (int var5 = 1; var5 <= var2; ++var5) {
                            while (true) {
                                while (true) {
                                    System.out.print("Enter total marks for Subject " + var5 + ": ");
                                    if (var1.hasNextInt()) {
                                        int var6 = var1.nextInt();
                                        if (var6 > 0) {
                                            int var7 = getValidatedMarks(var1, var5, var6);
                                            var3 += var7;
                                            var4 += var6;
                                            continue label31;
                                        }

                                        System.out.println("Invalid input! Total marks must be greater than 0.");
                                    } else {
                                        System.out.println("Invalid input! Please enter a valid number.");
                                        var1.next();
                                    }
                                }
                            }
                        }

                        double var12 = (double) var3 / (double) var4 * 100.0;
                        String var13 = determineGrade(var12);
                        double var8 = calculateGPA(var12);
                        double var10 = var8 / 4.0 * 10.0;
                        System.out.println("\n======================================");
                        System.out.println("            RESULT SUMMARY            ");
                        System.out.println("======================================");
                        System.out.println("Total Obtained Marks:  " + var3 + " / " + var4);
                        System.out.printf("Average Percentage:    %.2f%%\n", var12);
                        System.out.println("Grade:                 " + var13);
                        System.out.printf("GPA (out of 4.0):      %.2f\n", var8);
                        System.out.printf("CGPA (out of 10):      %.2f\n", var10);
                        System.out.println("======================================\n");
                        var1.close();
                        return;
                    }

                    System.out.println("Invalid input! Number of subjects must be greater than 0.");
                } else {
                    System.out.println("Invalid input! Please enter a valid number.");
                    var1.next();
                }
            }
        }
    }
}
