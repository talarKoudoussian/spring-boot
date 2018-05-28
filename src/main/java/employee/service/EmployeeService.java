package employee.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    public HashMap<String, String> splitHeaderComponents(String contentType, String[] keys) { //application/vnd.pl.employee+json; version=1.0

        String[] beginSplit = { "\\/", "\\+", "=" };
        String[] endSplit = { "+", ";", "" };

        HashMap<String, String> map = new HashMap();

        for(int i=0; i<keys.length; i++) {
            String[] pairs = contentType.split(beginSplit[i]);

            if(pairs[1].indexOf(endSplit[i]) != pairs[1].length()){
                int endIndex = endSplit[i] == "" ||  pairs[1].indexOf(endSplit[i]) == -1 ? pairs[1].length() : pairs[1].indexOf(endSplit[i]);
                String value = pairs[1].substring(0, endIndex);
                map.put(keys[i], value);
            }

        }

        for (Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        return map;
    }

    public String getVersion(String contentType) {
        String[] keys;

        if(contentType.contains("version")) {
            keys = new String[]{ "vendor", "returnType", "version" };
        }
        else {
            keys = new String[]{ "vendor", "returnType" };
        }

        HashMap<String, String> headerMap = splitHeaderComponents(contentType, keys);

        if(headerMap.get("vendor").equals("vnd.pl.employee")) {
            if(headerMap.get("returnType").equals("json")) {
                if (headerMap.get("version") != null) {
                    System.out.println(headerMap.get("version"));
                   return headerMap.get("version");
                }
                System.out.println("2.0");
                return "2.0";
            }
            System.out.println("-1");
            return "-1";
        }
        System.out.println("-1");
        return  "-1";

    }
}
