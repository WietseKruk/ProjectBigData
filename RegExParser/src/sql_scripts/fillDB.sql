INSERT INTO MOVIES(movie_title, director, filming_loc, running_time, genre)
VALUES(
    SELECT title, td.directorname, tl.filming_loc, tmr.runtime, tg.genre
    FROM TEMPMOVIES
    LEFT JOIN TEMPDIRECTORS td ON director 
    LEFT JOIN TEMPLOCATION tl ON filming_loc
    LEFT JOIN TEMPMOVIERUNTIME tmr ON running_time
    LEFT JOIN TEMPGENRE tg ON genre
)

INSERT INTO SERIES(title, year_start, year_end, director)
VALUES(
    SELECT title, year_start, year_end, td.directorname
    FROM TEMPSERIES
    LEFT JOIN TEMPDIRECTORS td ON director 
)

INSERT INTO ACTORS(actorName)
VALUES(
    SELECT actorName
    FROM TEMPACTORS
)

INSERT INTO ACTRESSES(actressName)
VALUES(
    SELECT actressName
    FROM TEMPACTRESSES
)

INSERT INTO CASTING(movie_title, series_title)
VALUES(
    SELECT title, st.series_title
    FROM TEMPMOVIES
    LEFT JOIN TEMPSERIES st ON series_title
)

INSERT INTO EPISODES(series_title, series_year, episode_title, filming_loc, running_time)
VALUES(
    SELECT title, series_year, te.episode_title, tl.filming_loc, ter.runtime
    FROM TEMPSERIES
    LEFT JOIN TEMPEPISODES te ON episode_title
    LEFT JOIN TEMPLOCATION tl ON filming_loc
    LEFT JOIN TEMPEPISODERUNTIME ter ON running_time
)

