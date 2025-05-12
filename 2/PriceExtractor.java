import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * Класс PriceExtractor предназначен для извлечения цен из текстового файла.
 * Он считывает содержимое файла, ищет цены по заданному регулярному выражению
 * и выводит их в консоль.
 */
public class PriceExtractor {
    public static void main(String[] args) {

        // Имя файла
        String fileName = "prices.txt";
        List<String> validPrices = new ArrayList<>();
        List<String> invalidPrices = new ArrayList<>();

        // Регулярное выражение:
        // - (пробел|0|число без нулей) (,|.) (две цифры не больше) (пробел) (USD | RUR | EU)
        String regex = "^\\s*(?:0|[1-9]\\d*)(?:[.,]\\d{1,2})?\\s+(?:USD|RUR|EU)\\.?\\s*$";
        Pattern pattern = Pattern.compile(regex);

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        // Проходим по каждой строке из файла и проверяем соответствие регулярному выражению
        for (String line : lines) {
            if (pattern.matcher(line).matches()) {
                validPrices.add(line);
            } else {
                invalidPrices.add(line);
            }
        }

        // Вывод результатов
        System.out.println("Правильные выражения:");
        for (String valid : validPrices) {
            System.out.println(valid);
        }

        System.out.println("\nНеправильные выражения:");
        for (String invalid : invalidPrices) {
            System.out.println(invalid);
        }
    }
}
