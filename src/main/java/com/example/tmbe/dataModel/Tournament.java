package com.example.tmbe.dataModel;

import com.example.tmbe.enumConverter.TournamentType;
import com.example.tmbe.enumConverter.TournamentTypeConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tournaments")
public class Tournament {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "type")
  @Convert(converter = TournamentTypeConverter.class)
  private TournamentType type;

  @Column(name = "startDate")
  private Date startDate;

  @Column(name = "endDate")
  private Date endDate;

  @Column(name = "groupSize")
  private int groupSize;

  @Column(name = "comment")
  private String comment;

  // Bidirectional @ManyToMany, two parents, no children, one owner (Player)
  @JsonBackReference
  @ManyToMany(mappedBy = "playedTournaments")
  private Set<Player> participatingPlayers = new HashSet<>();

  public void addPlayer(Player player){
    this.participatingPlayers.add(player);
    player.getPlayedTournaments().add(this);
  }
  public void removePlayer(Player player){
    this.participatingPlayers.remove(player);
    player.getPlayedTournaments().remove(this);
  }

  @Override
  public String toString() {
    return "Tournament{"
        + "id="
        + id
        + ", type='"
        + type
        + '\''
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + ", groupSize="
        + groupSize
        + ", comment='"
        + comment
        + '\''
        + ", players="
        + participatingPlayers.size()
        + '}';
  }
}
