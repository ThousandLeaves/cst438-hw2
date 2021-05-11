package cst438hw2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="city")
public class City {
	
	// Variables
	@Id
	private long id;
	private String name;
	private String district;
	private String countrycode;
	private long population;
	
	@Transient
	private TempAndTime tempTime;
	
	// Constructors
	public City() {
		
	}
	
	public City(long id, String name, String district, String countryCode,
			long population) {
		this.id = id;
		this.name = name;
		this.district = district;
		this.countrycode = countryCode;
		this.population = population;
		
	}
	
	// Setters, getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountryCode() {
		return countrycode;
	}

	public void setCountryCode(String countryCode) {
		this.countrycode = countryCode;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
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
		City other = (City) obj;
		if (countrycode == null) {
			if (other.countrycode != null)
				return false;
		} else if (!countrycode.equals(other.countrycode))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		if (tempTime == null) {
			if (other.tempTime != null)
				return false;
		} else if (!tempTime.equals(other.tempTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", district=" + district + ", countrycode=" + countrycode
				+ ", population=" + population + ", tempTime=" + tempTime + "]";
	}

	
}
