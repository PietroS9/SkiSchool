package be.sanna.SkiSchool;

import java.util.Date;

public class Period {

	//Attributes
	private Date startDate;
	private Date endDate;
	private boolean isVacation;
	
	//Constructor
	public Period(Date start, Date end, boolean isVacation_) {
		this.startDate = start;
		this.endDate = end;
		this.isVacation = isVacation_;
	}
	
	//Getter & Setter
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public boolean getIsVacation() {
		return isVacation;
	}
	
	public void setStartDate(Date start) {
		this.startDate = start;
	}
	
	public void setEndDate(Date end) {
		this.endDate = end;
	}
	
	public void setIsVacation(boolean isVac) {
		this.isVacation = isVac;
	}
	
	//Methods
}
