package me.cxis.websocket.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class NettyConfig {

    /**
     * 管理所有的channel
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 保存用户和Channel的关系
     */
    private static ConcurrentHashMap<String, Channel> userChannels = new ConcurrentHashMap<>();

    public static ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public static ConcurrentHashMap<String, Channel> getUserChannels() {
        return userChannels;
    }
}
