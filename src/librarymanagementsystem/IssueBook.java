/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 88017
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    Connection con1,con2,con;
    public IssueBook() {
        try {
            initComponents();
            
            con1 = DriverManager.getConnection("jdbc:derby://localhost:1527/managebooks", "root","root");
            con2 = DriverManager.getConnection("jdbc:derby://localhost:1527/managemembers", "root","root");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/issuebooks", "root","root");
       
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getBookDetails(){
        try {
            int bookId=Integer.parseInt(txt_bookId.getText());
            PreparedStatement ps=con1.prepareStatement("SELECT * FROM MANAGEBOOKS WHERE BOOK_ID = ?");
            ps.setInt(1,bookId);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("BOOK_ID"));
                lbl_bookName.setText(rs.getString("BOOK"));
                lbl_author.setText(rs.getString("AUTHOR"));
                lbl_quantity.setText(rs.getString("QUANTITY"));
                bookerror.setText(null);
            }
            else{
                bookerror.setText("Invalid Book Id !!!");
                lbl_bookId.setText(null); 
                lbl_bookName.setText(null); 
                lbl_author.setText(null);
                lbl_quantity.setText(null);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getMemberDetails(){
        try {
            int memberId=Integer.parseInt(txt_memberId.getText());
            PreparedStatement ps=con2.prepareStatement("SELECT * FROM MANAGEMEMBERS WHERE MEMBER_ID = ?");
            ps.setInt(1,memberId);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                lbl_memberId.setText(rs.getString("MEMBER_ID"));
                lbl_memberName.setText(rs.getString("MEMBER"));
                lbl_profession.setText(rs.getString("PROFESSION"));
                lbl_contact.setText(rs.getString("PHONE"));
                membererror.setText(null);
            }
            else{
                membererror.setText("Invalid Member Id !!!");
                lbl_memberId.setText(null); 
                lbl_memberName.setText(null); 
                lbl_profession.setText(null);
                lbl_contact.setText(null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean issueBooks(){
        boolean isIssued=false;
        int bookId= Integer.parseInt(txt_bookId.getText());
        int memberId= Integer.parseInt(txt_memberId.getText());
        String bookName=lbl_bookName.getText();
        String memberName=lbl_memberName.getText();
        
        java.util.Date issueDate = date_issueDate.getDatoFecha();
        java.util.Date returnDate = date_returnDate.getDatoFecha();
        
        long l1 =issueDate.getTime();
        long l2= returnDate.getTime();
        
        java.sql.Date IssueDate = new java.sql.Date(issueDate.getTime());
        java.sql.Date ReturnDate = new java.sql.Date(returnDate.getTime());
        
        try{
            String sql = "INSERT INTO ISSUEBOOKS (BOOK_ID, BOOK, MEMBER_ID, MEMBER, ISSUE_DATE, RETURN_DATE, STATUS) VALUES (?, ?, ?, ?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,bookId);
            ps.setString(2,bookName);
            ps.setInt(3,memberId);
            ps.setString(4,memberName);
            ps.setDate(5, IssueDate);
            ps.setDate(6, ReturnDate);
            ps.setString(7,"Pending");
            int rowCount=ps.executeUpdate();
            if(rowCount>0){
                isIssued=true;
            }
            else{
                isIssued=false;
            }
        }catch(Exception e){
            
        }
        return isIssued;
    }
    
    public void updateBookCount(){
        int bookId= Integer.parseInt(txt_bookId.getText());
        try{
            String sql="UPDATE MANAGEBOOKS SET QUANTITY=QUANTITY-1 WHERE BOOK_ID = ?";
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.setInt(1,bookId);
            int rowCount=ps.executeUpdate();
            if(rowCount>0){
                JOptionPane.showMessageDialog(this,"Book count updated!");
                int initialCount= Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount-1));
            }
            else{
                JOptionPane.showMessageDialog(this,"Book count update failed!");
            }
        }catch(Exception e){
           
        }
    }
    
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued=false;
        int bookId= Integer.parseInt(txt_bookId.getText());
        int memberId= Integer.parseInt(txt_memberId.getText());
        
        try {
            String sql = "SELECT FROM ISSUEBOOKS WHERE BOOK_ID=? AND MEMBER_ID=? AND STATUS=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,bookId);
            ps.setInt(2,memberId);
            ps.setString(3,"Pending");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                isAlreadyIssued=true;
            }
            else{
                isAlreadyIssued=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAlreadyIssued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        membererror = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_contact = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_memberId = new javax.swing.JLabel();
        lbl_memberName = new javax.swing.JLabel();
        lbl_profession = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bookerror = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_memberId = new app.bolivia.swing.JCTextField();
        date_returnDate = new rojeru_san.componentes.RSDateChooser();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel2.setText("Member Details");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        membererror.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        membererror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(membererror, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 300, 30));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Id :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        lbl_contact.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_contact.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 250, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Profession :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Member's Name :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        lbl_memberId.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_memberId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 250, 30));

        lbl_memberName.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_memberName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 230, 30));

        lbl_profession.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_profession.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_profession, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 250, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Contact no :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 420, 810));

        jPanel4.setBackground(new java.awt.Color(240, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 116, 42));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

        jLabel12.setFont(new java.awt.Font("Yu Gothic Medium", 0, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText(" Book Details");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        bookerror.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        bookerror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel4.add(bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 310, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Book Id :");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 250, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author :");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book Name :");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 250, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 250, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 250, 30));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Quantity :");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText(" Issue Book");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 240, 60));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(102, 102, 255));
        jLabel10.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" X");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 0, 50, 50));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Return Date :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 540, 130, 30));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id....");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 220, 280, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Member Id :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 120, 30));

        txt_memberId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_memberId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_memberId.setPlaceholder("Enter Book Id....");
        txt_memberId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 310, 280, -1));

        date_returnDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_returnDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_returnDate.setPlaceholder("Select Return date");
        jPanel1.add(date_returnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 530, 280, -1));

        date_issueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_issueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_issueDate.setPlaceholder("Select Issue date");
        jPanel1.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 430, 280, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Book Id :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 100, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("Issue Date :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 440, 120, 30));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("Issue Book");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 650, 240, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 810));

        setSize(new java.awt.Dimension(1411, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if(lbl_quantity.getText().equals("0")){
            JOptionPane.showMessageDialog(this,"Book isn't available");
        }
        else{
            if(isAlreadyIssued()==false){
            if(issueBooks()==true){
            JOptionPane.showMessageDialog(this,"Book issued successfully");
            updateBookCount();
        }
        else{
            JOptionPane.showMessageDialog(this,"Book issue failed!");
        }
        }
        else{
            JOptionPane.showMessageDialog(this,"This member has already this book!");
        }
        }
        
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if(!txt_bookId.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_memberIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberIdFocusLost
        if(!txt_memberId.getText().equals("")){
        getMemberDetails();
        }
    }//GEN-LAST:event_txt_memberIdFocusLost

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookerror;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private rojeru_san.componentes.RSDateChooser date_returnDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_contact;
    private javax.swing.JLabel lbl_memberId;
    private javax.swing.JLabel lbl_memberName;
    private javax.swing.JLabel lbl_profession;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel membererror;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_memberId;
    // End of variables declaration//GEN-END:variables
}
