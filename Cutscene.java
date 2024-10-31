import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cutscene {
    public String[] currentObjects;
    public String[] newObjects;
    public String newJsonString;

    public void replaceContent() {
        StringBuilder newJsonStringBuilder = new StringBuilder();
        for (int i = 0; i < currentObjects.length; i += 1) {
            Pattern regex = Pattern.compile("copy: ([\"']).*([\"']),");
            Matcher matcher = regex.matcher(currentObjects[i]);
            if (matcher.find()) {
                String newCopyLine = "copy: '" + newObjects[i] + "',";
                String replaced = currentObjects[i].replace(matcher.group(0), newCopyLine);
                newJsonStringBuilder.append(replaced).append("\n},");
            }
        }
        newJsonString = newJsonStringBuilder.toString();
    }
}
