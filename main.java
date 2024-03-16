import java.util.LinkedList;
import java.util.Queue;

interface QueueBehaviour<T> {
    void enqueue(T person);

    T dequeue();
}

interface MarketBehaviour<T> {
    void acceptOrder(T order);

    T completeOrder();
}

public class Market<T> implements QueueBehaviour<T>, MarketBehaviour<T> {
    private Queue<T> queue;
    private T currentOrder;

    public Market() {
        queue = new LinkedList<>();
    }

    @Override
    public void enqueue(T person) {
        queue.add(person);
    }

    @Override
    public T dequeue() {
        return queue.poll();
    }

    @Override
    public void acceptOrder(T order) {
        if (currentOrder == null) {
            currentOrder = order;
            System.out.println("Заказ принят: " + order);
        } else {
            System.out.println("Заказ уже в обработке.");
        }
    }

    @Override
    public T completeOrder() {
        if (currentOrder != null) {
            T completedOrder = currentOrder;
            currentOrder = null;
            System.out.println("Заказ выполнен: " + completedOrder);
            return completedOrder;
        } else {
            System.out.println("Нет текущего заказа для выполнения.");
            return null;
        }
    }

    public void update() {
        if (currentOrder == null && !queue.isEmpty()) {
            T nextOrder = dequeue();
            acceptOrder(nextOrder);
        }
    }

    public static void main(String[] args) {
        Market<String> market = new Market<>();
        market.enqueue("Покупатель 1");
        market.enqueue("Покупатель 2");

        market.update();
        market.update();
        market.update();
        market.update();
    }
}
