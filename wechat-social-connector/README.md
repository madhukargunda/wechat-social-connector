
```
Simple Spring Boot REST API with two REST Endpoints to connect from wechat service account to verify 
what are all the headers wechat service account is sending to our network.

- How to run using the docker command

  docker run -it -p 80:8000 --name wechat-social-connector -d madhukargunda/wechat-social-connector:1.0.0

- How to  run using the docker compose

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
  
PS : docker-compose creates the default network adds the all the containers specified in the compose file  
    if there is no explicit network specified.
    
Use full docker compose docker commands
  
    1.docker-compose stop
    2.docker-compose start
    3.docker-compose restart
    4.docker-compose ps
    
Step5: docker compose file options

	1.docker-compose -f docker-compose2.yml up -d
	2.docker-compose -f docker-compose2.yml ps
	3.docker-compose -f docker-compose2.yml down 
	
Step6: Build the image along with the docker compose file 

 docker-compose -f docker-compose2.yml up -d --build
  
Step8: HaProxy Terminlology 

    a.ACL - Access Control List
    b.backend 
    c.frontend 
 	
Step 9 : Haproxy Syntax for backend part

	backend <backendname>
	balance <loadbalancing algorithm>
	server <name of the server> <ip>:<port> check
	....
	server <name of the server> <ip>:<port> check
	
Step 10: 

    frontend <frontend name>
    bind <IPs or wild card>:80
    acl <aclname> <criterion> [flags] [operator] [<value>] ...
    use_backend <backend name> if <aclname>
    
Step 11:

 Load balancing algorithoms
 
 - Round Robin
 - Least connection
 - Source
 - Sticky Session
 - Health Check
 
```