package com.systemdesign.lld.financiallimitsystem.model;

import java.time.LocalDateTime;

public class Payee {
		private String payeeId;

		private String payeeName;

		private LocalDateTime creationDate;

		private String accountNumber;

		private String ifscCode;

		public Payee(String payeeId, String payeeName, LocalDateTime creationDate, String accountNumber, String ifscCode) {
				this.payeeId = payeeId;
				this.payeeName = payeeName;
				this.creationDate = creationDate;
				this.accountNumber = accountNumber;
				this.ifscCode = ifscCode;
		}

		public String getPayeeId() {
				return payeeId;
		}

		public String getPayeeName() {
				return payeeName;
		}

		public LocalDateTime getCreationDate() {
				return creationDate;
		}

		public String getAccountNumber() {
				return accountNumber;
		}

		public String getIfscCode() {
				return ifscCode;
		}
}
