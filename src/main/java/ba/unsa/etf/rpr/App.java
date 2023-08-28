package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Label;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import org.apache.commons.cli.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static void main(String[] args) throws RecordStoreException {

    }
}
