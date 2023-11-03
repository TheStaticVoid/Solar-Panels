package dev.thestaticvoid.solar_panels.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Taken from Modern Industrialization:
// https://github.com/AztechMC/Modern-Industrialization/blob/master/src/main/java/aztech/modern_industrialization/datagen/translation/EnglishTranslation.java
@Retention(RetentionPolicy.RUNTIME)
public @interface EnglishTranslation {
    String value();
}
