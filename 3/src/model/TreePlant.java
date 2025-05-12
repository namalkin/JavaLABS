package model;

/**
 * Класс TreePlant представляет деревья и расширяет функциональность класса Plant.
 */
public class TreePlant extends Plant {
    private double trunkDiameter; 
    private String barkType;      
    private boolean isEvergreen;  

    /**
     * Конструктор класса TreePlant
     *
     * @param name          Название дерева
     * @param height        Высота дерева
     * @param fruitSize     Размер плодов (если есть)
     * @param color         Цвет дерева или листвы
     * @param age           Возраст дерева
     * @param trunkDiameter Диаметр ствола дерева
     * @param barkType      Тип коры дерева
     * @param isEvergreen   Является ли дерево вечнозеленым (true – да, false – нет)
     */
    public TreePlant(String name, double height, double fruitSize, String color, int age,
                     double trunkDiameter, String barkType, boolean isEvergreen) {
        super(name, height, fruitSize, color, age); // Вызов конструктора родительского класса Plant
        this.trunkDiameter = trunkDiameter;
        this.barkType = barkType;
        this.isEvergreen = isEvergreen;
    }

    /**
     * Переопределенный метод toString для представления объекта дерева в виде строки.
     *
     * @return Строковое представление дерева.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Диаметр ствола: %.2f, Кора: %s, Вечнозеленое: %b",
                trunkDiameter, barkType, isEvergreen);
    }

}
