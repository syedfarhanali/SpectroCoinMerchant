### Running the application SpectroCoinMerchant

>To generate jar run below command from home directory

```shell
mvn package
```

> To run the application run below command

```shell
java -jar SpectroCoinMerchant-0.0.1-SNAPSHOT.jar --server.port={port} --spectro.privatekey={private_key_location}
```

eg

```shell
java -jar SpectroCoinMerchant-0.0.1-SNAPSHOT.jar --server.port=9090 --spectro.privatekey=/opt/spectro/private.der
```

### Using the API

```shell
URL : http://localhost:9090/getSignature
Type : POST
Argument : "parameters" (sample value= "merchantId=947909&apiId=42925&orderId=&payCurrency=BTC&payAmount=1.1&receiveCurrency=&receiveAmount=0.0&description=&culture=&callbackUrl=&successUrl=&failureUrl=") 
Return type : String (Signature)
```


