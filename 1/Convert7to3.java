public class Convert7to3 {
    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("Использование: java Convert7to3 <число_в_семеричной_системе>");
            return;
        }
        
        try {
            int Int7Input = Integer.parseInt(args[0]);
            
            Int7 num7 = new Int7(Int7Input);
            int Int10 = num7.Convert7to10();
            Int3 num3 = new Int3(Int10);
            
            System.out.println("Число " + args[0] + " (семеричная система) = " 
                    + num3 + " (троичная система)");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргумент должен быть числом.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

class Int7 {
    private int number;
    
    public Int7(int number) {
        int temp = number;
        if (temp < 0) {
            throw new IllegalArgumentException("Цифра " + temp + " недопустима для семеричной системы.");
        }
        while (temp > 0) {
            int digit = temp % 10;
            if (digit < 0 || digit > 6) {
                throw new IllegalArgumentException("Цифра " + digit + " недопустима для семеричной системы.");
            }
            temp /= 10;
        }
        this.number = number;
    }
    
    public int Convert7to10() {
        int result = 0;
        int power = 1;  // Начинаем с 7^0
        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            result += digit * power;
            power *= 7;
            temp /= 10;
        }
        return result;
    }
}

class Int3 {
    private String representation;
    
    public Int3(int decimal) {
        representation = Convert10to3(decimal);
    }
    
    private String Convert10to3(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 3;
            sb.append(remainder);
            decimal /= 3;
        }
        return sb.reverse().toString();
    }
    
    @Override
    public String toString() {
        return representation;
    }
}
