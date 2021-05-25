import java.util.*;

public class Soluton {
    public static void main(String[] args) {

        List<String> log1 = new ArrayList(Arrays.asList(new String[]{"345366", "89921"}));
        List<String> log2 = new ArrayList(Arrays.asList(new String[]{"29323", "38239"}));
        List<String> log3 = new ArrayList(Arrays.asList(new String[]{"38239", "345366"}));
        List<String> log4 = new ArrayList(Arrays.asList(new String[]{"29323", "38239"}));
        List<String> log5 = new ArrayList(Arrays.asList(new String[]{"345366", "38239"}));
        List<String> log6 = new ArrayList(Arrays.asList(new String[]{"29323", "345366"}));
        List<String> log7 = new ArrayList(Arrays.asList(new String[]{"38239", "38239"}));

        List<List<String>> logData = new ArrayList();
        logData.add(log1);
        logData.add(log2);
        logData.add(log3);
        logData.add(log4);
        logData.add(log5);
        logData.add(log6);
        logData.add(log7);

        System.out.println(new Soluton().getFraudIds(logData, 3));
    }

    public List<String> getFraudIds(List<List<String>> logData, int threshold) {
        TreeMap<String, Integer> map = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int i1 = Integer.parseInt(s1);
                int i2 = Integer.parseInt(s2);
                if (i2 > i1) return -1;
                if (i1 > i2) return 1;
                return 0;
            }
        });

        for(List<String> log : logData) {
            String user1 = log.get(0);
            String user2 = log.get(1);
            if(!user1.equals(user2)) {
                map.put(user1, map.getOrDefault(user1, 0) + 1);
                map.put(user2, map.getOrDefault(user2, 0) + 1);
            }
            else {
                map.put(user1, map.getOrDefault(user1, 0) + 1);
            }
        }

        List<String> res = new ArrayList();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {

            if(entry.getValue() >= threshold) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}
