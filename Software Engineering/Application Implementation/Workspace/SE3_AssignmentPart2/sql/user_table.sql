DROP TABLE `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `BALANCE` varchar(20) NOT NULL,
  PRIMARY KEY  (`ID`)
  );
  
  INSERT INTO user VALUES (null, "Jason", "Byrne", "10043882", "password", "1500.50"),
  						  (null, "Dylan", "Moran", "10043921", "password", "800.00"),
  						  (null, "Bill", "Bailey", "10050213", "password", "457.39"),
  						  (null, "Jimmy", "Carr", "10039835", "password", "3020.80");
