package com.vasilitate.vapp.sdk;

/**
 * Actions which the SMS service broadcasts
 */
interface VappActions {

    /**
     * Broadcast when the SMS sending progressed
     */
    String ACTION_SMS_PROGRESS = "com.vasilitate.vapp.sdk.ACTION_SMS_PROGRESS";


    /**
     * A message detailing an SMS send error
     */
    String EXTRA_ERROR_MESSAGE = "com.vasilitate.vapp.sdk.EXTRA_ERROR_MESSAGE";

    /**
     * The sending of the SMSs has completed.
     */
    String EXTRA_SMS_COMPLETED = "com.vasilitate.vapp.sdk.EXTRA_SMS_COMPLETED";

    /**
     * The sending of the SMSs is not supported on the current operator.
     */
    String EXTRA_SMS_PURCHASE_UNSUPPORTED = "com.vasilitate.vapp.sdk.EXTRA_SMS_PURCHASE_UNSUPPORTED";

    /**
     * A check on the server could not be completed due to no conection, please try again later.
     */
    String EXTRA_SMS_PURCHASE_NO_CONNECTION = "com.vasilitate.vapp.sdk.EXTRA_SMS_PURCHASE_NO_CONNECTION";


    /**
     * The sending of the SMSs has cancelled.
     */
    String EXTRA_SMS_CANCELLED = "com.vasilitate.vapp.sdk.EXTRA_SMS_CANCELLED";

    /**
     * The sent SMS count
     */
    String EXTRA_SMS_SENT_COUNT = "com.vasilitate.vapp.sdk.EXTRA_SMS_SENT_COUNT";

    /**
     * Percentage completion for the complete purchase.
     */
    String EXTRA_PROGRESS_PERCENTAGE = "com.vasilitate.vapp.sdk.EXTRA_PROGRESS_PERCENTAGE";

    /**
     * Product Id.
     */
    String EXTRA_PRODUCT_ID = "com.vasilitate.vapp.sdk.EXTRA_PRODUCT_ID";

    /**
     * Determines whether user is allowed to exit payment screen or not
     */
    String EXTRA_MODAL = "com.vasilitate.vapp.sdk.EXTRA_MODAL";

    /**
     * Shows a notification stating payment progress
     */
    String EXTRA_NOTIFICATION_INVOKED = "com.vasilitate.vapp.sdk.EXTRA_NOTIFICATION_INVOKED";
}