io.codearte.accurest.dsl.GroovyDsl.make {
                request {
                method """PUT"""
                url """/fraudcheck"""
                body('''
                    {
                    "clientPesel":"1234567890",
                    "loanAmount":123.123}
                '''
                )
                headers {
                    header("""Content-Type""", """application/vnd.fraud.v1+json""")

                }
                
            }
            response {
                status 200
                body( """{
    "fraudCheckStatus": "OK",
    "rejectionReason": null
}""")
                headers {
                     header('Content-Type': 'application/vnd.fraud.v1+json')

                    }
                
            }
        
}
