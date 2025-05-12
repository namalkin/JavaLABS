package domain;

import java.io.Serializable;

/**
 * Класс VisitCounter реализует счетчик посещений с возможностью сериализации.
 */
public class VisitCounter implements Serializable {
    private int count;

    /**
     * Конструктор по умолчанию. Счетчик равен 0.
     */
    public VisitCounter() { this.count = 0; }

    /**
     * Увеличить счетчик посещений на 1.
     */
    public synchronized void increment() { count++; }

    /**
     * Получить текущее значение счетчика.
     * @return количество посещений
     */
    public int getCount() { return count; }

    /**
     * Установить значение счетчика.
     * @param count новое значение
     */
    public void setCount(int count) { this.count = count; }
}
