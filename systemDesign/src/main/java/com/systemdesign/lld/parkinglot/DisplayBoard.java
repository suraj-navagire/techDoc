package com.systemdesign.lld.parkinglot;

import java.util.Observable;
import java.util.Observer;

public class DisplayBoard implements Observer {
		private Integer count;
		private String id;

		public DisplayBoard(int count, String displayId) {
				this.count = count;
				this.id = displayId;
				System.out.println("Display : "+id+". Available slots are : "+count);
		}

		@Override public void update(Observable o, Object count) {
				this.count = (Integer) count;
				System.out.println("Display : "+id+". Available slots are : "+count);
		}

		public Integer getCount() {
				return count;
		}

		public String getId() {
				return id;
		}
}
