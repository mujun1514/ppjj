package goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {
	static Connection conn;
	static PreparedStatement psmt;
	static ResultSet rs;
	static String sql;

	private static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 등록
	public boolean register(GoodVO good) {
		conn = Dao.getConnect();
		sql = "insert into goods_table (good_no, good_name, good_price, good_content, goods_number)"//
				+ "values(good_seq.nextval, ?, ?, ?, ?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, good.getGood_name());
			psmt.setString(2, good.getGood_price());
			psmt.setString(3, good.getGood_content());
			psmt.setInt(4, good.getGoods_number());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;

	}

	// 수정
	public int plusSys(String good_no,int goods_number) {
				
		System.out.println(good_no);
		System.out.println(goods_number);
		
		
		conn = Dao.getConnect();
		sql = "update goods_table set goods_number = goods_number + ? where good_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,goods_number);
			psmt.setString(2,good_no);
			
			int r = psmt.executeUpdate();
			
			System.out.println("성공");
			
			if (r > 0) {
				return 1;
			}
		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
	public int minusSys(String good_no,int goods_number) {
		
		GoodVO good =new GoodVO();
		SysList sys =new SysList();
//		System.out.println(good_no);
//		System.out.println(goods_number);
//		int a = Integer.parseInt(good.getGoods_number());
//		int b = Integer.parseInt(goods_number);
		conn = Dao.getConnect();
		sql = "update goods_table set goods_number = goods_number - ? where good_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,goods_number);
			psmt.setString(2,good_no);
			
			if(good.getGoods_number() - goods_number < 0) {
				System.out.println("개수가 부족합니다");
				sys.updateStart();
				return 0;
			}else if(good.getGoods_number() - goods_number > 0) {
				System.out.println("정상적으로 출고되었습니다.");
				return 1;
			}
			
			int r = psmt.executeUpdate();
			
			if (r > 0) {
				return 1;
			}
		} catch (Exception e) {
			System.out.println("오류");
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
//	public boolean update(GoodVO good) {
//		sql = " update goods_table" 
//				+ "	set good_name = nvl(?,good_name), "	
//				+ "     good_price = nvl(?,good_name), "
//				+ "     good_content = nvl(?,good_name), "
//				+ "     goods_number = nvl(?,good_name) "
//				+ "	where good_no = ?";
//		conn = Dao.getConnect();
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, good.getGood_name());
//			psmt.setString(2, good.getGood_price());
//			psmt.setString(3, good.getGood_content());
//			psmt.setString(4, good.getGoods_number());
//			psmt.setString(5, good.getGood_no());
//
//			int r = psmt.executeUpdate();
//
//			if (r > 0) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return false;
//	}

	// 삭제
	public boolean remove(int good_no) {
		sql = "delete from goods_table where good_no=?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, good_no);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return false;

	}

	// 조회
	public GoodVO search(String good_no) {
		sql = " select * from goods_table where good_no=?";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, good_no);
			rs = psmt.executeQuery();
			if (rs.next()) {

				GoodVO good = new GoodVO();
				good.setGood_no(rs.getString("good_no"));
				good.setGood_name(rs.getString("good_name"));
				good.setGood_price(rs.getString("good_price"));
				good.setGood_content(rs.getString("good_content"));
				good.setGoods_number(rs.getInt("goods_number"));
				good.setGood_date(rs.getString("good_date"));
				good.setGood_cnt(rs.getString("good_cnt"));
				return good;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return null;
	}

	// 목록
	public List<GoodVO> list() throws SQLException {
		List<GoodVO> list = new ArrayList<>();

		sql = "select * from goods_table";

		conn = Dao.getConnect();
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		
		try {
			while (rs.next()) {
				GoodVO good = new GoodVO();

				good.setGood_no(rs.getString("good_no"));
				good.setGood_name(rs.getString("good_name"));
				good.setGood_price(rs.getString("good_price"));
				good.setGood_content(rs.getString("good_content"));
				good.setGoods_number(rs.getInt("goods_number"));
				good.setGood_date(rs.getString("good_date"));
				good.setGood_cnt(rs.getString("good_cnt"));

				list.add(good);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;

	}

}