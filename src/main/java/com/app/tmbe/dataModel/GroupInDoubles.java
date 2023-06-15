package com.app.tmbe.dataModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups_in_doubles")
public class GroupInDoubles {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  // Bidirectional @ManyToMany, two parents, no children, one owner (Team)
  @JsonBackReference
  @ManyToMany(mappedBy = "belongsToDoublesGroups")
  private Set<Team> members = new HashSet<>();

  // Bidirectional @OneToMany, two parents, no children, one owner (GroupInSingles)
  @ManyToOne
  @JoinColumn(name = "doubles_tournament_id")
  private DoublesTournament partOfDoublesTournament;

  @Override
  public String toString() {
    return "GroupInDoubles{"
        + "id="
        + id
        + ", members="
        + members
        + ", partOfDoublesTournament="
        + partOfDoublesTournament.getId()
        + '}';
  }
}
