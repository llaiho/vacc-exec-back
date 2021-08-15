package VaccineBack;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public class VaccineReader {

    private static Logger LOG = Logger.getLogger("ErrorCatchLogging");

    private String sfs = System.getProperty("file.separator");

    private String filepath = System.getProperty("user.dir") + sfs
            + "src" + sfs
            + "main" + sfs
            + "resources" + sfs;


    private String originalFiles = filepath + "original" + System.getProperty("file.separator");
    private String modifiedFiles = filepath + "modified" + System.getProperty("file.separator");

    private String orders = "orders" + System.getProperty("file.separator");
    private String vaccinations = "vaccinations" + System.getProperty("file.separator");


    private File[] originalOrderFiles = directoryReader(originalFiles + orders);
    private File[] originalVaccinationsFiles = directoryReader(originalFiles + vaccinations);

    public String startTime = "2021-01-02T13:26:29.670392Z";
    public String endTime = "2021-08-01T00:00:00.000";





    /**
     * directoryReader
     * Reads files in given filepath and returns array of files
     */

    private File[] directoryReader(String thisDirectory) {
        File[] listOfFiles = new File[0];
        File dir = new File(thisDirectory);
        if (dir.isDirectory()) {
            listOfFiles = dir.listFiles();
        }
        return listOfFiles;
    }



    /**
     * readVaccinations combines all vaccinations to single list
     * @return combined list of orders
     */

    public List<JSONObject> readVaccinations() {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        List<JSONObject> vaccinationList = new ArrayList<JSONObject>() {};

        if (originalVaccinationsFiles.length <= 1) {
            vaccinationList = readLineByLine(originalVaccinationsFiles[0]);
        } else {
            for (int i = 0; i < originalVaccinationsFiles.length; i++) {
                thisList = readLineByLine(originalVaccinationsFiles[i]);

                for (int j = 0; j < thisList.size(); j++) {
                    JSONObject thisObject = thisList.get(j);
                    vaccinationList.add(thisObject);
                }
            }
        }
        return vaccinationList;
    }


    /**
     * readOrder combines all orders to single list, old versions left for reference
     * @return combined list of orders
     */

    public List<JSONObject> readOrders() {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        List<JSONObject> orderList = new ArrayList<JSONObject>() {};

        for (int i = 0; i < originalOrderFiles.length; i++) {
            thisList = readLineByLine(originalOrderFiles[i]);

            for (int j = 0; j < thisList.size(); j++) {
                JSONObject thisObject = thisList.get(j);
                orderList.add(thisObject);
            }
        }
        return orderList;
    }

/*
    public List<JSONObject> readOrders() {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        thisList = readOrders(startTime, endTime, true);
        return thisList;
    }

    public List<JSONObject> readOrders(String start, String end, boolean readAll) {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        List<JSONObject> orderList = new ArrayList<JSONObject>() {};

        for (int i = 0; i < originalOrderFiles.length; i++) {
            thisList = readLineByLine(originalOrderFiles[i]);

            for (int j = 0; j < thisList.size(); j++) {
                JSONObject thisObject = thisList.get(j);
                String arrivedTime = (String) thisObject.get("arrived");

                if (readAll == false) {
                    if (validTimeFrame(start, end, arrivedTime)) {
                        orderList.add(thisObject);
                    }
                } else {
                    orderList.add(thisObject);
                }
            }
        }
        return orderList;
    }
*/


    /**
     * Cutting string timestamp to make LocalDateTime.parse work
     * @return shortened string timestamp
     */



    public String modifyTimeStamp(String arrived) {
        char[] chars = new char[23];
        for (int i = 0; i < 23; i++) {
            chars[i] = arrived.charAt(i);
        }
        String newArrived = String.valueOf(chars);
        return newArrived;
    }


    /**
     * DateTime checkers
     */

/*    private boolean validTimeFrame(String start, String end, String thisJSONArrived) {
        LocalDateTime dateTimeStart = LocalDateTime.parse(modifyTimeStamp(start));
        LocalDateTime dateTimeEnd = LocalDateTime.parse(modifyTimeStamp(end));
        LocalDateTime dateTimeArrived = LocalDateTime.parse(modifyTimeStamp(thisJSONArrived));

        if (dateTimeStart.isBefore(dateTimeArrived) && dateTimeEnd.isAfter(dateTimeArrived)) {
            return true;
        }
        return false;
    }
*/
    public boolean validTimeFrame(String start, String end, String thisJSONArrived) {
        if ((earlierThan(start, thisJSONArrived)) && (laterThan(end, thisJSONArrived))) {
            return true;
        }
        return false;
    }

    public boolean laterThan(String comparingThis, String toThis) {
        LocalDateTime dateTimeComparingThis = LocalDateTime.parse(modifyTimeStamp(comparingThis));
        LocalDateTime dateTimeToThis = LocalDateTime.parse(modifyTimeStamp(toThis));

        if (dateTimeComparingThis.isAfter(dateTimeToThis)) {
            return true;
        }
        return false;
    }

    public boolean earlierThan(String comparingThis, String toThis) {
        LocalDateTime dateTimeComparingThis = LocalDateTime.parse(modifyTimeStamp(comparingThis));
        LocalDateTime dateTimeToThis = LocalDateTime.parse(modifyTimeStamp(toThis));

        if (dateTimeComparingThis.isBefore(dateTimeToThis)) {
            return true;
        }
        return false;
    }





    /**
     * Sorting by date, from TutorialsPoint
     * https://www.tutorialspoint.com/how-can-we-sort-a-jsonarray-in-java
     */


    public List<JSONObject> orderedOrders() {
        List<JSONObject> orders = processData();
        List<JSONObject> sortedOrders = new ArrayList<JSONObject>();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < orders.size(); i++) {
            jsonValues.add(orders.get(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            private static final String KEY_NAME = "arrived";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                } catch (JSONException e) {
                }

                return valA.compareTo(valB);
            }
        });

        for (int i = 0; i < orders.size(); i++) {
            sortedOrders.add(jsonValues.get(i));
        }
        return sortedOrders;
    }



    /**
     *  processData adds usedates to orders
     * @return list of modifies orders
     */

    public List<JSONObject> processData() {
        List<JSONObject> orders = readOrders();
        List<JSONObject> vaccinations = readVaccinations();

        for (int i = 0; i < orders.size(); i++) {
            String bottle = null;
            bottle = (String) orders.get(i).get("id");

            List<String> usedOnDateStrings = new ArrayList<>();
            String usedBottle = null;
            for (int j = 0; j < vaccinations.size(); j++) {

                usedBottle = (String) vaccinations.get(j).get("sourceBottle");
                if (usedBottle.equals(bottle)) {
                    String found = (String) vaccinations.get(j).get("vaccinationDate");
                    usedOnDateStrings.add(found);
                }
            }
            orders.get(i).put("usedOnDays", usedOnDateStrings);
        }
        return orders;


    }


    /**
     *      Filereader, from JournalDev
     *      https://www.journaldev.com/709/java-read-file-line-by-line
     */


    public List<JSONObject> readLineByLine(File fileToRead) {
        JSONParser parser = new JSONParser();
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};

        BufferedReader br = null;
        try {
            System.out.println(fileToRead.getAbsolutePath());
            File file = new File("" + fileToRead);
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = (JSONObject) parser.parse(line);
                thisList.add(json);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return thisList;
    }


/*
    public List<JSONObject> whatToSend() {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        thisList = whatToSend(startTime, endTime);
        return thisList;

    }


    public boolean dayEquals(String compareThis, Integer toThis) {
        boolean isEqual = false;
        Integer d1 = (LocalDateTime.parse(modifyTimeStamp(compareThis))).getDayOfMonth();
     //   Integer d2 = (LocalDateTime.parse(modifyTimeStamp(toThis))).getDayOfMonth();
        if (d1.equals(toThis)) {
            return true;
        }
        return isEqual;
    }

    public boolean monthEquals(String compareThis, String toThis) {
        boolean isEqual = false;
        Month d1 = (LocalDateTime.parse(modifyTimeStamp(compareThis))).getMonth();
        Month d2 = (LocalDateTime.parse(modifyTimeStamp(toThis))).getMonth();
        if (d1.equals(d2)) {
            return true;
        }
        return isEqual;
    }


    public List<JSONObject> whatToSend(String startTime, String endTime) {
        List<JSONObject> thisList = new ArrayList<JSONObject>() {};
        List<JSONObject> orders = orderedOrders();

        Integer nowYear = LocalDateTime.parse(startTime).getYear();
        Month nowMonth = LocalDateTime.parse(startTime).getMonth();
        Integer nowDay = LocalDateTime.parse(startTime).getDayOfMonth();

        for (int i = 0; i < orders.size(); i++) {
            String orderDate = modifyTimeStamp((String) orders.get(i).get("arrived"));
            if (LocalDateTime.parse(orderDate).getMonth().equals(nowMonth)) {
                if (dayEquals(orderDate, nowDay)) {
                    if (thisList.contains(orders.get(i).get("vaccine"))) {
                        System.out.println("vaccine: " + orders.get(i).get("vaccine"));
                        (orders.get(i).get("vaccine")) + 1;
                    }
                }
            }
        }








        return thisList;
    }
*/

}

