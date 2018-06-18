package employee.utility;

public class HttpRequestUtil {

    private boolean isValidVendor(String vendor) {
        return vendor.equals("vnd.pl.employee") ? true : false;
    }

    private boolean isValidVendorType(String vndType) {
        return vndType.equals("json") ? true : false;
    }

    public String getVendor(String acceptHeader) {
        String[] header = acceptHeader.split("/|\\+");
        return header[1];
    }

    public String getVendorType(String acceptHeader) {
        String[] header = acceptHeader.split("\\+|;");
        return header[1];
    }

    public int getVersion(String acceptHeader) {
        int version = -1;

        if(isVersionSpecified(acceptHeader)) {
            String[] header = acceptHeader.split(";|=");
            version = Integer.parseInt(header[2]);
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

    private boolean isVersionSpecified(String acceptHeader) {
        boolean isSpecified = false;
        String[] header = acceptHeader.split("/|\\+|;");

        if(header.length > 3) {
            isSpecified = true;
        }

        return isSpecified;
    }
}
