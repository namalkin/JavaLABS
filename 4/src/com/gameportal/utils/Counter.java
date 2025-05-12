package utils;

import java.io.*;

public class Counter {
    private static final String COUNTER_FILE = "counter.txt";
    private static int count = 0;

    static {
        // Читаем сохранённое значение при загрузке класса
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            String line = reader.readLine();
            count = Integer.parseInt(line);
        } catch (Exception e) {
            count = 0; // если файла нет или ошибка
        }
    }

    public static synchronized int increment() {
        count++;
        saveCounter();
        return count;
    }

    public static int getCount() {
        return count;
    }

    private static void saveCounter() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTER_FILE))) {
            writer.write(Integer.toString(count));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения счетчика: " + e.getMessage());
        }
    }
}
