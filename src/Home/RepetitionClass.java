package Home;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class RepetitionClass {

    private File stemmFolder = null;
    private TreeMap<Integer, String> treeObj = new TreeMap<Integer, String>();

    public RepetitionClass() {
        stemmFolder = new File("stemm folder");
    }

    public TreeMap<Integer, String> termWithCount(String getTerm) {
        int globalCount = 0;
        for (File getFile : stemmFolder.listFiles()) {
            List<String> addItems = new ArrayList<String>();
            try {
                BufferedReader brObj = new BufferedReader(new FileReader(getFile));
                String str = "";
                while ((str = brObj.readLine()) != null) {
                    addItems.add(str);
                }
                for (int i = 0; i < addItems.size() - 1; i++) {
                    if (addItems.get(i).equals(getTerm)) {
                        ++globalCount;
                    }
                }
            } catch (Exception eMsg) {
                javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
            }
        }
       // System.out.println(getTerm + "--" + globalCount);
        treeObj.put(globalCount, getTerm);
        return treeObj;
    }
}
