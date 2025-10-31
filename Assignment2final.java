/*AUTHOR:LEVIS CHUMBA
ADM NO:BSE-01-0036/2025
*/
package assignment2;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ButtonDemo extends javax.swing.JFrame {

    public ButtonDemo() {
        initComponents();
        setupButtonGroup();
        setupActionListeners();
    }

    private void setupButtonGroup() {
        ButtonGroup petGroup = new ButtonGroup();
        petGroup.add(jRadioButton1);
        petGroup.add(jRadioButton2);
        petGroup.add(jRadioButton3);
        petGroup.add(jRadioButton4);
        petGroup.add(jRadioButton5);
    }

    private void setupActionListeners() {
        jRadioButton1.addActionListener(e -> handlePetSelection("bird"));
        jRadioButton2.addActionListener(e -> handlePetSelection("cat"));
        jRadioButton3.addActionListener(e -> handlePetSelection("dog"));
        jRadioButton4.addActionListener(e -> handlePetSelection("rabbit"));
        jRadioButton5.addActionListener(e -> handlePetSelection("pig"));
    }

    private void handlePetSelection(String pet) {
        JOptionPane.showMessageDialog(
            this,
            "You selected: " + pet,
            "Pet Selection",
            JOptionPane.INFORMATION_MESSAGE
        );
        displayPetImage(pet);
    }

    
    private void displayPetImage(String pet) {
        String[] extensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        String imagePath = null;

        for (String ext : extensions) {
            File file = new File("image/" + pet.toLowerCase() + ext);
            if (file.exists()) {
                imagePath = file.getPath();
                break;
            }
        }

        if (imagePath != null) {
            try {
                ImageIcon icon = new ImageIcon(imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(
                    jTextField1.getWidth(), jTextField1.getHeight(), Image.SCALE_SMOOTH
                );
                icon = new ImageIcon(scaledImage);

                
                jTextField1.setText("");
                jTextField1.setHorizontalAlignment(JTextField.CENTER);
                jTextField1.setBackground(Color.WHITE);

                //  JLabel  icon holder inside the text field area
                jTextField1.setLayout(new BorderLayout());
                jTextField1.removeAll(); // clear previous icon
                JLabel imageLabel = new JLabel(icon);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                jTextField1.add(imageLabel, BorderLayout.CENTER);

                jTextField1.revalidate();
                jTextField1.repaint();

            } catch (Exception e) {
                jTextField1.setText("Error loading image: " + pet);
//                jTextField1.setIcon(null);
            }
        } else {
            jTextField1.removeAll();
            jTextField1.setText("Image not found for: " + pet);
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("bird");

        jRadioButton2.setText("cat");

        jRadioButton3.setText("dog");

        jRadioButton4.setText("rabbit");

        jRadioButton5.setText("pig");

        jTextField1.setName("jLabelImage"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3)
                        .addGap(13, 13, 13)
                        .addComponent(jRadioButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton5))
                    .addComponent(jTextField1))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ButtonDemo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
