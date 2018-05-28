package employee.utility;

public class EmployeeUtil {

    public int checkHeader(String contentType) { // application/vnd.pl.employee+json; version=1.0

        String[] contents = contentType.split("/|;"); // 0. application (ignore) 1. vendor+returnTYpe & 2. version
        System.out.println("contents.length: " + contents.length);
        int returnVal;

        String[] vendorType = contents[1].split("\\+"); // 0. vendor 1. returnType
        if(vendorType.length > 1) {

            if(vendorType[0].equals("vnd.pl.employee")) {

                if(vendorType[1].equals("json")) {

                    if(contents.length >= 3) {
                        String[] version = contents[2].split("=");
                        if(version.length > 1) {
                            returnVal = (int) Double.parseDouble(version[1]);
                            System.out.println("version: " + returnVal);
                        }
                        else {
                            returnVal = 2;
                            System.out.println("default version(empty): " + returnVal);
                        }
                    }
                    else {
                        returnVal = 2;
                        System.out.println("version default: " +  returnVal);
                    }

                    return returnVal;
                }

                System.out.println("Invalid return type");
                return -1;
            }

            System.out.println("Invalid vendor");
            return -1;
        }

        System.out.println("No return type specified");
        return -1;
    }

}
