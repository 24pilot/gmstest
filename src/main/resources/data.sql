//primary key is index by default
CREATE TABLE currency_exchange
(
  id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  base TEXT               NOT NULL,
  date TEXT               NOT NULL
);