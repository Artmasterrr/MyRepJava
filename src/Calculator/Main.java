package Calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = reader.readLine();
        System.out.println(calc(operation));
        reader.close();
       
    }

    static String finalResult;
    static int countArab = 0;
    static int countRom = 0;
    static int intMaxValue = 10;

    static int countSum = 0;
    static int countSubtract = 0;
    static int countDivide = 0;
    static int countMultiply = 0;


    static List<RomNum> romNums = new ArrayList<>();
    static List<Integer> arabicNums = new ArrayList<>();

    public static String calc(String input) throws Exception {

        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        int countTokens = tokenizer.countTokens();
        if (countTokens < 2)
        {
            throw new Exception("Строка это не математическая операция");
        }

        while ((tokenizer.hasMoreTokens())) {
            String token = tokenizer.nextToken();

            for (RomNum num : RomNum.values()) {
                if (token.equals(num.name())) {
                    if (num.getNumber() > intMaxValue) {
                        throw new Exception("Слишком большое число");
                    } else {
                        romNums.add(num);
                        countRom++;
                    }
                }
            }

            try{
                if (Integer.parseInt(token) > intMaxValue) {
                    throw new Exception("Слишком большое число");
                }
            }catch (NumberFormatException e){}

            if (token.contains(".")){
                throw new Exception("Вы не можете использовать тип Double");
            }

            for (int val = 0; val <= intMaxValue;val++) {
                if (token.equals(String.valueOf(val))) {
                    arabicNums.add(val);
                    countArab++;
                }
            }


            for (String str : MathOperations.getMathOperands()) {
                if (token.equals(str)) {
                    switch (str) {
                        case "+" -> countSum++;
                        case "-" -> countSubtract++;
                        case "/" -> countDivide++;
                        case "*" -> countMultiply++;
                    }
                }
            }

        }
        if (countArab == 1 && countRom == 1) {
            throw new Exception("Невозможно использовать арабские и римские цифры вместе");
        }
        if (countSum + countSubtract + countDivide + countMultiply + countArab + countRom != 3) {
            throw new Exception("Формат математической операции не соответствует задаче - два операнда и один оператор (+, -, /, *)");
        }


        if (countRom == 2) {
            if (countSum == 1) {
                finalResult = MathOperations.sumRom(romNums);
            }else if(countSubtract == 1){
                finalResult = MathOperations.subtractRom(romNums);
            }else if(countDivide == 1){
                finalResult = MathOperations.divideRom(romNums);
            }else if(countMultiply == 1){
                finalResult = MathOperations.multiplyRom(romNums);
            }
        }
        if (countArab == 2) {
            if (countSum == 1) {
                finalResult = MathOperations.sumArab(arabicNums);
            }else if(countSubtract == 1){
                finalResult = MathOperations.subtractArab(arabicNums);
            }else if(countDivide == 1){
                finalResult = MathOperations.divideArab(arabicNums);
            }else if(countMultiply == 1){
                finalResult = MathOperations.multiplyArab(arabicNums);
            }
        }return finalResult;
    }

}
class MathOperations {
    private static final List<String> mathOperands = new ArrayList<>()
    {{
        add("+");
        add("-");
        add("/");
        add("*");
    }};

    public static List<String> getMathOperands() {
        return mathOperands;
    }

    public static String sumRom(List <RomNum> y){
        int sum = y.get(0).getNumber() + y.get(1).getNumber();

        for(RomNum num : RomNum.values()) {
            if (num.getNumber() == sum) {
                return num.name();
            }
        }

        return null;
    }


    public static String subtractRom(List <RomNum> y) throws Exception {
        int result = y.get(0).getNumber() - y.get(1).getNumber();
        if(result <= 0){
            throw new Exception("Римские номера не могут принимать отрицательное или 0 значение");
        }else {
            for (RomNum num : RomNum.values()) {
                if (num.getNumber() == result) {
                    return num.name();
                }
            }
        }return null;
    }

    public static String divideRom(List <RomNum> y) throws Exception {
        int result = (y.get(0).getNumber() / y.get(1).getNumber());
        if (result<= 0){
            throw new Exception("Римские номера не могут принимать отрицательное или 0 значение");
        }

        for(RomNum num : RomNum.values()) {
            if (num.getNumber() == result) {
                return num.name();
            }
        }return null;
    }

    public static String multiplyRom(List <RomNum> y){
        int umn = y.get(0).getNumber() * y.get(1).getNumber();
        for(RomNum num : RomNum.values()) {
            if (num.getNumber() == umn) {
                return num.name();
            }
        }return null;
    }

    public static String sumArab(List<Integer>  y){
        int sum = y.get(0) + y.get(1);

        return String.valueOf(sum);
    }


    public static String subtractArab(List<Integer> y){
        int raz = y.get(0) - y.get(1);


        return String.valueOf(raz);
    }

    public static String divideArab(List<Integer> y){
        int del = y.get(0) / y.get(1);

        return String.valueOf(del);
    }

    public static String multiplyArab(List<Integer> y){
        int umn = y.get(0) * y.get(1);
        return String.valueOf(umn);
    }

}

enum RomNum {
    I (1), II (2), III (3), IV (4), V (5), VI (6),VII (7),VIII (8), IX (9), X (10),
    XI(11), XII(12), XIII (13), XIV (14), XV (15), XVI (16), XVII (17), XVIII (18), XIX (19), XX (20),
    XXI(21),XXII(22),XXIII(23),XXIV(24),XXV(25),XXVI(26),XXVII(27),XXVIII(28),XXIX(29),XXX(30),
    XXXI(31), XXXII(32),XXXIII(33),XXXIV(34),XXXV(35),XXXVI(36),XXXVII(37),XXXVIII(38),XXXIX(39),XL(40),
    XLI(41),	XLII(42),XLIII(43),XLIV(44),XLV(45),XLVI(46),XLVII(47),XLVIII(48),XLIX(49), L(50),
    LI(51),LII(52),LIII(53),LIV(54),LV(55),LVI(56),LVII(57),LVIII(58),LIX(59), LX(60),
    LXI(61),LXII(62),LXIII(63),LXIV(64),LXV(65),LXVI(66),LXVII(67),LXVIII(68), LXIX(69), LXX(70),
    LXXI(71),LXXII(72),LXXIII(73),LXXIV(74),LXXV(75),LXXVI(76),LXXVII(77),LXXVIII(78), LXXIX(79),LXXX(80),
    LXXXI(81),LXXXII(82),LXXXIII(83),LXXXIV(84),LXXXV(85), LXXXVI(86),LXXXVII(87),LXXXVIII(88),LXXXIX(89),XC(90),
    XCI(91),XCII(92),XCIII(93),XCIV(94),XCV(95),XCVI(96),XCVII(97),XCVIII(98),XCIX(99),C(100);
    private final int number;

    RomNum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
