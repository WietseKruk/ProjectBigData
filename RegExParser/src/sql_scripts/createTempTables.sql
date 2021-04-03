use imdbdb;
CREATE TABLE IF NOT EXISTS TEMPMOVIES(title VARCHAR(255) NOT NULL, movie_year VARCHAR(4) NOT NULL, part VARCHAR(255), suspended VARCHAR(13), platform VARCHAR(255), movie_year_2 VARCHAR(4));

CREATE TABLE IF NOT EXISTS TEMPSERIES(
    title VARCHAR(255) NOT NULL,
    part VARCHAR(13),
    suspended VARCHAR(255),
    year_start VARCHAR(4),
    year_end VARCHAR(4)
    );
    
CREATE TABLE IF NOT EXISTS TEMPACTORS(
    actorName VARCHAR(255) NOT NULL,
    serieormovie VARCHAR(255) NOT NULL, 
    title VARCHAR(255) NOT NULL, 
    play_year VARCHAR(4) NOT NULL, 
    part VARCHAR(255), 
    platform VARCHAR(255), 
    episode VARCHAR(255),
    suspended VARCHAR(255),
    voice VARCHAR(255), 
    credit VARCHAR(255),
    played LONGTEXT
    );

CREATE TABLE IF NOT EXISTS TEMPACTRESSES(
    actressName VARCHAR(255) NOT NULL,
    serieormovie VARCHAR(255) NOT NULL, 
    title VARCHAR(255) NOT NULL, 
    play_year VARCHAR(4) NOT NULL, 
    part VARCHAR(255), 
    platform VARCHAR(255), 
    episode VARCHAR(255),
    suspended VARCHAR(255),
    voice VARCHAR(255), 
    credit VARCHAR(255),
    played LONGTEXT
    );

CREATE TABLE IF NOT EXISTS TEMPEPISODES(
    series_title VARCHAR(255) NOT NULL, 
    series_year VARCHAR(4) NOT NULL, 
    part VARCHAR(255), 
    episode_title VARCHAR(255) NOT NULL, 
    platform VARCHAR(255), 
    episode_year VARCHAR(4)
);

CREATE TABLE IF NOT EXISTS TEMPLOCATION(
    title VARCHAR(255) NOT NULL,
    play_year VARCHAR(255) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    filming_loc LONGTEXT
);

CREATE TABLE IF NOT EXISTS TEMPGENRE(
    title VARCHAR(255) NOT NULL,
    play_year VARCHAR(4) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    episode_name VARCHAR(255), 
    suspended VARCHAR(255), 
    genre VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS TEMPMOVIERUNTIME(
    title VARCHAR(255) NOT NULL,
    movie_year VARCHAR(255) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    suspended VARCHAR(255), 
    filming_loc VARCHAR(255), 
    runtime VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS TEMPEPISODERUNTIME(
    title VARCHAR(255) NOT NULL,
    episode_year VARCHAR(255) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    episode_name VARCHAR(255),
    suspended VARCHAR(255), 
    filming_loc VARCHAR(255), 
    runtime VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS TEMPSERIESRUNTIME(
    title VARCHAR(255) NOT NULL,
    serie_year VARCHAR(255) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    suspended VARCHAR(255), 
    filming_loc VARCHAR(255), 
    runtime VARCHAR(255) 
    );

CREATE TABLE IF NOT EXISTS TEMPSOUNDTRACKMOVIES(
    title VARCHAR(255) NOT NULL,
    movie_year VARCHAR(255) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    suspended VARCHAR(255), 
    songtitle LONGTEXT
    );

CREATE TABLE IF NOT EXISTS TEMPSOUNDTRACKSERIES(
    series_title VARCHAR(255) NOT NULL,
    series_year VARCHAR(255), 
    episode VARCHAR(255),
    suspended VARCHAR(255), 
    songtitle LONGTEXT
    );

CREATE TABLE IF NOT EXISTS TEMPDIRECTORS(
    directorname VARCHAR(255) NOT NULL,
    serieormovie VARCHAR(255) NOT NULL,
    play_title VARCHAR(255), 
    play_year VARCHAR(4) NOT NULL,
    part VARCHAR(255),
    platform VARCHAR(255),
    episode VARCHAR(255),
    suspended VARCHAR(255),
    credit LONGTEXT
    );
