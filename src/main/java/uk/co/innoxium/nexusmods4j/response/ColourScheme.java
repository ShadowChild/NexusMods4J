package uk.co.innoxium.nexusmods4j.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;

@ToString
@EqualsAndHashCode
public class ColourScheme {

    public int id;
    public String name;
    public String primary_colour, secondary_colour, darker_colour;

    public Color getPrimaryColour() {

        return getColourFromHex(primary_colour);
    }

    public Color getSecondaryColour() {

        return getColourFromHex(secondary_colour);
    }

    public Color getDarkerColour() {

        return getColourFromHex(darker_colour);
    }

    public Color getColourFromHex(String hex) {

        hex = hex.replace("#", "");
        return switch (hex.length()) {
            case 6 -> new Color(
                    Integer.valueOf(hex.substring(0, 2), 16),
                    Integer.valueOf(hex.substring(2, 4), 16),
                    Integer.valueOf(hex.substring(4, 6), 16));
            case 8 -> new Color(
                    Integer.valueOf(hex.substring(0, 2), 16),
                    Integer.valueOf(hex.substring(2, 4), 16),
                    Integer.valueOf(hex.substring(4, 6), 16),
                    Integer.valueOf(hex.substring(6, 8), 16));
            default -> null;
        };
    }
}
