package cst438hw2.domain;

// Contains date to return to client: city, country, temp&time objects
// Sends data to CityController (or rest controller??)
public class CityInfo {

	public City city;
	public Country country;
	public TempAndTime tempTime;
	
	public CityInfo() {
		
	}
	
	public CityInfo(City city, Country country, TempAndTime tempTime) {
		this.city = city;
		this.country = country;
		this.tempTime = tempTime;
	}
	
	// Helpful methods
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			System.out.println("sploosh");
			return true;
		}
		if (obj == null) {
			System.out.println("bang");
			return false;
		}
		if (getClass() != obj.getClass()) {
			System.out.println("bang");		
			return false;
		}
		CityInfo other = (CityInfo) obj;
		if (city == null) {
			if (other.city != null) { 
				System.out.println("bang");
				return false;
			}
		} else if (!city.equals(other.city)) {
			System.out.println("bang");
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				System.out.println("bang");
				return false;
			}
		} else if (!country.equals(other.country)) {
			System.out.println("bang");
			return false;
		}
		if (tempTime == null) {
			if (other.tempTime != null) {
				System.out.println("bang");
				return false;
			}
		} else if (!tempTime.equals(other.tempTime)) {
			System.out.println("bang");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CityInfo [city=" + city + ", country=" + country + ", tempTime=" + tempTime + "]";
	}	
	
}
