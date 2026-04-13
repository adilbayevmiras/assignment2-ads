import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndexForAdd(index);
        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        ensureCapacity();
        data[size++] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return get(size - 1);
    }

    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        data[size - 1] = null;
        size--;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort() {
        for (int i = 1; i < size; i++) {
            T key = (T) data[i];

            if (!(key instanceof Comparable)) {
                throw new IllegalStateException("Elements must implement Comparable");
            }

            int j = i - 1;
            while (j >= 0 && ((Comparable<T>) data[j]).compareTo(key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null) {
                if (data[i] == null) return i;
            } else if (object.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null) {
                if (data[i] == null) return i;
            } else if (object.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            return (T) data[cursor++];
        }
    }
}