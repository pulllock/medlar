package me.cxis.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 服务端读取到客户端写入消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channels.writeAndFlush(String.format("【%s】 - %s \n", incoming.remoteAddress(), msg));
            } else {
                channel.writeAndFlush(String.format("【自己】 - %s \n", msg));
            }
        }

    }

    /**
     * 服务器接收到新的客户端连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        // 向所有channel广播新加入的消息
        channels.writeAndFlush(String.format("【服务器】 - %s 加入\n", incoming.remoteAddress()));

        // 加入到channels缓存
        channels.add(incoming);
    }

    /**
     * 服务端收到客户端连接断开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        // 向所有channel广播离开的消息
        channels.writeAndFlush(String.format("【服务器】 - %s 离开\n", incoming.remoteAddress()));

        // 不需要从channels缓存中移除，channel关闭后会自动从ChannelGroup中移除掉
    }

    /**
     * 服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(String.format("%s在线", incoming.remoteAddress()));
    }

    /**
     * 服务端监听到客户端不活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(String.format("%s掉线", incoming.remoteAddress()));
    }

    /**
     * 出现异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(String.format("%s异常", incoming.remoteAddress()));
        cause.printStackTrace();
        ctx.close();
    }
}
