package songroommanage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;
public class foodmanagement extends JFrame {
	private int foodcount = 0;
	private JPanel foodmanagepanel;
	private JTable FLT;//foodlisttable
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnModify;
	private GridLayout GLO;
	private String colNames[]= {"ID","이름","가격","정보"};
	private DefaultTableModel model = new DefaultTableModel(colNames,0);
	public foodmanagement() {
		this.setTitle("음식물 관리");
		this.setSize(565,576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new JScrollPane(foodlist()),BorderLayout.WEST);
		this.getContentPane().add(getfoodmanagepanel(),BorderLayout.EAST);
	}
	public JPanel getfoodmanagepanel() {
		if(foodmanagepanel==null) {
			foodmanagepanel = new JPanel();
			foodmanagepanel.setBackground(Color.WHITE);
			foodmanagepanel.setLayout(foodbtnlayout());
			foodmanagepanel.add(foodaddbtn());
			foodmanagepanel.add(foodmodifybtn());
			foodmanagepanel.add(fooddeletebtn());
		}
		return foodmanagepanel;
	}
	private JButton foodaddbtn() {
		if(btnAdd == null) {
			btnAdd = new JButton();
			btnAdd.setText("음식물 추가");
			btnAdd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					AddFood AF = new AddFood();
					AF.setVisible(true);
				}
			});
		}
		return btnAdd;
	}
	private JButton fooddeletebtn() {
		if(btnDelete==null) {
			btnDelete = new JButton();
			btnDelete.setText("음식물 삭제");
			btnDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					DelFood DF = new DelFood();
					DF.setVisible(true);
				}
			});
		}
		return btnDelete;
	}
	private JButton foodmodifybtn() {
		if(btnModify == null ) {
			btnModify = new JButton();
			btnModify.setText("음식물 수정");
			btnModify.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Modifyfood MF = new Modifyfood();
					MF.setVisible(true);
				}
			});
		}
		return btnModify;
	}
	private GridLayout foodbtnlayout()
	{
		if(GLO == null)
		{
			GLO = new GridLayout(3,1);
		}
		return GLO;
	}
	private JTable foodlist() {
		if(FLT==null) {
			try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songroomdb","root","alice0628!!"); 
			 System.out.println("DB 연결 완료");
			 Statement stmt = conn.createStatement();
			 ResultSet srs = stmt.executeQuery("select * from food_info;");
			 while(srs.next()) {
				 model.addRow(new Object[] {srs.getString("f_id"),srs.getString("f_name"),srs.getString("f_price"),srs.getString("f_data")});
				 foodcount++;
			 }
			 FLT = new JTable(model);
			 FLT.getColumn("ID").setPreferredWidth(100);
			 FLT.getColumn("이름").setPreferredWidth(100);
			 FLT.getColumn("가격").setPreferredWidth(100);
			 FLT.getColumn("정보").setPreferredWidth(100);
			 } catch (ClassNotFoundException e) {
			 System.out.println("JDBC 드라이버 로드 에러");
			 } catch (SQLException e) {
			 System.out.println("DB 연결 에러");
			 }
		}
		return FLT;
	}
	class AddFood extends JFrame{
		private JTextField fname;
		private JTextField fprice;
		private JTextField fdata;
		private JPanel fnamepanel;
		private JPanel fpricepanel;
		private JPanel fdatapanel;
		private JButton addbtn;
		AddFood(){
			setTitle("음식 추가하기");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(getfnamepanel());
			c.add(getfpricepanel());
			c.add(getfdatapanel());
			c.add(getaddbtn());
			this.setSize(300,300);
			this.setVisible(true);
		}
		private JPanel getfnamepanel()
		{
			if(fnamepanel == null)
			{
				fnamepanel = new JPanel();
				fnamepanel.setBackground(Color.WHITE);
				fnamepanel.add(new JLabel("이름 :"));
				fnamepanel.add(getfname());
			}
			return fnamepanel;
		}
		private JPanel getfpricepanel()
		{
			if(fpricepanel == null)
			{
				fpricepanel = new JPanel();
				fpricepanel.setBackground(Color.WHITE);
				fpricepanel.add(new JLabel("가격 :"));
				fpricepanel.add(getfprice());
			}
			return fpricepanel;
		}
		private JPanel getfdatapanel()
		{
			if(fdatapanel == null)
			{
				fdatapanel = new JPanel();
				fdatapanel.setBackground(Color.WHITE);
				fdatapanel.add(new JLabel("정보 :"));
				fdatapanel.add(getfdata());
			}
			return fdatapanel;
		}
		private JTextField getfname() {
			if(fname == null) {
				fname = new JTextField(20);
			}
			return fname;
		}
		private JTextField getfprice() {
			if(fprice == null) {
				fprice = new JTextField(20);
			}
			return fprice;
		}
		private JTextField getfdata() {
			if(fdata == null) {
				fdata = new JTextField(20);
			}
			return fdata;
		}
		private JButton getaddbtn() {
			if(addbtn==null) {
				addbtn = new JButton();
				addbtn.setText("추가하기");
				addbtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							 Class.forName("com.mysql.cj.jdbc.Driver");
							 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songroomdb","root","alice0628!!"); 
							 System.out.println("DB 연결 완료");
							 Statement stmt = conn.createStatement();
							 DataManagementClass.foodinsert(stmt, foodcount, getfname().getText(), Integer.parseInt(getfprice().getText()), getfdata().getText());
							 dispose();
							 FLT.repaint();
							 } catch (ClassNotFoundException e1) {
							 System.out.println("JDBC 드라이버 로드 에러");
							 } catch (SQLException e1) {
							 System.out.println("DB 연결 에러");
							 }
					}
				});
			}
			return addbtn;
		}
	}
	class DelFood extends JFrame{
			private JTextField fid;
			private JPanel fidpanel;
			private JButton delbtn;
			DelFood(){
				setTitle("음식 삭제하기");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container c = getContentPane();
				c.setLayout(new FlowLayout());
				c.add(getfidpanel());
				c.add(getfdelbtn());
				this.setSize(300,300);
				this.setVisible(true);
			}
			private JPanel getfidpanel() {
				if(fidpanel==null) {
					fidpanel = new JPanel();
					fidpanel.setBackground(Color.WHITE);
					fidpanel.add(new JLabel("삭제할 id :"));
					fidpanel.add(getfid());
				}
				return fidpanel;
			}
			private JTextField getfid() {
				if(fid == null) {
					fid = new JTextField(20);
				}
				return fid;
				}
			private JButton getfdelbtn() {
				if(delbtn==null) {
					delbtn = new JButton();
					delbtn.setText("삭제하기");
					delbtn.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							try {
								 Class.forName("com.mysql.cj.jdbc.Driver");
								 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songroomdb","root","alice0628!!"); 
								 System.out.println("DB 연결 완료");
								 Statement stmt = conn.createStatement();
								 DataManagementClass.fooddelete(stmt,Integer.parseInt(getfid().getText()));
								 dispose();
								 FLT.repaint();
								 } catch (ClassNotFoundException e1) {
								 System.out.println("JDBC 드라이버 로드 에러");
								 } catch (SQLException e1) {
								 System.out.println("DB 연결 에러");
								 }
						}
					});
			}
				return delbtn;
		}
	}
	class Modifyfood extends JFrame 
	{
		private JTextField fid;
		private JPanel fidpanel;
		private JTextField fname;
		private JTextField fprice;
		private JTextField fdata;
		private JPanel fnamepanel;
		private JPanel fpricepanel;
		private JPanel fdatapanel;
		private JButton modifybtn;
		Modifyfood(){
			setTitle("음식 수정하기");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			c.add(getfidpanel());
			c.add(getfnamepanel());
			c.add(getfpricepanel());
			c.add(getfdatapanel());
			c.add(getmodifybtn());
			this.setSize(300,300);
			this.setVisible(true);
		}
		private JPanel getfidpanel() {
			if(fidpanel==null) {
				fidpanel = new JPanel();
				fidpanel.setBackground(Color.WHITE);
				fidpanel.add(new JLabel("수정할 ID: "));
				fidpanel.add(getfid());
			}
			return fidpanel;
		}
		private JTextField getfid() {
			if(fid == null) {
				fid = new JTextField(20);
			}
			return fid;
			}
		private JPanel getfnamepanel()
		{
			if(fnamepanel == null)
			{
				fnamepanel = new JPanel();
				fnamepanel.setBackground(Color.WHITE);
				fnamepanel.add(new JLabel("이름 :"));
				fnamepanel.add(getfname());
			}
			return fnamepanel;
		}
		private JPanel getfpricepanel()
		{
			if(fpricepanel == null)
			{
				fpricepanel = new JPanel();
				fpricepanel.setBackground(Color.WHITE);
				fpricepanel.add(new JLabel("가격 :"));
				fpricepanel.add(getfprice());
			}
			return fpricepanel;
		}
		private JPanel getfdatapanel()
		{
			if(fdatapanel == null)
			{
				fdatapanel = new JPanel();
				fdatapanel.setBackground(Color.WHITE);
				fdatapanel.add(new JLabel("정보 :"));
				fdatapanel.add(getfdata());
			}
			return fdatapanel;
		}
		private JTextField getfname() {
			if(fname == null) {
				fname = new JTextField(20);
			}
			return fname;
		}
		private JTextField getfprice() {
			if(fprice == null) {
				fprice = new JTextField(20);
			}
			return fprice;
		}
		private JTextField getfdata() {
			if(fdata == null) {
				fdata = new JTextField(20);
			}
			return fdata;
		}
		private JButton getmodifybtn() {
			if(modifybtn == null) {
				modifybtn = new JButton();
				modifybtn.setText("수정하기");
				modifybtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							 Class.forName("com.mysql.cj.jdbc.Driver");
							 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/songroomdb","root","alice0628!!"); 
							 System.out.println("DB 연결 완료");
							 Statement stmt = conn.createStatement();
							 DataManagementClass.foodmodify(stmt,Integer.parseInt(getfid().getText()), getfname().getText(), Integer.parseInt(getfprice().getText()), getfdata().getText());
							 dispose();
							 } catch (ClassNotFoundException e1) {
							 System.out.println("JDBC 드라이버 로드 에러");
							 } catch (SQLException e1) {
							 System.out.println("DB 연결 에러");
							 }
					}
				});
			}
			return modifybtn;
		}
	}
}


