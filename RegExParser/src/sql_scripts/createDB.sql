CREATE TABLE MOVIE (
    movie_title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    running_time DOUBLE,
    genre VARCHAR(255),
    PRIMARY KEY (movie_title)
);

CREATE TABLE SERIES
    (title VARCHAR(255) not NULL,
    series INTEGER(10) not NULL,
    year_start INTEGER(4) not NULL,
    year_end VARCHAR(4) not NULL,
    director VARCHAR(255),
    PRIMARY KEY (id));
    
CREATE TABLE ACTOR
    (actorName VARCHAR(255) not NULL,
    PRIMARY KEY(name));

 CREATE TABLE ACTRESS
    (actressName VARCHAR(255) not NULL,
    PRIMARY KEY(name));
  
CREATE TABLE CASTING
    (movie_title VARCHAR(255) not NULL,
    castID INTEGER(10) not NULL,
    castmember VARCHAR(255) not NULL,
    PRIMARY KEY (id));  

CREATE TABLE EPISODE (
    title VARCHAR(255) NOT NULL,
    running_time VARCHAR(255),
    location VARCHAR(255),
    castID INTEGER(10),
    series VARCHAR(255) NOT NULL,
    PRIMARY KEY (TITLE),
    FOREIGN KEY (castID)
        REFERENCES CASTING (castID),
    FOREIGN KEY (series)
        REFERENCES SERIES (series)
);