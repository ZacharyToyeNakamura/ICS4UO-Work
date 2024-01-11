public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String str = "こんにちは、世界£©S~aÊ  ̳";
        String result = "";
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result += ((String)(Integer.toBinaryString(messChar[i]))) + " ";
        }
        System.out.println(Integer.toString((char)Integer.parseInt("1100110011", 2)));
        

        System.out.println(result);
    }
}