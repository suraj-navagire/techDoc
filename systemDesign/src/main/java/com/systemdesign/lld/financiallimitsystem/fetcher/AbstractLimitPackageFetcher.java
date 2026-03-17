package com.systemdesign.lld.financiallimitsystem.fetcher;

import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;

public abstract class AbstractLimitPackageFetcher implements Comparable<AbstractLimitPackageFetcher>{

		protected EntityType entityType;

		abstract public EntityLimitPackageMap fetchLimitPackage(LimitCheckRequest request);
}
