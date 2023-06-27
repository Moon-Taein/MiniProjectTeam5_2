package Function;


public class DetailOrder {
	private int detailOrderNumber;
	private String menu;
	private int menu_count;
	private int mainOrderNumber;

	public DetailOrder(int detailOrderNumber, String menu, int menu_count, int mainOrderNumber) {
		super();
		this.detailOrderNumber = detailOrderNumber;
		this.menu = menu;
		this.menu_count = menu_count;
		this.mainOrderNumber = mainOrderNumber;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + detailOrderNumber;
		result = prime * result + mainOrderNumber;
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + menu_count;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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
		return true;
	}

	@Override
	public String toString() {
		return "DetailOrder [detailOrderNumber=" + detailOrderNumber + ", menu=" + menu + ", menu_count=" + menu_count
				+ ", mainOrderNumber=" + mainOrderNumber + "]";
	}

}