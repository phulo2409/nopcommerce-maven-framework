package utilities;

import common.GlobalConstants;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import lombok.Data;
import java.io.File;

@Data
public class NopCommerceData {
    public static NopCommerceData getNopCommerceData(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "dataTest.json"), NopCommerceData.class);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("company")
    private String company;

    @JsonProperty("password")
    private String password;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("searchTest")
    private SearchTest searchTest;

    @Data
    public static class SearchTest{

        @JsonProperty("noResultValidation")
        private String noResultValidation;

        @JsonProperty("lenovoThinkpadResult")
        private String lenovoThinkpadResult;

        @JsonProperty("appleSearch")
        private String appleSearch;

        @JsonProperty("categorySelect")
        private String categorySelect;

        @JsonProperty("appleResult")
        private String appleResult;
    }
}
