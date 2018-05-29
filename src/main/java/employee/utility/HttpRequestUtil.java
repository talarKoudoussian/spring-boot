package employee.utility;

public class HttpRequestUtil {

    private boolean isValidVendor(String vendor) {
        return vendor.equals("vnd.pl.employee") ? true : false;

    }

    private boolean isValidVendorType(String vndType) {
        return vndType.equals("json") ? true : false;
    }

    public String getVendor(String contentType) {
        String[] content = contentType.split("/|\\+");
        return content[1];
    }

    public String getVendorType(String contentType) {
        String[] content = contentType.split("\\+|;");
        return content[1];
    }

    public double getVersion(String contentType) {
        double version = -1;

        if(isVersionSpecified(contentType)) {
            String[] content = contentType.split(";|=");
            version = Double.parseDouble(content[2]);
        }

        return version;
    }

    public boolean isValidHeader(String vnd, String vndType) {
        boolean isValid = false;

        if(isValidVendor(vnd)) {

            if(isValidVendorType(vndType)) {
                return true;
            }

        }
        
        return isValid;
    }

    public boolean isVersionSpecified(String contentType) {
        boolean isSpecified = false;
        String[] content = contentType.split("/|\\+|;");

        if(content.length > 3) {
            isSpecified = true;
        }

        return isSpecified;
    }
}
