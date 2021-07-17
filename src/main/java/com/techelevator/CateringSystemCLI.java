package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import com.techelevator.view.Menu;

public class CateringSystemCLI {

	private static final String DISPLAY_CATERING_ITEMS = "Display Catering Items"; // these just make our lives easier below
	private static final String ORDER = "Order";
	private static final String QUIT = "Quit";
	private static final String[] MAIN_MENU = { DISPLAY_CATERING_ITEMS, ORDER, QUIT};
	private static final String ADD_MONEY = "Add Money";
	private static final String SELECT_PRODUCTS = "Select Products";
	private static final String COMPLETE_TRANSACTION = "Complete Transaction";
	private static final String[] ORDER_MENU_OPTIONS = {ADD_MONEY, SELECT_PRODUCTS, COMPLETE_TRANSACTION};
	private Menu menu;
	List<Item> purchasedItems = new ArrayList<Item>(); // keeps track of what has been removed(purchased)

	public CateringSystemCLI(Menu menu) { // makes our lives easier below
		this.menu = menu;
	}

	public void run() throws IOException { // the engine that powers the machine!!
		menu.showWelcomeMessage();

		Cafe cafe = new Cafe();
		File givenFilePath = new File(menu.getInventoryPathFromUser());
		Map<String, Item> inventoryMap = cafe.getInventory(givenFilePath);

		menu.showWelcomeMessage();

		while(true) {
			String choicesForMainMenu = menu.getChoiceFromOptions(MAIN_MENU, cafe.getCurrentAccountBalance());

			if(choicesForMainMenu.equals(DISPLAY_CATERING_ITEMS)) {

				while(true) {
					String[] itemsForSale = new String[inventoryMap.size()];
					Set<Map.Entry<String, Item>> entrySet = inventoryMap.entrySet();
					int count = 0; // helps display current inventory if a purchase is made and we go back to display items

					for (Entry<String, Item> item: entrySet) {
						String itemName = item.getKey();
						Item itemCost = item.getValue();
						itemsForSale[count] = itemName + " " + itemCost.toString();
						count++;
					}
					menu.displayCateringItems(itemsForSale);
					break;
				}
			}

			if(choicesForMainMenu.equals(ORDER)) {

				while(true) {
					String choicesForOrderMenu = menu.getChoiceFromOptions(ORDER_MENU_OPTIONS, cafe.getCurrentAccountBalance());

					if(choicesForOrderMenu.equals(ADD_MONEY)) {
						while(true) {
							try {
								System.out.println("\nPlease enter amount to deposit or (R) to return to last page: ");
								Scanner in = new Scanner(System.in);
								String input = in.nextLine();

								if(input.equalsIgnoreCase("R")) {
									break;
								} else {
									double amountEntered = Double.parseDouble(input);
									cafe.addToAccountBalance(amountEntered);
									System.out.println("\nThank you for your deposit, but feel free to add more! ;) \n");
									break;
								}
							} catch (NumberFormatException e) {
								System.out.println("\nPlease re-enter deposit amount in this format ($100 = 100.00): \n");
							}
						}

					} else if(choicesForOrderMenu.equals(SELECT_PRODUCTS)) {
						while(true) {
							System.out.println("\nPlease enter the item code(1st) and amount you'd like(2nd) one at a time. Example: B1(item code) 10(amount)");
							System.out.println("Once done adding to cart, press (R) to return to last page.");
							Scanner in = new Scanner(System.in);
							String input = in.next().toUpperCase().trim(); // takes the user input and grabs the item code

							if (input.equalsIgnoreCase("R")) {
								System.out.println();
								break;
							}

							String inputtedAmountOfItem = in.next().trim(); // takes the user input and grabs the amount requested

							if (inventoryMap.containsKey(input)) {

								if (inventoryMap.get(input).isAvailableToPurchase() && cafe.currentAccountBalance >= inventoryMap.get(input).getPrice() * Integer.parseInt(inputtedAmountOfItem) &&
										Integer.parseInt(inputtedAmountOfItem) <= (inventoryMap.get(input).getNumberOfItems())) {

									cafe.logPurchase(Integer.parseInt(inputtedAmountOfItem), inventoryMap.get(input).getName(), (Double.parseDouble(inputtedAmountOfItem) * inventoryMap.get(input).getPrice()), cafe.currentAccountBalance);

									for (int i = 1; i <= Integer.parseInt(inputtedAmountOfItem); i++) {
										inventoryMap.get(input).removeItem();
										purchasedItems.add(inventoryMap.get(input));
										cafe.currentAccountBalance -= inventoryMap.get(input).getPrice();
									}
									System.out.println("\nAdded to cart!");
								}
								else if (!inventoryMap.get(input).isAvailableToPurchase()) {
									System.out.println("\n*** Sorry " + inventoryMap.get(input).getName() + " is sold out! ***\n");
								}
								else if (Integer.parseInt(inputtedAmountOfItem) > (inventoryMap.get(input).getNumberOfItems())) {
									System.out.println("\n*** There is only " + inventoryMap.get(input).getNumberOfItems() + " " + inventoryMap.get(input).getName() + " left. Please change amount or choose other item. ***\n");
								} else {
									System.out.println("\n*** Insufficient Funds, Please make a deposit! ***\n");
									break;
								}
							}
							else{
								System.out.println("\n*** Something went wrong, please try again. ***\n");
								break;
							}
						}

					} else if(choicesForOrderMenu.equals(COMPLETE_TRANSACTION)){
						cafe.giveChangeAndPrintReceipt();
						cafe.logFile();
						cafe.currentAccountBalance = 0;
						break;
					}

					else {
						System.out.println("\n*** This is not a valid option, please enter a choice from above. ***\n"); //letter
					}

				}
			}

			if(choicesForMainMenu.equals(QUIT)) {
				System.out.println("\nThank you for your business!"); // always thank the customer!
				menu.stopApplication();
			}
		}
	}

	public static void main(String[] args) throws IOException { // runs the WHOLE SHOW
		Menu menu = new Menu(System.in, System.out);
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		try {
			cli.run();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}