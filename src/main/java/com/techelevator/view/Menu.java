package com.techelevator.view;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import java.util.Scanner;

public class Menu {
	private PrintWriter out;
	private Scanner input;

	//Menu is the class where we have most of our methods as per requirement

	public void showWelcomeMessage() { // makes it look nice :D
		System.out.println();
		System.out.println("*************************");
		System.out.println("**     Weyland Corp.   **");
		System.out.println("**      Catering       **");
		System.out.println("*************************");
		System.out.println();
	}

	public String getInventoryPathFromUser(){ // gets file path from user
		System.out.println("To start please enter file path to inventory: ");
		String stringPath = input.nextLine();
		File inputFile = new File(stringPath);

		if( !inputFile.exists() ) {
			System.out.println();
			System.out.println("*** " + inputFile + " : does not exist! Please restart :D ***"); // if they enter something completely wrong
			stopApplication();
		} else if (!inputFile.isFile() ) {
			System.out.println();
			System.out.println("*** " + inputFile + " : is not a file! Please restart :D ***"); // if they enter something not a file
			stopApplication();
		}
		return stringPath;
	}

	public Menu(InputStream input, OutputStream output) { // allows us to better use "in" and "out" elsewhere
		this.input = new Scanner(input);
		this.out = new PrintWriter(output);
	}

	public void stopApplication(){ // to create desired result depending on if needed
		System.exit(0);
	}

	public String getChoiceFromOptions(String[] options, double balance) { // works with both menus (main and order) to get the command given by user
		String choice = null;

		while(choice == null) {
			try {
				displayMenuOptions(options, balance);
				choice = getChoiceFromUserInput(options);
			} catch (NumberFormatException e){
				if(e.equals(getChoiceFromUserInput(options))){
					System.out.println("\n*** " + options + " is not a valid option, please enter corresponding number to your choice ***\n");
				}
			}
		}
		return choice;
	}

	private String getChoiceFromUserInput(String[] options) { // for use with our CLI. gets user input and directs the destination based on input
		String choice = null;
		String userInput = input.nextLine().trim();

		try {
			int selectedOption = Integer.parseInt(userInput);
			// put back on line 82 in case of NumberFormat Exception
			if(userInput.equalsIgnoreCase(("R"))) {
				choice = "R";
				return choice;
			}
			if(selectedOption > 0 && selectedOption <= options.length) { // makes it work as intended
				choice = options[selectedOption - 1];
			} else{
				System.out.println("\n*** " + userInput + " is not a valid option, please enter corresponding number to your choice. ***\n"); //
			}
		} catch(NumberFormatException e) {
			System.out.println("\n*** " + userInput + " is not a valid option, please enter corresponding number to your choice. ***\n");
		}
		if(choice == null) {
			System.out.println("\n*** " + userInput + " is not a valid option, please enter corresponding number to your choice. ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(String[] options, double balance) { // formats the menus how we want it

		for(int i = 0; i < options.length; i++) {
			int optionNumber = i+1;
			System.out.println(optionNumber+") " + options[i]);
		}
		DecimalFormat myFormat = new DecimalFormat("#0.00");
		System.out.println("\nCurrent Account Balance: $" + myFormat.format(balance));
		System.out.print("\nEnter selection here: ");
		System.out.flush();
	}

	public void displayCateringItems(String[] itemsForSale) { // when display catering options is picked from main menu this will format it as desired
		String itemHeaderFormatted = String.format("%1$-10s %2$-11s %3$7s %4$10s", "Code", "Name", "Price", "Quantity\n");

		System.out.println(itemHeaderFormatted);
		System.out.println("-----------------------------------------");
		for(int i = 0; i < itemsForSale.length; i++) {
			System.out.println(itemsForSale[i]);
		}
		System.out.println("-----------------------------------------\n");
		System.out.flush();
	}

}