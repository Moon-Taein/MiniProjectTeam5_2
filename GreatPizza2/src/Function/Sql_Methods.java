package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql_Methods {

	public static boolean mainOrderInsert(MainOrder mo) {

		String sql = "insert into mainorder (no, total_price, `주문날짜`, `주문시간`, `state`) values (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mo.getOrderNumber());
			stmt.setInt(2, mo.getTotal_Price());
			stmt.setString(3, mo.getDate());
			stmt.setString(4, mo.getTime());
			stmt.setString(5, mo.getState());
			return stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return false;
	}

	public static boolean detailOrderInsert(DetailOrder deo) {

		String sql = "insert into detailorder (no, menu, menu_count, mainorder) values (?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, deo.getDetailOrderNumber());
			stmt.setString(2, deo.getMenu());
			stmt.setInt(3, deo.getMenu_count());
			stmt.setInt(4, deo.getMainOrderNumber());
			return stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return false;
	}

	public static boolean menuitemInsert(MenuItem mi) {

		String sql = "insert into menuitem (detailorder_no, inventory_id) values (?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mi.getDetailOrderNumber());
			stmt.setString(2, mi.getInventory_id());
			return stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return false;
	}

	public static boolean findMenuEverything(String target) {

		String sql = "select * from menu where menu_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, target);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String menu_id = rs.getString("menu_id");
				int price = rs.getInt("price");
				String size = rs.getString("size");
				byte[] image = rs.getBytes("image");

				System.out.printf("%d %s %d %s\n", no, menu_id, price, size);
				if (image != null) {
					System.out.println(image.toString());
				}
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return false;
	}

	public String findPriceMenuId(String string) {
		String sql = "select price from menu where menu_id like ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + string + "%");
			rs = stmt.executeQuery();
			int price = 0;

			while (rs.next()) {
				price = rs.getInt("price");
			}

			return String.valueOf(price);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return "";
	}

	public byte[] findPizzaImageMenuId(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		byte[] bytes = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM menu WHERE menu_id = ?");
			stmt.setString(1, string);

			rs = stmt.executeQuery();

			// sql workbench에서 올린거는 그냥 bytes로 받아오면 써지네
			if (rs.next()) {
				bytes = rs.getBytes("image_big");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return bytes;
	}

	// 엣지 이름 담기
	public List<String> pizzamakeSetEdge(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> edgeName = new ArrayList<String>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE inventory_id like ?");
			stmt.setString(1, "%" + string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				String a = rs.getString("inventory_name");
				edgeName.add(a);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return edgeName;

	}

	// 엣지이름 키값, 엣지 이미지 벨류로 갖고있는 해쉬맵
	public HashMap<String, byte[]> pizzamakeSetEdgeimg(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> edgeName = pizzamakeSetEdge(string);
		byte[] bytes = null;
		List<byte[]> edgeImg = new ArrayList<byte[]>();
		HashMap<String, byte[]> edge = new HashMap<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE inventory_id like ?");
			stmt.setString(1, "%" + string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image");
				edgeImg.add(bytes);
			}

			for (int i = 0; i < edgeImg.size(); i++) {
				edge.put(edgeName.get(i), edgeImg.get(i));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return edge;

	}

}
