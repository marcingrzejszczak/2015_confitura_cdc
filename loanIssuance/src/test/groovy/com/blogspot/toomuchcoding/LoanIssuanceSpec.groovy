package com.blogspot.toomuchcoding

import com.blogspot.toomuchcoding.frauddetection.Application
import com.blogspot.toomuchcoding.frauddetection.LoanIssuanceService
import com.blogspot.toomuchcoding.frauddetection.model.Client
import com.blogspot.toomuchcoding.frauddetection.model.LoanApplication
import com.blogspot.toomuchcoding.frauddetection.model.LoanApplicationResult
import com.blogspot.toomuchcoding.frauddetection.model.LoanApplicationStatus
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class LoanIssuanceSpec extends Specification {

	@ClassRule
	@Shared
	WireMockClassRule wireMockRule = new WireMockClassRule()

	@Autowired
	LoanIssuanceService sut

	def 'should successfully apply for loan'() {
		given:
			LoanApplication application =
					new LoanApplication(client: new Client(pesel: '1234567890'), amount: 123.123)
		when:
			LoanApplicationResult loanApplication = sut.loanApplication(application)
		then:
			loanApplication.loanApplicationStatus == LoanApplicationStatus.LOAN_APPLIED
			loanApplication.rejectionReason == null
	}

}
