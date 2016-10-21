package Home;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JTable;

public class TableGenerator extends javax.swing.JFrame {

    /**
     * Creates new form TableGenerator
     */
    ArrayList repeatedTokens = new ArrayList();
    ArrayList finalal = new ArrayList();

    public TableGenerator() {
        initComponents();
        File repated = new File("RepeatedWords.txt");
        try {
            FileInputStream fis = new FileInputStream(repated);
            byte b[] = new byte[fis.available()];
            fis.read(b);
            String data = new String(b);
            data = data.replaceAll(",", "#");
            System.out.println("The Repated data is :" + data);
            StringTokenizer str = new StringTokenizer(data, " -->");
            if(str.hasMoreTokens()){
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            String s1 = str.nextToken();
            System.out.println(s1);
            StringTokenizer stk = new StringTokenizer(s1, "#");
            while (stk.hasMoreTokens()) {
                repeatedTokens.add(stk.nextToken().trim());
            }
            System.out.println("Tokens are :" + repeatedTokens);

            File dataFiles = new File("stemm folder");


            for (File dada : dataFiles.listFiles()) {

                int z = dataFiles.listFiles().length;



                ArrayList al = new ArrayList();
                ArrayList values = new ArrayList();
                //  System.out.println("dda is "+dada);
                FileInputStream fis1 = new FileInputStream(dada);
                byte[] b1 = new byte[fis1.available()];
                fis1.read(b1);
                String temp = new String(b1);
                StringTokenizer str1 = new StringTokenizer(temp);
                while (str1.hasMoreTokens()) {
                    al.add(str1.nextToken().trim());
                }
                System.out.println("al is: " + al);
                for (int i = 0; i < repeatedTokens.size(); i++) {
                    if (al.contains(repeatedTokens.get(i))) {
                        values.add("1");
                        System.out.print("1 \t");
                    } else {
                        values.add("0");
                        System.out.print("0 \t");
                    }
                }



                finalal.add(values);
//                int n = dataFiles.listFiles().length;
//                JTable t = new JTable(n, values.size());
//                t.setVisible(true);
//                t.setBounds(0, 0, 850, 700);
//                jPanel1.add(t);
//                int k=0;
//                for(int c=0;c<=n;c++){
//                    t.setValueAt(values.get(c), k, c);
//                }
//                k++;
//                t.setValueAt(" ", 0, 0);
//                for (int c = 0; c < n + 1; c++) {
//                    for (int r = 1; r <=values.size() + 1; r++) {
//                        t.setValueAt("File " + r, r, c);
//                    }
//                    t.setValueAt("Word "+c, 0, c);
//                }


                System.out.println("\n");
            }

            System.out.println("aFinal values are " + finalal);
            Testing test = new Testing(finalal);
            JTable t = new JTable(finalal.size(), 2);
            t.setVisible(true);
            t.setBounds(0, 0, 850, 700);
            jPanel1.add(t);
            for (int r = 0; r <= finalal.size() - 1; r++) {
                t.setValueAt("File " + r, r, 0);
                t.setValueAt(finalal.get(r), r, 1);
            }

        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 880, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 356, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-ENF:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableGenerator().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-ENF:variables
}
