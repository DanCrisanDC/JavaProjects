package streamPackage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MonitoredData {
	
	LocalDateTime startT;
	LocalDateTime endT;
	String activ = new String();
	
	public MonitoredData(LocalDateTime startT, LocalDateTime endT, String activ) {
		this.startT = startT;
		this.endT = endT;
		this.activ = activ;
	}
	
	public long getTimeHours() {
		return ChronoUnit.HOURS.between(getStartT(), getEndT());
	}
	
	public long getTimeMins() {
		return ChronoUnit.MINUTES.between(getStartT(), getEndT());
	}
	
	public int getDay() {
		return startT.getDayOfMonth();
	}
	
	public LocalDateTime getStartT() {
		return startT;
	}
	public void setStartT(LocalDateTime startT) {
		this.startT = startT;
	}
	public LocalDateTime getEndT() {
		return endT;
	}
	public void setEndT(LocalDateTime endT) {
		this.endT = endT;
	}
	public String getActiv() {
		return activ;
	}
	public void setActiv(String activ) {
		this.activ = activ;
	}
}
