package PenkinJavaBasicHomework11;

import java.util.NoSuchElementException;  // в github vetka1

class MyLinkedList<T> {  // класс, реализующий связанный список
    private Node<T> first; // первый узел
    private Node<T> last; // последний узел
    private int size; // размер списка

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addFirst(T data) {     // добавление элемента в начало списка
        Node<T> newNode = new Node<>(data);
        if (first == null) { // если список пуст, то новый элемент и будет первым и последним
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first; // новый элемент ссылается на текущий первый элемент
            first.prev = newNode;
            first = newNode; // новый элемент ссылается на текущий первый элемент
        }
        size++; //увеличиваем размер списка
    }

    public void addLast(T data) {     // добавление элемента в конец списка

        Node<T> newNode = new Node<>(data);
        if (last == null) {   // если список пуст, то новый элемент и будет первым и последним
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode; // текущий последний элемент ссылается на новый элемент
            last = newNode; // новый элемент становится последним
        }
        size++; //увеличиваем размер списка
    }

    public T getFirst() {      // получение первого элемента списка
        if (first == null) {   // если список пуст, то возвращаем null
            throw new NoSuchElementException("Список пуст");
        }
        return first.data;
    }

    public T getLast() {       // получение последнего элемента списка
        if (last == null) {    // если список пуст, то возвращаем null
            throw new NoSuchElementException("Список пуст");
        }
        return last.data;
    }

    public void add(int position, T data) {       // добавление элемента на указанную позицию
        if (position < 0 || position > size) {    // проверяем корректность позиции
            throw new IndexOutOfBoundsException("Недопустимая позиция");
        }
        if (position == 0) {    // если позиция 0, то добавляем в начало списка
            addFirst(data);
        } else if (position == size) {  // если позиция равна размеру списка, то добавляем в конец списка
            addLast(data);
        } else {
            Node<T> temp = getNode(position);
            Node<T> newNode = new Node<>(data);
            newNode.prev = temp.prev;
            newNode.next = temp;
            temp.prev.next = newNode;
            temp.prev = newNode;
            size++;
        }
    }

    public T remove(int position) {     // удаление элемента с указанным номером позиции
        if (position < 0 || position >= size) {  // проверяем корректность позиции
            throw new IndexOutOfBoundsException("Недопустимая позиция");
        }
        Node<T> temp = getNode(position);
        if (temp.prev == null) {
            first = temp.next;
        } else {
            temp.prev.next = temp.next;
        }
        if (temp.next == null) {
            last = temp.prev;
        } else {
            temp.next.prev = temp.prev;
        }
        size--;
        return temp.data;
    }

    public T get(int position) {  // возвращение элемента с указанным номером позиции
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Недопустимая позиция");
        }
        Node<T> temp = getNode(position);
        return temp.data;
    }

    public int getSize() {  // возвращение количества элементов в списке.
        return size;
    }

    private Node<T> getNode(int position) {
        Node<T> temp;
        if (position < size / 2) {
            temp = first;
            for (int i = 0; i < position; i++) {
                temp = temp.next;
            }
        } else {
            temp = last;
            for (int i = size - 1; i > position; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }
}

class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        list = new MyLinkedList<>();
    }

    public void offer(T data) { // добавление элемента в очередь
        list.addLast(data);
    }

    public T poll() {   // забор элемента из очереди
        return list.remove(0);
    }
}

class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {  // помещение элемента в стек
        list = new MyLinkedList<>();
    }

    public void push(T data) {  // забор элемента из стека
        list.addFirst(data);
    }

    public T pop() {
        return list.remove(0);
    }
}

class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList<>();  // п.1 сделал
        System.out.println("-----------------------------");
        linkedList.addFirst(3);
        linkedList.addFirst(8);
        linkedList.addLast(5);
        System.out.println(linkedList.getFirst()); // Вывод в консоль 8
        System.out.println(linkedList.getLast());  // Вывод в консоль 5
        linkedList.add(1, 4);
        System.out.println(linkedList.get(1));  // Вывод в консоль 4
        linkedList.remove(2);
        System.out.println(linkedList.getSize()); // Вывод в консоль 3

        MyQueue<String> queue = new MyQueue<>();  // п.2
        queue.offer("Пункт 2 домашнего задания 11");
        queue.offer("Работает");
        System.out.println("-----------------------------");
        System.out.println(queue.poll()); // Вывод в консоль "Пункт 2 домашнего задания 11"
        System.out.println(queue.poll()); // Вывод в консоль "Работает"
        System.out.println("-----------------------------");

        MyStack<Integer> stack = new MyStack<>(); // п.3 сделал
        stack.push(25);
        stack.push(9);
        System.out.println(stack.pop()); // Вывод в консоль 9
        System.out.println(stack.pop()); // Вывод в консоль 25

        int[] array = {365, 53, 78, 15, 26, 7, 64, 31}; // п.4 сделал
        System.out.println("-----------------------------");
        System.out.print("Массив, отсортированный по возрастанию: ");
        array = sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}