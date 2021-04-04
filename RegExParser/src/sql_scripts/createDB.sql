use imdbdb;
--CREATE TABLE IF NOT EXISTS episodes(series_title VARCHAR(255) NOT NULL, series_year VARCHAR(255) NOT NULL, quarter VARCHAR(255), episode_title VARCHAR(255) NOT NULL, filming_loc VARCHAR(255), running_time FLOAT, seriesID INTEGER(10) NOT NULL, PRIMARY KEY (episode_title), FOREIGN KEY (seriesID) REFERENCES SERIES (id));

CREATE TABLE IF NOT EXISTS MOVIES (
    movie_title VARCHAR(255) NOT NULL,
    movie_id INTEGER(10) auto_increment not NULL,
    director VARCHAR(255) NOT NULL,
    filming_loc VARCHAR(255),
    running_time FLOAT,
    genre VARCHAR(255),
    PRIMARY KEY (movie_id)
);

CREATE TABLE IF NOT EXISTS SERIES(
    title VARCHAR(255) not NULL,
    year_start INTEGER(4) not NULL,
    year_end VARCHAR(4) not NULL,
    seriesid INTEGER(10) auto_increment not NULL,
    PRIMARY KEY (seriesid));
    
CREATE TABLE IF NOT EXISTS ACTORS(
    actorname VARCHAR(255) not NULL,
    actorID INTEGER(10) auto_increment not NULL,
    filetype VARCHAR(255),
    title VARCHAR(255),
    PRIMARY KEY (actorID));

CREATE TABLE IF NOT EXISTS CASTING(
    actorID INT(10),
    actressID INT(10),
    seriesID INT(10),
    movieID INT(10),
    FOREIGN KEY (seriesID) REFERENCES SERIES (seriesid),
    FOREIGN KEY (actorID) REFERENCES ACTORS (actorID),
    FOREIGN KEY (movieID) REFERENCES MOVIES (movie_id),
    FOREIGN KEY (actressID) REFERENCES ACTRESSES (actressID));

CREATE TABLE IF NOT EXISTS ACTRESSES(
    actressName VARCHAR(255) not NULL,
    actressID INTEGER(10) auto_increment not NULL,
    filetype VARCHAR(255),
    title VARCHAR(255),
    PRIMARY KEY(actressID));
    
CREATE TABLE IF NOT EXISTS EPISODES(
    series_title VARCHAR(255) not NULL,
    series_year VARCHAR(255) not NULL,
    episode_title VARCHAR(255) not NULL,
    episode_id INTEGER(10) auto_increment not NULL, 
    filming_loc VARCHAR(255),
    director VARCHAR(255),
    running_time VARCHAR(255),
    seriesID INTEGER(10) NOT NULL,
    PRIMARY KEY (episode_id),
    FOREIGN KEY (seriesID) REFERENCES SERIES (seriesid));
