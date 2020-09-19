package com.gmail.stefvanschiedev.customblocks;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.stefvanschiedev.customblocks.events.listeners.BlockBreakEventListener;

public class CustomBlockPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		new BlockBreakEventListener(this);
	}
}