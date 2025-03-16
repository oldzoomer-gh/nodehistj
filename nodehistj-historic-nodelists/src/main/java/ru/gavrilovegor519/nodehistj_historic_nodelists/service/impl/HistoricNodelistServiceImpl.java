package ru.gavrilovegor519.nodehistj_historic_nodelists.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.gavrilovegor519.Nodelist;
import ru.gavrilovegor519.dto.NodelistEntryDto;
import ru.gavrilovegor519.nodehistj_historic_nodelists.repo.NodelistEntityRepository;
import ru.gavrilovegor519.nodehistj_historic_nodelists.service.HistoricNodelistService;

import java.util.Map;

/**
 * Historic nodelist service layer
 */
@Service
@RequiredArgsConstructor
public class HistoricNodelistServiceImpl implements HistoricNodelistService {
    private final NodelistEntityRepository nodelistEntityRepository;

    /**
     * Get all nodelist entries
     *
     * @param year      year
     * @param dayOfYear day of year
     * @return Map of nodelist entries
     */
    @Override
    @Cacheable(value = "historicAllDataOfNodelist")
    public Map<Integer, NodelistEntryDto> getNodelistEntries(int year, int dayOfYear) {
        return getNodelistEntriesInternal(year, dayOfYear);
    }

    /**
     * Get zone nodelist entry
     *
     * @param year      year
     * @param dayOfYear day of year
     * @param zone      zone
     * @return Zone nodelist entry
     */
    @Override
    @Cacheable(value = "historicGetZoneNodelistEntry",
            key = "#year + '-' + #dayOfYear + '-' + #zone")
    public NodelistEntryDto getNodelistEntry(int year, int dayOfYear, int zone) {
        return getNodelist(year, dayOfYear).getZoneNodelistEntries(zone);
    }

    /**
     * Get network nodelist entry
     *
     * @param year      year
     * @param dayOfYear day of year
     * @param zone      zone
     * @param network   network
     * @return Network nodelist entry
     */
    @Override
    @Cacheable(value = "historicGetNetworkNodelistEntry",
            key = "#year + '-' + #dayOfYear + '-' + #zone + '-' + #network")
    public NodelistEntryDto getNodelistEntry(int year, int dayOfYear, int zone, int network) {
        return getNodelist(year, dayOfYear).getNetworkNodelistEntries(zone, network);
    }

    /**
     * Get node nodelist entry
     *
     * @param year      year
     * @param dayOfYear day of year
     * @param zone      zone
     * @param network   network
     * @param node      node address
     * @return Node nodelist entry
     */
    @Override
    @Cacheable(value = "historicGetNodeNodelistEntry",
            key = "#year + '-' + #dayOfYear + '-' + #zone + '-' + #network + '-' + #node")
    public NodelistEntryDto getNodelistEntry(int year, int dayOfYear, int zone, int network, int node) {
        return getNodelist(year, dayOfYear).getNodelistEntry(zone, network, node);
    }

    /**
     * Get nodelist (for internal use)
     *
     * @param year      year
     * @param dayOfYear day of year
     * @return Nodelist
     */
    private Nodelist getNodelist(int year, int dayOfYear) {
        return new Nodelist(getNodelistEntries(year, dayOfYear));
    }

    /**
     * Get nodelist entries (for internal use)
     * @param year year
     * @param dayOfYear day of year
     * @return Map of nodelist entries
     */
    private Map<Integer, NodelistEntryDto> getNodelistEntriesInternal(int year, int dayOfYear) {
        return nodelistEntityRepository.findByNodelistYearAndNodelistName(
                year, String.format("nodelist.%03d", dayOfYear)
        ).getNodelist();
    }
}
