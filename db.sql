CREATE TABLE Location (
	ID 			INT 			NOT NULL AUTO_INCREMENT,
	location 	VARCHAR(32)		NOT NULL,

	PRIMARY KEY (ID)
);

CREATE TABLE TravelType (
	ID 			INT 			NOT NULL AUTO_INCREMENT,
	travelType 	VARCHAR(32) 	NOT NULL,

	PRIMARY KEY (ID)
);

CREATE TABLE TransportType (
	ID 				INT 		NOT NULL AUTO_INCREMENT,
	transportType 	VARCHAR(32) NOT NULL,

	PRIMARY KEY (ID)
);

CREATE TABLE Travel (
	ID 				INT 		NOT NULL AUTO_INCREMENT,
	travelType 		INT 		NOT NULL,
	fromLocation 	INT			NOT NULL,
	toLocation 		INT 		NOT NULL,
	leavesAt 		DATETIME 	NOT NULL,
	arrivesAt 		DATETIME 	NOT NULL,
	numOfPlaces 	INT 		NOT NULL,
	transportType 	INT 		NOT NULL,
	perPersonLimit 	INT 		NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (travelType) 	REFERENCES TravelType 	 	(ID),
	FOREIGN KEY (fromLocation) 	REFERENCES Location 	 	(ID),
	FOREIGN KEY (toLocation) 	REFERENCES Location 	 	(ID),
	FOREIGN KEY (transportType) REFERENCES TransportType 	(ID)
);

CREATE TABLE Person (
	ID 			INT 		NOT NULL AUTO_INCREMENT,
	FirstName 	VARCHAR(32) NOT NULL,
	LastName 	VARCHAR(32) NOT NULL,
	AccountID	INT 		NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (AccountID)		REFERENCES Account (ID)
);

CREATE TABLE Customer (
	ID 			INT NOT NULL AUTO_INCREMENT,
	personality INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (personality) REFERENCES Person (ID)
);


CREATE TABLE Position (
	ID 			INT 		NOT NULL AUTO_INCREMENT,
	position 	VARCHAR(32) NOT NULL,

	PRIMARY KEY (ID)
);

CREATE TABLE Employee (
	ID 			INT NOT NULL AUTO_INCREMENT,
	personality INT NOT NULL,
	position 	INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (personality) 	REFERENCES Person 	(ID),
	FOREIGN KEY (position) 		REFERENCES Position (ID)

);

CREATE TABLE Cashier (
	ID			INT NOT NULL AUTO_INCREMENT,
	personality INT NOT NULL,
	employment 	INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (personality) 	REFERENCES Person 	(ID),
	FOREIGN KEY (employment) 	REFERENCES Employee (ID)
);

CREATE TABLE Ticket (
	ID 			INT NOT NULL AUTO_INCREMENT,
	customer 	INT NOT NULL,
	travel 		INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (customer) 	REFERENCES Customer (ID),
	FOREIGN KEY (travel) 	REFERENCES Travel 	(ID)
);

CREATE TABLE Company (
	ID 		INT 		NOT NULL AUTO_INCREMENT,
	Name 	VARCHAR(32) NOT NULL,
	EIN		VARCHAR(9) 	NOT NULL,

	PRIMARY KEY (ID)
);

CREATE TABLE Distributor (
	ID 		INT NOT NULL AUTO_INCREMENT,
	company INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (company) REFERENCES Company (ID)
);

CREATE TABLE Organisator (
	ID 		INT NOT NULL AUTO_INCREMENT,
	company INT NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (company) REFERENCES Company (ID)
);


CREATE TABLE EmployeeToDistributor (
	employee 	INT NOT NULL,
	distributor INT NOT NULL,

	PRIMARY KEY (employee, distributor),
	FOREIGN KEY (employee) 		REFERENCES Employee 	(ID),
	FOREIGN KEY (distributor) 	REFERENCES Distributor  (ID),
);

CREATE TABLE EmployeeToOrganisator (
	employee 	INT NOT NULL,
	organistor 	INT NOT NULL,

	PRIMARY KEY (employee, organistor),
	FOREIGN KEY (employee) 		REFERENCES Employee 	(ID),
	FOREIGN KEY (organistor) 	REFERENCES Organisator  (ID),
);


CREATE TABLE RequestedTickets (
	ID 			INT 	 NOT NULL AUTO_INCREMENT,
	ticket 		INT 	 NOT NULL,
	employee 	INT 	 NOT NULL,
	requestedAt DATETIME NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (ticket)	REFERENCES Ticket 	(ID),
	FOREIGN KEY (employee)	REFERENCES Employee (ID)
);


CREATE TABLE SoldTicket (
	ID 				INT 	 NOT NULL AUTO_INCREMENT,
	requestedTicket INT 	 NOT NULL,
	employee 		INT 	 NOT NULL,
	soldAt 			DATETIME NOT NULL,

	PRIMARY KEY (ID),
	FOREIGN KEY (requestedTicket) 	REFERENCES RequestedTickets (ID),
	FOREIGN KEY (employee) 			REFERENCES Employee 		(ID)
);


CREATE TABLE Permission (
	ID 	 INT 		 NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,

	PRIMARY KEY (ID)
);


CREATE TABLE PERSON_TO_PERMISSION (
	person 		INT 	NOT NULL,
	permission 	INT 	NOT NULL,

	PRIMARY KEY (person, permission),
	FOREIGN KEY (person) 	 REFERENCES Person 	 	(ID),
	FOREIGN KEY (permission) REFERENCES Permission  (ID)
);


CREATE TABLE Account (
	ID			INT     	 NOT NULL	AUTO_INCREMENT,
	username	VARCHAR(32)	 NOT NULL,	
	email 		VARCHAR(32)	 NOT NULL,
	password	VARCHAR(32)	 NOT NULL,

	PRIMARY KEY (ID),

)



