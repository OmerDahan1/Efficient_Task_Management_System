

/**
 * A queue class, implemented as a linked list.
 * The nodes of the linked list are implemented as the TaskElement class.
 * <p>
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class TaskQueue {

    TaskElement first;
    TaskElement last;

    /**
     * Constructs an empty queue
     */
    public TaskQueue() {
        this.first = null;
        this.last = null;
    }

    /**
     * Removes and returns the first element in the queue
     *
     * @return the first element in the queue
     */
    public TaskElement dequeue() {
        TaskElement firstout = first;
        if (first == null) {
            throw new RuntimeException("underflow");
        }else if (first.next == null) {
            first = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
            return firstout;
    }

        /**
         * Returns and does not remove the first element in the queue
         *
         * @return the first element in the queue
         */
        public TaskElement peek () {
            return first;
        }

        /**
         * Adds a new element to the back of the queue
         *
         * @param node
         */
        public void enqueue (TaskElement node){
            if(last != null){
                last.next = node;
                node.prev = last;
                last = last.next;
            }else{
                first = node;
                last = first;
            }
        }

        /**
         *
         * @return true iff the queue is Empty
         */
        public boolean isEmpty () {
            return (first == null);
        }
    }
	

