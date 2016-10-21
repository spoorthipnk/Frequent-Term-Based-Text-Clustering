package Home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JTable;

public class Matrix extends javax.swing.JFrame {

    public Matrix() {
        initComponents();
        ArrayList al = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Count.txt"));
            String s = null;
            int row = 0, col = 0;
            while ((s = br.readLine()) != null) {
                row++;
                StringTokenizer str = new StringTokenizer(s);
                while (str.hasMoreTokens()) {
                    al.add(str.nextToken());
                }
            }
            col = row;
            System.out.println(col);
            System.out.println(row);
            System.out.println(al);
            JTable tab = new JTable(row, col);
            tab.setVisible(true);
            tab.setBounds(0, 0, 850, 700);
            jPanel1.add(tab);
            int count = 0;
            Comapare cObj = new Comapare();
            for (int i = 0; i <= row; i++) {
                for (int j = 0; j < col; j++) {
                    tab.setValueAt(al.get(count), i, j);
                    count++;
                }
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
                .addGap(0, 811, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 477, Short.MAX_VALUE));

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Matrix().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-ENF:variables
}
