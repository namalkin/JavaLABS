package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Абстрактный класс Plant (Растение), представляющий общие свойства и методы для всех растений.
 */
public abstract class Plant {
    // Статический список (коллекция) для хранения всех созданных растений
    private static final List<Plant> allPlants = new ArrayList<>();
    
    public double PlantAvg;
    public long PlantCount;

    /**
    * Название растения
    */
    protected String name;  
    /**
     * Высота растения
     */  
    protected double height;
    /** 
     * Размер плодов
     */    
    protected double fruitSize; 
    /**
     * Цвет растения 
    */
    protected String color;  
    /**
     * Возраст растения
     */  
    protected int age;           

    /**
     * Конструктор класса Plant
     *
     * @param name      Название растения
     * @param height    Высота растения
     * @param fruitSize Размер плодов
     * @param color     Цвет растения
     * @param age       Возраст растения
     */
    public Plant(String name, double height, double fruitSize, String color, int age) {
        this.name = name;
        this.height = height;
        this.fruitSize = fruitSize;
        this.color = color;
        this.age = age;
        allPlants.add(this); // Добавление растения в общий список
    }

    // Геттеры и сеттеры для доступа и изменения полей объекта

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getFruitSize() { return fruitSize; }
    public void setFruitSize(double fruitSize) { this.fruitSize = fruitSize; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /**
     * Получение списка всех созданных растений.
     *
     * @return Список всех экземпляров Plant.
     */
    public static List<Plant> getAllPlants() { return allPlants; }
    
    /**
     * Переопределенный метод toString для представления объекта в виде строки.
     *
     * @return Строковое представление растения.
     */
    @Override
    public String toString() {
        return String.format("Название: %s, Высота: %.2f, Размер плода: %.2f, Цвет: %s, Возраст: %d",
                name, height, fruitSize, color, age);
    }

    /**
     * Метод addFlowersTest заполняет коллекцию тестовыми данными
     */
    public static void addFlowersTest(){
        new FloweringPlant("Роза", 0.5, 0.1, "Красный", 1,
        "Красный", 5, true);
        new TreePlant("Дуб", 15.0, 0.05, "Коричневый", 50,
                1.0, "Шероховатая", false);
        new FloweringPlant("Подсолнух", 2.0, 0.2, "Желтый", 1,
                "Желтый", 34, false);
    }

    /**
     * Метод countBelowAverageFruitSize считывает количество растений, у которых размер плода меньше среднего.
     */
    public static double[] countBelowAverageFruitSize() {
        double plantAvg = Plant.getAllPlants().stream()
                .mapToDouble(Plant::getFruitSize)
                .average()
                .orElse(0.0);
        long plantCount = Plant.getAllPlants().stream()
                .filter(p -> p.getFruitSize() < plantAvg)
                .count();
        return new double[]{plantAvg, plantCount};
    }

    /**
     * Поиск самого высокого растения среди всех добавленных.
     */
    public static Plant findTallestPlant() {
        Plant tallest = Plant.getAllPlants().stream()
                .max(Comparator.comparingDouble(Plant::getHeight))
                .orElse(null);
        return tallest;
    }

    
    /**
     * Создает и добавляет в коллекцию новое цветущее растение
     * 
     * @param name название растения
     * @param height высота растения
     * @param fruitSize размер плода
     * @param color цвет растения
     * @param age возраст растения
     * @param flowerColor цвет цветка
     * @param petalCount количество лепестков
     * @param isPerennial является ли многолетним
     * @return созданное цветущее растение
     * @throws NullPointerException если какой-либо из параметров null
     */
    public static FloweringPlant addFloweringPlant(
            String name, 
            double height, 
            double fruitSize, 
            String color, 
            int age,
            String flowerColor, 
            int petalCount, 
            boolean isPerennial) {
        return new FloweringPlant(name, height, fruitSize, color, age, 
                                flowerColor, petalCount, isPerennial);
    }
    
    /**
     * Создает и добавляет в коллекцию новое дерево
     * 
     * @param name название дерева
     * @param height высота дерева
     * @param fruitSize размер плода
     * @param color цвет дерева
     * @param age возраст дерева
     * @param trunkDiameter диаметр ствола
     * @param barkType тип коры
     * @param isEvergreen является ли вечнозеленым
     * @return созданное дерево
     * @throws NullPointerException если какой-либо из параметров null
     */
    public static TreePlant addTreePlant(
            String name, 
            double height, 
            double fruitSize, 
            String color, 
            int age,
            double trunkDiameter, 
            String barkType, 
            boolean isEvergreen) {
        return new TreePlant(name, height, fruitSize, color, age, 
                           trunkDiameter, barkType, isEvergreen);
    }

}
