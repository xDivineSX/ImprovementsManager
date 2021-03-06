package de.divinesx.improvementsmanager.core;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

public class ImprovementList extends ObservableArrayList<Improvement> {

	public enum FilterType { PRIORITY, YEAR, MONTH, DAY }
		
	public ImprovementList() {}
	
	public ImprovementList(Collection<? extends Improvement> collection) { super(collection); }
	
	public ImprovementList getBy(FilterType type, String... identifier) {
		if (type == null) return this;
		
		switch (type) {
			case PRIORITY:
				return new ImprovementList(this.stream().filter(i -> i.getPriority().toString().toLowerCase().equalsIgnoreCase(identifier[0]))
														.collect(Collectors.toList()));
			case YEAR:
				return new ImprovementList(this.stream().filter(i -> String.valueOf(i.getTimestamp().get(Calendar.YEAR)).equalsIgnoreCase(identifier[0]))
														.collect(Collectors.toList()));
			case MONTH:
				return new ImprovementList(this.stream().filter(i -> String.valueOf(i.getTimestamp().get(Calendar.YEAR)).equalsIgnoreCase(identifier[0])
						  					  					  && String.valueOf(i.getTimestamp().get(Calendar.MONTH) + 1).equalsIgnoreCase(identifier[1]))
														.collect(Collectors.toList()));
			case DAY:
				return new ImprovementList(this.stream().filter(i -> String.valueOf(i.getTimestamp().get(Calendar.YEAR)).equalsIgnoreCase(identifier[0])
											  					  && String.valueOf(i.getTimestamp().get(Calendar.MONTH ) + 1).equalsIgnoreCase(identifier[1])
											  					  && String.valueOf(i.getTimestamp().get(Calendar.DAY_OF_MONTH)).equalsIgnoreCase(identifier[2]))
														.collect(Collectors.toList()));
			default:
				return this;
		}
	}
	
	public ImprovementList getSortedBy(FilterType type) {
		if (type == null) return this;
		
		switch (type) {
			case PRIORITY:
				return new ImprovementList(this.stream().sorted((c1, c2) -> c2.getPriority().getId() - c1.getPriority().getId()).collect(Collectors.toList()));
			case DAY:
				return new ImprovementList(this.stream().sorted((c1, c2) -> (int)(c2.getTimestamp().getTime().getTime() - c1.getTimestamp().getTime().getTime())).collect(Collectors.toList()));
			default:
				return this;
		}
	}

}
