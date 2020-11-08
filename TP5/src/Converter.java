import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Converter {
    public static void convertClass(String classJava, String classCpp) throws ClassNotFoundException {
        Map<String, ArrayList<String>> content = new HashMap<>();
        Set<String> dependencies = new HashSet<>();
        Set<String> imports = new HashSet<>();
        Class java = Class.forName(classJava);

        List<String> motherClasses = new ArrayList<>();
        getExtention(java, classCpp, imports, motherClasses);
        getFields(java, content, dependencies);
        getMethods(java, content, dependencies);
        getConstructor(java, content, dependencies);

        displayGuardianStart(classCpp);
        displayDependencies(dependencies, imports);
        displayClassStart(java, classCpp, motherClasses);
        displayContent(content);
        displayClassEnd(classCpp);
        displayGuardianEnd(classCpp);
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

    private static void getConstructor(Class javaClass, Map<String, ArrayList<String>> content, Set<String> dependencies) {
        for (Constructor constructor : javaClass.getDeclaredConstructors()) {
            String carry = constructor.toString().split(" ")[0];

            List<String> params = new ArrayList<>();
            for (Class c : constructor.getParameterTypes()) {
                params.add(convertType(c.getName(), dependencies));
            }

            if (!content.containsKey(carry)) {
                content.put(carry, new ArrayList<>());
            }
            content.get(carry).add(constructor.getName() + "(" + String.join(", ", params) + ")");
        }
    }

    public static void displayGuardianStart(String cpp) {
        System.out.println("#ifndef " + cpp.toUpperCase() + "_HPP");
        System.out.println("#define " + cpp.toUpperCase() + "_HPP");
        System.out.println();
    }

    private static void displayDependencies(Set<String> dependencies, Set<String> imports) {
        for (String dependency : dependencies) {
            System.out.println("#include <" + dependency + ">");
        }
        for (String localImport : imports) {
            System.out.println("#include \"" + localImport + ".hpp\"");
        }
        System.out.println();
    }

    private static void displayClassStart(Class javaClass, String cpp, List<String> motherClasses) {
        System.out.print("Class " + cpp);

        if ( ! motherClasses.isEmpty() ) {
            System.out.print(" : " + String.join(", ", motherClasses));
        }

        System.out.println(" {");
    }

    private static void displayContent(Map<String, ArrayList<String>> content) {
        for (Map.Entry<String, ArrayList<String>> entry : content.entrySet()) {
            System.out.println("\t" + entry.getKey() + ":");
            for (String method : entry.getValue()) {
                System.out.println("\t\t" + method + ";");
            }
        }
    }

    private static void displayClassEnd(String cpp) {
        System.out.println("} // " + cpp);
    }

    public static void displayGuardianEnd(String cpp) {
        System.out.println();
        System.out.println("#endif //" + cpp.toUpperCase() + "_HPP");
    }
}
