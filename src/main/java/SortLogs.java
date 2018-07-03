import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SortLogs {

    private static TreeMap<String, Integer> logsMap = new TreeMap<>();

    public static void putLogsToMap(String path) throws IOException {
        File file = new File(path);
        FileReader content = new FileReader(file);
        BufferedReader br = new BufferedReader(content);
        String line = null;


        Pattern pattern = Pattern.compile("\\[(.*)\\].+\\[(.*)\\]");
        while((line = br.readLine()) != null){
            Matcher matcher = pattern.matcher(line);

            if(matcher.find()){
                String serverName =  matcher.group(1);
                if(logsMap.containsKey(serverName)){
                    logsMap.put(serverName, logsMap.get(serverName) + matcher.group(2).length());
                }else{
                    logsMap.put(serverName, matcher.group(2).length());
                }
            }
        }
        br.close();
    }

    public static void findFiveBiggest(TreeMap<String, Integer> logs) {
        List<Map.Entry<String, Integer>> pl = logs.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toList());
        System.out.println(pl.get(0).getKey() + " " +  pl.get(0).getValue());
        System.out.println(pl.get(1).getKey() + " " +  pl.get(1).getValue());
        System.out.println(pl.get(2).getKey() + " " +  pl.get(2).getValue());
        System.out.println(pl.get(3).getKey() + " " +  pl.get(3).getValue());
        System.out.println(pl.get(4).getKey() + " " +  pl.get(4).getValue());
    }

    public static void main(String args[]) throws IOException {
        putLogsToMap("/Users/benas/PROJECTS/javaStuff/my-app/src/main/java/Logs.txt");
        findFiveBiggest(logsMap);
    }
}
