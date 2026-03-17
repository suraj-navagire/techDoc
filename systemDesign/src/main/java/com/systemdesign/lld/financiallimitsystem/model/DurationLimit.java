package com.systemdesign.lld.financiallimitsystem.model;

import java.util.UUID;

public class DurationLimit implements ILimit {

		private String id;
		private String name;
		private String description;

		private int endTime;

		private CurrencyAmount amount;

		public DurationLimit(String name, String description, int startTime, int endTime,
				CurrencyAmount amount) {
				this.id = UUID.randomUUID().toString();
				this.name = name;
				this.description = description;
				this.endTime = endTime;
				this.amount = amount;
		}

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getDescription() {
				return description;
		}

		public void setDescription(String description) {
				this.description = description;
		}

		public int getEndTime() {
				return endTime;
		}

		public void setEndTime(int endTime) {
				this.endTime = endTime;
		}

		public CurrencyAmount getAmount() {
				return amount;
		}

		public void setAmount(CurrencyAmount amount) {
				this.amount = amount;
		}
}