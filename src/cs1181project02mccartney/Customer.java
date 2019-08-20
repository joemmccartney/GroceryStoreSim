/*
 * This class holds the customer object and keeps track of all the important
 * values of a given customer.
 */
package cs1181project02mccartney;

/**
 * Name:        Joseph McCartney
 * Section:     6
 * TA:          Quackenbush
 * Instructor:  R. Volkers
 */
public class Customer {
    
    private final double arrivalTime;
    private final int numOfItems;
    private final double avgTime;
    private final double startCheckout;
    private double endCheckout;
    private int laneNum;
    private int laneType;
    
    /**
     * Defined constructor.
     * @param arrivalTime
     * @param numOfItems
     * @param avgTime
     */
    public Customer(double arrivalTime, int numOfItems, double avgTime) {
        
       this.arrivalTime = arrivalTime;
       this.numOfItems = numOfItems;
       this.avgTime = avgTime;
       this.startCheckout = arrivalTime + (numOfItems * avgTime);
       
    }
    
    /**
     * Getter for arrivalTime
     * @return arrivalTime
     */
    public double getArrivalTime() {
        return this.arrivalTime;
    }
    
    /**
     * Getter for numOfItems
     * @return numOfItems
     */
    public int getNumOfItems() {
        return this.numOfItems;
    }
    
    /**
     * Getter for startCheckout
     * @return startCheckout
     */
    public double getStartCheckout() {
        return this.startCheckout;
    }
    
    /**
     * Getter for endCheckout
     * @return endCheckout
     */
    public double getEndCheckout() {
        return this.endCheckout;
    }
    
    /**
     * Getter for laneNum
     * @return laneNum
     */
    public int getLaneNum() {
        return this.laneNum;
    }
    
    /**
     * Getter for laneType
     * @return laneType
     */
    public int getLaneType() {
        return this.laneType;
    }
    
    /**
     * Setter for endCheckout
     * @param endCheckout the value endCheckout is being set to in double form
     */
    public void setEndCheckout(double endCheckout) {
        this.endCheckout = endCheckout;
    }
    
    /**
     * Setter for laneNum
     * @param laneNum the value laneNum is being set to in int form
     */
    public void setLaneNum(int laneNum) {
        this.laneNum = laneNum;
    }
    
    /**
     * Setter for laneType
     * @param laneType the value laneType is being set to in int form
     */
    public void setLaneType(int laneType) {
        this.laneType = laneType;
    }
    
    @Override
    public String toString() {
        String outputString;
        outputString = "Customer{ oSize = " + numOfItems + ", itemTime = " + (avgTime) 
                +"," + " arrivedAt: " + arrivalTime + ", shoppingDone:\n" 
                + startCheckout + ", checkOutDone: " + endCheckout + "\n";
        return outputString;
    }
    
}
