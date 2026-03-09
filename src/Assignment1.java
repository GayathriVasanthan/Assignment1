import java.util.*;

public class Assignment1 {

    static HashMap<String, Integer> users = new HashMap<>();
    static HashMap<String, Integer> attempts = new HashMap<>();

    public static boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            String s = username + i;
            if (!users.containsKey(s)) {
                suggestions.add(s);
            }
        }

        String modified = username.replace("_", ".");
        if (!users.containsKey(modified)) {
            suggestions.add(modified);
        }

        return suggestions;
    }

    public static String getMostAttempted() {
        String name = "";
        int max = 0;

        for (Map.Entry<String, Integer> e : attempts.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                name = e.getKey();
            }
        }

        return name + " (" + max + " attempts)";
    }

    public static void main(String[] args) {
        users.put("john_doe", 1);
        users.put("admin", 2);

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));
        System.out.println(suggestAlternatives("john_doe"));

        for (int i = 0; i < 10543; i++) {
            checkAvailability("admin");
        }

        System.out.println(getMostAttempted());
    }
}