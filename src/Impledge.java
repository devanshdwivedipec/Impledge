import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Impledge
{
    public static void main(String[] args) throws FileNotFoundException
    {
        long begin = System.currentTimeMillis();
        Impledge obj = new Impledge();
        File file= new File("D:\\Impledge\\src\\Input_02.txt");
        Scanner sc = new Scanner(file);
        Scanner gc = new Scanner(file);
        int count =0;

        int i =0;
        while (gc.hasNextLine())
        {
            String x = gc.next();
            count++;
        }
        String []xyz = new String[count];
        while (sc.hasNextLine())
        {
            String s = sc.next();
            xyz[i]=s;
            i++;
        }
        List<String> list ;

        list= obj.FACW(xyz);
        String longest = list.stream().max(Comparator.comparingInt(String::length)).get();
        String second_Largest  = obj.getSecondLongestString(list,longest);
        long end = System.currentTimeMillis();
        long time = end-begin;
        System.out.println("Longest Compound Word        :  "+longest);
        System.out.println("Second Longest Compound Word :  "+second_Largest);
        System.out.print("Time taken to process file Input_02.txt : "+time +" milli second");
    }
    public static List<String> FACW(String[] words)
    {
        long begin1 = System.currentTimeMillis();
        List<String> list =new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Map<String,Integer> map=new HashMap<>();
        for(String i:words)
        {
            if(check(i,set,map))
            {
                list.add(i);
            }
        }
        long end = System.currentTimeMillis();
        return list;
    }
    public static boolean check(String i,Set<String>set,Map<String,Integer>map)
    {
        if (map.containsKey(i))
        {
            return map.get(i) == 1;
        }
        for (int x = 0; x < i.length(); x++)
        {
            if (set.contains(i.substring(0, x)))
            {
                String s1 = i.substring(x);
                if (set.contains(s1) || check(s1,set,map))
                {
                    map.put(i, 1);
                    return true;
                }
            }
        }
        map.put(i,0);
        return false;
    }
    public static String getSecondLongestString(List<String> list, String longestWord)
    {
        int maxLength = 0;
        String secondlongestString = "";
        for (String s : list) {
            if (s.length() > maxLength && s.length() < longestWord.length()) {
                maxLength = s.length();
                secondlongestString = s;
            }
        }
        return secondlongestString;
    }

}
