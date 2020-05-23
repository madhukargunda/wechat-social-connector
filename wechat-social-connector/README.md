
```
Simple Spring Boot REST API with two REST Endpoints to connect from wechat service account to verify 
what are all the headers wechat service account is sending to our network.

How to run using the docker command

  docker run -it -p 80:8000 --name wechat-social-connector -d madhukargunda/wechat-social-connector:1.0.0


How to  run using the docker compose

Step1: Verify docker compose is installed or not
  docker-compose --version 

Step2: Verify the docker compose file validity
  docker-compose config

Step3: Run the compose file by using the below commands
	docker-compose up -d 
	docker-compose down 

Step4: scaling the docker only specific service
 Note: Service should not bind to host port to scale the applications

  docker-compose up -d --scale wechat=4
 

```