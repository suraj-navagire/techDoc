package com.systemdesign.lld.financiallimitsystem.model;

public class LimitCheckRequest {

		private String userId;

		private Transaction transaction;

		private CurrencyAmount amount;

		private String payeeId;

		public String getUserId() {
				return userId;
		}

		public void setUserId(String userId) {
				this.userId = userId;
		}

		public Transaction getTransaction() {
				return transaction;
		}

		public void setTransaction(Transaction transaction) {
				this.transaction = transaction;
		}

		public CurrencyAmount getAmount() {
				return amount;
		}

		public void setAmount(CurrencyAmount amount) {
				this.amount = amount;
		}

		public String getPayeeId() {
				return payeeId;
		}

		public void setPayeeId(String payeeId) {
				this.payeeId = payeeId;
		}
}
