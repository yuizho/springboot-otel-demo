# spring-html-demo
## build & launch App server
```bash
./mvnw clean package && docker compose up 
```

And then you can access to `http://localhost/todo` by your browser.
Your request is handled by Nginx and forwarded to the Spring Boot server.