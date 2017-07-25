package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExpressInvoiceProperties {

    @JsonProperty("NewAddress")
    private final String NewAddress = "1";
    @JsonProperty("PayerType")
    private final String PayerType = "Sender";
    @JsonProperty("PaymentMethod")
    private final String PaymentMethod = "Cash";
    @JsonProperty("CargoType")
    private final String CargoType = "Cargo";
    @JsonProperty("VolumeGeneral")
    private final String VolumeGeneral = "0.0004";
    @JsonProperty("Weight")
    private final String Weight = "0,1";
    @JsonProperty("ServiceType")
    private final String ServiceType = "WarehouseWarehouse";
    @JsonProperty("SeatsAmount")
    private final String SeatsAmount = "1";
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Cost")
    private String Cost;
    @JsonProperty("CitySender")
    private final String CitySender = "db5c88d0-391c-11dd-90d9-001a92567626";
    @JsonProperty("Sender")
    private String Sender = "98a6581b-5755-11e6-a54a-005056801333";
//    private String Sender = "a25e6e4d-9b58-11e6-a54a-005056801333";
    @JsonProperty("SenderAddress")
    private final String SenderAddress = "5a39e58e-e1c2-11e3-8c4a-0050568002cf";
    @JsonProperty("ContactSender")
    private String ContactSender = "9cbea1e6-7057-11e7-8ba8-005056881c6b";
    @JsonProperty("SendersPhone")
    private final String SendersPhone = "380957031465";
    @JsonProperty("RecipientCityName")
    private String RecipientCityName;
    @JsonProperty("RecipientArea")
    private final String RecipientArea = "";
    @JsonProperty("RecipientAreaRegions")
    private final String RecipientAreaRegions = "";
    @JsonProperty("RecipientAddressName")
    private String RecipientAddressName;
    @JsonProperty("RecipientHouse")
    private final String RecipientHouse = "";
    @JsonProperty("RecipientFlat")
    private final String RecipientFlat = "";
    @JsonProperty("RecipientName")
    private String RecipientName;
    @JsonProperty("RecipientType")
    private final String RecipientType = "PrivatePerson";
    @JsonProperty("RecipientsPhone")
    private String RecipientsPhone;
    @JsonProperty("DateTime")
    private String DateTime;

    public ExpressInvoiceProperties() {
        setCurrentDate();
    }

    private void setCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        DateTime = dateFormat.format(cal.getTime());
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public void setRecipientCityName(String recipientCityName) {
        RecipientCityName = recipientCityName;
    }

    public void setRecipientAddressName(String recipientAddressName) {
        RecipientAddressName = recipientAddressName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        RecipientsPhone = recipientsPhone;
    }

}
