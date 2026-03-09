import java.util.*;

public class Assignment1 {

    static HashMap<String, Integer> stock = new HashMap<>();
    static HashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new HashMap<>();

    public static String checkStock(String productId) {
        int count = stock.getOrDefault(productId, 0);
        return count + " units available";
    }

    public static synchronized String purchaseItem(String productId, int userId) {
        int count = stock.getOrDefault(productId, 0);

        if (count > 0) {
            stock.put(productId, count - 1);
            return "Success, " + (count - 1) + " units remaining";
        } else {
            waitingList.putIfAbsent(productId, new LinkedHashMap<>());
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            queue.put(userId, queue.size() + 1);
            return "Added to waiting list, position #" + queue.size();
        }
    }

    public static void main(String[] args) {
        stock.put("IPHONE15_256GB", 100);

        System.out.println(checkStock("IPHONE15_256GB"));
        System.out.println(purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(purchaseItem("IPHONE15_256GB", 67890));

        for (int i = 0; i < 98; i++) {
            purchaseItem("IPHONE15_256GB", i);
        }

        System.out.println(purchaseItem("IPHONE15_256GB", 99999));
    }
}