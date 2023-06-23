import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// 문태인 바보 ㅠㅠ

		// 주문을 어떤 형식으로 받고 어떤 형식으로 전달할지
		// 일일현황, 정산결과 등을 어떻게 보관할것인지
		// 파일을 사용해서 보관할것과 DB에 그냥 보관할것 나누기

		// 주문이 있는지 계속해서 확인하는 동작 필요
		// 주문 발생시 사운드와 함께 주문내역에 주문내용 읽어서 표시해주기
		// 주문 상태 색깔로 표시

		// 피자를 만들고 포장해서 고객에게 전달 하는 일련의 과정
		// 데이터 후처리

		// 주문부터 고객전달까지 일련의 과정에서 필요한 동작
		// 위 동작이 완료 되었을때 데이터 처리

		String sql = "select * from movie";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("title");
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

	}
}