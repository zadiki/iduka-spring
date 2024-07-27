# Iduka Spring application
New spring boot application with the following implementations
  
- Restfull api
- Spring security
- Spring data jpa
- Swagger api documentation
- Global error handling
- Google Pub/Sub integration
- Api validation rules
- testing with mockito,AssertJ,Hemcrest,JsonAsset,JsonPath,spring test and spring boot test.As guided by this repo https://github.com/danvega/spring-boot-testing/tree/main

# Docker image
 use  
```bash
sudo ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=zadiki-build-2 
```

to build docker image

## License

[MIT](https://choosealicense.com/licenses/mit/)