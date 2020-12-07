DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
  id DOUBLE AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
);

DROP TABLE IF EXISTS TODO;
CREATE TABLE TODO (
  id DOUBLE AUTO_INCREMENT  PRIMARY KEY,
  user_id DOUBLE,
  description VARCHAR(250),
  target_date DATE,
  done BIT,
  foreign key (user_id) references USERS(id)
);