package com.gexingw.shop.order.service.application.event.order.submit;

import com.gexingw.shop.infrastucture.core.enums.RespCode;
import com.gexingw.shop.infrastucture.core.exception.OrderBizException;
import com.gexingw.shop.product.interfaces.feign.ProductFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * shop-cloud-ddd.
 *
 * @author GeXingW
 * @date 2023/11/12 12:46
 */
@Component
@RequiredArgsConstructor
public class SubmitOrderEventListener {

    private final ProductFeign productFeign;

    @EventListener
    public void persistOrderListener(SubmitOrderEvent event) {
        System.out.println(Thread.currentThread().getName() + " " + this.getClass().getName() + " persistOrderListener.");
    }

    /**
     * 锁定商品库存
     *
     * @param event SubmitOrderEvent
     */
    @EventListener
    public void lockStockListener(SubmitOrderEvent event) {
        if (!productFeign.decrStock(event.getOrder().getId())) {
            throw new OrderBizException(RespCode.UPDATE_ERROR, "库存扣减失败！");
        }
    }

    /**
     * 广播新订单信息
     *
     * @param event SubmitOrderEvent
     */
    @Async
    @TransactionalEventListener
    public void broadcastOrderListener(SubmitOrderEvent event) {
        System.out.println(Thread.currentThread().getName() + " " + this.getClass().getName() + " broadcastOrderListener.");
    }

}
