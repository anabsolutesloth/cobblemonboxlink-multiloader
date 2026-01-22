package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.BoxLinkCommon;
import com.emperdog.boxlink.platform.services.BoxLinkConfig;
import com.emperdog.boxlink.platform.services.IAccessoryModHelper;
import com.emperdog.boxlink.platform.services.IPlatformHelper;

import java.util.ServiceLoader;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

// Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
// environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
// is swapped out for the platform specific implementation at runtime.
public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static final IAccessoryModHelper ACCESSORY_MOD;

    public static final BoxLinkConfig CONFIG = load(BoxLinkConfig.class);

    static {
        ACCESSORY_MOD = load(IAccessoryModHelper.class, loader -> loader
                .filter(helper -> AccessoriesCompat.MOD_LOADED == helper.get() instanceof AccessoriesCompat));
    }

    public static <T> T load(Class<T> clazz, UnaryOperator<Stream<ServiceLoader.Provider<T>>> intercept) {

        final T loadedService = intercept.apply(ServiceLoader.load(clazz).stream())
                        //.peek(provider -> BoxLinkCommon.LOG.info(provider.type().getName()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()))
                .get();
        BoxLinkCommon.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static <T> T load(Class<T> clazz) {
        return load(clazz, UnaryOperator.identity());
    }
}