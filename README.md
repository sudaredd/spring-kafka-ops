### uage
1) Create topic with multiple partitions (3)
2) Run multiple instances with different server.port
3) Send PAUSE on one of instance (http://localhost:8080/action?state=PAUSE)
4) Write messages to topic for each paritions
5) We should see instance with paused partition should idle, none of messages will be read until we call RESUME

