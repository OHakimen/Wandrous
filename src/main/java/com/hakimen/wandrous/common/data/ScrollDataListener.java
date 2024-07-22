package com.hakimen.wandrous.common.data;

import com.google.gson.Gson;
import com.hakimen.wandrous.Wandrous;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ScrollDataListener {
    static List<Scroll> allScrolls = new ArrayList<>();

    public static List<Scroll> getAllScrolls() {
        return allScrolls;
    }

    public static void clearCache() {
        allScrolls.clear();
    }

    public static CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller1, Executor executor, Executor executor1) {
        clearCache();

        Gson gson = new Gson();
        long start = System.currentTimeMillis();

        resourceManager.listResources("scroll", location -> location.getPath().endsWith(".json")).forEach((location, resource) -> {
            try {
                Scroll scroll = gson.fromJson(resource.openAsReader(), Scroll.class);
                allScrolls.add(scroll);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.currentTimeMillis() - start;
        Wandrous.LOGGER.info("Finished parsing scrolls, took %sms, total scrolls %s".formatted(end, allScrolls.size()));

        return preparationBarrier.wait(null);
    }

}
