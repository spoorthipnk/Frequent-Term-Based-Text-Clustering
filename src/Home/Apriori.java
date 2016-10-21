package Home;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.ImageIcon;

public class Apriori extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private Set<String> uniqueWordsSet = new LinkedHashSet<String>();
    private File stemmFolder = null;
    private String writeToFile = null;
    private List<Integer> addCounts = new ArrayList<Integer>();
    private File 
            aprioriFolder= null;
    private String fileData = null;
    private String repeatedWords[] = new String[20];
    private List<Integer> repeatedWordCunts = new ArrayList<Integer>();

    public Apriori() {
        initComponents();
        ImageIcon icObj = new ImageIcon("F:\\Folder-icon.png");
        setIconImage(icObj.getImage());
        setTitle("Apriori");
        aprioriFolder = new File("apriori");
        if (aprioriFolder.isDirectory()) {
            for (File getEach : aprioriFolder.listFiles()) {
                getEach.delete();
            }
            aprioriFolder.mkdir();
        } else {
            aprioriFolder.mkdir();
        }
        writeToFile = "";
        stemmFolder = new File("stemm folder");
        getUniqueWords();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aprioriBtn = new javax.swing.JButton();
        featuresPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        featuresArea = new javax.swing.JTextArea();
        featuresBtn = new javax.swing.JButton();
        repeatedBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        aprioriBtn.setText("Get Final Terms");
        aprioriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprioriBtnActionPerformed(evt);
            }
        });

        featuresArea.setColumns(20);
        featuresArea.setRows(5);
        jScrollPane1.setViewportView(featuresArea);

        featuresBtn.setText("Get Global Features");
        featuresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                featuresBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout featuresPanelLayout = new javax.swing.GroupLayout(featuresPanel);
        featuresPanel.setLayout(featuresPanelLayout);
        featuresPanelLayout.setHorizontalGroup(
            featuresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(featuresPanelLayout.createSequentialGroup()
                .addGroup(featuresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(featuresPanelLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(featuresBtn))
                    .addGroup(featuresPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        featuresPanelLayout.setVerticalGroup(
            featuresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(featuresPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(featuresBtn)
                .addGap(31, 31, 31))
        );

        repeatedBtn.setText("Get Most repeated terms");
        repeatedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatedBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(aprioriBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(repeatedBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(featuresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(featuresPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(repeatedBtn)
                .addGap(90, 90, 90)
                .addComponent(aprioriBtn)
                .addGap(123, 123, 123))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aprioriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprioriBtnActionPerformed
        javax.swing.JOptionPane.showMessageDialog(null, "Total Number of unique terms : " + uniqueWordsSet.size());
        makeApriori();
        List<File> getList = new ArrayList<File>();
        for (File getFile : aprioriFolder.listFiles()) {
            getList.add(getFile);
        }
        File lastFile = new File(getList.get(0).toString());
        try {
            FileInputStream fisObj = new FileInputStream(lastFile);
            byte bytes[] = new byte[fisObj.available()];
            fisObj.read(bytes);
            fileData = new String(bytes);
            FileOutputStream fosObj = new FileOutputStream("RepeatedWords.txt");
            fosObj.write(fileData.getBytes());
            fosObj.flush();
            fosObj.close();
            getGlobalFeatures()
                    ;
        } catch (Exception eMsg) {
            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
        }
    }//GEN-LAST:event_aprioriBtnActionPerformed

    private void featuresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_featuresBtnActionPerformed
        featuresArea.setText(fileData);
        Similarity simObj = new Similarity();
        simObj.setVisible(true);
    }//GEN-LAST:event_featuresBtnActionPerformed

    private void repeatedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatedBtnActionPerformed
        knowRepetitionCount();
        String str = "";
        for (int i = 0; i < repeatedWords.length; i++) {
            str += repeatedWords[i] + " " + repeatedWordCunts.get(i).toString() + "\r\n";
        }
        try {
            FileOutputStream fosObj = new FileOutputStream("repeated words.txt");
            fosObj.write(str.getBytes());
            fosObj.flush();
        } catch (Exception eMsg) {
            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Repeated Words : " + str);
    }//GEN-LAST:event_repeatedBtnActionPerformed

    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public void getGlobalFeatures() {
        StringTokenizer stkObj = new StringTokenizer(fileData, "-->");
        String retToken = stkObj.nextToken();
        retToken.replaceAll(",", " ");
        StringTokenizer stkObj2 = new StringTokenizer(retToken, " ");
        ArrayList alObj = new ArrayList();
        while (stkObj2.hasMoreTokens()) {
            @SuppressWarnings("unchecked")
            boolean add;
            add = alObj.add(stkObj2.nextToken());
        }
        checkInFiles(alObj);
    }

    private void getUniqueWords() {
        try {
            for (File getFile : stemmFolder.listFiles()) {
                BufferedReader brObj = new BufferedReader(new FileReader(getFile));
                String tempStr = "";
                while ((tempStr = brObj.readLine()) != null) {
                    uniqueWordsSet.add(tempStr);
                }
            }
        } catch (Exception eMsg) {
            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
        }
    }

    public void checkInFiles(ArrayList alObj) {
        String globalString = "";
        for (File getFile : stemmFolder.listFiles()) {
            LinkedHashSet lhsObj = new LinkedHashSet();
            try {
                BufferedReader brObj = new BufferedReader(new FileReader(getFile));
                String str = "";
                while ((str = brObj.readLine()) != null) {
                    lhsObj.add(str);
                }
                Iterator itr = lhsObj.iterator();
                ArrayList alObj2 = new ArrayList();
                while (itr.hasNext()) {
                    alObj2.add(itr.next());
                }
                globalString += getFile.getName() + "-->";
                for (int i = 0; i < alObj.size(); i++) {
                    for (int j = 0; j < alObj2.size(); j++) {
                        if (alObj.get(i).toString().equals(alObj2.get(j).toString())) {
                            globalString += alObj.get(i).toString() + " ";
                        }
                    }
                }
                globalString += "\r\n";
            } catch (Exception eMsg) {
                javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
            }
        }
        featuresArea.setText(globalString);
    }

    @SuppressWarnings("unchecked")
    public void makeApriori() {
        ArrayList<String> repeatedList = new ArrayList<String>();
        for (int i = 0; i < repeatedWords.length; i++) {
            repeatedList.add(repeatedWords[i]);
        }
        int loopVar = 1;
        int argLength = 2;
        int getUserThreshold = 0;
        int getMaxCount = 0;
        int getMinCount = 0;
        do {
            for (int i = 0; i < repeatedList.size(); i++) {
                String argsList[] = new String[argLength];
                for (int j = i + 1, k = 1; j < repeatedList.size() && k < argLength; j++, k++) {
                    argsList[0] = repeatedList.get(i).toString();
                    argsList[k] = repeatedList.get(j).toString();
                }
                ArrayList list = new ArrayList();
                boolean addAll;
                addAll = list.addAll(Arrays.asList(argsList));
                if (list.contains(null)) {
                    break;
                } else {
                    generateCount(argsList);
                }
            }
            if (loopVar == 1) {
                Set<Integer> counts;
                counts = new TreeSet<Integer>();
                counts.addAll(addCounts);
                Iterator<Integer> countsIterator;
                countsIterator = counts.iterator();
                List<Integer> conList;
                conList = new ArrayList<Integer>();
                while (countsIterator.hasNext()) {
                    conList.add(countsIterator.next());
                }
                getMaxCount = Integer.parseInt(conList.get(0).toString());
                getMinCount = Integer.parseInt(conList.get(conList.size() - 1).toString());
                getUserThreshold = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Input an optimal value between [" + getMaxCount + "," + getMinCount + "]"));
                javax.swing.JOptionPane.showMessageDialog(null, "Your Selected Threshold is: " + getUserThreshold);
                for (int i = 0; i < conList.size(); i++) {
                    if (Integer.parseInt(conList.get(i).toString()) == getUserThreshold || (Integer.parseInt(conList.get(i).toString()) >= getUserThreshold)) {
                        try {
                            FileOutputStream fosObj;
                            fosObj = new FileOutputStream(aprioriFolder + "\\with count " + (loopVar + 1) + ".txt");
                            fosObj.write(writeToFile.getBytes());
                            fosObj.flush();
                            fosObj.close();
                        } catch (Exception eMsg) {
                            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
                        }
                    }
                }
                addCounts.clear();
                writeToFile = "";
                ++argLength;
                ++loopVar;
            } else if (loopVar >= 1) {
                Set<Integer> counts;
                counts = new TreeSet<Integer>();
                counts.addAll(addCounts);
                Iterator<Integer> countsIterator;
                countsIterator = counts.iterator();
                List<Integer> conList;
                conList = new ArrayList<Integer>();
                while (countsIterator.hasNext()) {
                    conList.add(countsIterator.next());
                }
                if (conList.size() == 0) {
                    featuresPanel.setVisible(true);
                    return;
                } else {
                    getMinCount = Integer.parseInt(conList.get(0).toString());
                    getMaxCount = Integer.parseInt(conList.get(conList.size() - 1).toString());
                    for (int i = 0; i < conList.size(); i++) {
                        if (Integer.parseInt(conList.get(i).toString()) == getUserThreshold || (Integer.parseInt(conList.get(i).toString()) >= getUserThreshold)) {
                            try {
                                FileOutputStream fosObj;
                                fosObj = new FileOutputStream(aprioriFolder + "\\with count " + (loopVar + 1) + ".txt");
                                fosObj.write(writeToFile.getBytes());
                                fosObj.flush();
                                fosObj.close();
                            } catch (Exception eMsg) {
                                javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
                            }
                        }
                    }
                }
                addCounts.clear();
                writeToFile = "";
                ++argLength;
                ++loopVar;
            }
        } while (getMinCount <= getUserThreshold);
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    public void generateCount(String... args) {
        List<String> argList;
        argList = new ArrayList<String>();
        boolean addAll;
        addAll = argList.addAll(Arrays.asList(args));
        int globalCount = 0;
        List<String> fileList;
        fileList = new ArrayList<String>();
        for (File getFile : stemmFolder.listFiles()) {
            try {
                BufferedReader brObj = new BufferedReader(new FileReader(getFile));
                String tempStr = "";
                while ((tempStr = brObj.readLine()) != null) {
                    boolean add;
                    add = fileList.add(tempStr);
                }
                if (fileList.containsAll(argList)) {
                    ++globalCount;
                }
                fileList.clear();
            } catch (Exception eMsg) {
                javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
            }
        }
        String display = "";
        for (int i = 0; i < args.length; i++) {
            display += args[i] + ",";
        }
        display += "-->" + globalCount;
        writeToFile += display + "\r\n";
        System.out.println(display);
        addCounts.add(globalCount);
    }

    public void knowRepetitionCount() {
        List<String> uniqueList = new ArrayList<String>();
        javax.swing.JOptionPane.showMessageDialog(null, "Total number of words : " + uniqueWordsSet.size());
        Iterator<String> itrObj = uniqueWordsSet.iterator();
        TreeMap<Integer, String> treeObj = new TreeMap<Integer, String>();
        RepetitionClass rcObj = new RepetitionClass();
        while (itrObj.hasNext()) {
            treeObj = rcObj.termWithCount(itrObj.next());
        }
        Set stObj = treeObj.entrySet();
        Iterator iterate = stObj.iterator();
        List<Integer> addInts = new ArrayList<Integer>();
        List<String> addStrs = new ArrayList<String>();
        while (iterate.hasNext()) {
            Map.Entry map = (Map.Entry) iterate.next();
            addInts.add(Integer.parseInt(map.getKey().toString()));
            addStrs.add(map.getValue().toString());
        }
        javax.swing.JOptionPane.showMessageDialog(null, "These are the total number of words" + addStrs);
        int getThreshold = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter the required number of repeated words"));
        repeatedWords = new String[getThreshold];
        int j = 0;
        for (int i = (addStrs.size() - 1); i >= addStrs.size() - getThreshold; i--) {
            System.out.println(i);
            repeatedWords[j] = addStrs.get(i);
            repeatedWordCunts.add(addInts.get(i));
            ++j;
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apriori().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprioriBtn;
    private javax.swing.JTextArea featuresArea;
    private javax.swing.JButton featuresBtn;
    private javax.swing.JPanel featuresPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton repeatedBtn;
    // End of variables declaration//GEN-END:variables
}
