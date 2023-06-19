package goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GoodMain {
	private static GoodVO gv;
	private GoodDao dao = new GoodDao();
	private static SysList lsy = new SysList();
	private static Scanner scn = new Scanner(System.in);
	private String loginid;

	public static void main(String[] args) {

		Login login = new Login();

		int loginYn = 0;
		System.out.println("=============상품관리===============");
		System.out.println("로그인 창입니다. 로그인 하시겠습니까? (1, 2, 3)");
		System.out.println("  1로그인 : 2회원가입 : 3프로그램종료");
		System.out.print(">");
		loginYn = scn.nextInt();
		switch(loginYn) {
		case 1:
			System.out.print("ID : ");
			String id = scn.next();
			System.out.print("PASSWORD : ");
			String pw = scn.next();
			
			if (login.longin(id, pw)) {
				System.out.println("로그인 완료");
				start();
			} else {
				System.out.println("로그인 실패");
			}
			break;
		case 2:
			System.out.println("회원가입 창입니다");
			UserInfo userinfo = new UserInfo();
			userinfo.UserInfo();
			start();
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		}
//		while (true) {
//			GoodMain main = new GoodMain();
//			
//			if (loginYn.toUpperCase().equals("Y")) {
//				System.out.print("ID : ");
//				String id = scn.next();
//				System.out.print("PASSWORD : ");
//				String pw = scn.next();
//
//				if (login.longin(id, pw)) {
//					System.out.println("로그인 완료");
//					main.start();
//				} else {
//					System.out.println("로그인 실패");
//					break;
//				}
//			} else if (loginYn.toUpperCase().equals("N")) {
//				System.out.println("회원가입 창입니다");
//				UserInfo userinfo = new UserInfo();
//				userinfo.UserInfo();
//			} else if(loginYn.toUpperCase().equals("O")){
//				System.out.println("프로그램 종료");
//				break;
//			}
//		}
	}

	public static void start() {
		
		while (true) {
			int selNum = 0;
			
			System.out.println("===================================================================");
			System.out.println("1상품등록 || 2상품입,출고 || 3상품삭제 || 4상품조회 || 5상품목록 || 6프로그램 종료");
			System.out.println("===================================================================");
			System.out.println("번호를 선택해주세요>:");
			selNum = scn.nextInt();
			// 등록
			if (selNum == 1) {
				lsy.registerStart();
				// 수정
			} else if (selNum == 2) {
				System.out.println("===============================");
				// 조회
				lsy.listStart();
				// 수정
				lsy.updateStart();

			} else if (selNum == 3) {
				// 제거
				lsy.removeStart();

			} else if (selNum == 4) {
				// 선택조회
				lsy.searchStart();

			} else if (selNum == 5) {
				System.out.println("===============================");
				System.out.println("목록 창 입니다.");

				lsy.listStart();

			} else if (selNum == 6) {
				System.out.println("프로그램 종료.");
				System.out.println("===============================");
				break;
			}
			else {
				System.out.println("잘못 입력하셨습니다");
			}
		}		
	}
}
