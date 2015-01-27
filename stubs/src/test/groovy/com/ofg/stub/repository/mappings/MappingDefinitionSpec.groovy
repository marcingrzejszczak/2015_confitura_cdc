package com.ofg.stub.repository.mappings

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import spock.lang.Specification
import spock.lang.Unroll

class MappingDefinitionSpec extends Specification {

	static final File MAPPINGS_REPOSITORY = new File('mappings')

	@Unroll
	def '#mapping should contain valid contract definition'() {
		when:
			buildStubMapping(mapping.text)
		then:
			noExceptionThrown()
		where:
			mapping << loadContractDefinitions()
	}

	def 'should detect invalid contract definition'() {
		given:
			String invalidContract = """
                                    {
                                        "request": {
                                            "method": "GET",
                                            "url": "/ping"
                                        },
                                        "response": {
                                            "status": 200,
                                            "body": "pong",
                                            "unknown_param": "not_part_of_valid_contract"
                                        }
                                    }
                                    """
		when:
			buildStubMapping(invalidContract)
		then:
			def exception = thrown(RuntimeException)
			exception.message.contains 'Unrecognized field'
	}

	void buildStubMapping(String mappingDefinition) {
		StubMapping.buildFrom(mappingDefinition)
	}

	List<File> loadContractDefinitions() {
		List<File> contracts = []
		MAPPINGS_REPOSITORY.eachFileRecurse { File file ->
			if (isMappingDescriptor(file)) {
				contracts << file
			}
		}
		return contracts
	}

	boolean isMappingDescriptor(File file) {
		return file.isFile() && file.name.endsWith('.json')
	}
}
