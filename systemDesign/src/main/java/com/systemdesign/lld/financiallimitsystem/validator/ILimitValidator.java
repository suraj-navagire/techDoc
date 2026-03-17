package com.systemdesign.lld.financiallimitsystem.validator;

import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;

public interface ILimitValidator {
		boolean validate(LimitCheckRequest request, EntityLimitPackageMap entityLimitPackageMap);

		boolean utilizeLimit(LimitCheckRequest request);
}
