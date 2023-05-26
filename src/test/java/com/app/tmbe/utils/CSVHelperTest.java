package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Tournament;
import com.app.tmbe.enumConverter.TournamentTypeConverter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVHelperTest {

  String playersUploadTestDataUrl = "src/test/resources/playersUploadTestData.csv";
  String tournamentsUploadTestDataUrl = "src/test/resources/tournamentsUploadTestData.csv";
  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

  BufferedReader playersBufferedReader =
      new BufferedReader(new FileReader(playersUploadTestDataUrl));

  BufferedReader tournamentsBufferedReader =
      new BufferedReader(new FileReader(tournamentsUploadTestDataUrl));

  CSVParser playersCsvParser =
      new CSVParser(
          new FileReader(playersUploadTestDataUrl),
          CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

  CSVParser tournamentsCsvParser =
      new CSVParser(
          new FileReader(tournamentsUploadTestDataUrl),
          CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

  // required for readers
  CSVHelperTest() throws IOException {}

  @BeforeEach
  void setUp() {}

  @Test
  void readersAndFilesPresent() {
    assertNotNull(playersBufferedReader);
    assertNotNull(tournamentsBufferedReader);
  }

  @Test
  void hasCSVFormat() {
    assertEquals("csv", playersUploadTestDataUrl.substring(playersUploadTestDataUrl.length() - 3));
    assertEquals(
        "csv", tournamentsUploadTestDataUrl.substring(tournamentsUploadTestDataUrl.length() - 3));
  }

  @Test
  void givenCSVFile_whenRead_thenContentsAsExpected_csvToPlayers() throws IOException {

    List<Player> players = new ArrayList<>();

    Iterable<CSVRecord> csvRecords = playersCsvParser.getRecords();

    for (CSVRecord csvRecord : csvRecords) {
      Player player =
          new Player(
              Long.parseLong(csvRecord.get("id")),
              Boolean.parseBoolean(csvRecord.get("isChecked")),
              csvRecord.get("firstName"),
              csvRecord.get("lastName"),
              Integer.parseInt(csvRecord.get("strength")),
              csvRecord.get("comment"),
              null);

      players.add(player);
    }
    assertEquals(5, players.size());
    assertEquals("bubba1", players.get(1).getFirstName());
    assertEquals("smith1", players.get(1).getLastName());
  }

  @Test
  void givenCSVFile_whenRead_thenContentsAsExpected_csvToTournaments()
      throws IOException, ParseException {
    List<Tournament> tournaments = new ArrayList<>();

    Iterable<CSVRecord> csvRecords = tournamentsCsvParser.getRecords();

    TournamentTypeConverter tournamentTypeConverter = new TournamentTypeConverter();

    for (CSVRecord csvRecord : csvRecords) {

      Tournament tournament =
          new Tournament(
              Long.parseLong(csvRecord.get("id")),
              tournamentTypeConverter.convertToEntityAttribute(csvRecord.get("type")),
              df.parse(csvRecord.get("startDate")),
              df.parse(csvRecord.get("endDate")),
              Integer.parseInt(csvRecord.get("groupSize")),
              csvRecord.get("comment"),
              null);
      tournaments.add(tournament);

      tournaments.add(tournament);
    }
    assertEquals(10, tournaments.size());
    assertEquals(0, tournaments.get(1).getGroupSize());
    assertEquals("singles", tournaments.get(1).getType().getNaming());
  }
}
