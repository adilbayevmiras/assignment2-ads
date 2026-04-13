import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
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

    private MyNode nodeAt(int index) {
        checkIndex(index);

        if (index < size / 2) {
            MyNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            MyNode current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        nodeAt(index).data = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndexForAdd(index);

        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }

        MyNode current = nodeAt(index);
        MyNode newNode = new MyNode(item);
        MyNode before = current.prev;

        before.next = newNode;
        newNode.prev = before;
        newNode.next = current;
        current.prev = newNode;

        size++;
    }

    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);

        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item);

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        return nodeAt(index).data;
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }

        MyNode current = nodeAt(index);
        MyNode before = current.prev;
        MyNode after = current.next;

        before.next = after;
        after.prev = before;

        current.next = null;
        current.prev = null;
        size--;
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }

        if (size == 1) {
            head = tail = null;
        } else {
            MyNode oldHead = head;
            head = head.next;
            oldHead.next = null;
            head.prev = null;
        }

        size--;
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }

        if (size == 1) {
            head = tail = null;
        } else {
            MyNode oldTail = tail;
            tail = tail.prev;
            tail.next = null;
            oldTail.prev = null;
        }

        size--;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort() {
        if (size < 2) return;

        for (MyNode i = head.next; i != null; i = i.next) {
            T key = i.data;

            if (!(key instanceof Comparable)) {
                throw new IllegalStateException("Elements must implement Comparable");
            }

            MyNode j = i.prev;
            while (j != null && ((Comparable<T>) j.data).compareTo(key) > 0) {
                j.next.data = j.data;
                j = j.prev;
            }

            if (j == null) {
                head.data = key;
            } else {
                j.next.data = key;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (MyNode current = head; current != null; current = current.next) {
            if (object == null) {
                if (current.data == null) return index;
            } else if (object.equals(current.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (MyNode current = tail; current != null; current = current.prev) {
            if (object == null) {
                if (current.data == null) return index;
            } else if (object.equals(current.data)) {
                return index;
            }
            index--;
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
        int i = 0;
        for (MyNode current = head; current != null; current = current.next) {
            result[i++] = current.data;
        }
        return result;
    }

    @Override
    public void clear() {
        MyNode current = head;
        while (current != null) {
            MyNode next = current.next;
            current.prev = null;
            current.next = null;
            current.data = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private MyNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            T value = current.data;
            current = current.next;
            return value;
        }
    }
}