package com.example.tmbe.dataModel;

import com.example.tmbe.enumConverter.TournamentType;
import com.example.tmbe.enumConverter.TournamentTypeConverter;
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
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tournaments")
public class Tournament {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

//  @Column(name = "type")
//  private String type;

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

  @ManyToMany(mappedBy = "playedTournaments")
  List<Player> participatingPlayers;

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
