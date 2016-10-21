package Home;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class InputDataset extends javax.swing.JFrame {

    private File getFile = null;
    private File stopFolder = null;
    private File stemmFolder = null;
    private File pathFile = null;
    
    public InputDataset() {
        initComponents();
        ImageIcon icObj = new ImageIcon("F:\\Folder-icon.png");
        setIconImage(icObj.getImage());
        aprioriBtn.setVisible(false);
        setTitle("Input Dataset");
        stopFolder = new File("stop folder");
        if (stopFolder.isDirectory()) {
            for (File list : stopFolder.listFiles()) {
                list.delete();
            }
        } else {
            stopFolder.mkdir();
        }
        stemmFolder = new File("stemm folder");
        if (stemmFolder.isDirectory()) {
            for (File list : stemmFolder.listFiles()) {
                list.delete();
            }
        } else {
            stemmFolder.mkdir();
        }
        try {
            pathFile = new File("save dataset path.txt");
            if (pathFile.exists()) {
                pathFile.delete();
                boolean newFile = pathFile.createNewFile();                                
            } else {
                boolean newFile = pathFile.createNewFile();
            }
        } catch (Exception eMsg) {
        }    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pathField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        preprocessingBtn = new javax.swing.JButton();
        aprioriBtn = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("A Frequent Term BasedText Clustering Approach Using Novel Similarity Measure");

        jLabel2.setText("Dataset File");

        pathField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathFieldActionPerformed(evt);
            }
        });

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        preprocessingBtn.setText("Apply Preprocessing");
        preprocessingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preprocessingBtnActionPerformed(evt);
            }
        });

        aprioriBtn.setText("Apply Apriori");
        aprioriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprioriBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(aprioriBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(preprocessingBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(browseBtn)))))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseBtn))
                .addGap(60, 60, 60)
                .addComponent(preprocessingBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(aprioriBtn)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        JFileChooser jfcObj = new JFileChooser();
        jfcObj.setFileSelectionMode(jfcObj.DIRECTORIES_ONLY);
        int status = jfcObj.showOpenDialog(this);
        getFile = jfcObj.getSelectedFile();
        pathField.setText(getFile.getPath());
        try{
            FileOutputStream fosObj = new FileOutputStream(pathFile);
            fosObj.write(getFile.getPath().getBytes());
            fosObj.flush();
        }catch(Exception eMsg){
            javax.swing.JOptionPane.showMessageDialog(null,eMsg.getMessage());
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void preprocessingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preprocessingBtnActionPerformed
        for (File file : getFile.listFiles()) {
            try {
                FileInputStream fisObj = new FileInputStream(file);
                byte bytes[] = new byte[fisObj.available()];
                fisObj.read(bytes);
                String getData = new String(bytes);
                String retData = removeStopWords(getData);
                FileOutputStream fosObj = new FileOutputStream(stopFolder + "\\" + file.getName());
                fosObj.write(retData.getBytes());
                fosObj.flush();
            } catch (Exception eMsg) {
                javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
            }
        }
        performStemming();
        try {
            int status = javax.swing.JOptionPane.showConfirmDialog(null, "Do you want to open Stopwords Folder");
            if (status == 0) {
                Desktop deskObj = Desktop.getDesktop();
                deskObj.open(stopFolder);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Skipped");
            }
            int flag = javax.swing.JOptionPane.showConfirmDialog(null, "Do you want to open Stemm Folder");
            if (flag == 0) {
                Desktop deskObj = Desktop.getDesktop();
                deskObj.open(stemmFolder);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Skipped");
            }
            aprioriBtn.setVisible(true);
        } catch (Exception eMsg) {
            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
        }

    }//GEN-LAST:event_preprocessingBtnActionPerformed

    private void aprioriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprioriBtnActionPerformed
        Apriori apObj = new Apriori();
        apObj.setVisible(true);
        this.hide();
    }//GEN-LAST:event_aprioriBtnActionPerformed

private void pathFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_pathFieldActionPerformed

    public String removeStopWords(String getData) {
        String retData = "";
        Pattern stopWords = Pattern.compile("\\b(?:i|a|and|about|an|are|the|at|for|it|to|is|in|of|which|on|will|was|what|when|where|from|this|that|there|with|have|most|into|could|should|us|among|be|...)\\b\\s*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = stopWords.matcher(getData);
        retData = matcher.replaceAll("");
        return retData;
    }

    public void performStemming() {
        try {
            for (File file : stopFolder.listFiles()) {
                char[] words = new char[501];
                Stemmer stemmObj = new Stemmer();
                BufferedWriter bwObj = new BufferedWriter(new FileWriter(stemmFolder + "\\" + file.getName()));
                BufferedReader brObj = new BufferedReader(new FileReader(file));
                while (true) {
                    int ch = brObj.read();
                    if (Character.isLetter((char) ch)) {
                        int j = 0;
                        while (true) {
                            ch = Character.toLowerCase((char) ch);
                            words[j] = (char) ch;
                            if (j < 500) {
                                j++;
                            }
                            ch = brObj.read();
                            if (!Character.isLetter((char) ch)) {
                                for (int c = 0; c < j; c++) {
                                    stemmObj.add(words[c]);
                                }
                                stemmObj.stem();
                                {
                                    String retData;
                                    retData = stemmObj.toString();                         // 
                                    //System.out.print(retData);
                                    bwObj.write(retData);
                                    bwObj.newLine();                        // 
                                }
                                break;
                            }
                        }
                    }
                    if (ch < 0) {
                        break;             //                  
                    }
                }
                bwObj.close();
            }
        } catch (Exception eMsg) {
            javax.swing.JOptionPane.showMessageDialog(null, eMsg.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(InputDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputDataset().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprioriBtn;
    private javax.swing.JButton browseBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField pathField;
    private javax.swing.JButton preprocessingBtn;
    // End of variables declaration//GEN-END:variables
}
