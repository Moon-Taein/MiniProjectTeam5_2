package function;


public class MenuItem {
	private int detailOrderNumber;
	private String inventory_id;

	public MenuItem(int detailOrderNumber, String inventory_id) {
		super();
		this.detailOrderNumber = detailOrderNumber;
		this.inventory_id = inventory_id;
	}

	public int getDetailOrderNumber() {
		return detailOrderNumber;
	}

	public void setDetailOrderNumber(int detailOrderNumber) {
		this.detailOrderNumber = detailOrderNumber;
	}

	public String getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(String inventory_id) {
		this.inventory_id = inventory_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + detailOrderNumber;
		result = prime * result + ((inventory_id == null) ? 0 : inventory_id.hashCode());
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
		if (inventory_id == null) {
			if (other.inventory_id != null)
				return false;
		} else if (!inventory_id.equals(other.inventory_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuItem [detailOrderNumber=" + detailOrderNumber + ", inventory_id=" + inventory_id + "]";
	}

}
