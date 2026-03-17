package com.systemdesign.lld.financiallimitsystem;

import com.systemdesign.lld.financiallimitsystem.fetcher.AbstractLimitPackageFetcher;
import com.systemdesign.lld.financiallimitsystem.fetcher.SystemLevelLimitPackageFetcher;
import com.systemdesign.lld.financiallimitsystem.fetcher.UserGroupLevelLimitPackageFetcher;
import com.systemdesign.lld.financiallimitsystem.fetcher.UserLevelLimitPackageFetcher;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckResponse;
import com.systemdesign.lld.financiallimitsystem.validator.DurationalLimitValidator;
import com.systemdesign.lld.financiallimitsystem.validator.ILimitValidator;
import com.systemdesign.lld.financiallimitsystem.validator.PeriodicLimitValidator;
import com.systemdesign.lld.financiallimitsystem.validator.TransactionalLimitValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LimitEvaluationService implements ILimitEvaluationService {

		private final TreeSet<AbstractLimitPackageFetcher> limitPackageFetchers;

		private final List<ILimitValidator> limitValidators;

		public LimitEvaluationService(){
				limitPackageFetchers = new TreeSet<>();
				limitPackageFetchers.add(new SystemLevelLimitPackageFetcher());
				limitPackageFetchers.add(new UserLevelLimitPackageFetcher());
				limitPackageFetchers.add(new UserGroupLevelLimitPackageFetcher());


				limitValidators = new ArrayList<>();
				limitValidators.add(new TransactionalLimitValidator());
				limitValidators.add(new PeriodicLimitValidator());
				limitValidators.add(new DurationalLimitValidator());
		}

		@Override public LimitCheckResponse validate(LimitCheckRequest request) {

				//Step 1 : Check at which level limit package is set
				EntityLimitPackageMap entityLimitPackageMap = null;
				for(AbstractLimitPackageFetcher fetcher : limitPackageFetchers){
						entityLimitPackageMap = fetcher.fetchLimitPackage(request);

						if(entityLimitPackageMap != null){
								// Once we know at which level limit is assigned we can use it for validation.
								break;
						}
				}


				// Step 2 : Call limit validator 1 by 1 and check all types of limits

				for (ILimitValidator validator : limitValidators){
						boolean isAllowed = validator.validate(request, entityLimitPackageMap);

						if(!isAllowed){
								return new LimitCheckResponse(false);
						}
				}


				return ;
		}
}
