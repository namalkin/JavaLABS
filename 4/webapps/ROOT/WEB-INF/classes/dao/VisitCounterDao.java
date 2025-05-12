package dao;

import domain.VisitCounter;
import java.io.*;

/**
 * Класс VisitCounterDao обеспечивает сохранение и загрузку счетчика посещений из файла.
 */
public class VisitCounterDao {
    private final String filePath;

    /**
     * Конструктор VisitCounterDao.
     * @param filePath путь к файлу для хранения счетчика
     */
    public VisitCounterDao(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Загрузить счетчик посещений из файла.
     * @return объект VisitCounter
     */
    public VisitCounter load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (VisitCounter) ois.readObject();
        } catch (Exception e) {
            return new VisitCounter();
        }
    }

    /**
     * Сохранить счетчик посещений в файл.
     * @param counter объект VisitCounter
     */
    public void save(VisitCounter counter) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
