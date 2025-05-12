package model;

/**
 * Класс FloweringPlant представляет цветущее растение,
 * расширяя функциональность класса Plant.
 */
public class FloweringPlant extends Plant {
    private String flowerColor; 
    private int petalCount;
    private boolean isPerennial;

    /**
     * Конструктор класса FloweringPlant
     *
     * @param name         Название растения
     * @param height       Высота растения
     * @param fruitSize    Размер плодов
     * @param color        Цвет растения
     * @param age          Возраст растения
     * @param flowerColor  Цвет цветка
     * @param petalCount   Количество лепестков
     * @param isPerennial  Является ли многолетним растением (true – да, false – нет)
     */
    public FloweringPlant(String name, double height, double fruitSize, String color, int age,
                          String flowerColor, int petalCount, boolean isPerennial) {
        super(name, height, fruitSize, color, age); // Вызов конструктора родительского класса Plant
        this.flowerColor = flowerColor;
        this.petalCount = petalCount;
        this.isPerennial = isPerennial;
    }

    /**
     * Переопределение метода toString для представления объекта в виде строки.
     *
     * @return Строковое представление цветущего растения.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Цвет цветка: %s, Лепестков: %d",
                flowerColor, petalCount);
    }

}
