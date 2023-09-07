package me.seetch.essentials.util;

import cn.nukkit.Player;

public record TPRequest(long startTime, Player from, Player to, boolean isTo) {
}