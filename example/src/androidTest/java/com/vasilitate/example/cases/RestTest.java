package com.vasilitate.example.cases;

import android.test.AndroidTestCase;

import com.vasilitate.vapp.sdk.exceptions.VappApiException;
import com.vasilitate.vapp.sdk.network.VappRestClient;
import com.vasilitate.vapp.sdk.network.request.PostLogsBody;
import com.vasilitate.vapp.sdk.network.response.GetHniStatusResponse;
import com.vasilitate.vapp.sdk.network.response.GetReceivedStatusResponse;
import com.vasilitate.vapp.sdk.network.response.PostLogsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.vasilitate.vapp.sdk.network.request.PostLogsBody.LogEntry;
import static com.vasilitate.vapp.sdk.network.response.GetHniStatusResponse.HNI_STATUS_WHITELISTED;

public class RestTest extends AndroidTestCase {

    private static final String ENDPOINT = "http://api.mobileyes.co.uk";
    private static final String VALID_SDK_KEY = "BG8R4X2PCXYCHRCRJTK6";
    private static final String VALID_MCC = "234";
    private static final String VALID_MNC = "30";

    private static final String VALID_CLI = "44777563222";
    private static final String VALID_DDI = "447700379500";
    private static final String VALID_RANDOM_2 = "867819020287608";
    private static final String VALID_RANDOM_3 = "70f7f9e";

    private static final String VALID_MESSAGE= "bestbottleshoot in app purchase 1/15 867820287608 70f7f9e telstra";
    private static final String VALID_CLI_DETAIL = "Australia Fixed Mobile Phones";

    private VappRestClient vappRestClient;

    @Override public void setUp() throws Exception {
        super.setUp();
        vappRestClient = new VappRestClient(ENDPOINT, VALID_SDK_KEY, true);
    }

    public void testInvalidSdkKey() throws IOException {
        vappRestClient = new VappRestClient(ENDPOINT, null, true);
        GetHniStatusResponse response = vappRestClient.getHniStatus(VALID_MCC, VALID_MCC);
        assertNotNull(response);
        assertEquals("error", response.getStatus());
        assertNotNull(response.getError());

        vappRestClient = new VappRestClient(ENDPOINT, "invalid", true);
        response = vappRestClient.getHniStatus(VALID_MCC, VALID_MCC);
        assertNotNull(response);
        assertEquals("error", response.getStatus());
        assertNotNull(response.getError());
    }

    public void testInvalidGetHniStatusParams() throws IOException {
        checkInvalidHniStatusCall(null, VALID_MNC);
        checkInvalidHniStatusCall(VALID_MCC, null);
    }

    private void checkInvalidHniStatusCall(String mcc, String mnc) throws IOException {
        try {
            vappRestClient.getHniStatus(mcc, mnc);
            fail("Should reject invalid parameter");
        }
        catch (VappApiException ignored) {
        }
    }

    public void testGetHniStatus() throws IOException {
        GetHniStatusResponse response = vappRestClient.getHniStatus(VALID_MCC, VALID_MNC);
        assertNotNull(response);
        assertEquals(HNI_STATUS_WHITELISTED, response.getStatus());
    }

    public void testInvalidGetReceivedStatus() throws IOException {
        checkInvalidReceivedStatusCall(VALID_DDI, VALID_RANDOM_2, VALID_RANDOM_3);
        checkInvalidReceivedStatusCall("", VALID_RANDOM_2, VALID_RANDOM_3);
        checkInvalidReceivedStatusCall(VALID_DDI, null, VALID_RANDOM_3);
        checkInvalidReceivedStatusCall(VALID_DDI, VALID_RANDOM_2, "");
    }

    public void testGetReceivedStatus() throws IOException {
        // invalid '+' symbol included in numbers should be handled
        GetReceivedStatusResponse receivedStatus = vappRestClient.getReceivedStatus(VALID_MCC, VALID_MNC, "+" +
                VALID_DDI,
                VALID_RANDOM_2,
                VALID_RANDOM_3,
                ""); //TODO: set a valid imei

        assertNotNull(receivedStatus);
        assertEquals(GetReceivedStatusResponse.RECEIVED_STATUS_BLACKLISTED, receivedStatus.getReceived());
    }

    private void checkInvalidReceivedStatusCall(String ddi, String rand2, String rand3) throws IOException {
        try {
            vappRestClient.getReceivedStatus(VALID_MCC, VALID_MNC, ddi, rand2, rand3, ""); //TODO: set a valid imei
            fail("Should reject invalid parameter");
        }
        catch (VappApiException ignored) {
        }
    }

    public void testInvalidPostLogs() {
        checkInvalidPostLogsCall(null, VALID_DDI);
        checkInvalidPostLogsCall(VALID_MESSAGE, "");
    }

    public void testPostLogs() throws IOException {
        LogEntry entry = new LogEntry(VALID_MESSAGE, VALID_DDI);
        List<LogEntry> values = new ArrayList<>();
        values.add(entry);

        // 200 if no error is thrown
        PostLogsResponse response = vappRestClient.postLog(new PostLogsBody(values, "+" + VALID_CLI, VALID_CLI_DETAIL));

        assertNotNull(response);
        assertNotNull(response.getStatus());
    }

    private void checkInvalidPostLogsCall(String message, String ddi) {
        try {
            new LogEntry(message, ddi);
            fail("Should reject invalid parameter");
        }
        catch (VappApiException ignored) {
        }
    }

}