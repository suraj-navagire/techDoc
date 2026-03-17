package com.systemdesign.lld.financiallimitsystem.model;

import java.util.Date;

public class LimitUtilization {

		private PartyType partyype;

		private String partyValue;

		private String transactionId;

		private CurrencyAmount amount;

		private int count;

		private Date date;

		//It will be null for payee records
		private PeriodicType periodicType;

		public PartyType getPartyype() {
				return partyype;
		}

		public void setPartyype(PartyType partyype) {
				this.partyype = partyype;
		}

		public String getPartyValue() {
				return partyValue;
		}

		public void setPartyValue(String partyValue) {
				this.partyValue = partyValue;
		}

		public String getTransactionId() {
				return transactionId;
		}

		public void setTransactionId(String transactionId) {
				this.transactionId = transactionId;
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

		public Date getDate() {
				return date;
		}

		public void setDate(Date date) {
				this.date = date;
		}

		public PeriodicType getPeriodicType() {
				return periodicType;
		}

		public void setPeriodicType(PeriodicType periodicType) {
				this.periodicType = periodicType;
		}
}
