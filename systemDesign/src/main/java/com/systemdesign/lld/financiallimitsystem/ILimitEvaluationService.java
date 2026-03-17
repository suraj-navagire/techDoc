package com.systemdesign.lld.financiallimitsystem;

import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckResponse;

public interface ILimitEvaluationService {
		LimitCheckResponse validate(LimitCheckRequest request);
}
