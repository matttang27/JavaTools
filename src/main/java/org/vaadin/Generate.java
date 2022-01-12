package org.vaadin;
import java.util.ArrayList;
import java.util.Arrays;
public class Generate {
    static String fromConstruct(String constructor) {
        String example = "public Task(String name, LocalDateTime created, LocalDateTime lastEdited, LocalDateTime nextDue, String cronJob, int priority, boolean done, Color color, String background, String icon, Task parent, String description, int id)";
        ArrayList<String> test = new ArrayList<>(Arrays.asList(example.split(",")));
        String[] temp = test.get(0).split("\\(");
        String className = temp[0].split(" ")[1];
        test.set(0,temp[1]);
        String last = test.get(test.size()-1);
        test.set(test.size()-1,last.substring(0, last.length() - 1));
        System.out.println(test.toString());
        System.out.println(className);
        
        String output = "";
        output += String.format("System.out.println(\"Enter info for %s:\")\n",className);
        for (int i=0;i<test.size();i++) {
            String type = test.get(i).split(" ")[0];
            String name = test.get(i).split(" ")[1];
            output += String.format("System.out.println(\"Enter %s (Type %s):\")\n",name,type);
            output += String.format("%s t%s = Scanner.nextLine();\n",type,name);
        }
        output += String.format("%s Example = new %s%s;",className,className,test.toString().replace("[",
        "(").replace("]",")"));
        return output;
    }
    static String fromList(String[][] list) {
        String output = "";
        String className = list[0][0];
        output += String.format("System.out.println(\"Enter info for %s:\")\n",list[0][0]);
        String construct = "";
        for (int i=1;i<list.length;i++) {
            String type = list[i][0];
            String name = list[i][1];
            output += String.format("System.out.println(\"Enter %s (Type %s):\")\n",name,type);
            output += String.format("%s t%s = Scanner.nextLine();\n",type,name);
            construct += String.format("%s %s, ");
        }
        construct = construct.substring(0,construct.length() - 2);
        output += String.format("%s Example = new %s(%s);",className,className,construct);
        return output;
    }
}
