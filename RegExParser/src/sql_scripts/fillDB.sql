--INSERT INTO MOVIES(movie_title, director)
--SELECT TEMPMOVIES.title, td.directorname
--FROM TEMPMOVIES
--INNER JOIN TEMPDIRECTORS td ON td.play_title = TEMPMOVIES.title

INSERT INTO SERIES(title, year_start, year_end)
SELECT title, year_start, year_end
FROM TEMPSERIES
);




--INSERT INTO SERIES(title, year_start, year_end, director)
--VALUES(
    --SELECT title, year_start, year_end, td.directorname
    --FROM TEMPSERIES
    --LEFT JOIN TEMPDIRECTORS td ON director 
--);

--INSERT INTO ACTORS(actorName)
--VALUES(
    --SELECT actorName
    --FROM TEMPACTORS
--);

--INSERT INTO ACTRESSES(actressName)
--VALUES(
    --SELECT actressName
    --FROM TEMPACTRESSES
--);

--NSERT INTO CASTING(movie_title, series_title)
--VALUES(
    --SELECT title, st.series_title
    --FROM TEMPMOVIES
    --LEFT JOIN TEMPSERIES st ON series_title
--);

--INSERT INTO EPISODES(series_title, series_year, episode_title, filming_loc, running_time)
--VALUES(
    --SELECT title, series_year, te.episode_title, tl.filming_loc, ter.runtime
    --FROM TEMPSERIES
    --LEFT JOIN TEMPEPISODES te ON episode_title
    --LEFT JOIN TEMPLOCATION tl ON filming_loc
    --LEFT JOIN TEMPEPISODERUNTIME ter ON running_time
--);

