package com.wen.encoder;

import com.wen.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * Created by wen on 2018/6/5
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    /**
     * 在这几行代码里还有几个重要的事情。
     * 第一，通过 ChannelPromise，当编码后的数据被写到了通道上 Netty 可以通过这个对象标记是成功还是失败。
     * 第二， 我们不需要调用 cxt.flush()。因为处理器已经单独分离出了一个方法 void flush(ChannelHandlerContext cxt),
     * 如果想自己实现 flush() 方法内容可以自行覆盖这个方法。
     * @param ctx
     * @param msg
     * @param promise
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise){
        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int) m.value());
        ctx.write(encoded, promise);
    }
}
