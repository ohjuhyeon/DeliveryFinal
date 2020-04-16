package delivery.controller;

import java.util.ArrayList;

import delivery.model.service.AdminService;
import delivery.model.service.OwnerService;
import delivery.model.vo.Menu;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;
import delivery.view.DeliveryMenu;
import myException.DeliveryException;

public class OwnerController {

	public void insertOwner(Owner signUpOwner) {

		DeliveryMenu menu = new DeliveryMenu();
		int result;
		result = new OwnerService().insertOwner(signUpOwner);
		if (result > 0) {
			menu.displaySuccess("점주 등록 성공");
		} else {
			menu.displayError("점주 등록 실패");
		}
	}

	public void loginOwner(Owner loginOwner) {
		DeliveryMenu menu = new DeliveryMenu();
		loginOwner = new OwnerService().loginOwner(loginOwner);
		if (loginOwner != null) {
			menu.OwnerLoginSuccess(loginOwner.getCeoName() + " 점주님 반갑습니다.");
			new DeliveryMenu().OwnerMenu();
		} else {
			menu.OwnerLoginFail("아이디나 비밀번호를 확인해주세요");
		}

	}
	
	public void selectAllStoreMenu(String a) {

		DeliveryMenu menu = new DeliveryMenu();
		ArrayList<Menu> menuList;
		menuList = new OwnerService().selectAllStoreMenu(a);
		if (!menuList.isEmpty()) {
			menu.displayMenuList(menuList);
		} else {
			menu.displayError("메뉴 전체 조회 실패 ");
		}

	}

	public void insertMenu(Menu insertMenu) {

		DeliveryMenu menu = new DeliveryMenu();
		int result;
		result = new OwnerService().insertMenu(insertMenu);
		if (result > 0) {
			menu.displaySuccess("메뉴 등록 성공.");
		} else {
			menu.displayError("메뉴 등록 실패");
		}

	}

	public void SelectOrderAllList(String strId) {
		DeliveryMenu menu = new DeliveryMenu();
		ArrayList<OrderMenu> list = null;
		try {
			list = new OwnerService().SelectOrderAllList(strId);
			if (!list.isEmpty()) {
				menu.diplayOrderList(list);
			} else {
				menu.displayError("전체 주문 내역 조회 실패");
			}
		} catch (DeliveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateMenu(Menu modifyMenu) {
		DeliveryMenu menu = new DeliveryMenu();
		int result;
		result = new OwnerService().updateMenu(modifyMenu);
		if (result > 0) {
			menu.displaySuccess("메뉴가 변경되었습니다.");
		} else {
			menu.displayError("메뉴 변경 실패 ");
		}

	}

	public void deleteMenu(int deleteMenuNo) {
		DeliveryMenu menu = new DeliveryMenu();
		int result;
		result = new OwnerService().deleteMenu(deleteMenuNo);
		if (result > 0) {
			menu.displaySuccess("메뉴 삭제 성공");
		} else {
			menu.displayError("메뉴 삭제 실패");
		}

	}

	public void DeliveryUpdate(OrderMenu inputDelivery) {

		DeliveryMenu menu = new DeliveryMenu();
		int result;

		try {
			result = new OwnerService().DeliveryUpdate(inputDelivery);
			if (result > 0) {
				menu.displaySuccess("주문내역 배송상태가 변경되었습니다.");
			} else {
				menu.displayError("주문내역 배송상태 변경 실패");
			}
		} catch (DeliveryException e) {
			e.printStackTrace();
		}

	}

	public void SelectDateMoney(String a) {
		DeliveryMenu menu = new DeliveryMenu();
		ArrayList<OrderMenu> list = null;
		try {
			list = new OwnerService().SelectDateMoney(a);
			if (!list.isEmpty()) {
				menu.diplayMoneyList(list);
			} else {
				menu.displayError("수입 조회 실패");
			}
		} catch (DeliveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
