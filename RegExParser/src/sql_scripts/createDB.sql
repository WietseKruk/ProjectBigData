use imdbdb;
CREATE TABLE IF NOT EXISTS episodes(series_title VARCHAR(255) NOT NULL, series_year VARCHAR(255) NOT NULL, quarter VARCHAR(255), episode_title VARCHAR(255) NOT NULL, filming_loc VARCHAR(255), running_time FLOAT, seriesID INTEGER(10) NOT NULL, PRIMARY KEY (episode_title), FOREIGN KEY (seriesID) REFERENCES SERIES (id));

CREATE TABLE IF NOT EXISTS MOVIES (
    movie_title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    filming_loc VARCHAR(255),
    running_time FLOAT,
    genre VARCHAR(255),
    PRIMARY KEY (movie_title)
);

CREATE TABLE IF NOT EXISTS SERIES(title VARCHAR(255) not NULL,year_start INTEGER(4) not NULL,year_end VARCHAR(4) not NULL,director VARCHAR(255),id INTEGER(10) auto_increment not NULL,PRIMARY KEY (id));
    
CREATE TABLE IF NOT EXISTS ACTORS(actorName VARCHAR(255) not NULL,PRIMARY KEY(actorName));

CREATE TABLE IF NOT EXISTS ACTRESSES(actressName VARCHAR(255) not NULL,PRIMARY KEY(actressName));
  
CREATE TABLE IF NOT EXISTS CASTING(movie_title VARCHAR(255) not NULL,castID INTEGER(10) auto_increment not NULL,castmember VARCHAR(255) not NULL,PRIMARY KEY (castID));  

CREATE TABLE IF NOT EXISTS EPISODES(series_title VARCHAR(255) NOT NULL,series_year VARCHAR(255) NOT NULL,quarter VARCHAR(3),episode_title VARCHAR(255) NOT NULL,filming_loc VARCHAR(255),running_time VARCHAR(255),seriesID INTEGER(10) NOT NULL,PRIMARY KEY (episode_title),FOREIGN KEY (seriesID) REFERENCES SERIES (id));
