package com.systemdesign.lld.financiallimitsystem.fetcher;

import com.systemdesign.lld.financiallimitsystem.DataBase;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;

public class UserLevelLimitPackageFetcher extends AbstractLimitPackageFetcher {

		public UserLevelLimitPackageFetcher(){
				this.entityType = EntityType.USER;
		}

		@Override public EntityLimitPackageMap fetchLimitPackage(LimitCheckRequest request) {
				return DataBase.getEntityLimitPackage(this.entityType, request.getUserId());
		}

		@Override public int compareTo(AbstractLimitPackageFetcher o) {
				return this.entityType.getPriority() - o.entityType.getPriority();
		}
}
