package com.yoanesber.spring.hibernate.native_query_postgresql.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.yoanesber.spring.hibernate.native_query_postgresql.dto.NetflixShowsDTO;
import com.yoanesber.spring.hibernate.native_query_postgresql.entity.EShowType;
import com.yoanesber.spring.hibernate.native_query_postgresql.entity.NetflixShows;
import com.yoanesber.spring.hibernate.native_query_postgresql.service.NetflixShowsService;
import com.yoanesber.spring.hibernate.native_query_postgresql.repository.NetflixShowsRepository;

@Service
public class NetflixShowsServiceImpl implements NetflixShowsService {
    private final NetflixShowsRepository netflixShowsRepository;

    public NetflixShowsServiceImpl(NetflixShowsRepository netflixShowsRepository) {
        this.netflixShowsRepository = netflixShowsRepository;
    }

    @Override
    @Transactional
    public void createNetflixShows(NetflixShowsDTO netflixShowsDTO) {
        Assert.notNull(netflixShowsDTO, "NetflixShowsDTO must not be null");

        try {
            // Create NetflixShows object
            NetflixShows netflixShows = new NetflixShows();
            netflixShows.setShowType(EShowType.valueOf(netflixShowsDTO.getShowType()));
            netflixShows.setTitle(netflixShowsDTO.getTitle());
            netflixShows.setDirector(netflixShowsDTO.getDirector());
            netflixShows.setCastMembers(netflixShowsDTO.getCastMembers());
            netflixShows.setCountry(netflixShowsDTO.getCountry());
            netflixShows.setDateAdded(netflixShowsDTO.getDateAdded());
            netflixShows.setReleaseYear(netflixShowsDTO.getReleaseYear());
            netflixShows.setRating(netflixShowsDTO.getRating());
            netflixShows.setDurationInMinute(netflixShowsDTO.getDurationInMinute());
            netflixShows.setListedIn(netflixShowsDTO.getListedIn());
            netflixShows.setDescription(netflixShowsDTO.getDescription());

            // Save NetflixShows object
            netflixShowsRepository.createNetflixShows(netflixShows);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create NetflixShows: " + e.getMessage());
        }
    }

    @Override
    public List<NetflixShowsDTO> getAllNetflixShows() {
        try {
            // Get all NetflixShows
            List<NetflixShows> netflixShows = netflixShowsRepository.findAllNetflixShows();

            // Check if the list is empty
            if (netflixShows.isEmpty()) {
                return null;
            }

            // Convert NetflixShows to NetflixShowsDTO
            return netflixShows.stream().map(NetflixShowsDTO::new).toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all NetflixShows: " + e.getMessage());
        }
    }

    @Override
    public NetflixShowsDTO getNetflixShowsById(Long id) {
        Assert.notNull(id, "ID must not be null");

        try {
            // Get NetflixShows by ID
            NetflixShows netflixShows = netflixShowsRepository.findNetflixShowsById(id)
                .orElse(null);

            // Check if the NetflixShows is null
            if (netflixShows == null) {
                return null;
            }

            // Return NetflixShowsDTO
            return new NetflixShowsDTO(netflixShows);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get NetflixShows by ID: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateNetflixShows(Long id, NetflixShowsDTO netflixShowsDTO) {
        Assert.notNull(netflixShowsDTO, "NetflixShowsDTO must not be null");
        Assert.notNull(id, "ID must not be null");

        try {
            // Get NetflixShows by ID
            NetflixShows netflixShows = netflixShowsRepository.findNetflixShowsById(id)
                .orElse(null);

            // Check if the NetflixShows is null
            if (netflixShows == null) {
                throw new RuntimeException("NetflixShows not found");
            }

            // Update NetflixShows object
            netflixShows.setShowType(EShowType.valueOf(netflixShowsDTO.getShowType()));
            netflixShows.setTitle(netflixShowsDTO.getTitle());
            netflixShows.setDirector(netflixShowsDTO.getDirector());
            netflixShows.setCastMembers(netflixShowsDTO.getCastMembers());
            netflixShows.setCountry(netflixShowsDTO.getCountry());
            netflixShows.setDateAdded(netflixShowsDTO.getDateAdded());
            netflixShows.setReleaseYear(netflixShowsDTO.getReleaseYear());
            netflixShows.setRating(netflixShowsDTO.getRating());
            netflixShows.setDurationInMinute(netflixShowsDTO.getDurationInMinute());
            netflixShows.setListedIn(netflixShowsDTO.getListedIn());
            netflixShows.setDescription(netflixShowsDTO.getDescription());

            // Save NetflixShows object
            netflixShowsRepository.updateNetflixShows(id, netflixShows);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update NetflixShows: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Boolean deleteNetflixShows(Long id) {
        Assert.notNull(id, "ID must not be null");

        try {
            // Get NetflixShows by ID
            NetflixShows netflixShows = netflixShowsRepository.findNetflixShowsById(id)
                .orElse(null);

            // Check if the NetflixShows is null
            if (netflixShows == null) {
                return false;
            }

            // Delete NetflixShows object
            netflixShowsRepository.deleteNetflixShows(netflixShows);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete NetflixShows: " + e.getMessage());
        }
    }
}
