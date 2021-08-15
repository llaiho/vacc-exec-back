package VaccineBack;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
// @EnableScheduling
public class DataController {




//    @Value("${timer.interval}")
//    protected String timerInterval;








    @RequestMapping(value = "/testVaccinations", method = RequestMethod.GET)
    public List<JSONObject> testVaccinationsList() {
        List<JSONObject> testVaccinationsList = new VaccineReader().readVaccinations();
        return testVaccinationsList;

    }

    @RequestMapping(value = "/testOrders", method = RequestMethod.GET)
    public List<JSONObject> testOrdersList() {
        List<JSONObject> testOrdersList = new VaccineReader().readOrders();
        return testOrdersList;
    }

    @RequestMapping(value = "/testProcessed", method = RequestMethod.GET)
    public List<JSONObject> testProcessedList() {
        List<JSONObject> testProcessedList = new VaccineReader().processData();
        return testProcessedList;
    }

    @RequestMapping(value = "/testOrderedOrders", method = RequestMethod.GET)
    public List<JSONObject> testOrderedOrdersList() {
        List<JSONObject> testOrderedOrdersList = new VaccineReader().orderedOrders();
        return testOrderedOrdersList;
    }



/*
    @RequestMapping(value = "/testWhatToSend", method = RequestMethod.GET)
    public List<JSONObject> testWhatToSendList() {
        List<JSONObject> testWhatToSend = new VaccineReader().orderedOrders();
        return testWhatToSend;
    }
*/



/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<JSONObject> orderedOrdersList() {
        List<JSONObject> orderedOrdersList = new VaccineReader().orderedOrders();
        return orderedOrdersList;
    }
*/




}


