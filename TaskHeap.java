
/**
 * A heap, implemented as an array.
 * The elements in the heap are instances of the class TaskElement,
 * and the heap is ordered according to the Task instances wrapped by those objects.
 * <p>
 * IMPORTANT: Except the percolation (private) functions and the constructors, no single function may loop/recurse through all elements in the heap.
 */
public class TaskHeap {

    public static int capacity = 200; // the maximum number of elements in the heap
    /*
     * The array in which the elements are kept according to the heap order.
     * The following must always hold true:
     * 			if i < size then heap[i].heapIndex == i
     */
    TaskElement[] heap;
    int size; // the number of elements in the heap, it is required that size <= heap.length

    /**
     * Creates an empty heap which can contain 'capacity' elements.
     */
    public TaskHeap() {
        this.heap = new TaskElement[capacity];
        this.size = 0;
        for (int i = 0; i < this.heap.length; i++) {
            heap[i] = new TaskElement(new Task(0, "no task has given"));
        }
    }

    /**
     * Constructs a heap that may contain 'capacity' many elements, from a given array of TaskElements, of size at most 'capacity'.
     * This should be done according to the "build-heap" function studied in class.
     * NOTE: the heapIndex field of each TaskElement might be -1 (or incorrect).
     * You may NOT use the insert function of heap.
     * In this function you may use loops.
     */
    public TaskHeap(TaskElement[] arr) {
        this();
        this.size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            this.heap[i] = arr[i];
            this.heap[i].heapIndex = i;
        }
        for (int i = this.size / 2 - 1; i >= 0; i--) {
            PercDown(i, heap[i]);
        }

    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return this.size;
    }

    /**
     * Inserts a given element into the heap.
     *
     * @param e - the element to be inserted.
     */
    public void insert(TaskElement e) {
        size++;
        heap[size - 1] = e;
        e.heapIndex = size - 1;
        PercUp(e.heapIndex,e);
    }

    private void PercDown(int i, TaskElement task) {

        if (2 * (i + 1) > size) {
            heap[i] = task;
            heap[i].heapIndex = i;
        }
        if (2 * (i + 1) == size) {
            if (heap[2 * (i + 1) - 1].t.compareTo(task.t) == 1) {
                heap[i] = heap[2 * (i + 1) - 1];
                heap[i].heapIndex = i;
                heap[2 * (i + 1) - 1] = task;
                heap[2 * (i + 1) - 1].heapIndex = 2 * (i + 1) - 1;
            } else {
                heap[i] = task;
                heap[i].heapIndex = i;
            }
        }
        if (2 * (i + 1) < size) {
            int j = 0;
            if (heap[2 * (i + 1) - 1].t.compareTo(heap[2 * (i + 1)].t) == 1) {
                j = 2 * (i + 1) - 1;
            } else {
                j = 2 * (i + 1);
            }
            if (heap[j].t.compareTo(task.t) == 1) {
                heap[i] = heap[j];
                heap[i].heapIndex = i;
                PercDown(j, task);
            } else {
                heap[i] = task;
                heap[i].heapIndex = i;
            }
        }
    }


    private void PercUp(int i, TaskElement task) {
        int p = i / 2;
        if (i == 0) {
            heap[0] = task;
            heap[0].heapIndex = 0;
        } else if (heap[p].t.compareTo(task.t) == 1) {
            heap[i] = task;
            heap[i].heapIndex = i;
        } else {
            heap[i] = heap[p];
            heap[i].heapIndex = i;
            PercUp(p, task);
        }
    }

    /**
     * Returns and does not remove the element which wraps the task with maximal priority.
     *
     * @return the element which wraps the task with maximal priority.
     */
    public TaskElement findMax() {
        return heap[0];
    }

    /**
     * Returns and removes the element which wraps the task with maximal priority.
     *
     * @return the element which wraps the task with maximal priority.
     */
    public TaskElement extractMax() {
        if(this.size == 0){
            return null;
        }
        TaskElement maxPriorityTask = heap[0];
        heap[0] = heap[size - 1];
        size--;
        this.PercDown(0 ,heap[0]);
        return maxPriorityTask;
    }

    /**
     * Removes the element located at the given index.
     * <p>
     * Note: this function is not part of the standard heap API.
     * Make sure you understand how to implement it, and why it is required.
     * There are several ways this function could be implemented.
     * No matter how you choose to implement it, you need to consider the different possible edge cases.
     *
     * @param index
     */
    public void remove(int index) {
        TaskElement maxPriorityTask = heap[index];
        heap[index] = heap[size - 1];
        size--;
        PercDown(index ,heap[index]);
    }
    public void print(){
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i].t.priority + " " + heap[i].heapIndex + " " + i);
        }
    }

    public static void main(String[] args) {

        /*
         * A basic test for the heap.
         * You should be able to run this before implementing the queue.
         *
         * Expected outcome:
         * 	task: Add a new feature, priority: 10
         *	task: Solve a problem in production, priority: 100
         *	task: Solve a problem in production, priority: 100
         *	task: Develop a new feature, priority: 10
         *	task: Code Review, priority: 3
         *	task: Move to the new Kafka server, priority: 2
         *
         */

        Task a = new Task(10, "Add a new feature");
        Task b = new Task(3, "Code Review");
        Task c = new Task(2, "Move to the new Kafka server");
        TaskElement[] arr = {new TaskElement(a), new TaskElement(b), new TaskElement(c)};
        TaskHeap heap = new TaskHeap(arr);
        System.out.println(heap.findMax());
        Task d = new Task(100, "Solve a problem in production");
        heap.insert(new TaskElement(d));
        System.out.println();
        System.out.println(heap.findMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());

    }
}
