package streamPackage;

public class Main {

	public static void main(String[] args) {
		Tasks t = new Tasks();
		t.readFile();
		t.countDifDays();
		t.countDifActions();
		t.countDayActivity();
		t.mapActivityDuration();
		t.filterActivities();
	}

}
