package com.systemdesign.lld.financiallimitsystem.model;

public enum EntityType {

		SYSTEM(3),
		USER_GROUP(2),
		USER(1);

		private int value;

		EntityType(int value){
				this.value = value;
		}

		public int getPriority(){
				return value;
		}
}
