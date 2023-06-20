package view;

import model.Case;
import model.Jardin;

import java.util.Arrays;

public class JardinStringGenerator {
    public static String generateJardinString(Jardin jardin){

        StringBuilder jardinStr = new StringBuilder("-------------------------------------\n");

        //  iterate over the y-axis
        for (Case[] y: jardin.getCases()) {
            System.out.println(Arrays.toString(y));
            for (int i = 0; i < y[0].getCaseAppearance().size(); i++) {
                int finalI = i;
                jardinStr.append("|");
                // iterate over the x-axis
                for (Case x: y) {
                    jardinStr.append(x.getCaseAppearance().get(finalI)).append("|");
                }
                //  break the line when the row is done
                jardinStr.append("\n");
            }
            jardinStr.append("-------------------------------------\n");
        }

        return jardinStr.toString();
    }
}
