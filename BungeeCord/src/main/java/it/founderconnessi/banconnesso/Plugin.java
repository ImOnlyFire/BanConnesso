package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import it.founderconnessi.lib.LoggerInt;
import it.founderconnessi.lib.PluginInt;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Classe concreta che implementa l'intefaccia {@link PluginInt}.
 */
public class Plugin implements PluginInt {

    /**
     * Logger.
     */
    private final LoggerInt logger;

    /**
     * Config.
     */
    private final ConfigInt config;

    /**
     * Costruisce un oggetto di tipo {@link Plugin}.
     * @param logger logger.
     * @param config config.
     */
    public Plugin(LoggerInt logger, ConfigInt config) {
        this.logger = logger;
        this.config = config;
    }

    @Override
    public String getPluginVersion() {
        return BanConnesso.getInstance().getDescription().getVersion();
    }

    @Override
    public String getServerType() {
        return "BungeeCord";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getProxy().getConsole().sendMessage(
                new TextComponent(message)
        );
    }

    @Override
    public LoggerInt getLogger() {
        return logger;
    }

    @Override
    public ConfigInt getConfig() {
        return config;
    }
}
