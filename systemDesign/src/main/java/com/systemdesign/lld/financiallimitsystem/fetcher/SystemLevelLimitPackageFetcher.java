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
				EntityLimitPackageMap entityLimitPackageMap = DataBase.getEntityLimitPackage(EntityType.SYSTEM, null);

				//Now check if it contains limit for given transaction.

				boolean isPresent = entityLimitPackageMap.getLimitPackage().getLimitMap().containsKey(request.getTransaction().getId());

				return isPresent ? entityLimitPackageMap : null;
		}

		@Override public int compareTo(AbstractLimitPackageFetcher o) {
				return this.entityType.getPriority() - o.entityType.getPriority();
		}
}
