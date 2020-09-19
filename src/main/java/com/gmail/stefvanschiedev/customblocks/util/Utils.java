package com.gmail.stefvanschiedev.customblocks.util;

import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class Utils {

	public static ItemStack getSkull(String url) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

	    if(url.isEmpty()) {
	    	return head;
	    }
	        
	    ItemMeta headMeta = head.getItemMeta();
	    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	    byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
	    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
	    Field profileField = null;
	        
	    try {
	    	profileField = headMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
	    	e.printStackTrace();
		}
	        
		profileField.setAccessible(true);
	        
		try {
	    	profileField.set(headMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	        
		head.setItemMeta(headMeta);
		return head;
	}
	
	public static ItemStack getSkull(String name, String value, String signature) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
	        
	    ItemMeta headMeta = head.getItemMeta();
	    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	    profile.getProperties().put(name, new Property(name, value, signature));
	    Field profileField = null;
	        
	    try {
	    	profileField = headMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
	    	e.printStackTrace();
		}
	        
		profileField.setAccessible(true);
	        
		try {
	    	profileField.set(headMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	        
		head.setItemMeta(headMeta);
		return head;
	}
}