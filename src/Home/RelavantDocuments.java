package Home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class RelavantDocuments {

    private File getDatasetsPath = null;
    private Map<String, String> insertFileNames = new HashMap<String, String>();
    private Set lhsObj = new LinkedHashSet();

    public RelavantDocuments() {
        try {

            FileInputStream fisObj = new FileInputStream("save dataset path.txt");
            byte bytes[] = new byte[fisObj.available()];
            fisObj.read(bytes);
            String getData = new String(bytes);
            getDatasetsPath = new File(getData);
            //javax.swing.JOptionPane.showMessageDialog(null,getDatasetsPath.getName());
            ArrayList keyList = new ArrayList();
            ArrayList valueList = new ArrayList();
            for (File file : getDatasetsPath.listFiles()) {
                FileInputStream inStream = new FileInputStream(file);
                byte getBytes[] = new byte[inStream.available()];
                inStream.read(getBytes);
                String readData = new String(getBytes);
                StringTokenizer stk = new StringTokenizer(readData, "\r\n");
                ArrayList<String> alObj = new ArrayList<String>();
                while (stk.hasMoreTokens()) {
                    alObj.add(stk.nextToken());
                }
                insertFileNames.put(alObj.get(0).toString().trim(), file.getName());
                Set stObj = insertFileNames.entrySet();
                Iterator itrObj = stObj.iterator();
                while (itrObj.hasNext()) {
                    Map.Entry mapElements = (Map.Entry) itrObj.next();
                    keyList.add(mapElements.getKey());
                    valueList.add(mapElements.getValue());
                }
                lhsObj.addAll(keyList);
            }
            //System.out.println(lhsObj);
            //System.out.println(keyList.size());
            //System.out.println(valueList.size());
            ArrayList uniqueKeyList = new ArrayList();
            Iterator iterate = lhsObj.iterator();
            while (iterate.hasNext()) {
                uniqueKeyList.add(iterate.next());
            }
            String str = "";
            for (int i = 0; i < uniqueKeyList.size(); i++) {
                str += uniqueKeyList.get(i).toString() + "-->";
                for (int j = 0; j < keyList.size(); j++) {
                    if (uniqueKeyList.get(i).toString().equals(keyList.get(j).toString())) {
                        str += valueList.get(j).toString() + " ";
                    }
                }
                str += "\r\n";
            }
            System.out.println(str);
            StringTokenizer stkObj = new StringTokenizer(str, "\r\n");
            String finalWrite = "";
            while (stkObj.hasMoreTokens()) {
                String getToken = stkObj.nextToken();
                StringTokenizer tempObj = new StringTokenizer(getToken, "-->");
                finalWrite += tempObj.nextToken() + "-->";
                StringTokenizer st1 = new StringTokenizer(tempObj.nextToken(), " ");
                Set lsObj = new LinkedHashSet();
                while (st1.hasMoreTokens()) {
                    lsObj.add(st1.nextToken());
                }
                Iterator it = lsObj.iterator();
                while (it.hasNext()) {
                    finalWrite += it.next().toString() + " ";
                }
                finalWrite += "\r\n";
            }
            System.out.println(finalWrite);
            FileOutputStream fosObj = new FileOutputStream("relevant documents.txt");
            fosObj.write(finalWrite.getBytes());
            fosObj.flush();
            //Runtime rObj = Runtime.getRuntime();
            //rObj.exec("notepad relevant documents.txt");
        } catch (Exception eMsg) {
            //javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
            //eMsg.printStackTrace();
        }
    }
}
