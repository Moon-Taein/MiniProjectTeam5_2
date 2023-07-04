package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MakePizzaFunction {

	public List<String> mypizzaDetailOrder(String souse, String edge, List<String> toppingList) {
		List<String> list = new ArrayList<>();
		list.add("소스_" + souse);
		list.add("엣지_" + edge);
		for (int i = 0; i < toppingList.size(); i++) {
			list.add("토핑_" + toppingList.get(i));
		}
		return list;
	}
	// for문 돌리기

	public MenuItem mypizzaMenuItem(int detailOrderNumber, String ingredient_id, int price) {

		return new MenuItem(detailOrderNumber, ingredient_id, price);

	}

	public DetailOrder detailOrder(int detailOrderNumber, int mainOrderNumber, int detailOrderFullPrice) {
		String menu = "피자_나만의피자";
		int menu_count = 1;

		return new DetailOrder(detailOrderNumber, menu, menu_count, mainOrderNumber, detailOrderFullPrice);
	}

	public int sumDetailOrder(List<String> toppingId) {
		int originDetailOrder = 9900;
		int price;

		for (int i = 0; i < toppingId.size(); i++) {
			price = sumDetailOrderTopping(toppingId.get(i));
			originDetailOrder += price;
			System.out.println(originDetailOrder);
		}
		return originDetailOrder;

	}

	public int sumDetailOrderTopping(String toppingId) {

		String sql = "select * from ingredient where ingredient_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, toppingId);
			System.out.println(toppingId);
			rs = stmt.executeQuery();
			int price = 0;
			if (rs.next()) {
				price = rs.getInt("price_retail");
				return price;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);

		}

		return 0;

	}
}
