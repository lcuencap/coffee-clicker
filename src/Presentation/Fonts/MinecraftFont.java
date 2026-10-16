package Presentation.Fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Provides the custom Minecraft-style font used by the original game when available.
 * Falls back to a standard system font in the curated portfolio copy.
 */
public final class MinecraftFont {
    private static final String FONT_PATH = "/files/Resources/Fonts/Minecraftchmc-dBlX.ttf";

    private MinecraftFont() {
    }

    public static Font getFont() {
        return getFontWithSize(20);
    }

    public static Font getFontWithSize(int size) {
        File fontFile = new File(System.getProperty("user.dir") + FONT_PATH);

        if (fontFile.isFile()) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                return font.deriveFont(Font.BOLD, (float) size);
            } catch (IOException | FontFormatException ignored) {
                // Fall back to a system font below.
            }
        }

        return new Font(Font.SANS_SERIF, Font.BOLD, size);
    }
}
