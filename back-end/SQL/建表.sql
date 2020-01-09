DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    uid      INT         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    avatar   VARCHAR(200),
    admin    INT,
    PRIMARY KEY (`uid`)
);

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task`
(
    tid         INT          NOT NULL AUTO_INCREMENT,
    uid         INT          NOT NULL,
    oid         INT,
    image       VARCHAR(200),
    title       VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    reason      VARCHAR(1000) DEFAULT '-',
    frequency   INT,
    allocated   INT,
    finished    INT,
    PRIMARY KEY (`tid`),
    FOREIGN KEY (`uid`) REFERENCES user (`uid`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `calendar`;

CREATE TABLE `calendar`
(
    cid     INT NOT NULL AUTO_INCREMENT,
    uid     INT,
    uname   VARCHAR(200),
    context VARCHAR(200),
    date    DATE,
    PRIMARY KEY (`cid`)
);

DROP TABLE IF EXISTS `old`;

CREATE TABLE `old`
(
    oid               INT         NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50) NOT NULL,
    image             VARCHAR(200),
    birthday          DATE,
    gender            INT,
    identity          VARCHAR(20) NOT NULL,
    address           VARCHAR(200),
    nation            VARCHAR(50),
    education         VARCHAR(50),
    phone             VARCHAR(14) NOT NULL,
    single            VARCHAR(50),
    finance           VARCHAR(50),
    insurance         VARCHAR(50),
    dependant         VARCHAR(50),
    dependantphone    VARCHAR(14),
    dependantidentity VARCHAR(20),
    responsibility    INT,
    PRIMARY KEY (`oid`)
);

DROP TABLE IF EXISTS `health`;

CREATE TABLE `health`
(
    hid           INT          NOT NULL AUTO_INCREMENT,
    oid           INT          NOT NULL,
    weight        DOUBLE,
    bloodpressure VARCHAR(100) NOT NULL,
    heartbeat     INT,
    leftsight     VARCHAR(20),
    rightsight    VARCHAR(20),
    recognize     VARCHAR(100),
    mental        VARCHAR(100),
    date          DATE         NOT NULL,
    PRIMARY KEY (`hid`),
    FOREIGN KEY (`oid`) REFERENCES old (`oid`) ON DELETE CASCADE
);
