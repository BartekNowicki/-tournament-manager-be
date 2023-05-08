package com.example.tmbe.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "isChecked")
  private Boolean isChecked;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "strength")
  private int strength;

  @Column(name = "comment")
  private String comment;

  @ManyToMany
  @JoinTable(
      name = "player_tournament",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "tournament_id"))
  private Set<Tournament> playedTournaments;

  @Override
  public String toString() {
    return "Player{"
        + "id="
        + id
        + ", isChecked="
        + isChecked
        + ", firstName="
        + firstName
        + ", lastName="
        + lastName
        + ", strength="
        + strength
        + ", comment="
        + comment
        + '\''
        + ", players="
        + playedTournaments.size()
        + '}';
  }
}
