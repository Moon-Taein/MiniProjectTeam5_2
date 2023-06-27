package Function;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainOrder {
	private int orderNumber;
	private int total_Price;
	private String date;
	private String time;
	private String state;
	private List<DetailOrder> deoList;

	public MainOrder(int orderNumber, int total_Price) {
		super();
		this.orderNumber = orderNumber;
		this.total_Price = total_Price;
		this.date = LocalDate.now().toString();
		String pattern = "HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		this.time = LocalTime.now().format(formatter).toString();
		this.state = "미확인";
		this.deoList = new ArrayList<>();
	}

	public int getTotal_Price() {
		return total_Price;
	}

	public void setTotal_Price(int total_Price) {
		this.total_Price = total_Price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<DetailOrder> getDeoList() {
		return deoList;
	}

	public void setDeoList(List<DetailOrder> deoList) {
		this.deoList = deoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((deoList == null) ? 0 : deoList.hashCode());
		result = prime * result + orderNumber;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + total_Price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MainOrder))
			return false;
		MainOrder other = (MainOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deoList == null) {
			if (other.deoList != null)
				return false;
		} else if (!deoList.equals(other.deoList))
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (total_Price != other.total_Price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MainOrder [orderNumber=" + orderNumber + ", total_Price=" + total_Price + ", date=" + date + ", time="
				+ time + ", state=" + state + ", deoList=" + deoList + "]";
	}

}
