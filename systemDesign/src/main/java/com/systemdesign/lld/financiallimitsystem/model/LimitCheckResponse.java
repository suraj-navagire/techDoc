package com.systemdesign.lld.financiallimitsystem.model;

public class LimitCheckResponse {

		private boolean isAllowed;

		public LimitCheckResponse(boolean isAllowed) {
				this.isAllowed = isAllowed;
		}

		public boolean isAllowed() {
				return isAllowed;
		}

		public void setAllowed(boolean allowed) {
				isAllowed = allowed;
		}
}