package api.countrylayer.com.utils;

public class CountriesList {

    public static String getCountryCode(String country) {
        switch (country) {
            case "US":
                return "US";
            case "DE":
                return "DE";
            case "GB":
                return "GB";
            default:
                return country;
        }
    }

}
