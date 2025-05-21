# Database Multi-Tenancy Concept on JBoss 8

## Background
This is a sample repository to simulate a single apps capability to connect to a multiple different databases depending on request. This approach is necessary to simulate a multi-tenancy concept leveraging JBoss EAP 8's connection pooling via JNDI.

## How To

### Database

create two different database as an example, each with the same table structure
```sql
create database one_db;

create table one_db.tb_testing
(
    id       int auto_increment
        primary key,
    username varchar(60) null
);


INSERT INTO one_db.tb_testing (id, username) VALUES (1, 'lelelelee');
```

and 

```sql
create database two_db;

create table two_db.tb_testing
(
    id       int auto_increment
        primary key,
    username varchar(60) null
);

INSERT INTO two_db.tb_testing (id, username) VALUES (1, 'one one one');
INSERT INTO two_db.tb_testing (id, username) VALUES (2, 'two two two');
INSERT INTO two_db.tb_testing (id, username) VALUES (3, 'three three three');
```

### JBoss EAP Connection Pool
Create two different database connection pool, one for each database
```xml
<datasource jndi-name="java:/one_db-ds" pool-name="one_db-ds">
    <connection-url>jdbc:mysql://localhost:3306/one_db</connection-url>
    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
    <driver>mysql-connector-j-9.3.0.jar</driver>
    <security>
        <user-name>aaaa</user-name>
        <password>bbbb</password>
    </security>
</datasource>

<datasource jndi-name="java:/two_db-ds" pool-name="two_db-ds">
    <connection-url>jdbc:mysql://localhost:3306/two_db</connection-url>
    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
    <driver>mysql-connector-j-9.3.0.jar</driver>
    <security>
        <user-name>xxxx</user-name>
        <password>yyy</password>
    </security>
</datasource>
```

## Testing
```
$ curl "http://127.0.0.1:8080/index?customerId=one_db"

$ curl "http://127.0.0.1:8080/index?customerId=two_db"
```