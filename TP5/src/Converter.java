import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Converter {
    public static void convertClass(String classJava, String classCpp, PrintStream out) throws ClassNotFoundException {
        Map<String, ArrayList<String>> contentConstructors = new HashMap<>();
        Map<String, ArrayList<String>> contentFields = new HashMap<>();
        Map<String, ArrayList<String>> contentMethods = new HashMap<>();

        Set<String> dependencies = new HashSet<>();
        Set<String> imports = new HashSet<>();
        Class java = Class.forName(classJava);

        List<String> motherClasses = new ArrayList<>();
        getExtention(java, classCpp, imports, motherClasses);
        getConstructor(java, classCpp, contentConstructors, dependencies);
        getFields(java, contentFields, dependencies);
        getMethods(java, contentMethods, dependencies);

        displayGuardianStart(classCpp, out);
        displayDependencies(dependencies, imports, out);
        displayClassStart(java, classCpp, motherClasses, out);
        displayContent(contentConstructors, contentFields, contentMethods, out);
        displayClassEnd(classCpp, out);
        displayImplementClass(classCpp, contentConstructors, contentMethods, out);
        displayGuardianEnd(classCpp, out);
    }

    private static String convertType(String javaType, Set<String> dependencies) {
        if (javaType.equals("java.lang.String") || javaType.equals("String")) {
            dependencies.add("string");
            return "std::string";
        }

        return javaType;
    }

    private static void getExtention(Class javaClass, String cpp, Set<String> imports, List<String> motherClasses) {
        Class motherClass = javaClass.getSuperclass();
        if (! motherClass.equals(Object.class)) {
            imports.add(motherClass.getName());
            motherClasses.add(motherClass.getName());
        }

        for (Class i : javaClass.getInterfaces()) {
            imports.add(i.getName());
            motherClasses.add(i.getName());
        }
    }

    private static void getFields(Class java, Map<String, ArrayList<String>> content, Set<String> dependencies) {
        for (Field field : java.getDeclaredFields()) {
            String carry = field.toString().split(" ")[0];
            String returns = convertType(field.toString().split(" ")[1], dependencies);

            if (!content.containsKey(carry)) {
                content.put(carry, new ArrayList<>());
            }
            content.get(carry).add(returns + " " + field.getName());
        }
    }

    private static void getMethods(Class java, Map<String, ArrayList<String>> content, Set<String> dependencies) {
        for (Method method : java.getDeclaredMethods()) {
            String carry = method.toString().split(" ")[0];
            String returns = convertType(method.toString().split(" ")[1], dependencies);

            List<String> params = new ArrayList<>();
            for (Class c : method.getParameterTypes()) {
                params.add(convertType(c.getName(), dependencies));
            }

            if (!content.containsKey(carry)) {
                content.put(carry, new ArrayList<>());
            }
            content.get(carry).add(returns + " " + method.getName() + "(" + String.join(", ", params) + ")");
        }
    }

    private static void getConstructor(Class javaClass, String classCpp, Map<String, ArrayList<String>> content, Set<String> dependencies) {
        for (Constructor constructor : javaClass.getDeclaredConstructors()) {
            String carry = constructor.toString().split(" ")[0];

            List<String> params = new ArrayList<>();
            for (Class c : constructor.getParameterTypes()) {
                params.add(convertType(c.getName(), dependencies));
            }

            if (!content.containsKey(carry)) {
                content.put(carry, new ArrayList<>());
            }
            content.get(carry).add(classCpp + "(" + String.join(", ", params) + ")");
        }
    }

    public static void displayGuardianStart(String cpp, PrintStream out) {
        out.println("#ifndef " + cpp.toUpperCase() + "_HPP");
        out.println("#define " + cpp.toUpperCase() + "_HPP");
        out.println();
    }

    private static void displayDependencies(Set<String> dependencies, Set<String> imports, PrintStream out) {
        for (String dependency : dependencies) {
            out.println("#include <" + dependency + ">");
        }
        for (String localImport : imports) {
            out.println("#include \"" + localImport + ".hpp\"");
        }
        out.println();
    }

    private static void displayClassStart(Class javaClass, String cpp, List<String> motherClasses, PrintStream out) {
        out.print("class " + cpp);

        if ( ! motherClasses.isEmpty() ) {
            out.print(" : public " + String.join(", public ", motherClasses));
        }

        out.println(" {");
    }

    private static void displayContent(Map<String, ArrayList<String>> contentConstructors, Map<String, ArrayList<String>> contentFields, Map<String, ArrayList<String>> contentMethods, PrintStream out) {
        String[] visibilities = new String[] {"private", "protected", "public"};
        for (String visibility : visibilities) {
            if (contentConstructors.containsKey(visibility) || contentFields.containsKey(visibility) || contentMethods.containsKey(visibility)) {
                out.println("\t" + visibility + ":");
                for (String fields : contentFields.get(visibility))
                    out.println("\t\t" + fields + ";");
                for (String constructor : contentConstructors.get(visibility))
                    out.println("\t\t" + constructor + ";");
                for (String method : contentMethods.get(visibility))
                    out.println("\t\t" + method + ";");
            }
        }
    }

    private static void displayClassEnd(String cpp, PrintStream out) {
        out.println("}; // " + cpp);
    }

    private static void displayImplementClass(String cpp, Map<String, ArrayList<String>> constructors, Map<String, ArrayList<String>> methods, PrintStream out) {
        String[] visibilities = new String[] {"private", "protected", "public"};

        for (String visibility : visibilities) {
            out.println();
            out.println("// " + visibility + " implementation");
            for (String constructor : constructors.get(visibility)) {
                String[] split = constructor.split(" ");
                for (String s : split) {
                    if (s.contains("(")) {
                        out.print(cpp + "::");
                    }
                    out.print(s + " ");
                }
                out.println("{}");
            }
            for (String method : methods.get(visibility)) {
                String[] split = method.split(" ");
                for (String s : split) {
                    if (s.contains("(")) {
                        out.print(cpp + "::");
                    }
                    out.print(s + " ");
                }
                out.print("{");

                if (!split[0].equals("void")) {
                    out.print(" return " + split[0] + "(); ");
                }

                out.println("}");
            }
        }
    }

    public static void displayGuardianEnd(String cpp, PrintStream out) {
        out.println();
        out.println("#endif //" + cpp.toUpperCase() + "_HPP");
    }
}
