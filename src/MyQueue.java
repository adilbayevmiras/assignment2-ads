public class MyQueue<T> {
    private final MyArrayList<T> list = new MyArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public void enqueue(T item) {
        list.add(item); // в конец
    }

    public T dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value = list.get(0);
        list.remove(0);
        return value;
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.get(0);
    }
}