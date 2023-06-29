package function;

public class MenuItem {
	private int detailOrderNumber; // detailorderNumber 넣어주기
	private String ingredient_id; // ingredient_id 넣어주고
	private int menuItemPrice;

	public MenuItem(int detailOrderNumber, String ingredient_id, int price) {
		super();
		this.detailOrderNumber = detailOrderNumber;
		this.ingredient_id = ingredient_id;
		this.menuItemPrice = price;
	}

	public int getMenuItemPrice() {
		return menuItemPrice;
	}

	public void setMenuItemPrice(int menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}

	public int getDetailOrderNumber() {
		return detailOrderNumber;
	}

	public void setDetailOrderNumber(int detailOrderNumber) {
		this.detailOrderNumber = detailOrderNumber;
	}

	public String getingredient_id() {
		return ingredient_id;
	}

	public void setingredient_id(String ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + detailOrderNumber;
		result = prime * result + ((ingredient_id == null) ? 0 : ingredient_id.hashCode());
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
		MenuItem other = (MenuItem) obj;
		if (detailOrderNumber != other.detailOrderNumber)
			return false;
		if (ingredient_id == null) {
			if (other.ingredient_id != null)
				return false;
		} else if (!ingredient_id.equals(other.ingredient_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuItem [detailOrderNumber=" + detailOrderNumber + ", ingredient_id=" + ingredient_id
				+ ", menuItemPrice=" + menuItemPrice + "]";
	}

}