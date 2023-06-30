package view;

import configuration.GardenMowConfiguration;
import model.Case.Case;
import model.Jardin;

import java.util.stream.IntStream;

public class JardinStringGenerator {
    public static String generateJardinString(Jardin jardin){

        StringBuilder jardinStr = new StringBuilder();
        jardinStr.append(getSeparator());
        //  iterate over the y-axis
        for (Case[] y: jardin.getCases()) {
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
            jardinStr.append(getSeparator());
        }

        return jardinStr.toString();
    }

    public static String getSeparator(){
        StringBuilder separatorBuilder = new StringBuilder();
        IntStream.range(0, GardenMowConfiguration.JARDIN_MAX_WIDTH).forEach(i -> separatorBuilder.append("---------"));
        separatorBuilder.append("-\n");

        return separatorBuilder.toString();
    }

    public static String generateTurnString(int i) {
        return getSeparator() +
                "TURN " + i;
    }
}
