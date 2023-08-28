package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.LabelManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Command line interface
 * @author Emina Peljto
 */
public class App {
    private static final Option getRecord = new Option("gr","get-record",true,"Outputs a record");
    private static final Option getRecords = new Option("grs","get-records",false,"Outputs all records");
    private static final Option addRecord = new Option("ar","add-record",false,"Adds a new record");
    private static final Option updateRecord = new Option("ur","update-record",true,"Updates a record");
    private static final Option deleteRecord = new Option("dr","delete-record",true,"Deletes a record");
    private static final Option getArtist = new Option("ga","get-artist",true,"Outputs an artist");
    private static final Option getArtists = new Option("gas","get-artists",false,"Outpust all artists");
    private static final Option addArtist = new Option("aa","add-artist",false,"Adds a new artist");
    private static final Option updateArtist = new Option("ua","update-artist",true,"Updates an artist");
    private static final Option deleteArtist = new Option("da","delete-artist",true,"Deletes an artist");
    private static final Option getLabel = new Option("gl","get-label",true,"Outputs a label");
    private static final Option getLabels = new Option("gls","get-labels",false,"Outputs all labels");
    private static final Option addLabel = new Option("al","add-label",false,"Adds a new label");
    private static final Option updateLabel = new Option("ul","update-label",true,"Updates a label");
    private static final Option deleteLabel = new Option("dl","delete-label",true,"Deletes a label");

    private static final RecordManager recordManager = new RecordManager();
    private static final ArtistManager artistManager = new ArtistManager();
    private static final LabelManager labelManager = new LabelManager();
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(addOptions(), args);

            if (commandLine.hasOption("get-records")) {
                showAllRecords();
            } else if (commandLine.hasOption("get-record")) {
                int recordId = Integer.parseInt(commandLine.getOptionValue("get-record"));
                showRecord(recordManager.getById(recordId));
            } else if (commandLine.hasOption("add-record")) {
                Record record = inputRecordDetails(null);
                recordManager.add(record);
                System.out.println("Added a new record successfully.");
            } else if (commandLine.hasOption("update-record")) {
                int recordId = Integer.parseInt(commandLine.getOptionValue("update-record"));
                Record record = inputRecordDetails(recordManager.getById(recordId));
                recordManager.update(record);
                System.out.println("Updated a record successfully.");
            } else if (commandLine.hasOption("delete-record")) {
                int recordId = Integer.parseInt(commandLine.getOptionValue("delete-record"));
                recordManager.delete(recordId);
                System.out.println("Deleted a record successfully.");
            } else if (commandLine.hasOption("get-artists")) {
                showAllArtists();
            } else if (commandLine.hasOption("get-artist")) {
                int artistId = Integer.parseInt(commandLine.getOptionValue("get-artist"));
                showArtist(artistManager.getById(artistId));
            } else if (commandLine.hasOption("add-artist")) {
                Artist artist = inputArtistDetails(null);
                artistManager.add(artist);
                System.out.println("Added a new artist successfully.");
            } else if (commandLine.hasOption("update-artist")) {
                int artistId = Integer.parseInt(commandLine.getOptionValue("update-artist"));
                Artist artist = inputArtistDetails(artistManager.getById(artistId));
                artistManager.update(artist);
                System.out.println("Updated an artist successfully.");
            } else if (commandLine.hasOption("delete-artist")) {
                int artistId = Integer.parseInt(commandLine.getOptionValue("delete-artist"));
                artistManager.delete(artistId);
                System.out.println("Deleted an artist successfully.");
            } else if (commandLine.hasOption("get-labels")) {
                showAllLabels();
            } else if (commandLine.hasOption("get-label")) {
                int labelId = Integer.parseInt(commandLine.getOptionValue("get-label"));
                showLabel(labelManager.getById(labelId));
            } else if (commandLine.hasOption("add-label")) {
                Label label = inputLabelDetails(null);
                labelManager.add(label);
                System.out.println("Added a new label successfully.");
            } else if (commandLine.hasOption("update-label")) {
                int labelId = Integer.parseInt(commandLine.getOptionValue("update-label"));
                Label label = inputLabelDetails(labelManager.getById(labelId));
                labelManager.update(label);
                System.out.println("Updated a label successfully.");
            } else if (commandLine.hasOption("delete-label")) {
                int labelId = Integer.parseInt(commandLine.getOptionValue("delete-label"));
                labelManager.delete(labelId);
                System.out.println("Deleted a label successfully.");
            } else {
                printHelp();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 100, "java -jar RecordStore-cli-jar-with-dependencies.jar [option] [arg]");
        helpFormatter.printOptions(printWriter, 100, addOptions(), 2, 5);
        printWriter.close();
    }

    private static Options addOptions() {
        return new Options().addOption(getRecord).addOption(getRecords).addOption(addRecord).addOption(updateRecord)
                .addOption(deleteRecord).addOption(getArtist).addOption(getArtists).addOption(addArtist)
                .addOption(updateArtist).addOption(deleteArtist).addOption(getLabel).addOption(getLabels)
                .addOption(addLabel).addOption(updateLabel).addOption(deleteLabel);
    }

    private static Label inputLabelDetails(Label defLabel)
    {
        Label label = new Label();
        Scanner in = new Scanner(System.in);

        if(defLabel!=null)
        {
            label.setId(defLabel.getId());
            System.out.println("(Old label details: "+defLabel.getName()+", "+defLabel.getCountry()+").");
        }

        try
        {
            System.out.println("Label name: ");
            label.setName(in.nextLine());
            System.out.println("Label country: ");
            label.setCountry(in.nextLine());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + "\nTry again.");
            inputLabelDetails(defLabel);
        }
        return label;
    }

    private static Artist inputArtistDetails(Artist defArtist)
    {
        Artist artist = new Artist();
        Scanner in = new Scanner(System.in);

        if(defArtist!=null)
        {
            artist.setId(defArtist.getId());
            System.out.println("(Old artist details: "+defArtist.getName()+", "+
                    defArtist.getLabel().getName()+", "+defArtist.getCountry()+", "+defArtist.getType()+").");
        }

        try
        {
            System.out.println("Artist name: ");
            artist.setName(in.nextLine());
            showAllLabels();
            System.out.println("Artist label id: ");
            artist.setLabel(labelManager.getById(in.nextInt()));
            System.out.println("Artist country: ");
            artist.setCountry(in.nextLine());
            System.out.println("Artist type: ");
            artist.setType(in.nextLine());
            artistManager.validateArtist(artist);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + "\nTry again.");
            inputArtistDetails(defArtist);
        }
        return artist;
    }

    private static Record inputRecordDetails(Record defRecord)
    {
        Record record = new Record.Builder(0,"").build();
        Scanner in = new Scanner(System.in);

        if(defRecord!=null)
        {
            record.setId(defRecord.getId());
            System.out.println("(Old record details: "+defRecord.getName()+", "+
                    defRecord.getArtist().getName()+", "+defRecord.getRelease_date()+", "+defRecord.getGenre()+", "+
                    defRecord.getCountry()+")");
        }

        try
        {
            System.out.println("Record name: ");
            record.setName(in.nextLine());
            showAllArtists();
            System.out.println("Record artist id: ");
            record.setArtist(artistManager.getById(in.nextInt()));
            System.out.println("Record release date (yyyy-mm-dd): ");
            record.setRelease_date(Date.valueOf(LocalDate.parse(in.next())));
            System.out.println("Record genre: ");
            record.setGenre(in.nextLine());
            System.out.println("Record country: ");
            record.setCountry(in.nextLine());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + "\nTry again.");
            inputRecordDetails(defRecord);
        }
        return record;
    }

    private static void showAllLabels() throws RecordStoreException
    {
        System.out.println("All labels:");
        labelManager.getAll().forEach(label -> {
            System.out.println(" - " + label.getName() + " (id: " + label.getId() + ")");
        });
    }

    private static void showLabel(Label label)
    {
        System.out.println("Label details:");
        System.out.println(" - id: " + label.getId());
        System.out.println(" - name: " + label.getName());
        System.out.println(" - country: " + label.getCountry());
    }

    private static void showAllArtists() throws RecordStoreException
    {
        System.out.println("All artists:");
        artistManager.getAll().forEach(artist -> {
            System.out.println(" - " + artist.getName() + " (id: " + artist.getId() + ")");
        });
    }

    private static void showArtist(Artist artist)
    {
        System.out.println("Artist details:");
        System.out.println(" - id: " + artist.getId());
        System.out.println(" - name: " + artist.getName());
        System.out.println(" - label: " + artist.getLabel().getName());
        System.out.println(" - country: " + artist.getCountry());
        System.out.println(" - type: " + artist.getType());
    }

    private static void showAllRecords() throws RecordStoreException
    {
        System.out.println("All records:");
        recordManager.getAll().forEach(record -> {
            System.out.println(" - " + record.getName() + " (id: " + record.getId() + ")");
        });
    }

    private static void showRecord(Record record)
    {
        System.out.println("Record details:");
        System.out.println(" - id: " + record.getId());
        System.out.println(" - name: " + record.getName());
        System.out.println(" - artist: " + record.getArtist().getName());
        System.out.println(" - release date: " + record.getRelease_date());
        System.out.println(" - genre: " + record.getGenre());
        System.out.println(" - country: " + record.getCountry());
    }
}
