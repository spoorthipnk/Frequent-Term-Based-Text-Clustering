package Home;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

public class Comapare {

    public Comapare() {
        try {
            FileOutputStream fos = new FileOutputStream("FinalClusters.txt");
            BufferedReader br = new BufferedReader(new FileReader("Count.txt"));
            String s = null;
            MultiMap tm = new MultiHashMap();
            while ((s = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(s);
                while (str.hasMoreTokens()) {
                    String t = str.nextToken();
                    StringTokenizer str1 = new StringTokenizer(t, "=");
                    String s1 = str1.nextToken();
                    int s2 = Integer.parseInt(str1.nextToken());
                    tm.put(s2, s1);
                }
            }
            String demo = tm.keySet().toString();
            System.out.println("" + "demo is" + demo);

            ArrayList keys = new ArrayList();
            StringTokenizer str = new StringTokenizer(demo, ",");
            while (str.hasMoreTokens()) {
                keys.add(str.nextToken().trim());
            }
            System.out.println("tm is " + tm);
            String temp1 = keys.get(0).toString();
            String s1 = temp1.replace('[', ' ').trim();
            String temp2 = keys.get(keys.size() - 1).toString();
            String s2 = temp2.replace(']', ' ').trim();
            //keys.remove(0);
            //keys.remove(keys.size() - 1);
            keys.set(0, s1);
            keys.set(keys.size() - 1, s2);
            System.out.println("Keys" + keys);
            javax.swing.JOptionPane.showMessageDialog(null,keys);
            ArrayList al = new ArrayList();
            String replace = null;
            HashSet h = new HashSet();
            for (int i = keys.size() - 1; i >= 0; i--) {
                System.out.println(tm.get(Integer.parseInt(keys.get(i).toString())));
                String value = tm.get(Integer.parseInt(keys.get(i).toString())).toString();
                StringTokenizer token = new StringTokenizer(value, ",");
                while (token.hasMoreTokens()) {
                    String data = token.nextToken();
                    if (data.contains("[")) {
                        replace = data.replace('[', ' ');
                    } else if (data.contains("]")) {
                        replace = data.replace(']', ' ');
                    } else {
                        replace = data;
                    }

                    System.out.println("replace is " + replace.trim());
                    if (!al.contains(replace.trim())) {
                        h.add(replace.trim());
                    }
                }
                if (h.size() != 0) {
                    fos.write(h.toString().getBytes());
                    fos.write("\r\n".getBytes());
                }
                Iterator it = h.iterator();
                while (it.hasNext()) {
                    al.add(it.next());
                }
                h.clear();
            }
            fos.flush();
            System.out.println(h);
            Runtime rObj = Runtime.getRuntime();
            rObj.exec("notepad FinalClusters.txt");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
