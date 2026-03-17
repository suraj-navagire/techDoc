package com.systemdesign.lld.financiallimitsystem.fetcher;

import com.systemdesign.lld.financiallimitsystem.DataBase;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;

public class SystemLevelLimitPackageFetcher extends AbstractLimitPackageFetcher {

		public SystemLevelLimitPackageFetcher(){
				this.entityType = EntityType.SYSTEM;
		}

		@Override public EntityLimitPackageMap fetchLimitPackage(LimitCheckRequest request) {
				return DataBase.getEntityLimitPackage(EntityType.SYSTEM, null);
		}

		@Override public int compareTo(AbstractLimitPackageFetcher o) {
				return this.entityType.getPriority() - o.entityType.getPriority();
		}
}
