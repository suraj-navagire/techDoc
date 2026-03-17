package com.systemdesign.lld.financiallimitsystem.fetcher;

import com.systemdesign.lld.financiallimitsystem.DataBase;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;

public class UserGroupLevelLimitPackageFetcher extends AbstractLimitPackageFetcher {
		public UserGroupLevelLimitPackageFetcher(){
				this.entityType = EntityType.USER_GROUP;
		}

		@Override public EntityLimitPackageMap fetchLimitPackage(LimitCheckRequest request) {
				String userGroup = DataBase.getUserGroupForUser(request.getUserId());

				if (userGroup == null){
						return null;
				}

				return DataBase.getEntityLimitPackage(EntityType.USER_GROUP, userGroup);
		}

		@Override public int compareTo(AbstractLimitPackageFetcher o) {
				return this.entityType.getPriority() - o.entityType.getPriority();
		}
}
