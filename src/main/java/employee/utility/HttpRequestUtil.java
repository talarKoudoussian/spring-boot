package employee.utility;

import javassist.bytecode.stackmap.TypeData;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpRequestUtil {

    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    public double checkHeader(String contentType) { // application/vnd.pl.employee+json; version=1.0

        String[] contents = contentType.split("/|;"); // 0. application (ignore) 1. vendor+returnTYpe & 2. version
        LOGGER.log(Level.INFO, "contents.length: " + contents.length);
        double returnVal;

        String[] vendorType = contents[1].split("\\+"); // 0. vendor 1. returnType
        if(vendorType.length > 1) {

            if(isValidVendor(vendorType)) {

                if(isValidReturnType(vendorType)) {
                    return getVersion(contents);
                }

                LOGGER.log(Level.INFO, "Invalid return type");
                return -1;
            }

            LOGGER.log(Level.INFO, "Invalid vendor");
            return -1;
        }

        LOGGER.log(Level.INFO, "No return type specified");
        return -1;
    }

    private boolean isValidVendor(String[] content) {
        return content[0].equals("vnd.pl.employee") ? true : false;

    }

    private boolean isValidReturnType(String[] content) {
        return content[1].equals("json") ? true : false;
    }

    private double getVersion(String[] content) {
        double returnVal;
        if(content.length >= 3) {
            String[] version = content[2].split("=");

            if(version.length > 1) {
                returnVal = Double.parseDouble(version[1]);
                LOGGER.log(Level.INFO, "version: " + returnVal);
                returnVal = Math.floor(returnVal);
                LOGGER.log(Level.INFO, "version to call: " + returnVal + " or DEFAULT");
            }
            else {
                returnVal = 2;
                LOGGER.log(Level.INFO, "default version(empty): " + returnVal);
            }
        }
        else {
            returnVal = 2;
            LOGGER.log(Level.INFO, "version default: " +  returnVal);
        }

        return returnVal;
    }

}
