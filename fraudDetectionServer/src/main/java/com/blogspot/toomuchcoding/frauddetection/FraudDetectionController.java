package com.blogspot.toomuchcoding.frauddetection;

import com.blogspot.toomuchcoding.frauddetection.model.FraudCheck;
import com.blogspot.toomuchcoding.frauddetection.model.FraudCheckResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.blogspot.toomuchcoding.frauddetection.model.FraudCheckStatus.REJECTED;
import static com.blogspot.toomuchcoding.frauddetection.model.FraudCheckStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class FraudDetectionController {

	private static final String FRAUD_SERVER_JSON_VERSION_1 = "application/vnd.fraud.v1+json";
	private static final String NO_REASON = null;

	@RequestMapping(
			value = "/fraudcheck",
			method = PUT,
			consumes = FRAUD_SERVER_JSON_VERSION_1,
			produces = FRAUD_SERVER_JSON_VERSION_1)
	public FraudCheckResult fraudCheck(@RequestBody FraudCheck fraudCheck) {
		return new FraudCheckResult(OK, NO_REASON);
	}

}
