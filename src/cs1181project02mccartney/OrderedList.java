/**
 * This class creates the ordered linked list with the help of all the functions
 * this program executes in methods through the class.
 */
package cs1181project02mccartney;

/**
 * Name:        Joseph McCartney
 * Section:     6
 * TA:          Quackenbush
 * Instructor:  R. Volkers
 */
public class OrderedList {

    private class Node<Customer> {

        public Customer payload;
        public double keyValue;
        public Node next;

        public Node(Customer payload, double value) {
            this.payload = payload;
            this.keyValue = value;
        }
    }

    private Node first = new Node(null, 0);

    /**
     * checks to see if the list is empty.
     * @return if the list is empty in a boolean statement.
     */
    public boolean empty() {
        return first.next == null;
    }

    /**
     * This method adds a new item into the list at the appropriate location
     * based on the item's key value.
     * @param payload the object stored by the item.
     * @param key the value used to determine location in the list.
     */
    public void insert(Customer payload, double key) {
        Node current = first.next;
        Node newNode = new Node(payload, key);
        if (first.next == null) {
            first.next = newNode;
            return;
        }
        current = first;
        if (current.next == null) {
            current.next = newNode;
            return;
        }
        while (current.next != null) {
            if (current.next.keyValue > key) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
        current.next = newNode;
    }

    /**
     * This method removes an item at a given key value.
     * @return 
     * @throws java.lang.Exception
     */
    public Customer dequeue() throws Exception {

        // Case for if the queue is empty
        if (first.next == null) {
            throw new Exception("Cannot dequeue - queue is empty");
        }

        Node<Customer> returnNode = first.next;

        // Case for if there is only one item in the queue
        if (first.next.next == null) {
            first.next = null;
            return returnNode.payload;
        }
        
        // Default case
        first.next = first.next.next;
        return returnNode.payload;

    }
}
