# Завершённый проект "javalabs/3"

Проект реализован в рамках задания по объектно-ориентированному программированию на языке Java. В нём была разработана функциональная модель для работы с растениями, включающая подробную иерархию классов и удобный пользовательский интерфейс.

---

## Структура проекта

```
javalabs/3/
├── src/
│   ├── model/
│   │   ├── Plant.java
│   │   ├── FloweringPlant.java
│   │   └── TreePlant.java
│   ├── ui/
│   │   └── UI.java
│   └── Main.java
├── build/
├── compile.bat
└── run.bat
```

---

## Диаграмма классов

```mermaid
classDiagram
    class Plant {
        <<abstract>>
        -static List~Plant~ allPlants
        +double PlantAvg
        +long PlantCount
        #String name
        #double height
        #double fruitSize
        #String color
        #int age
        +Plant(name, height, fruitSize, color, age)
        +getName() String
        +setName(String)
        +getHeight() double
        +setHeight(double)
        +getFruitSize() double
        +setFruitSize(double)
        +getColor() String
        +setColor(String)
        +getAge() int
        +setAge(int)
        +static getAllPlants() List~Plant~
        +toString() String
        +static addFlowersTest() void
        +static countBelowAverageFruitSize() double[]
        +static findTallestPlant() Plant
        +static addFloweringPlant(String, double, double, String, int, String, int, boolean) void
        +static addTreePlant(String, double, double, String, int, double, String, boolean) void
    }

    class FloweringPlant {
        -String flowerColor
        -int petalCount
        -boolean isPerennial
        +FloweringPlant(name, height, fruitSize, color, age, flowerColor, petalCount, isPerennial)
        +toString() String
    }

    class TreePlant {
        -double trunkDiameter
        -String barkType
        -boolean isEvergreen
        +TreePlant(name, height, fruitSize, color, age, trunkDiameter, barkType, isEvergreen)
        +toString() String
    }

    class UI {
        -Scanner scanner
        +UI()
        +start() void
        -addPlant() void
        -editPlant(Plant) void
        -readInt(String) int
        -readDouble(String) double
        -readBoolean(String) boolean
        -readString(String) String
    }

    class Main {
        +main(String[]) void
    }

    Plant <|-- FloweringPlant
    Plant <|-- TreePlant
    Main ..> UI : creates
    UI ..> Plant : uses
```

---

## Запуск приложения

Для работы приложения выполните следующие шаги:

~~~bash
# Сначала скомпилируйте проект
compile.bat

# Затем запустите приложение
run.bat
~~~

---

## Реализованные возможности

В рамках проекта были выполнены следующие задачи:

- **Иерархия классов**  
  Созданы три класса (Plant, FloweringPlant, TreePlant), каждый из которых содержит более 5 полей и минимум 3 метода. Реализовано наследование с использованием абстрактного базового класса, который содержит общую коллекцию (статическое поле) для хранения всех объектов.

- **Анализ предметной области "Растения"**  
  На основе анализа предметной области спроектирована структура классов для работы с растениями. Программа демонстрирует следующие функции:
  - Определение самого высокого растения.
  - Подсчёт растений с размером плода ниже среднего.
  - Сортировка списка растений по их названиям.
  - Поиск растения по названию с возможностью редактирования одного из полей и выводом полной информации об объекте после внесённых изменений.

- **Пользовательский интерфейс**  
  Реализован интуитивно понятный UI, позволяющий пользователю:
  - Добавлять новые объекты растений.
  - Редактировать данные уже существующих объектов.
  - Получать полную информацию о выбранном растении после редактирования.
