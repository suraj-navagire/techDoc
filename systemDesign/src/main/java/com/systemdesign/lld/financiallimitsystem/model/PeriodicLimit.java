package com.systemdesign.lld.financiallimitsystem.model;

import java.util.UUID;

public class PeriodicLimit implements ILimit {

		private String id;
		private String name;
		private String description;

		private PeriodicType type;

		private CurrencyAmount amount;

		private int count;

		public PeriodicLimit(String name, String description, PeriodicType type, CurrencyAmount amount,
				int count) {
				this.id = UUID.randomUUID().toString();
				this.name = name;
				this.description = description;
				this.type = type;
				this.amount = amount;
				this.count = count;
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

		public PeriodicType getType() {
				return type;
		}

		public void setType(PeriodicType type) {
				this.type = type;
		}

		public CurrencyAmount getAmount() {
				return amount;
		}

		public void setAmount(CurrencyAmount amount) {
				this.amount = amount;
		}

		public int getCount() {
				return count;
		}

		public void setCount(int count) {
				this.count = count;
		}
}