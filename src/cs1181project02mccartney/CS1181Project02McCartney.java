/*
 * This program runs a simulation of a stores checkout lanes. It determines a 
 * number of useful values and statistics about the lanes and allows for the lanes
 * to be evaluated for their efficiency.
 */
package cs1181project02mccartney;

import java.io.File;
import java.util.Scanner;

/**
 * Name:        Joseph McCartney
 * Section:     6
 * TA:          Quackenbush
 * Instructor:  R. Volkers
 */
public class CS1181Project02McCartney {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        // File variables
        File lanesFile = new File("CheckoutLanes.txt");
        File customerFile = new File("CustomerArrivals.txt");

        // Scanner variables
        Scanner scanLaneFile = new Scanner(lanesFile);
        Scanner scanCustomerFile = new Scanner(customerFile);

        // Creates an array of regular CheckoutLanes and an array of express
        // CheckoutLanes
        CheckoutLane[] regLaneArray = new CheckoutLane[scanLaneFile.nextInt()];
        regLaneArray = GenerateLane(regLaneArray, scanLaneFile);
        CheckoutLane[] expLaneArray = new CheckoutLane[scanLaneFile.nextInt()];
        expLaneArray = GenerateLane(expLaneArray, scanLaneFile);

        OrderedList customers = GenerateCustomers(scanCustomerFile);

        // Handles the events for the shopping being completed and checking being
        // completed.
        while (!customers.empty()) {
            Customer currentCustomer = customers.dequeue();
            customers.insert(currentCustomer, currentCustomer.getStartCheckout());
            ShoppingDoneEventHandler(currentCustomer, regLaneArray, expLaneArray);
            CheckoutDoneEventHandler(currentCustomer, regLaneArray, expLaneArray);
            customers.dequeue();
        }
        for (int i = 0; i < regLaneArray.length; i++) {
            regLaneArray[i].calculateAverageValues();
        }
        for (int i = 0; i < expLaneArray.length; i++) {
            expLaneArray[i].calculateAverageValues();
        }
        for (int i = 0; i < regLaneArray.length; i++) {
            System.out.print(regLaneArray[i].toString());
        }
        for (int i = 0; i < expLaneArray.length; i++) {
            System.out.print(expLaneArray[i].toString());
        }

    }

    /**
     * Creates an array of CheckoutLanes
     * @param laneArray The array being filled and subsequently returned
     * @param laneFile The scanner used to read the file that contains the information
     * for each CheckoutLane
     * @return the array of CheckoutLanes
     */
    public static CheckoutLane[] GenerateLane(CheckoutLane[] laneArray, Scanner laneFile) {

        for (int i = 0; i < laneArray.length; i++) {
            CheckoutLane temp = new CheckoutLane(laneFile.nextDouble(), laneFile.nextDouble());
            laneArray[i] = temp;
        }
        return laneArray;
    }

    /**
     * Creates an array of Customers that holds all Customers in the simulation
     * @param customerFile The scanner used to read the file that contains the 
     * information for each Customer
     * @return The array of Customers
     */
    public static OrderedList GenerateCustomers(Scanner customerFile) {

        OrderedList allCustomers = new OrderedList();
        while (customerFile.hasNext()) {
            Customer temp = new Customer(customerFile.nextDouble(), customerFile.nextInt(),
                    customerFile.nextDouble());
            allCustomers.insert(temp, temp.getStartCheckout());
        }
        return allCustomers;
    }

    /**
     * Handles the event of a given Customer finishing shopping and heading to a
     * CheckoutLane by determining what time of lane they should be added to and
     * then adding them to the lane with the fewest amount of Customers currently
     * waiting.
     * @param customer The Customer to be added to a CheckoutLane
     * @param regLane The array of regular CheckoutLanes
     * @param expLane The array of express CheckoutLanes
     */
    public static void ShoppingDoneEventHandler(Customer customer, CheckoutLane[] regLane,
            CheckoutLane[] expLane) {
        int laneIndex = 0;
        int minLength = 10000;
        if (customer.getNumOfItems() > 12) {
            for (int i = 0; i < regLane.length; i++) {
                if (regLane[i].getCustomersWaiting() < minLength) {
                    minLength = regLane[i].getCustomersWaiting();
                    laneIndex = i;
                }
            }
            customer.setLaneNum(laneIndex);
            customer.setLaneType(0);
            regLane[laneIndex].addCustomerToWaitingLine(customer);
        } else {
            for (int i = 0; i < expLane.length; i++) {
                if (expLane[i].getCustomersWaiting() < minLength) {
                    minLength = expLane[i].getCustomersWaiting();
                    laneIndex = i;
                }
            }
            customer.setLaneNum(laneIndex);
            customer.setLaneType(1);
            expLane[laneIndex].addCustomerToWaitingLine(customer);
        }
    }

    /**
     * Handles the event of a given Customer completing checkout by removing them
     * from the simulation and printing their information to the console.
     * @param customer The Customer being removed
     * @param regLane The array of regular CheckoutLanes
     * @param expLane The array of express CheckoutLanes
     * @throws Exception
     */
    public static void CheckoutDoneEventHandler(Customer customer, CheckoutLane[] regLane,
            CheckoutLane[] expLane) throws Exception {

        if (customer.getLaneType() == 0) {
            regLane[customer.getLaneNum()].customerDone(customer);
        } else {
            expLane[customer.getLaneNum()].customerDone(customer);
        }
        System.out.print(customer.toString());

    }

}
