import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;



/**
 * Created by LaunchCode
 */
public class JobData {

    private static final String DATA_FILE = "src/main/resources/job_data.csv";
    private static boolean isDataLoaded = false;

    private static ArrayList<HashMap<String, String>> allJobs;

    /**
     * Fetch list of all values from loaded data,
     * without duplicates, for a given column.
     *
     * @param field The column to retrieve values from
     * @return List of all the values of the given field
     */
    public static ArrayList<String> findAll(String field) {

        // load data, if not already loaded
        loadData();

        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {
            String aValue = row.get(field);

            if (!values.contains(aValue)) {
                values.add(aValue);
            }
        }

        return values;
    }

    public static ArrayList<HashMap<String, String>> findAll() {

        // load data, if not already loaded
        loadData();

        return allJobs;


    }

    /**
     * Returns results of search the jobs data by key/value, using
     * inclusion of the search term.*
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".*
     * @param column   Column that should be searched.
     * @param value Value of teh field to search for
     * @return List of all jobs matching the criteria
     */
    public static ArrayList<HashMap<String, String>> findByColumnAndValue(String column, String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {

            String aValue = row.get(column);

            if (aValue.toLowerCase().contains(value.toLowerCase())) {
                jobs.add(row);
            }
        }

        return jobs;
    }



    //Class to remove duplicates from array lists to avoid removing using iteration
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    /**
     * Search all columns for the given term
     *
     * @param value The search term to look for
     * @return      List of all jobs with at least one field containing the value
     */

    public static ArrayList<HashMap<String, String>> findByValue(String value) {
        // load data, if not already loaded
        loadData();


        ArrayList<HashMap<String, String>> returnArrayList = new ArrayList<>();
       // Integer[] indexOfReturns = new Integer[allJobsCaseTest.size()];

        //Arrays.fill(indexOfReturns, 0);


        for (HashMap<String, String> jobs : allJobs) {

            for (HashMap.Entry<String, String> entries : jobs.entrySet()) {
                String entryValue = entries.getValue();
                if (entryValue.equalsIgnoreCase(value)) {
                    returnArrayList.add(jobs);
                    break;
                } else if (entryValue.toLowerCase().contains(value.toLowerCase())) {
                    returnArrayList.add(jobs);
                    break;
                }
            }
        }
        return removeDuplicates(returnArrayList);
    }





        /*int i = 0;

                for (HashMap<String, String> jobs: allJobsCaseTest) {

                    String[] values = new String[jobs.size()];
                    int v = 0;

                    for (String entries : jobs.values()) {
                        values[v] = entries;
                        v++;
                    }

                    for (int c = 0; c < values.length; c++) {
                        if(values[c].contains(value)){
                            indexOfReturns[i] = allJobsCaseTest.indexOf(jobs);
                            i++;
                            break;
                        }
                    }

                }

                System.out.print(allJobsCaseTest);

                for (int m = 0; m < indexOfReturns.length; m++) {
                    if(indexOfReturns[m] != null || indexOfReturns[m+1].equals(indexOfReturns[m+2])) {
                            returnArrayList.add(allJobs.get(indexOfReturns[m]));
                    } else {
                        break;
                    }
                }

        return removeDuplicates(returnArrayList);*/

            public static void loadData() {

        // Only load data once
        if (isDataLoaded) {
            return;
        }

        /*try {

            // Open the CSV file and set up pull out column header info and records
            Reader in = new FileReader(DATA_FILE);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            allJobsCaseTest = new ArrayList<>();


            // Put the records into a more friendly format

            for (CSVRecord record : records) {

                HashMap<String, String> newJobCaseTest = new HashMap<>();

                for (String headerLabelTest : headers) {
                    String recordStringTest = record.get(headerLabelTest);
                    recordStringTest = recordStringTest.toLowerCase();
                    newJobCaseTest.put(headerLabelTest, recordStringTest);
                }

                allJobsCaseTest.add(newJobCaseTest);
            }

                // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


        try {

            Reader in = new FileReader(DATA_FILE);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            allJobs = new ArrayList<>();

        for (CSVRecord record : records) {

            HashMap<String, String> newJob = new HashMap<>();

            for (String headerLabel : headers) {
                String recordString = record.get(headerLabel);
                newJob.put(headerLabel, recordString);
            }

            allJobs.add(newJob);
        }
            isDataLoaded = true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
    } catch (IOException e) {
            throw new RuntimeException(e);
        }

            }
}
