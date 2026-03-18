package com.systemdesign.lld.financiallimitsystem.validator;

import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitPackage;
import com.systemdesign.lld.financiallimitsystem.model.TransactionLimit;

import java.util.List;

//Please note that transactional limit is mandatory
public class TransactionalLimitValidator implements ILimitValidator{
		@Override public boolean validate(LimitCheckRequest request, EntityLimitPackageMap entityLimitPackageMap) {

				LimitPackage limitPackage = entityLimitPackageMap.getLimitPackage();
				List<ILimit> limits = limitPackage.getLimitMap().get(request.getTransaction().getId());

				TransactionLimit transactionLimit = null;
				for (ILimit limit : limits){
						if (limit instanceof TransactionLimit){
								transactionLimit = (TransactionLimit) limit;
								break;
						}
				}

				if (transactionLimit == null){
						return false;
				}

				//Validate limit. Ignoring currency conversion
				int inputAmount = request.getAmount().getAmount();

				int minAmount = transactionLimit.getMinAmount().getAmount();

				int maxamount = transactionLimit.getMaxAmount().getAmount();

				if(minAmount <= inputAmount && inputAmount <= maxamount){
						return true;
				}

				return false;
		}

		@Override public void utilizeLimit(LimitCheckRequest request) {
				System.out.println("Not applicable");
		}
}
