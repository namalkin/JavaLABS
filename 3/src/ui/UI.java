package ui;

import model.Plant;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Класс UI реализует пользовательский интерфейс для работы с растениями.
 */
public class UI {
    // Объект Scanner для считывания пользовательского ввода с консоли
    private Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.forName("CP866")));

    /**
     * Конструктор UI.
     * Устанавливает кодировку вывода в CP866 для корректного отображения русских
     * символов в Windows.
     */
    public UI() {
        try {
            System.setOut(new PrintStream(System.out, true, "CP866"));
        } catch (Exception e) {
            System.out.println("Не удалось установить кодировку вывода: " + e.getMessage());
        }
    }

    /**
     * Метод запускает цикл пользовательского интерфейса.
     */
    public void start() {
        // Добавляем обработчик завершения программы
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> System.out.println("\nПрограмма прервана. Завершение работы.")));

        while (true) {
            System.out.println("\n1. Добавить растение");
            System.out.println("2. Найти самое высокое растение");
            System.out.println("3. Посчитать растения с размером плода ниже среднего");
            System.out.println("4. Сортировать растения по названию");
            System.out.println("5. Поиск и редактирование растения");
            System.out.println("6. Показать все растения");
            System.out.println("7. Добавить тестовые данные");
            System.out.println("0. Выход");

            int choice = readInt("Выберите пункт меню: ");

            switch (choice) {
                case 0:
                    return;
                case 1:
                    addPlant();
                    break;
                case 2: // Самое высокое
                    Plant tallest = Plant.findTallestPlant();
                    if (tallest != null) {
                        System.out.println("Самое высокое растение: " + tallest);
                    } else {
                        System.out.println("Список растений пуст.");
                    }
                    break;
                case 3: // Ниже среднего
                    double[] results = Plant.countBelowAverageFruitSize();
                    double avg = results[0];
                    long count = (long) results[1];
                    System.out.printf("Растений с размером плода ниже среднего (%.2f): %d%n", avg, count);
                    break;
                case 4: // Сортировка
                    Plant.getAllPlants().stream().sorted(Comparator.comparing(Plant::getName)).forEach(System.out::println);
                    break;
                case 5: // Поиск и редактирование 
                    String searchName = readString("Введите название растения для поиска: ");
                    Plant.getAllPlants().stream().filter(p -> p.getName().equalsIgnoreCase(searchName)).findFirst().ifPresentOrElse(this::editPlant, () -> System.out.println("Растение не найдено."));
                    break;
                case 6: // Все растения
                    if (Plant.getAllPlants().isEmpty()) {
                        System.out.println("Список растений пуст.");
                    } else {
                        Plant.getAllPlants().forEach(System.out::println);
                    }
                    break;
                case 7:
                    Plant.addFlowersTest();
                    System.out.println("Данные обновлены!");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    /**
     * Добавление нового растения (цветущего или дерева).
     */
    private void addPlant() {
        int type;
        while (true) {
            System.out.println("1. Цветущее растение");
            System.out.println("2. Дерево");
            type = readInt("Выберите тип растения: ");
            if (type == 1 || type == 2) {
                break;
            } else {
                System.out.println("Неверный тип растения. Пожалуйста, введите 1 или 2.");
            }
        }

        // Ввод общих параметров
        String name = readString("Название: ");
        double height = readDouble("Высота: ");
        double fruitSize = readDouble("Размер плода: ");
        String color = readString("Цвет: ");
        int age = readInt("Возраст: ");

        // Создание объекта в зависимости от типа растения
        if (type == 1) { // Цветущее растение
            String flowerColor = readString("Цвет цветка: ");
            int petalCount = readInt("Количество лепестков: ");
            boolean isPerennial = readBoolean("Многолетнее (true/false): ");
            Plant.addFloweringPlant(name, height, fruitSize, color, age,
                    flowerColor, petalCount, isPerennial);
        } else { // Дерево
            double trunkDiameter = readDouble("Диаметр ствола: ");
            String barkType = readString("Тип коры: ");
            boolean isEvergreen = readBoolean("Вечнозеленое (true/false): ");

            Plant.addTreePlant(name, height, fruitSize, color, age,
                    trunkDiameter, barkType, isEvergreen);
        }
        System.out.println("Растение успешно добавлено!");
    }

    /**
     * Редактирование найденного растения (изменение высоты).
     */
    private void editPlant(Plant plant) {
        System.out.println("Текущее растение: " + plant);
        double newHeight = readDouble("Введите новую высоту: ");
        plant.setHeight(newHeight);
        System.out.println("Обновленное растение: " + plant);
    }

    // Методы чтения пользовательского ввода с проверкой корректности данных

    /**
     * Проверка на int
     * @param prompt
     * @return int
     */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера после ввода числа
                if (value < 0) {
                    System.out.println("Ошибка: число не должно быть отрицательным. Попробуйте снова.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: требуется целое число. Попробуйте снова.");
                scanner.nextLine(); // Очистка буфера после ошибки
            }
        }
    }

    /**
     * Проверка на Double
     * @param prompt
     * @return double
     */
    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value < 0) {
                    System.out.println("Ошибка: число не должно быть отрицательным. Попробуйте снова.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: требуется число. Попробуйте снова.");
                scanner.nextLine(); // очищаем буфер
            }
        }
    }

    /**
     * Проверка на true false
     * @param prompt
     * @return true/false
     */
    private boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                boolean value = scanner.nextBoolean();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: требуется true или false. Попробуйте снова.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Ввод строки
     * @param prompt
     * @return строка
     */
    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
