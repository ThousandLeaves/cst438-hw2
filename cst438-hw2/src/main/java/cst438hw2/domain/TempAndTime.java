package cst438hw2.domain;

// Helper class that returns multiple vals from getTimeAndTemp method
// Not an entity, just holds values

public class TempAndTime {
	public double temp;
	public String time;
	public int timezone;
	
	public TempAndTime() {
		
	}
	
	public TempAndTime(double temp, String time, int timezone) {
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.temp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + timezone;
		return result;
	}
	
	// Helpful functions
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TempAndTime other = (TempAndTime) obj;
		if (Double.doubleToLongBits(temp) != Double.doubleToLongBits(other.temp))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timezone != other.timezone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TempAndTime [temp=" + temp + ", time=" + time + ", timezone=" + timezone + "]";
	}	
}
