package com.elementaryengineers.fwc.db;

/**
 * Created by sarahakk on 4/28/16.
 */
public class DatabasePopulator {

    FWCDatabaseConnection dbConn;

    public DatabasePopulator(FWCDatabaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    public void populateDatabase() {
        // Fill the database up with dummy data, accounts, etc, for testing
    }
}