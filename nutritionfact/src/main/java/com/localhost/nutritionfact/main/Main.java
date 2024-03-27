package com.localhost.nutritionfact.main;

import com.localhost.nutritionfact.db.DbInit;
import com.localhost.nutritionfact.dao.NutritionDaoImp;
import com.localhost.nutritionfact.dao.INutritionDao;
import com.localhost.nutritionfact.model.Nutrition;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @file Main.java
 * @brief Contains the main class for the Nutrition Facts Detector System.
 * 
 * This class provides the main entry point for the Nutrition Facts Detector System.
 * It presents a menu-driven interface to interact with nutrition facts, allowing
 * users to add, read, list, update, and delete nutrition information stored in the
 * database.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final INutritionDao nutritionDao = new NutritionDaoImp();
    
    /**
     * @brief Main method to start the Nutrition Facts Detector System.
     * 
     * This method initializes the database schema using the DbInit class, then enters
     * a loop to present a menu-driven interface for interacting with the system. The
     * user can choose various options such as adding, reading, updating, listing, and
     * deleting nutrition facts. The loop continues until the user chooses to exit the
     * system.
     */
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
        System.out.println("\n[Add a nutrition fact]");
        System.out.print("Enter Nutrition Info: \n");
        Nutrition nutrition = getNutritionDetailsFromUser(0);
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
        System.out.println("\n[Update a nutrition fact]\n");
        System.out.print("Enter Nutrition ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Nutrition Info: \n");
        Nutrition nutrition = getNutritionDetailsFromUser(id);
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

    private static Nutrition getNutritionDetailsFromUser(int nutritionId) {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Source Type, Only:[Video, BlogArticle, MedicalInfo]: ");
        String sourceType = scanner.nextLine();
        List<String> allowedTypes = Arrays.asList("Video", "MedicalInfo", "BlogArticle");
        if (!allowedTypes.contains(sourceType)) {
            throw new IllegalArgumentException("Invalid source type. Allowed types are: Video, MedicalInfo, BlogArticle.");
        }
        System.out.print("Source Details: ");
        String sourceDetails = scanner.nextLine();
        return new Nutrition(nutritionId, title, description, sourceType, sourceDetails);
    }
}