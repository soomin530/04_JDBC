package studentManagement.dao;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class StudMgmtDAOImpl implements StudMgmtDAO {
	
	// JDBC 객체 참조변수 + Properties 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public StudMgmtDAOImpl() {
		// StudMgmtDAOImpl 객체가 생성될 때
		// sql.xml 파일의 내용을 읽어와
		// Properties prop 객체에 K:V 세팅
		
		try {
			
			String filePath = StudMgmtDAOImpl.class
					.getResource("/xml/sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath)); 
			
		} catch (Exception e) {
			System.out.println("sql.xml 로드 중 예외발생");
			e.printStackTrace();
		}
		
	}

}
