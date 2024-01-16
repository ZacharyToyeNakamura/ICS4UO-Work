public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        String str = "こんにちは、世界£©S~aÊ  ̳";
//        String result = "";
//        char[] messChar = str.toCharArray();
//
//        for (int i = 0; i < messChar.length; i++) {
//            result += ((String)(Integer.toBinaryString(messChar[i]))) + " ";
//        }
//        int start = Integer.parseInt("00001000000", 2);
//        for(int i = start; i < start * Math.pow(2, 4); i++) {
//            System.out.println((char)Integer.parseInt(Integer.toBinaryString(i), 2));
//        }
//        System.out.println((char)Integer.parseInt("1100110011", 2));
//        System.out.println(new Character((char)Integer.parseInt("00001000000", 2)));
//
//        System.out.println(result);


        Store store = new Store();
        // store.saveInventory("src/saved.txt");
        store.saveInventory("/workspaces/javaG12/Toye-NakamuraZacharyFinalProject/src/saved.txt");
        // System.out.println(store.encrypt("...............................", 34));
        // Food fod = new Food("Apple", "A red fruit", "PO236270", 1.99, 1.00, 100 , 1705076069, false, false, 1, false);
        // System.out.println(fod.getExpirationDate());
        // System.out.println(fod.isExpired());
        // store.loadData("src/saved.txt");
        for(int i = 0; i < 1000; i++) {
            store.saveInventory("/workspaces/javaG12/Toye-NakamuraZacharyFinalProject/src/saved.txt");
            store.loadData("/workspaces/javaG12/Toye-NakamuraZacharyFinalProject/src/saved.txt");
            try {
                Thread.sleep(10);
            } catch(InterruptedException iox) {
                System.out.println("sleep eeor" + iox.getMessage());
            }
        }
        
        // String s = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+,./;'[]<>?:\"\\=-1234567890`~ ";
        // for(int i = 0; i < s.length(); i++) {
        //     if(String.format("%08d",Integer.parseInt(Integer.toBinaryString(s.charAt(i)))).length() != 8) {
        //         System.out.println(String.format("%08d",Integer.parseInt(Integer.toBinaryString(s.charAt(i)))));
        //     }
        // }

    }
}