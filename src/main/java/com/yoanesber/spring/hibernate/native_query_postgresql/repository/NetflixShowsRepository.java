package com.yoanesber.spring.hibernate.native_query_postgresql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yoanesber.spring.hibernate.native_query_postgresql.entity.NetflixShows;

@Repository
public interface NetflixShowsRepository extends JpaRepository<NetflixShows, Long> {
    // Create NetflixShows
    @Modifying(clearAutomatically = true, flushAutomatically = true) // This annotation is required for modifying queries (e.g., delete, update) and it will clear the persistence context after the query has been executed.
    @Query(value = """
    insert into
        netflix_shows
    (
        "type",
        title,
        director,
        cast_members,
        country,
        date_added,
        release_year,
        rating,
        duration_in_minute,
        listed_in,
        description
    )
    values
    (
        :#{#netflixShows.showType.toString()},
        :#{#netflixShows.title},
        :#{#netflixShows.director},
        :#{#netflixShows.castMembers},
        :#{#netflixShows.country},
        :#{#netflixShows.dateAdded},
        :#{#netflixShows.releaseYear},
        :#{#netflixShows.rating},
        :#{#netflixShows.durationInMinute},
        :#{#netflixShows.listedIn},
        :#{#netflixShows.description}
    )
    """, nativeQuery = true)
    void createNetflixShows(@Param("netflixShows") NetflixShows netflixShows); // Modifying queries can only use void or int/Integer as return type

    // Get all NetflixShows
    @Query(value = """
    select
        *
    from
        netflix_shows
    order by
        title
    """, nativeQuery = true)
    List<NetflixShows> findAllNetflixShows();

    // Get NetflixShows by id
    @Query(value = """
    select
        *
    from
        netflix_shows
    where
        id = :id
    """, nativeQuery = true)
    Optional<NetflixShows> findNetflixShowsById(@Param("id") Long id);


    // Update NetflixShows
    @Modifying(clearAutomatically = true, flushAutomatically = true) // This annotation is required for modifying queries (e.g., delete, update) and it will clear the persistence context after the query has been executed.
    @Query(value = """
    update
        netflix_shows
    set
        "type" = :#{#netflixShows.showType.toString()},
        title = :#{#netflixShows.title},
        director = :#{#netflixShows.director},
        cast_members = :#{#netflixShows.castMembers},
        country = :#{#netflixShows.country},
        date_added = :#{#netflixShows.dateAdded},
        release_year = :#{#netflixShows.releaseYear},
        rating = :#{#netflixShows.rating},
        duration_in_minute = :#{#netflixShows.durationInMinute},
        listed_in = :#{#netflixShows.listedIn},
        description = :#{#netflixShows.description}
    where
        id = :id
    """, nativeQuery = true)
    void updateNetflixShows(@Param("id") Long id, @Param("netflixShows") NetflixShows netflixShows); // Modifying queries can only use void or int/Integer as return type


    // Delete NetflixShows
    @Modifying(clearAutomatically = true, flushAutomatically = true) // This annotation is required for modifying queries (e.g., delete, update) and it will clear the persistence context after the query has been executed.
    @Query(value = """ 
    delete
    from
        netflix_shows
    where
        id = :#{#netflixShows.id}
    """, nativeQuery = true)
    void deleteNetflixShows(@Param("netflixShows") NetflixShows netflixShows); // Modifying queries can only use void or int/Integer as return type
}
