package com.systemdesign.lld.financiallimitsystem.model;

public class CurrencyAmount {

		private int amount;
		private Currency currency;

		public CurrencyAmount(int amount, Currency currency) {
				this.amount = amount;
				this.currency = currency;
		}

		public int getAmount() {
				return amount;
		}

		public Currency getCurrency() {
				return currency;
		}

		public void setAmount(int amount) {
				this.amount = amount;
		}
}