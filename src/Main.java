public class Main {
    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testStack();
        testQueue();
        testMinHeap();
    }

    private static void testArrayList() {
        System.out.println("=== MyArrayList ===");
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(10);
        list.add(20);
        list.addFirst(5);
        list.addLast(30);
        list.add(2, 15);

        printList(list);
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Index of 15: " + list.indexOf(15));

        list.removeFirst();
        list.removeLast();
        printList(list);

        list.add(1);
        list.add(100);
        list.sort();
        printList(list);

        System.out.println();
    }

    private static void testLinkedList() {
        System.out.println("=== MyLinkedList ===");
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);
        list.addFirst(5);
        list.addLast(30);
        list.add(2, 15);

        printList(list);
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Index of 15: " + list.indexOf(15));

        list.removeFirst();
        list.removeLast();
        printList(list);

        list.add(1);
        list.add(100);
        list.sort();
        printList(list);

        System.out.println();
    }

    private static void testStack() {
        System.out.println("=== MyStack ===");
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Peek after pop: " + stack.peek());
        System.out.println("Size: " + stack.size());
        System.out.println();
    }

    private static void testQueue() {
        System.out.println("=== MyQueue ===");
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Peek after dequeue: " + queue.peek());
        System.out.println("Size: " + queue.size());
        System.out.println();
    }

    private static void testMinHeap() {
        System.out.println("=== MyMinHeap ===");
        MyMinHeap<Integer> heap = new MyMinHeap<>();

        heap.insert(20);
        heap.insert(5);
        heap.insert(15);
        heap.insert(2);
        heap.insert(30);

        System.out.println("Min: " + heap.getMin());
        System.out.println("Extract min: " + heap.extractMin());
        System.out.println("New min: " + heap.getMin());
        System.out.println("Size: " + heap.size());

        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
        System.out.println();
        System.out.println();
    }

    private static <T> void printList(MyList<T> list) {
        for (T item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}