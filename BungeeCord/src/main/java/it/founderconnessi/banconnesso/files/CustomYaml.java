package it.founderconnessi.banconnesso.files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Classe concreta necessaria per gestire un file di configurazione del plugin in formato YAML.
 * @see <a href="https://hub.spigotmc.org/javadocs/spigot/org/bukkit/configuration/file/YamlConfiguration.html">YamlConfiguration</a>
 */
public class CustomYaml {

    /**
     * Nome del file comprensivo di estensione.
     */
    private final String fileName;

    /**
     * Percorso relativo della cartella del plugin.
     */
    private final Path pluginPath;

    /**
     * Configurazione.
     */
    private Configuration configuration;

    /** Costruisce un oggetto di tipo {@link CustomYaml}.
     * @param pluginPath percorso relativo della cartella del plugin.
     * @param fileName nome del file privo di estensione.
     */
    public CustomYaml(Path pluginPath, String fileName) {
        this.fileName = fileName + ".yml";
        this.pluginPath = pluginPath;
        reload();
    }

    /**
     * Metodo utile per ricaricare la configurazione del plugin.
     * <p>Il metodo carica in memoria il file salvato in {@link #pluginPath} se presente,<p>
     * altrimenti recupera la configurazione predefinita dalle risorse del plugin.
     */
    public void reload() {
        try {
            if (!Files.exists(pluginPath)) {
                Files.createDirectory(pluginPath);
            }
            Path configPath = pluginPath.resolve(fileName);
            if (!Files.exists(configPath)) {
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
                Files.copy(Objects.requireNonNull(in), configPath);
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che restituisce la configurazione.
     * @return Configurazione.
     */
    public Configuration getConfiguration() {
        return configuration;
    }
}