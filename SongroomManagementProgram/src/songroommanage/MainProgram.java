package songroommanage;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainProgram {
	public static void main(String[] args) {
		System.out.println("program start!");
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songroomdb","root","alice0628!!"); 
			 System.out.println("DB 연결 완료");
			 Statement stmt = conn.createStatement();
			 SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						foodmanagement jFrame = new foodmanagement();
						jFrame.setVisible(true);
					}
				});
			 } catch (ClassNotFoundException e) {
			 System.out.println("JDBC 드라이버 로드 에러");
			 } catch (SQLException e) {
			 System.out.println("DB 연결 에러");
			 }
	}
}
