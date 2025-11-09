import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class FinalsMain {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int userChoice = 0;     // user menu choice

        // object for food menu
        FoodMenu menu = new FoodMenu();

        // user order
        ArrayList<Food> order = new ArrayList<>();

        // loop main menu
        do{
            try{
                System.out.println("===Menu===");
                System.out.println("1. View Food Menu\n2. View Your Orders\n3. Proceed to Payment\n4. View Order History\n5. Exit\n");
                System.out.print("Enter your choice: ");
                userChoice = scan.nextInt();

                switch (userChoice){
                    // view food menu
                    case 1:
                        while (true){
                            // display the menu
                            menu.displayMenu();

                            // user input
                            System.out.print("\nEnter the food number to add to your order: ");
                            int menuChoice = scan.nextInt();

                            if (menuChoice >= 1 && menuChoice <= menu.getMenuSize()){
                                Food choice = menu.getFood(menuChoice - 1);

                                // add the selected food to order
                                order.add(choice);
                                System.out.println(choice.getName() + " added to your order!");
                                break;
                            }
                            else{
                                System.out.println("Your input is not in the menu. Try again.\n");
                                continue;
                            }
                        }
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("\nExiting...");
                        break;
                    default:
                        System.out.println("\nUser input '" + userChoice + "' does not belong to the menu.\n");
                }
            }catch (InputMismatchException e){      // exception handling
                System.out.println("\nInvalid input! Only number inputs are allowed.\n");
                scan.nextLine();    // clear invalid input
            }
        }while(userChoice != 5);    // loop until user chooses exit

        scan.close();
    }
}
