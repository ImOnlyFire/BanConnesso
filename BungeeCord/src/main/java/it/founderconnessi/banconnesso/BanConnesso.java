package it.founderconnessi.banconnesso;

import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.banconnesso.listeners.LoginListener;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public final class BanConnesso extends Plugin {
    private static BanConnesso instance;
    private BanManager banManager;
    private CustomYaml config;

    @Override
    public void onEnable() {
        instance = this;
        config = new CustomYaml("config");
        banManager = new BanManager();
        getProxy().getPluginManager().registerListener(
                this,
                new LoginListener()
        );
        getProxy().getPluginManager().registerCommand(
                this,
                new MainCommand("banconnesso", "bc")
        );
        getProxy().getConsole().sendMessage(
                new TextComponent("§8§l[§c§lBanConnesso§8§l] §aSviluppato da FounderConnessi.")
        );
    }

    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage(
                new TextComponent("§8§l[§c§lBanConnesso§8§l] §4Sviluppato da FounderConnessi.")
        );
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public static BanConnesso getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config.getConfiguration();
    }

}