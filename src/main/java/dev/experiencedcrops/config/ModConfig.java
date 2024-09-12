package dev.experiencedcrops.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Jankson;
import net.minecraft.util.Identifier;

public final class ModConfig {

  public final static ModConfigData CONFIG;

  static {
    Jankson jankson = new Jankson.Builder()
        .registerSerializer(Identifier.class, ((identifier, marshaller) -> marshaller.serialize(identifier.toString())))
        .registerDeserializer(String.class, Identifier.class, (object, marshaller) -> Identifier.of(object))
        .build();
    ConfigHolder<ModConfigData> configHolder = AutoConfig.register(ModConfigData.class, (config, configClass) -> new JanksonConfigSerializer<>(config, configClass, jankson));
    CONFIG = configHolder.getConfig();
  }

  public static void init() {
    StringSetGuiProvider<Identifier> guiProvider = new StringSetGuiProvider<>(Identifier.class, Identifier::of);
    AutoConfig.getGuiRegistry(ModConfigData.class).registerPredicateProvider(guiProvider, guiProvider.getPredicate());
    AutoConfig.getGuiRegistry(ModConfigData.class).registerTypeProvider(new IdentifierGuiProvider(), Identifier.class);
  }
}
