# cdc_examples

## 

In this repository you'll find examples of CDC using [Accurest](https://github.com/Codearte/accurest) 
from [Codearte](https://github.com/Codearte) and [4financeIT](https://github.com/4finance).

## How to run it?

Just execute

```
./gradlew clean build
```

## What happens under the hood?

The Gradle project builds the server side, generates its Wiremock stubs and copies them to the resources of the client side.
That way when you're running the client side build the tests are passing.