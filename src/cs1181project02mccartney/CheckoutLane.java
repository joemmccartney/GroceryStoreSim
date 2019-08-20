/*
 * This class holds the checkout lane object and keeps track of all the important
 * values of a given checkout lane.
 */
package cs1181project02mccartney;

/**
 * Name:        Joseph McCartney
 * Section:     6
 * TA:          Quackenbush
 * Instructor:  R. Volkers
 */
public class CheckoutLane {
    
    private final double checkoutTime;
    private final double paymentTime;
    private int customersServed;
    private int customersWaiting;
    private int maxWaitLine;
    private double avgLineLength;
    private double avgWaitTime;
    private double laneAvailableTime;
    private String toStringReturn;
    
    /**
     * Defined constructor
     * @param checkoutTime
     * @param paymentTime
     */
    public CheckoutLane(double checkoutTime, double paymentTime) {
        this.checkoutTime = checkoutTime;
        this.paymentTime = paymentTime;
        this.customersServed = 0;
        this.customersWaiting = 0;
        this.maxWaitLine = 0;
        this.laneAvailableTime = 0;
        this.toStringReturn = "";
    }
    
    /**
     * Getter for customersWaiting
     * @return customersWaiting
     */
    public int getCustomersWaiting() {
        return this.customersWaiting;
    }
    
    /**
     * Adds a customer to the waiting line by incrementing the amount of customers
     * waiting, updating the time that the line will be available at and by updating
     * the customers end checkout time.
     * @param customer the customer being added to the line and edited
     */
    public void addCustomerToWaitingLine(Customer customer) {
        if (customer.getStartCheckout() > laneAvailableTime) {
            laneAvailableTime = customer.getStartCheckout();
            customersWaiting = 0;
        } 
        customer.setEndCheckout(laneAvailableTime + (customer.getNumOfItems() * checkoutTime) 
                + paymentTime);
        laneAvailableTime = customer.getEndCheckout();
        avgWaitTime = avgWaitTime + (laneAvailableTime - customer.getStartCheckout());
        avgLineLength = avgLineLength + customersWaiting;
        customersWaiting++;
        if (customersWaiting > maxWaitLine) {
            maxWaitLine = customersWaiting;
        }
    }
    
    /**
     * Increments the amount of customers serviced by this particular checkout
     * lane and adds the current customer to the final return string.
     * @param customer the customer finished shopping and being removed from the
     * simulation
     * @throws Exception
     */
    public void customerDone(Customer customer) throws Exception {
        customersServed++;
        toStringReturn = toStringReturn + customer.toString();
    }
    
    /**
     * Calculates the average wait time and the average line length of the checkout
     * lane
     */
    public void calculateAverageValues() {
        avgWaitTime = avgWaitTime / customersServed;
        avgLineLength = avgLineLength / customersServed;
    }
    
    @Override
    public String toString() {
        return "Customers Serviced: " + customersServed + ", Max Line Length: "
                + maxWaitLine + ", Average Line Length: " + (avgLineLength)
                + ", Average Customer Wait: " + (avgWaitTime)
                + "\n";
    }
}
