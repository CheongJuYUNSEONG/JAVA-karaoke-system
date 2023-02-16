package songroommanage;
import java.sql.*;

public class DataManagementClass {
	static void userinsert(Statement stmt,String u_id,String u_name,int point) throws SQLException
	{
		//DataManagementClass.userinsert(stmt,"01028224080","코윤성",0); 유저 삽입문
		stmt.executeUpdate("insert into users_info(u_id,u_name,point) value ('"+u_id+"','"+u_name+"',"+point+");");
	}
	static void foodinsert(Statement stmt,int f_id,String f_name,int f_price,String f_data) throws SQLException
	{
		//DataManagementClass.foodinsert(stmt,201810704,"라면",2000,"평범한 라면인듯 하다"); 음식 삽입문
		stmt.executeUpdate("insert into food_info(f_id,f_name,f_price,f_data) value ("+f_id+",'"+f_name+"',"+f_price+",'"+f_data+"');");
	}
	static void fooddelete(Statement stmt,int f_id) throws SQLException
	{
		stmt.executeUpdate("delete from food_info where f_id="+f_id+";");
	}
	static void foodmodify(Statement stmt,int f_id,String f_name,int f_price,String f_data) throws SQLException
	{
		stmt.executeUpdate("update food_info set f_name='"+f_name+"',f_price="+f_price+",f_data='"+f_data+"' where f_id="+f_id+";");
	}
}
/*class user{
	String name;
	String id;
 	int point;
	user(String name,String id,int point)
	{
		this.name=name; this.id=id;this.point=point;
	}
}*/