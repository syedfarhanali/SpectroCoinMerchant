### Running the application SpectroCoinMerchant

>To generate jar run below command from home directory

```shell
mvn package
```

> To run the application run below command

```shell
java -jar SpectroCoinMerchant-0.0.1-SNAPSHOT.jar --server.port={port} --spectro.privatekey={absolute_private_key_location} --spectro.publickey={absolute_public_key_location}
```

eg

```shell
java -jar SpectroCoinMerchant-0.0.1-SNAPSHOT.jar --server.port=9090 --spectro.privatekey=/opt/spectro/private.der --spectro.publickey=/opt/spectro/public.pem
```

### Using the API

```shell
URL : http://localhost:9090/getSignature
Type : POST
Argument : "parameters" (sample value= "merchantId=947909&apiId=42925&orderId=&payCurrency=BTC&payAmount=1.1&receiveCurrency=&receiveAmount=0.0&description=&culture=&callbackUrl=&successUrl=&failureUrl=") 
Return type : String (Signature)
```

```shell
URL : http://localhost:9090/validate
Type : POST
Argument : 
"parameters" (sample value= "merchantId=947909&apiId=42925&orderId=&payCurrency=BTC&payAmount=1.1&receiveCurrency=&receiveAmount=0.0&description=&culture=&callbackUrl=&successUrl=&failureUrl=") 
"responseSign" (sample value = "a88NZA8Kcs9vMZULiCkJfLswDn2rlVQrYpx6b2wJV7LqjUGIrKu9olVJOHb0mXnCqMZdJvG5nq4QiipGMw83MCgtXC5j+uC2VrJdIDdAurpvMUUapBDAvG93s6PGrD3KclrGVToDXofd3tYV4fs8PpCgsJ5LDEW8opqYCaK8MLkfDxLc3z2flcrL8b3uAaTVjLcjFDRNoU4jKw6MVg92+G2r4+FLyWie9aAsZ6K19RrSaptmF59DjTRSFtmjNIDNZdpE+SrvsnqZLnWEmejs80AWx+d/MP6vK7rnND64J91wwMfIkNUtJ7QsipSkpvLlzFvkLz+UcDXvPEwASvLF1A==")
Return type : Bolean
```


