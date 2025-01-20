package com.emperdog.boxlink;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FabricBoxLinkConfig implements BoxLinkConfig{
    private static Path configFilePath;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static boolean boxLinkBindRequiresItem;

    public static void load() {
        Reader reader;
        if(getConfigFilePath().toFile().exists()) {
            try {
                reader = Files.newBufferedReader(getConfigFilePath());

                Data data = gson.fromJson(reader, Data.class);

                boxLinkBindRequiresItem = data.server.boxLinkBindRequiresItem;

                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        try {
            Writer writer = Files.newBufferedWriter(getConfigFilePath());
            Data data = new Data(
                    new Data.Server(boxLinkBindRequiresItem)
            );

            gson.toJson(data, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getConfigFilePath() {
        if(configFilePath == null)
            configFilePath = FabricLoader.getInstance().getConfigDir().resolve(BoxLinkCommon.MOD_ID + ".json");
        return configFilePath;
    }

    private static class Data {

        private final Server server;

        private Data(Server server) {
            this.server = server;
        }

        private static class Server {
            private final boolean boxLinkBindRequiresItem;

            private Server() {
                boxLinkBindRequiresItem = true;
            }

            private Server(boolean boxLinkBindRequiresItem) {
                this.boxLinkBindRequiresItem = boxLinkBindRequiresItem;
            }
        }
    }

    @Override
    public boolean boxLinkBindRequiresItem() {
        return boxLinkBindRequiresItem;
    }
}
