package delivery.view;

import java.util.ArrayList;
import java.util.Scanner;

import delivery.controller.AdminController;
import delivery.controller.CustomerController;
import delivery.controller.OrderController;
import delivery.controller.OwnerController;
import delivery.model.vo.BlackList;
import delivery.model.vo.Complain;
import delivery.model.vo.Customer;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;

public class DeliveryMenu {

	private Scanner sc;
	private AdminController ac;
	private CustomerController cc;
	private OwnerController oc;
	private OrderController orderC;
	static String userID;
	static String a;

	public DeliveryMenu() {
		sc = new Scanner(System.in);
		ac = new AdminController();
		cc = new CustomerController();
		oc = new OwnerController();
		orderC=new OrderController();
	}

	public void Menu() {

		while (true) {

			System.out.println("==========배달 프로그램 ==========");
			System.out.println("1. 관리자 로그인");
			System.out.println("2. 점주 로그인");
			System.out.println("3. 고객 로그인");
			System.out.println("4. 회원가입(점주, 고객)");
			System.out.println("0. 프로그램 종료");
			System.out.print("■선택 > ");

			int select = sc.nextInt();
			switch (select) {

			case 1:
				loginAdmin(); // 아디 비번 admin 접속 함수
				break;
			case 2:
				oc.loginOwner(this.loginOwner()); // 점주 로그인 함수
				break;
			case 3:
				cc.loginCustomer(this.loginCustomer()); // 고객 로그인함수
				break;
			case 4:
				Signup();
				break;
			case 0:
				System.out.println("정말 종료 하시겠습니까? (y/n)");
				if (sc.next().charAt(0) == 'y')
					return;

			default:
				System.out.println("잘못누르셨습니다.");

			}
		}

	}

	// 관리자로 로그인 함수
	// 아이디 admin 비밀번호 admin

	public void loginAdmin() {
		System.out.println("==========관리자 로그인==========");
		System.out.print("■아이디 입력 > ");
		String admin = sc.next();
		if (admin.equals("admin")) {
			System.out.print("■비밀번호 입력  > ");
			String pass = sc.next();
			if (pass.equals("admin")) {
				System.out.println("로그인 성공");
				AdminMenu();
			}

		} else {
			System.out.println("로그인 실패");
		}

	}

	// 점주(가게)로 로그인하기위한 함수

	public Owner loginOwner() {
		Owner owner = new Owner();
		System.out.println("==========점주 로그인==========");
		System.out.print("■점주 아이디 입력 > ");
		a = sc.next();
		owner.setStrId(a);
		System.out.print("■점주 비밀번호 입력  > ");
		owner.setStrPwd(sc.next());
		return owner;

	}

	// 고객로 로그인하기 위한함수
	// 채은===================================================
	public Customer loginCustomer() {
		Customer customer = new Customer();
		System.out.println("==========고객 로그인==========");
		System.out.print("■고객 아이디 입력 : ");
		userID = sc.next();
		customer.setCusId(userID);
		System.out.print("■고객 비밀번호 입력 : ");
		customer.setCusPwd(sc.next());

		return customer;
	}

	// 회원 가입 메뉴 (1. 점주 회원가입 2. 고객 회원가입)
	public void Signup() {
		do {
		System.out.println("=========회원가입 메뉴=========");
		System.out.println("1.점주 회원가입");
		System.out.println("2.고객 회원가입");
		System.out.println("0.뒤로가기");
		
		System.out.print("■선택 > ");

		switch (sc.nextInt()) {
		case 1:
			oc.insertOwner(this.SignUpOwner());
			break;
		case 2:
			cc.insertCustomer(this.SignUpCustomer());
			break;
		case 0:	Menu();
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
		}while(true);
	}

	// 점주 회원가입에서 OwnerController에서 처리하는
	// insertOwner함수의 매개변수 입력받는 함수

	public Owner SignUpOwner() {

		Owner owner = new Owner();
		System.out.println("=========점주 회원가입=========");
		System.out.print("■점주 이름 > ");
		owner.setCeoName(sc.next());

		System.out.print("■점주 아이디 > ");
		owner.setStrId(sc.next());

		System.out.print("■비밀번호 > ");
		owner.setStrPwd(sc.next());

		System.out.print("■가게 이름> ");
		owner.setStrName(sc.next());

		System.out.print("■가게 주소 > ");
		sc.nextLine();
		owner.setStrAddr(sc.nextLine());

		System.out.print("■가게 전화번호 > ");
		owner.setStrPhone(sc.next());

		System.out.print("■카테고리 > ");
		owner.setCategory(sc.next());

		return owner;
	}

	// 고객 회원가입에서 CustomerController에서 처리하는
	// insertCustomer함수의 매개변수 입력받는 함수

	private Customer SignUpCustomer() {

		Customer customer = new Customer();
		System.out.println("=========고객 회원가입=========");
		System.out.print("■회원 이름 > ");
		customer.setCusName(sc.next());

		System.out.print("■아이디 > ");
		customer.setCusId(sc.next());

		System.out.print("■비밀번호 > ");
		customer.setCusPwd(sc.next());

		System.out.print("■주소 > ");
		sc.nextLine();
		customer.setCusAddr(sc.nextLine());

		System.out.print("■전화번호 > ");
		customer.setCusPhone(sc.next());

		return customer;
	}

	// 관리자 메뉴 (1. 회원관리 2. 가게관리)
	private void AdminMenu() {

		do {
			System.out.println("=========관리자 메뉴=========");
			System.out.println("1.회원 관리 메뉴");
			System.out.println("2.가게 관리 메뉴");
			System.out.println("0.뒤로가기");
			System.out.print("■선택 > ");

			switch (sc.nextInt()) {
			case 1:
				CustomerManageMenu();
				//AdminMenu();
				break;
			case 2:
				StoreManageMenu();
				//AdminMenu();
				break;
			//case 3:
				//AdminMenu();
				//break;
			case 0:
				Menu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				AdminMenu();
				break;
			}
		} while (true);
	}

	// 관리자 메뉴 안에있는 회원관리 메뉴

	private void CustomerManageMenu() {

		do {
			System.out.println("=========회원 관리 메뉴=========");
			System.out.println("1. 회원 전체 출력");
			System.out.println("2. 회원 검색 (회원 번호)");
			System.out.println("3. 전체 주문 내역");
			System.out.println("4. 신고 관리");
			System.out.println("0. 뒤로가기");
			System.out.print("■선택 > ");

			switch (sc.nextInt()) {
			case 1:
				ac.selectAllCus();
				break;
			case 2:
				ac.searchByNo(this.inputByNo());
				break;
			case 3:
				ac.selectAllOrder();
				break;
			case 4:
				complainManage();
				break;
			case 0:
				AdminMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		} while (true);

	}

	// 관리자 메뉴 안에있는 가게 관리 메뉴

	public void StoreManageMenu() {

		do {
			System.out.println("────가게 관리 메뉴────");
			System.out.println("1. 가게 전체 출력");
			System.out.println("2. 가게 검색");
			System.out.println("3. 가게 삭제");
			System.out.println("4. 가게 메뉴 확인");
			System.out.println("5. 가게 매출 순위");
			System.out.println("6. 블랙 리스트 관리");
			System.out.println("0. 뒤로가기");
			System.out.print("■선택 > ");

			switch (sc.nextInt()) {
			case 1:
				ac.selectAllStore();
				break;
			case 2:
				ac.searchStore(this.searchStoreNo());
				break;
			case 3:
				ac.deleteStore(this.deleteStoreNo());
				break;
			case 4:
				ac.selectAllMenu();
				break;
			case 5:
				ac.rankCheck();
				break;
			case 6:
				blackListManage();
				break;
			case 0:
				AdminMenu();
				break;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		} while (true);

	}

	public int searchStoreNo() {
		System.out.println("=========가게 검색=========");
		System.out.print("■검색할 가게 번호 입력 > ");
		return sc.nextInt();
	}

	private int deleteStoreNo() {
		System.out.println("=========가게 삭제=========");
		ac.selectAllStore();
		System.out.print("■삭제할 가게 번호 입력 > ");
		return sc.nextInt();
	}





	public void OwnerMenu() {

		do {
		System.out.println("=========점주 모드=========");
		System.out.println("1.전체 메뉴 출력");
		System.out.println("2.메뉴 추가");
		System.out.println("3.메뉴 삭제");
		System.out.println("4.메뉴 수정");
		System.out.println("5.주문내역 리스트"); // 주문내역
		System.out.println("6.주문 내역 처리");
		System.out.println("7.날짜별 총수입");
		System.out.println("0. 뒤로가기"); // 배달완료내역
		System.out.print("■선택 > ");

		switch (sc.nextInt()) {
		case 1:
			oc.selectAllStoreMenu(a);
			break;
		case 2:
			oc.insertMenu(this.intputMenu());
			break;
		case 3:
			oc.deleteMenu(this.deleteMenuNo());
			break;
		case 4:
			oc.updateMenu(this.modifyMenu());
			break;
		case 5:
			oc.SelectOrderAllList(a);
			break;
		case 6:
			oc.DeliveryUpdate(this.inputDelivery());
			break;
		case 7:
			oc.SelectDateMoney(a);
			break;
		case 0:
			Menu();
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	} while (true);

}
	//점주모드 메소드 
	
	private int deleteMenuNo() {
		oc.selectAllStoreMenu(a);
		System.out.print("■삭제할 메뉴번호 입력 > ");
		return sc.nextInt();
	}

	private delivery.model.vo.Menu modifyMenu() {
		delivery.model.vo.Menu menu = new delivery.model.vo.Menu();
		oc.selectAllStoreMenu(a);
		System.out.println("========= 메뉴 수정=========");
		System.out.print("■수정할 메뉴번호 입력 > ");
		menu.setMenuNo(sc.nextInt());

		System.out.print("■메뉴 이름 >");
		menu.setMenuName(sc.next());

		System.out.print("■가격 > ");
		menu.setPrice(sc.nextInt());

		return menu;
	}

	private delivery.model.vo.Menu intputMenu() {
		delivery.model.vo.Menu menu = new delivery.model.vo.Menu();
		System.out.println("=========메뉴 추가=========");
		System.out.print("■점주 아이디 > ");
		menu.setStrId(sc.next());

		System.out.print("■메뉴 이름 > ");
		menu.setMenuName(sc.next());

		System.out.print("■메뉴 가격 > ");
		menu.setPrice(sc.nextInt());

		System.out.print("■카테고리 > ");
		menu.setCategory(sc.next());

		return menu;
	}
	//

	public void CustomerMenu() {

		do {
			System.out.println("=========고객 모드=========");
			System.out.println("1. 주문하기");
			System.out.println("2. 배달완료 내역 확인");
			System.out.println("3. 신고하기");
			System.out.println("0. 뒤로가기");
			System.out.print("■메뉴 선택 > ");

			switch (sc.nextInt()) {
			case 1:
				owner_Menu();
				break;
			case 2:	
				orderC.orderListPrintAll(userID);
				break;
			case 3:
				cc.keywordList();
				cc.complainInsert(checkId(), checkKeyword());
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		} while (true);

	}

	// -----------------------------------------------------
	// ArrayList 출력 및 전달 메세지 출력 함수

	public void displayOwnerList(ArrayList<Owner> ownerList) {
		System.out.println("=========가게 정보 조회결과=========");
		System.out.println("점주아이디\t카테고리\t패스워드\t주소\t전화번호\t상호명\t점주이름\t점주번호");
		for (Owner owner : ownerList) {
			System.out.println(owner.toString());
		}
	}

	public void displayMenuList(ArrayList<delivery.model.vo.Menu> menuList) {
		System.out.println("=========메뉴 정보 조회결과=========");
		System.out.println("메뉴번호\t점주아이디\t메뉴이름\t가격\t카테고리");
		for (delivery.model.vo.Menu menu : menuList) {
			System.out.println(menu.toString());
		}
	}

	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패 > " + message);
	}

	public void displayOwner(Owner owner) {
		System.out.println(owner.toString());

	}

	public void displaySuccess(String message) {
		System.out.println("서비스 성공 > " + message);

	}

	public void OwnerLoginSuccess(String message) {
		System.out.println("점주 메뉴 로그인 > " + message);

	}

	public void CustomerLoginSuccess(String message) {
		System.out.println("고객 메뉴 로그인 > " + message);

	}

	public void OwnerLoginFail(String message) {
		System.out.println("점주 메뉴 로그인 실패 > " + message);

	}

	public void CustomerLoginFail(String message) {
		System.out.println("고객 메뉴 로그인 실패 > " + message);

	}

	public void displayAllCus(ArrayList<Customer> list) {

		System.out.println("=========회원 정보 조회결과=========");
		System.out.println("회원 아이디\t이름\t패스워드\t주소\t전화번호\t회원번호");
		for (Customer customer : list) {
			System.out.println(customer.toString());
		}

	}

	public void displayAllBlack(ArrayList<BlackList> bList) {
		System.out.println("=========전체 블랙리스트 조회결과=========");
		System.out.println("점주아이디\t컴플레인키워드\t컴플레인번호");
		for (BlackList black : bList) {
			System.out.println(black.toString());
		}
	}

	public void diplayOneBlack(BlackList blackList) {
		System.out.println("=========블랙리스트 조회결과=========");
		System.out.println("점주아이디\t컴플레인키워드\t컴플레인번호");
		System.out.println(blackList.toString());
	}

	public void displayOneCus(Customer customer) {
		System.out.println("=========회원 정보 조회결과=========");
		System.out.println("회원아이디\t회원이름\t패스워드\t주소\t전화번호\t회원번호");
		System.out.println(customer.toString());
	}

	public int inputByNo() {
		System.out.print("■조회할 회원 번호를 입력 > ");
		return sc.nextInt();
	}

	public String inputId() {
		System.out.print("■조회할 아이디 입력 > ");
		return sc.next();
	}

	public void displayAllOrder(ArrayList<OrderMenu> list) {
		System.out.println("=========전체 주문 내역 조회결과=========");
		System.out.println("주문번호 \t 주문날짜 \t 회원아이디 \t 가격 \t 수량\t 배달상태");
		for (OrderMenu order : list) {
			System.out.println(order.toString2());
		}
	}

	public void diplayOrderList(ArrayList<OrderMenu> list) {
		System.out.println("=========전체 주문내역 조회 결과=========");
		System.out.println("주문번호\t주문날짜\t회원아이디\t 가격 \t 수량 \t 배달상태");
		for (OrderMenu orderMenu : list) {
			System.out.println(orderMenu.toString2());
		}
	}

	public OrderMenu inputDelivery() {
		oc.SelectOrderAllList(a);
		OrderMenu oMenu = new OrderMenu();
		System.out.print("■배송 상태를 변경할 주문 내역 번호 선택 > ");
		oMenu.setOrderNo(sc.nextInt());
		System.out.print("■주문 배송 상태 선택 (완료 : Y / 미완료 : N) > ");
		oMenu.setDelivery(sc.next());
		oMenu.setStrId(a);
		return oMenu;
	}

	public void diplayMoneyList(ArrayList<OrderMenu> list) {
		System.out.println("=========날짜별 총 수입 결과=========");
		System.out.println(" 주문날짜\t\t매출 ");
		for (OrderMenu orderMenu : list) {
			System.out.println(orderMenu.printString());
		}

	}


	// 빛나=======================================================================================

	public void displayComplainList(ArrayList<BlackList> blList) {
		System.out.println();
		System.out.println("=========미처리 신고 리스트=========");
		System.out.println("점주ID\t신고키워드\t신고번호\t처리여부");
		for (BlackList Bl : blList) {
			System.out.println(Bl.toString());

		}
	}

	public int checkNo() {
		System.out.print("■처리할 신고번호를 입력하세요 > ");
		return sc.nextInt();

	}

	public String decision() {
		System.out.print("■블랙리스트로 추가 하시겠습니까? (y/n) > ");
		char answer;
		String decision = null;
		answer = sc.next().charAt(0);
		if (answer == 'y') {
			decision = "APPLY";
		} else {
			decision = "REJECT";

		}
		return decision;

	}

	public void displayBlackList(ArrayList<BlackList> blList) {
		System.out.println();
		System.out.println("=========블랙 리스트=========");
		System.out.println("점주ID\t신고키워드\t신고번호\t처리여부");
		for (BlackList Bl : blList) {
			System.out.println(Bl.toString());

		}
	}

	// 신고 관리(임빛나)
	public void complainManage() {
		int choice = 99;

		System.out.println("=========신고관리=========");
		System.out.println("1. 미처리 신고 현황 조회");// 검토중만 가져오기
		System.out.println("2. 신고건 처리하기");// 검토리스트에서 원하는 번호 입력해서 처리
		System.out.println("3. 블랙리스트 내역 조회");// 승인만 가져오기
		System.out.print("■번호 선택 : ");
		choice = sc.nextInt();

		switch (choice) {
		case 1:
			ac.complainSelectAll();
			break;
		case 2:
			ac.complainSelectAll();
			ac.complainUpdate(this.checkNo(), this.decision());
			break;
		case 3:
			ac.blackListSelectApplyAll();
			break;

		}

	}

	// 블랙리스트 관리(임빛나)
	public void blackListManage() {
		int choice = 99;

		System.out.println("=========블랙리스트관리=========");
		System.out.println("1. 블랙리스트 내역 조회");// 블랙리스트 ADMIN현황 상관없이 가져오기
		System.out.println("2. 블랙리스트에서 삭제");// 블랙리스트에서 제거
		System.out.print("■번호 선택 : ");
		choice = sc.nextInt();

		switch (choice) {

		case 1:
			ac.blackListSelectAll();
			break;
		case 2:
			ac.deleteBlackList(checkNo());
			break;

		}

	}

	// 매출순위
	public void displayRankList(ArrayList<OrderMenu> oMList) {
		System.out.println();
		System.out.println("=========매출 순위=========");
		System.out.println("순위/점주ID/매출");
		for (OrderMenu oM : oMList) {
			System.out.println(oM.toToString());

		}
	}



	//
	public void owner_Menu() {

		do {
			System.out.println("=========주문하기=========");
			System.out.println("1. 장바구니 내용확인");
			System.out.println("2. 장바구니에 음식추가");
			System.out.println("3. 장바구니에 음식삭제");
			System.out.println("4. 최종 주문하기");
			System.out.println("0. 고객모드로 돌아가기");
			System.out.print("■선택: ");

			switch (sc.nextInt()) {
			case 1:orderC.orderPrintAll(userID);

				break;
			case 2:
				orderC.insertOrder(this.inputOrder());
				break;
			case 3:
				orderC.delectOrder(this.inputOrderNO());
				break;
			case 4:
				lastOrder();
				break;
			case 0:
				System.out.println();
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		} while (true);

	}

	public String checkId() {
		//내 주문내역 불러와서 점주 이름 가져오고, 거기서 조인으로 점주아이디 가져와야한다.
		sc.nextLine();//개행
		System.out.print("■신고할 점주 아이디 > ");
		String id = sc.nextLine();
		return id;

	}

	public void keywordList(ArrayList<Complain> list) {
		System.out.println("=========신고 키워드=========");
		System.out.println("키워드\t컴플레인");
		for(Complain complain : list) {
			System.out.println(complain.toString());
		}
		
		
	}
	
	
	public String checkKeyword() {
	//원래는 키워드 불러와야한다
		sc.nextLine();//개행
		/*System.out.println("<신고 키워드>");
		System.out.println("001: 비위생");
		System.out.println("002: 불친절");
		System.out.println("003: 배달지연");*/		
		System.out.print("■신고할 키워드 > ");
		String keyword = sc.nextLine();
		return keyword;
	}
	


	
	//==================================================================

	public void displayOrderList(ArrayList<OrderMenu> list) {
		System.out.println("=========주문내역=========");
		System.out.println("주문번호\t가게이름\t메뉴이름\t총금액\t수량\t날짜");
		for (OrderMenu order : list) {
			System.out.println(order.toString());
		}
	}

	public OrderMenu inputOrder() {
		OrderMenu order = new OrderMenu();
		Customer customer = new Customer();

		System.out.println("■고객아이디:" + userID);

		ac.selectAllStore();// 가게전체출력
		System.out.print("■가게아이디 > ");
		String strId=sc.next();
		order.setStrId(strId);
		// 가게메뉴출력
		oc.selectAllStoreMenu(strId);
		
		System.out.print("■메뉴번호 >");
		order.setMenuNo(sc.nextInt());
		System.out.print("■수량 > ");
		order.setOrderAmount(sc.nextInt());

		order.setCusId(userID); // 고객아이디

		return order;
	}

	public int inputOrderNO() {
		orderC.orderPrintAll(userID);
		System.out.print("■삭제할 NO를 입력 > ");
		return sc.nextInt();
	}

	public void lastOrder() {
		orderC.orderPrintAll(userID);
		System.out.print("■정말로 주문하시겠습니까?(y/n) > ");
		if (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y') {
			orderC.lastOrder();
			try {
				Thread.sleep(1000);
				System.out.println("배달완료!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("최종주문을 하지않습니다.");
		}
	}

}