package com.systemdesign.lld.financiallimitsystem.model;

import java.util.UUID;

public class TransactionLimit implements ILimit {

		private String id;
		private String name;
		private String description;

		private CurrencyAmount minAmount;
		private CurrencyAmount maxAmount;

		public TransactionLimit(String name, String description, CurrencyAmount minAmount,
				CurrencyAmount maxAmount) {
				this.id = UUID.randomUUID().toString();
				this.name = name;
				this.description = description;
				this.minAmount = minAmount;
				this.maxAmount = maxAmount;
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

		public CurrencyAmount getMinAmount() {
				return minAmount;
		}

		public void setMinAmount(CurrencyAmount minAmount) {
				this.minAmount = minAmount;
		}

		public CurrencyAmount getMaxAmount() {
				return maxAmount;
		}

		public void setMaxAmount(CurrencyAmount maxAmount) {
				this.maxAmount = maxAmount;
		}
}