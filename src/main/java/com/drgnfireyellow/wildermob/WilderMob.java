package com.drgnfireyellow.wildermob;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.drgnfireyellow.wildermob.Snares;

import net.kyori.adventure.text.Component;

public class WilderMob extends JavaPlugin implements Listener {

    public ArrayList<String> snareUsers = new ArrayList<String>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getLogger().info("Loaded!");
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (snareUsers.contains(((Player) event.getPlayer()).getName())) {

            Player player = (Player) event.getPlayer();
            ArrayList<Material> items = new ArrayList<Material>();
            ItemStack[] snareContents =  event.getInventory().getContents();
            for (ItemStack stack : snareContents) {
                if (stack != null) {
                    items.add(stack.getType());
                }
            }
            try {
                Location location = player.getLocation();
                Entity bondedMob = location.getWorld().spawnEntity(location, Snares.getMob(items));
                bondedMob.addScoreboardTag("bonded");
            }
            catch(Exception e) {
                player.sendMessage("Nothing happened...");
            }
            snareUsers.remove(player.getName());
        }
    }

    @EventHandler
    public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
        if (event.getEntity().getScoreboardTags().contains("bonded") && event.getTarget() instanceof Player) {
            event.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equals("snare")) {
                Inventory snareinventory = Bukkit.createInventory(null, 9, Component.text("Snare Items"));
                Player player = (Player) sender;
                snareUsers.add(player.getName());
                player.openInventory(snareinventory);
            }
        }
        return false;
    }
}
