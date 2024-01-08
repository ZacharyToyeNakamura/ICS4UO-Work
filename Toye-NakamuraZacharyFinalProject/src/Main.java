public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String str = "こんにちは、世界£©S~aÊ  ̳";
        String result = "";
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result += ((String)(Integer.toBinaryString(messChar[i]))) + " ";
        }
//        int start = Integer.parseInt("1000000000", 2);
//        for(int i = start; i < start * 2; i++) {
//            System.out.println((char)Integer.parseInt(Integer.toBinaryString(i), 2));
//        }
//        System.out.println((char)Integer.parseInt("1100110011", 2));
        System.out.println(new Character((char)Integer.parseInt("0000000111", 2)));
//
//        System.out.println(result);
    }
}