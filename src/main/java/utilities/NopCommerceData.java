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


    @JsonProperty("searchTest")
    private SearchTest searchTest;

    @Data
    public static class SearchTest{

        @JsonProperty("warningValidation")
        private String warningValidation;

        @JsonProperty("macbook2050Search")
        private String macbook2050Search;

        @JsonProperty("noResultValidation")
        private String noResultValidation;

        @JsonProperty("lenovoSearch")
        private String lenovoSearch;

        @JsonProperty("lenovoResult1")
        private String lenovoResult1;

        @JsonProperty("lenovoResult2")
        private String lenovoResult2;

        @JsonProperty("thinkPadSearch")
        private String thinkPadSearch;

        @JsonProperty("lenovoThinkpadResult")
        private String lenovoThinkpadResult;

        @JsonProperty("appleSearch")
        private String appleSearch;

        @JsonProperty("categorySelect")
        private String categorySelect;

        @JsonProperty("appleResult")
        private String appleResult;

        @JsonProperty("manufacturerHPSelect")
        private String manufacturerHPSelect;

        @JsonProperty("manufacturerAppleSelect")
        private String manufacturerAppleSelect;
    }
}
