package com.AxeApps.TabExpand;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int prefixSuffixReadLimit = 64;
    public static int prefixSuffixWriteLimit = 64;

    public static boolean enableExpansion = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        enableExpansion = configuration.getBoolean(
            "enableExpansion",
            Configuration.CATEGORY_GENERAL,
            enableExpansion,
            "Enable prefix/suffix expansion");

        prefixSuffixReadLimit = configuration.getInt(
            "prefixSuffixReadLimit",
            Configuration.CATEGORY_GENERAL,
            prefixSuffixReadLimit,
            16,
            256,
            "Max length when reading team prefix/suffix");

        prefixSuffixWriteLimit = configuration.getInt(
            "prefixSuffixWriteLimit",
            Configuration.CATEGORY_GENERAL,
            prefixSuffixWriteLimit,
            16,
            256,
            "Max length when writing team prefix/suffix");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
