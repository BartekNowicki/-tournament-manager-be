package com.app.tmbe.controller;

import com.app.tmbe.dataModel.SinglesTournament;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.enumConverter.TournamentType;
import com.app.tmbe.repository.PlayerRepository;
import com.app.tmbe.repository.SinglesTournamentRepository;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private PlayerRepository playerRepository;
  @Autowired private SinglesTournamentRepository singlesTournamentRepository;

  Player player1 = new Player(1L, true, "Joe", "Doe", 10, "Joe Doe is a great player", Set.of(), Set.of());

  Player player2 =
      new Player(2L, true, "Jack", "Schmoe", 9, "Jack Schmoe is a puny player", Set.of(), Set.of());

  Player player3 = new Player(3L, true, "Moe", "Broe", 9, "Moe cannot play", new HashSet<>(), new HashSet<>());


  public DataControllerTest() throws JSONException {}

  JSONObject getAnotherJoe() throws JSONException {
    return new JSONObject(
        "{\"id\":\"3\",\"isChecked\":\"false\",\"firstName\":\"anotherJoe\",\"lastName\":\"anotherJoe\",\"strength\":\"0\",\"comment\":\"Here goes another Joe\"}");
  }

  JSONObject getSameJoeChangeName() throws JSONException {
    return new JSONObject(
        "{\"id\":\"1\",\"isChecked\":\"false\",\"firstName\":\"sameJoeChangedName\",\"lastName\":\"sameJoeChangedName\",\"strength\":\"0\",\"comment\":\"Here goes another Joe\"}");
  }

  JSONObject getPlayerWithTournaments() throws JSONException {
    return new JSONObject(
        "{\"id\":\"1\",\"isChecked\":\"false\",\"firstName\":\"playerWithTournament\",\"lastName\":\"playerWithTournamentLastName\",\"strength\":\"0\",\"comment\":\"Here goes playerWithTournament\",\"playedSinglesTournaments\":[\n"
            + "   {   \"id\": 3,\n"
            + "      \"type\": \"DOUBLES\",\n"
            + "      \"startDate\": \"2000-12-10T23:00:00.000+00:00\",\n"
            + "      \"endDate\": \"2000-12-20T23:00:00.000+00:00\",\n"
            + "      \"groupSize\": 1,\n"
            + "      \"comment\": \"noComment111\",\n"
            + "      \"participatingPlayers\": []}]}");
  }

  JSONObject getNewTournament() throws JSONException {
    return new JSONObject(
        "{\n"
            + "    \"id\": 666,\n"
            + "    \"type\": \"SINGLES\",\n"
            + "    \"startDate\": \"2000-12-11T23:00:00.000+00:00\",\n"
            + "    \"endDate\": \"2000-12-21T23:00:00.000+00:00\",\n"
            + "    \"groupSize\": 6,\n"
            + "    \"comment\": \"noComment666\",\n"
            + "    \"participatingPlayers\": [\n"
            + "        {\n"
            + "            \"id\": 1,\n"
            + "            \"isChecked\": false,\n"
            + "            \"firstName\": \"bubba2changed\",\n"
            + "            \"lastName\": \"smith2changed\",\n"
            + "            \"strength\": 4,\n"
            + "            \"comment\": \"noComment20\",\n"
            + "            \"playedSinglesTournaments\": []\n"
            + "        }\n"
            + "    ]\n"
            + "}");
  }

  SinglesTournament singlesTournament1 =
      new SinglesTournament(
          1L,
          TournamentType.SINGLES,
          new Date(),
          new Date(),
          3,
          "singlesTournament1 was awesome",
          Set.of(player1, player2));
  SinglesTournament singlesTournament2 =
      new SinglesTournament(
          2L,
          TournamentType.DOUBLES,
          new Date(),
          new Date(),
          6,
          "singlesTournament2 was also awesome",
          Set.of(player1));

  @BeforeEach
  public void setUp() {
    playerRepository.save(player1);
    playerRepository.save(player2);
    playerRepository.save(player3);
    singlesTournamentRepository.save(singlesTournament1);
    singlesTournamentRepository.save(singlesTournament2);
  }

  @Test
  public void getAllPlayersOrderByIdAsc_success() throws Exception {
    this.mockMvc
        .perform(get("/api/data/players"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(equalTo(1)))
        .andExpect(jsonPath("$.[1].id").value(equalTo(2)))
        .andExpect(jsonPath("$.[0].firstName").value(equalTo("Joe")))
        .andExpect(jsonPath("$.[1].firstName").value(equalTo("Jack")));
  }

  @Test
  void getPlayer_success() throws Exception {
    this.mockMvc
        .perform(get("/api/data/players/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.firstName").value(equalTo("Joe")));
  }

  //@Test not deterministic
  void getAllTournamentsOrderByIdAsc_success() throws Exception {
    this.mockMvc
        .perform(get("/api/data/tournaments"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(equalTo(2)))
        .andExpect(jsonPath("$.[1].id").value(equalTo(3)))
        .andExpect(jsonPath("$.[1].type").value(equalTo(TournamentType.DOUBLES.name())))
        .andExpect(jsonPath("$.[0].type").value(equalTo(TournamentType.DOUBLES.name())));
  }

  @Test
  void getTournament_success() throws Exception {
    this.mockMvc
        .perform(get("/api/data/tournaments/singles/2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.id").value(equalTo(2)))
        .andExpect(jsonPath("$.type").value(equalTo(TournamentType.DOUBLES.name())));
  }

  @Test
  void getTournament_failure() throws Exception {
    this.mockMvc
        .perform(get("/api/data/tournaments/singles/11"))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  void deletePlayer_success() throws Exception {
    this.mockMvc
        .perform(delete("/api/data/players/3"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.id").value(equalTo(3)))
        .andExpect(jsonPath("$.firstName").value(equalTo("Moe")))
        .andDo(
            result ->
                this.mockMvc
                    .perform(get("/api/data/players/3"))
                    .andDo(print())
                    .andExpect(status().isNoContent()));
  }

  @Test
  void deletePlayer_failure() throws Exception {
    this.mockMvc
        .perform(delete("/api/data/players/3"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteTournament_success() throws Exception {
    this.mockMvc
        .perform(delete("/api/data/tournaments/singles/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.type").value(equalTo(TournamentType.SINGLES.name())))
        .andDo(
            result ->
                this.mockMvc
                    .perform(get("/api/data/tournaments/singles/1"))
                    .andDo(print())
                    .andExpect(status().isNoContent()));
  }

  @Test
  void deleteTournament_failure() throws Exception {
    this.mockMvc
        .perform(delete("/api/data/tournaments/singles/44"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void saveOrUpdatePlayer_saveNew_success() throws Exception {
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/api/data/players")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(this.getAnotherJoe().toString());

    this.mockMvc
        .perform(builder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.firstName").value(equalTo("anotherJoe")));
  }

  //@Test
  void saveOrUpdatePlayer_saveNew_ManyToManyPropagation_success() throws Exception {
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/api/data/players")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(this.getPlayerWithTournaments().toString());

    this.mockMvc
        .perform(builder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]").doesNotExist())
        .andExpect(jsonPath("$.playedSinglesTournaments.[0].id").value(equalTo(3)))
        .andExpect(jsonPath("$.playedSinglesTournaments.[0].type").value(equalTo("DOUBLES")))
        .andDo(
            result ->
                this.mockMvc
                    .perform(get("/api/data/tournaments/doubles/3"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(
                        jsonPath("$.participatingPlayers.[0].firstName")
                            .value(equalTo("playerWithTournament"))));
  }

  @Test
  void saveOrUpdatePlayer_UpdateOld_success() throws Exception {
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/api/data/players")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(this.getSameJoeChangeName().toString());

    this.mockMvc
        .perform(builder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.firstName").value(equalTo("sameJoeChangedName")));
  }

  @Test
  void saveOrUpdatePlayer_UpdateOld_failure() throws Exception {
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/api/data/players")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(this.getSameJoeChangeName().toString());

    this.mockMvc
        .perform(builder)
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.firstName").value(not(equalTo("Joe"))));
  }
}
