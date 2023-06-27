import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainOrder {
	private int orderNumber;
	private int total_Price;
	private String date;
	private String time;
	private String state;

	public MainOrder(int orderNumber, int total_Price) {
		super();
		this.orderNumber = orderNumber;
		this.total_Price = total_Price;
		this.date = LocalDate.now().toString();
		String pattern = "HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		this.time = LocalTime.now().format(formatter).toString();
		this.state = "미확인";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + total_Price;
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
		MainOrder other = (MainOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
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
		return "MainOrder [total_Price=" + total_Price + ", date=" + date + ", time=" + time + ", state=" + state + "]";
	}

}
