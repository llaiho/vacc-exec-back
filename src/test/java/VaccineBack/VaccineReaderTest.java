package VaccineBack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VaccineReaderTest {

    private static final String DATE_1 = "2021-01-11T08:59:28.642790Z";

    private static final String DATE_2 = "2021-01-11T08:59:28.642790Z";
    private static final String DATE_3 = "2021-01-10T08:59:28.642790Z";

    private static final String DATE_4 = "2021-01-11";
    private static final String DATE_5 = "2021-01-12";

    private static final String TIME_1 = "2021-01-01T00:00:00.000";
    private static final String TIME_2 = "2021-02-01T00:00:00.000";
    private static final String TIME_3 = "2021-03-01T00:00:00.000";
    private static final String TIME_4 = "2021-01-02T00:00:00.000";

    private static final String TIME_5 = "2021-01-01T00:00:01.000";
    private static final String TIME_6 = "2021-01-01T00:00:00.000";

    VaccineReader vaccineReader;

    @Before
    public void setUp() throws Exception {
        vaccineReader = new VaccineReader();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void laterThanTest_1() {
        Assert.assertTrue(vaccineReader.laterThan(TIME_2, TIME_1));

    }

    @Test
    public void laterThanTest_2() {
        Assert.assertTrue(vaccineReader.laterThan(TIME_3, TIME_4));

    }

    @Test
    public void earlierThanTest_1() {
        Assert.assertFalse(vaccineReader.earlierThan(TIME_4, TIME_1));
    }


    @Test
    public void earlierThanTest_2() {
        Assert.assertTrue(vaccineReader.earlierThan(TIME_1, TIME_2));
    }

    @Test
    public void earlierThanTest_3() {
        Assert.assertTrue(vaccineReader.earlierThan(TIME_6, TIME_5));
    }



    @Test
    public void between_1() {
        Assert.assertTrue(vaccineReader.validTimeFrame(TIME_6, TIME_4, TIME_5));
    }



    @Test
    public void modifyDate_1() {
        Assert.assertEquals("2021-01-11T08:59:28.642", vaccineReader.modifyTimeStamp(DATE_1));
    }


    @Test
    public void earlierThanTest_4() {
        Assert.assertTrue(vaccineReader.earlierThan(DATE_3, DATE_2));
    }



/*
    @Test
    public void earlierThanTest_5() {
        System.out.println(vaccineReader.modifyTimeStamp(DATE_5));
        System.out.println(vaccineReader.modifyTimeStamp(DATE_4));
        Assert.assertFalse(vaccineReader.earlierThan(DATE_5, DATE_4));
    }
*/





}
