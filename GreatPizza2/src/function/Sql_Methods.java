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
		// select no from menu ORDER BY no DESC LIMIT 1;
		// detailordernumber 도 똑같이 해야할듯
		// 메인오더 no 디비에서 menu no 가져와서 + 1 해서 만들기
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

	public static boolean addIngredientInsert(MenuItem mi) {
		String sql = "insert into addIngredient (detailorder_no, ingredient_id) values (?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mi.getDetailOrderNumber());
			stmt.setString(2, mi.getingredient_id());
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
			DBUtil.close(rs);
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
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return "";
	}

	public int findPriceIngredient(String string) {
		String sql = "select price_retail from ingredient where ingredient_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, string);
			rs = stmt.executeQuery();
			int price = 0;

			if (rs.next()) {
				price = rs.getInt("price_retail");
			}

			return price;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return 0;
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

	public List<String> findToppingPriceMenuId(String string) {
		String sql = "select A.ingredient_id, A.price_retail\r\n" + "from ingredient as A\r\n"
				+ "join (select menu_id, ingredient_id, count from recipe where menu_id = ? and ingredient_id like '토핑%') as B\r\n"
				+ "on A.ingredient_id = B.ingredient_id;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		// 세팅해줘서 넣어주기
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, string);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String ingredient_id = rs.getString("ingredient_id");
				int price = rs.getInt("price_retail");
				String price_retail = String.valueOf(price + "원");
				if (ingredient_id != null && price_retail != null) {
					list.add(ingredient_id);
					list.add(price_retail);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		System.out.println(list.toString());
		if (list.size() < 8) {
			for (int i = list.size(); i < 8; i++) {
				list.add(i, "");
			}
		}
		return list;
	}

	// 엣지 이름 담기
	public List<String> pizzamakeSetEdge(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> edgeName = new ArrayList<String>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
			stmt.setString(1, "%" + string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				String a = rs.getString("ingredient_name");
				edgeName.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
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
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
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
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return edge;

	}

	/**
	 * @author TAEIN
	 * @param name   ( %M, 사이드%, 음료% )
	 * @param target ( next 버튼 추가시 6씩만 더해주면됨 )
	 * @return List<Object> ( 메뉴이름, 이미지 순서 )
	 */
	public List<Object> findImageAndMenuIdTarget(String name, int target) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<>();
		byte[] bytes = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select menu_id, image from menu where menu_id like ? order by no limit 6 offset ?");
			stmt.setString(1, name);
			stmt.setInt(2, target);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String menu_id = rs.getString("menu_id");
				bytes = rs.getBytes("image");
				list.add(menu_id);
				list.add(bytes);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		System.out.println(list.toString());
		System.out.println(list.size());
//		if (list.size() < 12) {
//			for (int i = list.size(); i < 12; i++) {
//				list.add(i, "");
//			}
//		}
		return list;
	}

	// 토핑% 로 검색
	public List<Object> findImageAndIngredient_IdTarget(String name, int target) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<>();
		byte[] bytes = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select ingredient_id, image_box from ingredient where ingredient_id like ? order by no limit 6 offset ?");
			stmt.setString(1, name);
			stmt.setInt(2, target);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String ingredient_id = rs.getString("ingredient_id");
				bytes = rs.getBytes("image_box");
				list.add(ingredient_id);
				list.add(bytes);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		System.out.println(list.toString());
		System.out.println(list.size());
		return list;
	}

	// name으로 topping_Image 가져오기
	public byte[] findImageIngredientTarget(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		byte[] bytes = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select image from ingredient where ingredient_id = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image");
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

	public int findMainOrderCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select no from mainorder order by no desc limit 1");
			rs = stmt.executeQuery();

			int target = 0;
			if (rs.next()) {
				target = rs.getInt("no");
				System.out.println(target);
			}
			return target;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	public int findDetailOrderCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select no from detailorder order by no desc limit 1");
			rs = stmt.executeQuery();

			int target = 0;
			if (rs.next()) {
				target = rs.getInt("no");
				System.out.println(target);
			}
			return target;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	public List<String> pizzamakeSetToping(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> topingName = new ArrayList<String>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
			if (!string.equals("소스")) {
				stmt.setString(1, "%" + string + "%");
			} else {
				stmt.setString(1, string + "%");
			}

			rs = stmt.executeQuery();

			while (rs.next()) {
				String a = rs.getString("ingredient_name");
				topingName.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return topingName;

	}

	public HashMap<String, byte[]> getTopingImgInBox(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> topingName = pizzamakeSetToping("토핑");
		byte[] bytes = null;
		List<byte[]> topingImgBox = new ArrayList<byte[]>();
		HashMap<String, byte[]> topingBox = new HashMap<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
			stmt.setString(1, "%" + string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image_box");
				topingImgBox.add(bytes);
			}

			for (int i = 0; i < topingImgBox.size(); i++) {
				topingBox.put(topingName.get(i), topingImgBox.get(i));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return topingBox;

	}

	public HashMap<String, byte[]> getOnTopping(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> topingName = pizzamakeSetToping("토핑");
		byte[] bytes = null;
		List<byte[]> topingImgBox = new ArrayList<byte[]>();
		HashMap<String, byte[]> topingBox = new HashMap<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_name like ?");
			stmt.setString(1, string);

			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image");
				topingImgBox.add(bytes);
			}

			for (int i = 0; i < topingImgBox.size(); i++) {
				topingBox.put(topingName.get(i), topingImgBox.get(i));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return topingBox;

	}

	public HashMap<String, byte[]> getOnSource(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> sourceName = pizzamakeSetToping("소스");
		byte[] bytes = null;
		List<byte[]> sourceImg = new ArrayList<byte[]>();
		HashMap<String, byte[]> sourceBox = new HashMap<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
			stmt.setString(1, string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image_box");
				sourceImg.add(bytes);

			}

			for (int i = 0; i < sourceImg.size(); i++) {
				sourceBox.put(sourceName.get(i), sourceImg.get(i));

			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return sourceBox;

	}

	public HashMap<String, byte[]> getOnSourceImg(String string) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> sourceName = pizzamakeSetToping("소스");
		byte[] bytes = null;
		List<byte[]> sourceImg = new ArrayList<byte[]>();
		HashMap<String, byte[]> sourceBox = new HashMap<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ingredient WHERE ingredient_id like ?");
			stmt.setString(1, string + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {
				bytes = rs.getBytes("image");
				sourceImg.add(bytes);

			}

			for (int i = 0; i < sourceImg.size(); i++) {
				sourceBox.put(sourceName.get(i), sourceImg.get(i));

			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return sourceBox;

	}

}