
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil {
	// key = value 형태의 properties 파일을 객체 형태로 사용할 수 있는 클래스이다.
	private static final Properties PROPS = new Properties();
	private static DataSource dataSource;

	static {
		try {
			PROPS.load(DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties"));
//			Class.forName(PROPS.getProperty("jdbc.DRIVER"));

			BasicDataSource ds = new BasicDataSource();

			// datasource에 dirver, 사용할 db, user, password 세팅
			ds.setDriverClassName(PROPS.getProperty("jdbc.DRIVER"));
			ds.setUrl(PROPS.getProperty("jdbc.URL"));
			ds.setUsername(PROPS.getProperty("jdbc.USER"));
			ds.setPassword(PROPS.getProperty("jdbc.PASSWORD"));

			// 처음 connection 갯수
			ds.setInitialSize(0);

			// 최대 connection 갯수
			ds.setMaxTotal(8);

			dataSource = ds;
		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		// close 호출시 알아서 connection pool 에 반납
		return dataSource.getConnection();
//		return DriverManager.getConnection(PROPS.getProperty("jdbc.URL"), PROPS.getProperty("jdbc.USER"),
//				PROPS.getProperty("jdbc.PASSWORD"));
	}

	public static void close(Connection conect) {
		if (conect != null) {
			try {
				conect.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

}