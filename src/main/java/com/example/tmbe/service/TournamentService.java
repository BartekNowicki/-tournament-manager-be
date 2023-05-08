package com.example.tmbe.service;

import com.example.tmbe.dataModel.Tournament;
import com.example.tmbe.exception.NoEntityFoundCustomException;
import com.example.tmbe.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
  @Autowired TournamentRepository tournamentRepository;

  public List<Tournament> getAllTournamentsOrderByIdAsc() {
    return tournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Optional<Tournament> getTournamentById(long id) {
    return tournamentRepository.findById(id);
  }

  public Tournament deleteTournamentById(long id) throws NoEntityFoundCustomException {
    Optional<Tournament> tournamentToDelete = tournamentRepository.findById(id);
    if (tournamentToDelete.isEmpty()) {
      throw new NoEntityFoundCustomException("No tournament with that id exists: " + id);
    }
    tournamentRepository.delete(tournamentToDelete.get());
    return tournamentToDelete.get();
  }

  public Tournament saveOrUpdateTournament(Tournament tournament) {
    Optional<Tournament> tournamentToUpdate = tournamentRepository.findById(tournament.getId());
    if (tournamentToUpdate.isEmpty()) {

      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("ATTEMPTING TO SAVE TOURNAMENT WITH DATES: ");
      System.out.println(tournament.getStartDate());
      System.out.println(tournament.getEndDate());
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

      return tournamentRepository.save(tournament);
    } else {
      Tournament t = tournamentToUpdate.get();
      t.setType(tournament.getType());
      t.setComment(tournament.getComment());
      t.setEndDate(tournament.getEndDate());
      t.setStartDate(tournament.getStartDate());
      t.setGroupSize(tournament.getGroupSize());
      return tournamentRepository.save(t);
    }
  }
}
