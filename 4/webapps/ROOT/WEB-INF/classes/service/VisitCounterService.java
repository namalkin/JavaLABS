package service;

import domain.VisitCounter;
import dao.VisitCounterDao;

/**
 * Класс VisitCounterService инкапсулирует логику работы со счетчиком посещений,
 * обеспечивает сохранение и загрузку счетчика через DAO.
 */
public class VisitCounterService {
    private final VisitCounterDao dao;
    private VisitCounter counter;

    /**
     * Конструктор VisitCounterService.
     * @param dao DAO для работы с хранилищем счетчика
     */
    public VisitCounterService(VisitCounterDao dao) {
        this.dao = dao;
        this.counter = dao.load();
    }

    /**
     * Увеличить счетчик посещений и вернуть новое значение.
     * @return новое значение счетчика
     */
    public synchronized int incrementAndGet() {
        counter.increment();
        dao.save(counter);
        return counter.getCount();
    }

    /**
     * Получить текущее значение счетчика.
     * @return количество посещений
     */
    public int getCount() {
        return counter.getCount();
    }
}
