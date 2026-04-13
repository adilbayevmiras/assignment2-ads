public class MyStack<T> {
    private final MyArrayList<T> list = new MyArrayList<>();

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }

    public T push(T item) {
        list.add(item);
        return item;
    }

    public T pop() {
        T value = peek();
        list.remove(list.size() - 1);
        return value;
    }
}