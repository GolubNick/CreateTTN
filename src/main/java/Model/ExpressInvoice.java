package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpressInvoice {

    @JsonProperty("apiKey")
//    private final String apiKey = "e31073675ff3184a679560b7efe0b391";
    private final String apiKey = "cf80325063f63736711bf3c3fcb3bd95";
    @JsonProperty("modelName")
    private final String modelName = "InternetDocument";
    @JsonProperty("calledMethod")
    private final String calledMethod = "save";
    @JsonProperty("methodProperties")
    private ExpressInvoiceProperties methodProperties;

    public ExpressInvoice(ExpressInvoiceProperties methodProperties) {
        this.methodProperties = methodProperties;
    }

//    public String getApiKey() {
//        return apiKey;
//    }
//
//    public String getModelName() {
//        return modelName;
//    }
//
//    public String getCalledMethod() {
//        return calledMethod;
//    }
//
//    public ExpressInvoiceProperties getMethodProperties() {
//        return methodProperties;
//    }

}
