package com.systemdesign.lld.financiallimitsystem.model;

public class EntityLimitPackageMap {

		private EntityType entityType;

		private String entityValue;

		private LimitPackage limitPackage;

		public EntityLimitPackageMap(EntityType entityType, String entityValue, LimitPackage limitPackage) {
				this.entityType = entityType;
				this.entityValue = entityValue;
				this.limitPackage = limitPackage;
		}

		public EntityType getEntityType() {
				return entityType;
		}

		public void setEntityType(EntityType entityType) {
				this.entityType = entityType;
		}

		public String getEntityValue() {
				return entityValue;
		}

		public void setEntityValue(String entityValue) {
				this.entityValue = entityValue;
		}

		public LimitPackage getLimitPackage() {
				return limitPackage;
		}

		public void setLimitPackage(LimitPackage limitPackage) {
				this.limitPackage = limitPackage;
		}
}
