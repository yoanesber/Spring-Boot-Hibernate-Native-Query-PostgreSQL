package com.yoanesber.spring.hibernate.native_query_postgresql.service;

import java.util.List;

import com.yoanesber.spring.hibernate.native_query_postgresql.dto.NetflixShowsDTO;

public interface NetflixShowsService {
    // Create NetflixShows
    void createNetflixShows(NetflixShowsDTO netflixShowsDTO);

    // Get all NetflixShows
    List<NetflixShowsDTO> getAllNetflixShows();

    // Get NetflixShows by id
    NetflixShowsDTO getNetflixShowsById(Long id);

    // Update NetflixShows
    void updateNetflixShows(Long id, NetflixShowsDTO netflixShowsDTO);

    // Delete NetflixShows
    Boolean deleteNetflixShows(Long id);
}
