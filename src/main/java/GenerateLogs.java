import java.io.*;
import java.util.ArrayList;
import java.util.Random;




public class GenerateLogs {


    private static Random rand = new Random();

    private static ArrayList<String> arrList = new ArrayList<String>(100);

    public static void main(String args[]) throws IOException {

        addServerNamesToArray("/Users/benas/PROJECTS/javaStuff/my-app/src/main/java/randomNames.txt");
        logsAmount(100);

    }

    public static void storeLogs(String logs){
        try
        {
            String filename = "/Users/benas/PROJECTS/javaStuff/my-app/src/main/java/Logs.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(logs);//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static String generateYear(int max, int min){
        Integer n = generateRandom(min, max);
        return n.toString();
    }

    public static String generateMonth(int max, int min){
        Integer n = generateRandom(min, max);
        if(n < 10){
            String str1 = Integer.toString(n);
            return "0" + str1;
        }else{
            return n.toString();
        }
    }

    public static String generateDay(int max, int min){
        Integer n = generateRandom(min, max);
        if(n < 10){
            String str1 = Integer.toString(n);
            return "0" + str1;
        }else{
            return n.toString();
        }
    }

    public static String generateHours(int max, int min){
        Integer n = generateRandom(min, max);
        if(n < 10){
            String str1 = Integer.toString(n);
            return "0" + str1;
        }else{
            return n.toString();
        }
    }

    public static String generateMinutes(int max, int min){
        Integer n = generateRandom(min, max);
        if(n < 10){
            String str1 = Integer.toString(n);
            return "0" + str1;
        }else{
            return n.toString();
        }
    }

    public static String generateSeconds(int max, int min){
        Integer n = generateRandom(min, max);;
        if(n < 10){
            String str1 = Integer.toString(n);
            return "0" + str1;
        }else{
            return n.toString();
        }
    }

    public static String generateDate(){
        String yearMonthDay = generateYear(2018, 1950) + "-" + generateMonth(12, 1) + "-" + generateDay(31, 1) + " ";
        return yearMonthDay;
    }

    public static String generateTime(){
        String hourMinuteSecond = generateHours(23, 0) + ":" + generateMinutes(59, 0) + ":" + generateSeconds(59,0);
        return hourMinuteSecond;
    }

    public static void addServerNamesToArray(String path) throws IOException {
        String line = null;
        File file = new File(path);
        FileReader content = new FileReader(file);
        BufferedReader br = new BufferedReader(content);
        while((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            arrList.add(parts[0]);
            arrList.add(parts[1]);
        }
    }

    public static Integer generateRandom(Integer min, Integer max){
        Integer n = rand.nextInt((max - min) + 1) + min;
        return n;
    }

    public static String generatePayload(){
        String payload = "";
        Integer amount = generateRandom(50, 150);
        Random r = new Random();
        String alphabet = "abcdefghjxyz12345";
        for (int i = 0; i < amount; i++) {
            payload += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return payload;
    }

    public static String generateUniqueLog() throws IOException {
        String str1 = generateDate();
        String str2 = generateTime();
        String str3 = generatePayload();
        Integer randServerNum = generateRandom(0, 99);
        String str4 = arrList.get(randServerNum);
        String log = str1 + str2 + " <[" + str4 + "]> [" + str3 + "]" + "\n";
        return log;
    }

    private static void logsAmount(int amount) throws IOException {
        for(int i = 0; i < amount; i++){
            storeLogs(generateUniqueLog());
        }
    }
}