package com.localhost.nutritionfact.main;

import com.localhost.nutritionfact.db.DbInit;
import com.localhost.nutritionfact.dao.NutritionDaoImp;
import com.localhost.nutritionfact.dao.INutritionDao;
import com.localhost.nutritionfact.model.Nutrition;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final INutritionDao nutritionDao = new NutritionDaoImp();
    public static void main(String[] args) {
        DbInit.initializeDatabase();

        while (true) {
            System.out.println("\nNutrition Facts Detector System");
            System.out.println("1. Add a new nutrition fact");
            System.out.println("2. Read a nutrition fact");
            System.out.println("3. List all nutrition facts");
            System.out.println("4. Update a nutrition fact");
            System.out.println("5. Delete a nutrition fact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNutrition();
                    break;
                case 2:
                    readNutrition();
                    break;
                case 3:
                    listAllNutritions();
                    break;
                case 4:
                    updateNutrition();
                    break;
                case 5:
                    deleteNutrition();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addNutrition() {
        Nutrition nutrition = getNutritionDetailsFromUser();
        nutritionDao.addNutrition(nutrition);
        System.out.println("Nutrition fact added successfully!");
    }

    private static void readNutrition() {
        System.out.println("\n[Read a nutrition fact]");
        System.out.print("Enter Nutrition ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Nutrition nutrition = nutritionDao.getNutrition(id);
        if (nutrition != null) {
            System.out.println(nutrition);
        } else {
            System.out.println("Nutrition fact not found.");
        }
    }

    private static void listAllNutritions() {
        System.out.println("\n[Listing all nutrition facts]");
        List<Nutrition> nutritions = nutritionDao.getAllNutritions();
        if (nutritions.isEmpty()) {
            System.out.println("No nutrition facts available.");
        } else {
            nutritions.forEach(System.out::println);
        }
    }

    private static void updateNutrition() {
        System.out.println("\n[Update a nutrition fact]");
        System.out.print("Enter Nutrition ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("New Title: ");
        String title = scanner.nextLine();
        System.out.print("New Description: ");
        String description = scanner.nextLine();
        System.out.print("New Source Type (Video, BlogArticle, MedicalInfo): ");
        String sourceType = scanner.nextLine();
        System.out.print("New Source Details: ");
        String sourceDetails = scanner.nextLine();

        Nutrition nutrition = new Nutrition(id, title, description, sourceType, sourceDetails);
        nutritionDao.updateNutrition(nutrition);
        System.out.println("Nutrition fact updated successfully!");
    }

    private static void deleteNutrition() {
        System.out.println("\n[Delete a nutrition fact]");
        System.out.print("Enter Nutrition ID to delete: ");
        int id = scanner.nextInt();
        nutritionDao.deleteNutrition(id);
        System.out.println("Nutrition fact deleted successfully.");
    }

    private static Nutrition getNutritionDetailsFromUser() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Source Type (Video, BlogArticle, MedicalInfo): ");
        String sourceType = scanner.nextLine();
        System.out.print("Source Details: ");
        String sourceDetails = scanner.nextLine();
        return new Nutrition(0, title, description, sourceType, sourceDetails);
    }
}