package ru.gavrilovegor519.nodehistj_historic_nodelists.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.gavrilovegor519.nodelistj.enums.Keywords;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@ToString
@Table("nodelist")
public class NodelistEntity {

    @Id
    private Long id;

    @Column("nodelist_year")
    private Integer nodelistYear;

    @Column("nodelist_name")
    private String nodelistName;

    @Column("zone")
    private Integer zone;

    @Column("network")
    private Integer network;

    @Column("node")
    private Integer node;

    @Column("keywords")
    private Keywords keywords;

    @Column("node_name")
    private String nodeName;

    @Column("location")
    private String location;

    @Column("sys_op_name")
    private String sysOpName;

    @Column("phone")
    private String phone;

    @Column("baud_rate")
    private Integer baudRate;

    @Column("flags")
    private List<String> flags;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NodelistEntity that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(nodelistYear, that.nodelistYear) &&
               Objects.equals(nodelistName, that.nodelistName) &&
               Objects.equals(zone, that.zone) &&
               Objects.equals(network, that.network) &&
               Objects.equals(node, that.node) &&
               keywords == that.keywords &&
               Objects.equals(nodeName, that.nodeName) &&
               Objects.equals(location, that.location) &&
               Objects.equals(sysOpName, that.sysOpName) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(baudRate, that.baudRate) &&
               Objects.equals(flags, that.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodelistYear, nodelistName, zone, network,
                node, keywords, nodeName, location, sysOpName, phone, baudRate, flags);
    }
}