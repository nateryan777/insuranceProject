package Assignment03;


import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserLogin extends javax.swing.JFrame {

    
    InsuranceCompany insuranceCompany = new InsuranceCompany();
    
    /**
     * Creates new form UserLogin
     */
    public UserLogin() {
        try {
            test();
        } catch (PolicyException | NameException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
    }
    
    private void test() throws PolicyException, NameException
    {
       
        // create required objects for Insurance Company------------------------------------
                
        //create car objects, 1 per third party and comprehensive policy combination
        
        Car carSuv1 = new Car(CarType.SUV, "Nissan Dualis", 2015, 28000.00); // john $500
        Car carSuv2 = new Car(CarType.SUV, "Nissan Dualis", 2015, 49000.00); // john $1000
        Car carSed1 = new Car(CarType.SED, "Toyota Camry", 2020, 18000.00); // sara $200
        Car carSed2 = new Car(CarType.SED, "Toyota Camry", 2020, 21500.00); // sara $500
        Car carLux1 = new Car(CarType.LUX, "Toyota Camry", 2021, 154000.00); // Robert $5000
        Car carLux2 = new Car(CarType.LUX, "Ford Ranger", 2021, 89000.00); // john $2000
        Car carHatch1 = new Car(CarType.HATCH, "Toyota Camry", 2012, 23000.00); // sara $300
        Car carHatch2 = new Car(CarType.HATCH, "Toyota Yaris", 2012, 24499.00);
        Car carWagon1 = new Car(CarType.WAGON, "Ford Ranger", 2019, 13000.00);
        Car carWagon2 = new Car(CarType.WAGON, "Nissan Dualis", 2019, 49000.00); // alex, rob, sara $1000
        Car car4WD1 = new Car(CarType.FOURWD, "Porsche Cayenne", 2021, 18000.00); //robert $400
        Car car4WD2 = new Car(CarType.FOURWD, "Nissan Patrol", 2021, 78990.00);
        Car carCoupe1 = new Car(CarType.COUPE, "Ferrari 488", 2016, 8000.00); // alex $500
        Car carCoupe2 = new Car(CarType.WAGON, "Nissan Dualis", 2016, 49000.00);
        Car carUte1 = new Car(CarType.UTE, "Toyota Camry", 2016, 8000.00); // john $100
        Car carUte2 = new Car(CarType.UTE, "Mazda BT-50", 2016, 50990.00);
        
        // additional car for policy holder and car change
        Car carSed3 = new Car(CarType.SED, "Toyota Camry", 2018, 34990.00);
        
        //create expiry date objects for policies
        
        MyDate date1 = new MyDate(2023, 5, 30);
        MyDate date2 = new MyDate(2023, 3, 4);
        MyDate date3 = new MyDate(2023, 10, 15);
        MyDate date4 = new MyDate(2023, 9, 22);
        MyDate date5 = new MyDate(2023, 2, 6);
        MyDate date6 = new MyDate(2023, 12, 28);
        MyDate date7 = new MyDate(2023, 7, 19);
        MyDate date8 = new MyDate(2023, 5, 2);
        
        //create third party insurance policy objects
         
            ThirdPartyPolicy thirdParty1 = new ThirdPartyPolicy("John Steergood", 3000001, carSuv1, 1, date1, "Returning customer");   
            ThirdPartyPolicy thirdParty2 = new ThirdPartyPolicy("Sara Drivebetter", 3000003, carSed1, 0, date2, "Valued customer");   
            ThirdPartyPolicy thirdParty3 = new ThirdPartyPolicy("Robert Bloggs", 3000005, carWagon1, 0, date3, "Multiple policy holder");
            ThirdPartyPolicy thirdParty4 = new ThirdPartyPolicy("John Steergood", 3000007, carUte1, 0, date7, "Multiple policy holder");
    //        ThirdPartyPolicy thirdParty5 = new ThirdPartyPolicy("Ivana Steergood", 3000009, carHatch1, 0, date4, "Maximum no claim bonus");
            ThirdPartyPolicy thirdParty6 = new ThirdPartyPolicy("Sara Drivebetter", 3000011, carHatch1, 0, date5, "Multiple policy holder");
            ThirdPartyPolicy thirdParty7 = new ThirdPartyPolicy("Robert Bloggs", 3000013, car4WD1, 1, date7, "10 year loyalty discount");
            ThirdPartyPolicy thirdParty8 = new ThirdPartyPolicy("Alex Sumguy", 3000015, carCoupe1, 2, date8, "Multiple policy holder");

            //create comprehensive insurance policy objects
            ComprehensivePolicy compPolicy1 = new ComprehensivePolicy("John Steergood", 3000002, carSuv2, 0, date4, 38, 2);
            ComprehensivePolicy compPolicy2 = new ComprehensivePolicy("Sara Drivebetter", 3000004, carSed2, 0, date5, 29, 1);
            ComprehensivePolicy compPolicy3 = new ComprehensivePolicy("Robert Bloggs", 3000006, carLux1, 9, date6, 28, 3);
            ComprehensivePolicy compPolicy4 = new ComprehensivePolicy("John Steergood", 3000008, carLux2, 0, date8, 26, 1);
    //        ComprehensivePolicy compPolicy5 = new ComprehensivePolicy("Ivana Steergood", 3000010, carHatch2, 3, date2, 38, 2);
            ComprehensivePolicy compPolicy6 = new ComprehensivePolicy("Sara Drivebetter", 3000012, carWagon2, 0, date6, 31, 1);
            ComprehensivePolicy compPolicy7 = new ComprehensivePolicy("Robert Bloggs", 3000014, carWagon2, 0, date1, 31, 3);
            ComprehensivePolicy compPolicy8 = new ComprehensivePolicy("Alex Sumguy", 3000016, carWagon2, 0, date1, 36, 2);

        //create user objects that includes addresses
        User john = new User("John", 15, "Runagutter Lane", "Blackwall", "Wollongong", "john123", "pass1");
        User sara = new User("Sara", 48, "Crasheeps Avenue", "Corrimal", "Wollongong", "sara123","pass12");
        User robert = new User("Robert", 6, "Vague Street", "Janali", "Sydney", "rob123", "pass123");
        User alex = new User("Alex", 21, "Ninth Avenue", "St Kilda", "Melbourne", "alex123", "pass567");
        
        //create insurance company object
        insuranceCompany = new InsuranceCompany("NRMA", "bossman", "admin", 20);

        insuranceCompany.addUser("bossman", "admin", sara);
        insuranceCompany.addUser("bossman", "admin", robert);
        insuranceCompany.addUser("bossman", "admin", john);
        insuranceCompany.addUser("bossman", "admin", alex);

        insuranceCompany.addPolicy("bossman", "admin", sara.getUserID(), thirdParty6);
        insuranceCompany.addPolicy("bossman", "admin", sara.getUserID(), thirdParty2);
        insuranceCompany.addPolicy("bossman", "admin", robert.getUserID(), thirdParty3);
        insuranceCompany.addPolicy("bossman", "admin", robert.getUserID(), thirdParty7);
        insuranceCompany.addPolicy("bossman", "admin", sara.getUserID(), compPolicy6);
        insuranceCompany.addPolicy("bossman", "admin", sara.getUserID(), compPolicy2);
        insuranceCompany.addPolicy("bossman", "admin", robert.getUserID(), compPolicy3);
        insuranceCompany.addPolicy("bossman", "admin", robert.getUserID(), compPolicy7);
        insuranceCompany.addPolicy("bossman", "admin", alex.getUserID(), thirdParty8);
        insuranceCompany.addPolicy("bossman", "admin", alex.getUserID(), compPolicy8);
        insuranceCompany.addPolicy("bossman", "admin", john.getUserID(), compPolicy1);
        insuranceCompany.addPolicy("bossman", "admin", john.getUserID(), thirdParty1);
        insuranceCompany.addPolicy("bossman", "admin", john.getUserID(), compPolicy4);
        insuranceCompany.addPolicy("bossman", "admin", john.getUserID(), thirdParty4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        userIdLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        loginBut = new javax.swing.JButton();
        passText = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveMenu = new javax.swing.JMenuItem();
        loadMenu = new javax.swing.JMenuItem();
        listUsersMenu = new javax.swing.JMenu();

        jPasswordField1.setText("jPasswordField1");

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N

        userIdLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userIdLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLabel.setText("Password");

        userText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });

        loginBut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginBut.setForeground(new java.awt.Color(255, 0, 51));
        loginBut.setText("LOGIN");
        loginBut.setToolTipText("");
        loginBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButActionPerformed(evt);
            }
        });
        loginBut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButKeyPressed(evt);
            }
        });

        passText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passText.setActionCommand("<Not Set>");
        passText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        passText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passTextActionPerformed(evt);
            }
        });

        jMenuBar1.setToolTipText("Tim Glasgow 7409072");
        jMenuBar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        saveMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveMenu.setText("Save");
        saveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenu);

        loadMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loadMenu.setText("Load");
        loadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenu);

        jMenuBar1.add(fileMenu);

        listUsersMenu.setText("List of Users");
        listUsersMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        listUsersMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listUsersMenuMouseClicked(evt);
            }
        });
        listUsersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listUsersMenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(listUsersMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginBut)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userIdLabel)
                    .addComponent(passwordLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userText)
                    .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIdLabel)
                    .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(loginBut)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userLoginAttempt()
    {
        String password = String.valueOf(passText.getPassword()); // get text in password field is deprecated getPassword is used
        User user = insuranceCompany.validateUser(userText.getText(), password);

        if (user!=null)
        {
            passText.setText("");
            ArrayList<String> cities = insuranceCompany.populateDistinctCityNames(insuranceCompany.adminUsername, insuranceCompany.adminPassword);
            UserUI userUI = new UserUI(user, this, cities);
            userUI.setVisible(true);
            this.setVisible(false);
        }
        else if (insuranceCompany.validateAdmin(userText.getText(), password))
        {
            passText.setText("");
            ArrayList<String> cities = insuranceCompany.populateDistinctCityNames(insuranceCompany.adminUsername, insuranceCompany.adminPassword);
            AdminUI adminUI = new AdminUI(insuranceCompany, this, cities);
            adminUI.setVisible(true);
            this.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
            passText.setText("");
        }
    }
    
    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTextActionPerformed

    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuActionPerformed

        if (insuranceCompany.save("company.ser"))
        {
            JOptionPane.showMessageDialog(null, "Company Saved");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Save Failed.");
        }
    }//GEN-LAST:event_saveMenuActionPerformed

    private void loadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuActionPerformed
        
        if (insuranceCompany.load("company.ser"))
        {
            JOptionPane.showMessageDialog(null, "Company Loaded");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Load Failed!\nNo Company Data Found.");
        }

        
    }//GEN-LAST:event_loadMenuActionPerformed

    private void loginButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButActionPerformed

        userLoginAttempt();

    }//GEN-LAST:event_loginButActionPerformed

    private void passTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passTextActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileMenuActionPerformed

    private void listUsersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listUsersMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listUsersMenuActionPerformed

    private void listUsersMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listUsersMenuMouseClicked
        String userPasswordList = "";
        for (User user : insuranceCompany.users.values())
        {
            userPasswordList += "UN: " +user.getUsername() + "     PW: " + user.getPassword() + "\n";
        }
        userPasswordList += "\nAdmin\nUN: " + insuranceCompany.getAdminUsername() +"     PW: " + insuranceCompany.getAdminPassword();
                
        JOptionPane.showMessageDialog(this, userPasswordList, "Current Usernames and Passwords", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_listUsersMenuMouseClicked

    private void loginButKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButKeyPressed
        // TODO add your handling code here:
        userLoginAttempt();
    }//GEN-LAST:event_loginButKeyPressed

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
            java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JMenu listUsersMenu;
    private javax.swing.JMenuItem loadMenu;
    private javax.swing.JButton loginBut;
    private javax.swing.JPasswordField passText;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JMenuItem saveMenu;
    private javax.swing.JLabel userIdLabel;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables


    

}

