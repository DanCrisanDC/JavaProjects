package streamPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {

	List<String> list = new ArrayList<>();
	List<MonitoredData> mdata = new ArrayList<MonitoredData>();
	
	String file = "D:\\Activities.txt";
	
	public void readFile() {

		try (Stream<String> stream = Files.lines(Paths.get(file))) {

			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < list.size(); i++) {
			String[] sp;
			sp = list.get(i).split("\\s+");
			DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String[] start = {sp[0], sp[1]};
			String startT = String.join(" ", start);
			String[] end = {sp[2], sp[3]};
			String endT = String.join(" ", end);
			String acti = sp[4];
			
			LocalDateTime tStart = LocalDateTime.parse(startT, date);
			LocalDateTime tEnd = LocalDateTime.parse(endT, date);
			
			MonitoredData md = new MonitoredData(tStart, tEnd, acti);
			mdata.add(md);
		}
	}
	
	public void countDifDays() {
		List<Integer> collect = mdata.stream().map(m->m.getDay()).distinct().collect(Collectors.toList());
		System.out.println(collect.size());
	}
	
	public void countDifActions() {
		Map<String, Long> actions = mdata.stream().collect(Collectors.groupingBy(MonitoredData::getActiv,Collectors.counting()));
		
		try {
			PrintWriter wr = new PrintWriter("actions.txt", "UTF-8");
			for(Map.Entry<String, Long> en:actions.entrySet())
				wr.println(en.getKey() + " " + en.getValue());
			wr.close();
		} catch (IOException e) {
			System.out.println("Error writing to file!\n");
		}
	}
	
	public void countDayActivity() {
		Map<Integer, Map<String, Long>> dayact = mdata.stream().collect(Collectors.groupingBy(MonitoredData::getDay,Collectors.groupingBy(MonitoredData::getActiv,Collectors.counting())));
		
		try {
			PrintWriter wr = new PrintWriter("DailyActions.txt", "UTF-8");
			for(Entry<Integer, Map<String, Long>> en:dayact.entrySet())
				wr.println(en.getKey() + " " + en.getValue());
			wr.close();
		} catch (IOException e) {
			System.out.println("Error writing to file!\n");
		}
	}
	
	public void mapActivityDuration() {
		Map<String, Long> activityTime = mdata.stream().collect(Collectors.groupingBy(MonitoredData::getActiv,Collectors.summingLong(MonitoredData::getTimeHours)));
		Map<String, Long> timeActiv = activityTime.entrySet().stream().filter(m->m.getValue() >= 10).collect(Collectors.toMap(m->m.getKey(), m->m.getValue()));
		try {
			PrintWriter wr = new PrintWriter("TotalActivity.txt", "UTF-8");
			for(Map.Entry<String, Long> en:timeActiv.entrySet())
				if(en.getValue() >= 10)
					wr.println(en.getKey() + " " + en.getValue());
			wr.close();
		} catch (IOException e) {
			System.out.println("Error writing to file!\n");
		}
	}
	
	public void filterActivities() {
		Map<String, Long> activ5 = mdata.stream().filter(m->m.getTimeMins() < 5).collect(Collectors.groupingBy(MonitoredData::getActiv, Collectors.counting()));
		Map<String, Long> actions = mdata.stream().collect(Collectors.groupingBy(MonitoredData::getActiv,Collectors.counting()));
		List<String> activs = new ArrayList<String>();
		for(Map.Entry<String, Long> en:activ5.entrySet()) {
			long frecv = actions.get(en.getKey());
			if(frecv * 0.9 < en.getValue()) {
				activs.add(en.getKey());
			}
		}
		
		try {
			PrintWriter wr = new PrintWriter("FilteredActivities.txt", "UTF-8");
			for(String s : activs)
				wr.println(s);
			wr.close();
		} catch (IOException e) {
			System.out.println("Error writing to file!\n");
		}
	}	
}
