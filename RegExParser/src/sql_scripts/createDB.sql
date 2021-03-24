use imdbdb;
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
    year_start INTEGER(4) not NULL,
    year_end VARCHAR(4) not NULL,
    director VARCHAR(255),
    id INTEGER(10) auto_increment not NULL,
    PRIMARY KEY (id));
    
CREATE TABLE ACTOR
    (actorName VARCHAR(255) not NULL,
    PRIMARY KEY(actorName));

 CREATE TABLE ACTRESS
    (actressName VARCHAR(255) not NULL,
    PRIMARY KEY(actressName));
  
CREATE TABLE CASTING
    (movie_title VARCHAR(255) not NULL,
    castID INTEGER(10) auto_increment not NULL,
    castmember VARCHAR(255) not NULL,
    PRIMARY KEY (castID));  

CREATE TABLE EPISODE (
    title VARCHAR(255) NOT NULL,
    running_time VARCHAR(255),
    location VARCHAR(255),
    castID INTEGER(10),
    seriesID INTEGER(10) NOT NULL,
    PRIMARY KEY (TITLE),
    FOREIGN KEY (castID)
        REFERENCES CASTING (castID),
    FOREIGN KEY (seriesID)
        REFERENCES SERIES (id)
);