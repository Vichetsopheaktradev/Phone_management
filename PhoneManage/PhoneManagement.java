import java.io.*;
import java.util.Scanner;

public class PhoneManagement {

    private static final String DATA_FOLDER = "Phones/";
    

    public static void main(String[] args) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            clearScreen();
            System.out.println("\n");
            System.out.println("============================");
            System.out.println("||     Phone Management   ||");
            System.out.println("============================");
            System.out.println("|| [1] Add Phone          ||");
            System.out.println("|| [2] Edit Phone         ||");
            System.out.println("|| [3] Delete Phone       ||");
            System.out.println("|| [4] Display Phones     ||");
            System.out.println("|| [5] Exit               ||");
            System.out.println("============================");
            System.out.print("Enter your choice:    ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    editContact(scanner);
                    break;
                case 3:
                    deleteContact(scanner);
                    break;
                case 4:
                    displayContacts(scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Phone Brand : ");
        String name = scanner.nextLine();

        System.out.print("Enter Ram         : ");
        String phoneRam = scanner.nextLine();

        System.out.print("Enter Rom         : ");
        String phoneRom = scanner.nextLine();

        System.out.print("Enter Color       : ");
        String phoneColor = scanner.nextLine();

        System.out.print("Enter Quantity    : ");
        String phoneQuantity = scanner.nextLine();

        System.out.print("Enter Price       : ");
        String phonePrice = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(DATA_FOLDER + name + ".txt")) {
            writer.println("Phone name      : " + name);
            writer.println("Phone Ram       : " + phoneRam);
            writer.println("Phone Rom       : " + phoneRom);
            writer.println("Phone color     : " + phoneColor);
            writer.println("Phone Quantity  : " + phoneQuantity);
            writer.println("Phone price     : " + phonePrice);
            System.out.println("Phone has been successfully added");
        } catch (IOException e) {
            System.out.println("An error occurred while finding the phone: " + e.getMessage());
        }
    }

    private static void editContact(Scanner scanner) {
        System.out.print("Enter name of the Phone to edit: ");
        String name = scanner.nextLine();
        File file = new File(DATA_FOLDER + name + ".txt");

        if (!file.exists()) {
            System.out.println("Phone has not been found!");
            return;
        }

        System.out.print("Enter new phone Ram      : ");
        String newPhoneRam = scanner.nextLine();

        System.out.print("Enter new phone Rom       : ");
        String newPhoneRom = scanner.nextLine();

        System.out.print("Enter new phone Color     : ");
        String newPhoneColor = scanner.nextLine();

        System.out.print("Enter new phone Quantity  : ");
        String newPhoneQuantity = scanner.nextLine();

        System.out.print("Enter new phone price     : ");
        String newPhonePrice = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(DATA_FOLDER + name + ".txt")) {
            writer.println("Phone name      : " + name);
            writer.println("Phone Ram       : " + newPhoneRam);
            writer.println("Phone Rom       : " + newPhoneRom);
            writer.println("Phone Color     : " + newPhoneColor);
            writer.println("Phone Quantity  : " + newPhoneQuantity);
            writer.println("Phone price     : " + newPhonePrice);
            System.out.println("Phone has been successfully updated");
        } catch (IOException e) {
            System.out.println("An error occurred while finding the pho ne: " + e.getMessage());
        }
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter name of phone to delete: ");
        String name = scanner.nextLine();
        File file = new File(DATA_FOLDER + name + ".txt");

        if (!file.exists()) {
            System.out.println("Phone has not been found");
            return;
        }

        if (file.delete()) {
            System.out.println("Phone has been successfully deleted from the list");
        } else {
            System.out.println("Failed to delete phone from the list");
        }
    }

    private static void displayContacts(Scanner scanner) {
        System.out.println("How would you like to display the phones?");
        System.out.println("[1] Display all details");
        System.out.println("[2] Display only names ");
        System.out.print("Enter your choice:");
    
        int displayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        File folder = new File(DATA_FOLDER);
        File[] files = folder.listFiles();
    
        if (files == null || files.length == 0) {
            System.out.println("No phones have been found");
            return;
        }
    
        boolean found = false;
    
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                System.out.println("============================");
                while ((line = reader.readLine()) != null) {
                    switch (displayChoice) {
                        case 1:
                            System.out.println(line);
                            break;
                        case 2:
                            if (line.startsWith("Phone name")) {
                                System.out.println(line);
                            }
                            break;
                        default:
                            System.out.println("Invalid display choice!");
                            return;
                    }
                }
                System.out.println("============================");
                System.out.println();
                found = true;
            } catch (IOException e) {
                System.out.println("An error occurred while finding the phone: " + e.getMessage());
            }
        }
    
        if (!found) {
            System.out.println("Phone is unavailable");
        }
    }      
    
}
