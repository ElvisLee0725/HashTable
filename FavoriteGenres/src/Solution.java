import java.util.*;

public class Solution {
    public static void main(String[] args) {
        HashMap<String, List<String>> userMap = new HashMap();
        List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
        userMap.put("David", list1);
        List<String> list2 = Arrays.asList("song5", "song6", "song7");
        userMap.put("Emma", list2);
        HashMap<String, List<String>> genreMap = new HashMap();
        List<String> list3 = Arrays.asList("song1", "song3");
        genreMap.put("Rock", list3);
        List<String> list4 = Arrays.asList("song7");
        genreMap.put("Dubstep", list4);
        List<String> list5 = Arrays.asList("song2", "song4");
        genreMap.put("Techno", list5);
        List<String> list6 = Arrays.asList("song5", "song6");
        genreMap.put("Pop", list6);
        List<String> list7 = Arrays.asList("song8", "song9");
        genreMap.put("Jazz", list7);

        HashMap<String, List<String>> res = new Solution().favoritegenre(userMap, genreMap);
        for(Map.Entry<String, List<String>> entry : res.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            System.out.println();
        }
    }
    // Iterating the userMap, for each user, iterate his list and calculate the freq of genre from the song list
    // To find out the genre of a song, create another map with <Song Name, Genre> from the genreMap
    // Use a max heap to store the Map.Entry of the user's <Genre, Frequency> with the max Frequency on top
    // Use a map to store the result of <User Name, List of most listened to genres>
    // Return the result map
    public HashMap<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        HashMap<String, String> songMap = new HashMap();
        for(Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
            String genre = entry.getKey();
            for(String songName : entry.getValue()) {
                songMap.put(songName, genre);
            }
        }

        HashMap<String, List<String>> res = new HashMap();
        HashMap<String, Integer> gFreqMap;
        for(Map.Entry<String, List<String>> entry : userMap.entrySet()) {
            String userName = entry.getKey();
            // <Genre, Frequency>
            gFreqMap = new HashMap();
            int maxFreq = 0;
            for(String song : entry.getValue()) {
                String curSongGenre = songMap.get(song);
                gFreqMap.put(curSongGenre, gFreqMap.getOrDefault(curSongGenre, 0) + 1);
                maxFreq = Math.max(maxFreq, gFreqMap.get(curSongGenre));
            }

            List<String> list = new ArrayList();
            // Iterate the genre freq map and record those genre with max frequency
            for(Map.Entry<String, Integer> tmp: gFreqMap.entrySet()) {
                if(tmp.getValue() == maxFreq) {
                    list.add(tmp.getKey());
                }
            }
            res.put(userName, list);
        }
        return res;
    }
}
