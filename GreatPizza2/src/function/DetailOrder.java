package function;

import java.util.ArrayList;
import java.util.List;

public class DetailOrder {
	private int detailOrderNumber; 
	private String menu; // menu_id
	private int menu_count; // 수량
	private int mainOrderNumber; 
	private List<MenuItem> miList; // 피자 옵션 팝업화면에서 고른 옵션들 menuitem으로 만들어서 추가해주기

	public DetailOrder(int detailOrderNumber, String menu, int menu_count, int mainOrderNumber) {
		super();
		this.detailOrderNumber = detailOrderNumber;
		this.menu = menu;
		this.menu_count = menu_count;
		this.mainOrderNumber = mainOrderNumber;
		this.miList = new ArrayList<>();
	}

	public int getDetailOrderNumber() {
		return detailOrderNumber;
	}

	public void setDetailOrderNumber(int detailOrderNumber) {
		this.detailOrderNumber = detailOrderNumber;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getMenu_count() {
		return menu_count;
	}

	public void setMenu_count(int menu_count) {
		this.menu_count = menu_count;
	}

	public int getMainOrderNumber() {
		return mainOrderNumber;
	}

	public void setMainOrderNumber(int mainOrderNumber) {
		this.mainOrderNumber = mainOrderNumber;
	}

	public List<MenuItem> getMiList() {
		return miList;
	}

	public void setMiList(List<MenuItem> miList) {
		this.miList = miList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + detailOrderNumber;
		result = prime * result + mainOrderNumber;
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + menu_count;
		result = prime * result + ((miList == null) ? 0 : miList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DetailOrder))
			return false;
		DetailOrder other = (DetailOrder) obj;
		if (detailOrderNumber != other.detailOrderNumber)
			return false;
		if (mainOrderNumber != other.mainOrderNumber)
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (menu_count != other.menu_count)
			return false;
		if (miList == null) {
			if (other.miList != null)
				return false;
		} else if (!miList.equals(other.miList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + detailOrderNumber + ", " + menu + ", " + menu_count + ", " + mainOrderNumber + ", " + miList;
	}

}