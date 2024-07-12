package com.general_hello.commands.commands.Commands.RpgUser;

import com.general_hello.commands.commands.Commands.Objects.ResourcesEmojis;

import java.text.DecimalFormat;

public class ResourcesDataUtils {
    public static final DecimalFormat formatter = new DecimalFormat("#,###");

    public static String filter(String filterWord) {
        return filterWord.toLowerCase().replace("'", "").replaceAll("\\s+", "");
    }

    public static String getBarFromPercentage(int percentage) {
        String bar = "";

        if (percentage < 10) {
            bar = ResourcesEmojis.bar1Empty + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar3Empty;
        } else if (percentage < 20) {
            bar = ResourcesEmojis.bar1Half + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar3Empty;
        } else if (percentage < 30) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar3Empty;
        } else if (percentage < 40) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Half + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar3Empty;
        } else if (percentage < 50) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2High + ResourcesEmojis.bar2Empty + ResourcesEmojis.bar3Empty;
        } else if (percentage < 65) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar2Half + ResourcesEmojis.bar3Empty;
        } else if (percentage < 70) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar2High + ResourcesEmojis.bar3Empty;
        } else if (percentage < 85) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar3Half;
        } else if (percentage < 101) {
            bar = ResourcesEmojis.bar1Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar2Full + ResourcesEmojis.bar3Full;
        }

        return bar;
    }

    public static int getPercentage(int firstNumber, int secondNumber) {
        double solving = (double) firstNumber/secondNumber;
        solving = solving * 100;
        return (int) solving;
    }
}