package view;

import model.Jardin;

public class JardinStringGenerator {
    public static String generateJardinString(Jardin jardin){

        StringBuilder jardinStr = new StringBuilder("-------------------------------------\n");

        //  iterate over the y-axis
        jardin.getCases().forEach((yKey, yValue) -> {
            // loop over the number of lines of each cases
            System.out.println(yValue);
            for (int i = 0; i < yValue.get(1).getCaseAppearance().size(); i++) {
                int finalI = i;
                // iterate over the x-axis
                jardinStr.append("|");
                yValue.forEach((xKey, xValue) -> {

                    jardinStr.append(xValue.getCaseAppearance().get(finalI)).append("|");
                });
                //  break the line when the row is done
                jardinStr.append("\n");
            }
            jardinStr.append("-------------------------------------\n");
        });
        return jardinStr.toString();
    }
}
